package com.example.lab4;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.orm.SugarRecord;
import com.prof.rssparser.Article;
import com.prof.rssparser.OnTaskCompleted;
import com.prof.rssparser.Parser;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.prefs.Preferences;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    SwipeRefreshLayout swipeRefreshLayout;
    ArrayList<News> newsList;
    Snackbar snackbar;
    String rssUrl;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        swipeRefreshLayout = findViewById(R.id.pullToRefresh);
        swipeRefreshLayout.setOnRefreshListener(() -> getFeed(true));

        progressBar = findViewById(R.id.progressBar);

        recyclerView = findViewById(R.id.list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        newsList = new ArrayList<>();
        adapter = new NewsAdapter(newsList,
                (item, pos) -> {
                    Intent intent = new Intent(MainActivity.this, WebViewActivity.class);
                    intent.putExtra("URL", item.Url);
                    MainActivity.this.startActivityForResult(intent, 1);
                    Log.e("Click", "Clicked!");
                });
        recyclerView.setAdapter(adapter);

        snackbar = Snackbar.make(recyclerView, "No internet connection", Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction("Retry", v ->
        {
            getFeed();
            snackbar.dismiss();
        });

        SharedPreferences preferences = getSharedPreferences("prefName", Context.MODE_PRIVATE);
        rssUrl = preferences.getString("URL", "");
        if (rssUrl.equals(""))
            askUrl();
        else
            getFeed();
    }

    private void getFeed(){
        getFeed(false);
    }

    private void getFeed(boolean isSwipe) {
        new Thread(() -> {
            if (!isOnline())
            {
                swipeRefreshLayout.setRefreshing(false);

                snackbar.show();
                List<News> savedNews = SugarRecord.listAll(News.class);
                newsList.addAll(savedNews);

                runOnUiThread(() -> adapter.notifyDataSetChanged());
            }

            snackbar.dismiss();
            newsList.clear();

            Parser parser = new Parser();
            parser.onFinish(new OnTaskCompleted() {
                @Override
                public void onTaskCompleted(List<Article> list) {
                    if (!isSwipe)
                    {
                        runOnUiThread(() -> progressBar.setVisibility(View.VISIBLE));
                    }
                    int i = 0;
                    SugarRecord.deleteAll(News.class);
                    for(Article article : list) {
                        News news = new News();

                        news.Title = article.getTitle();
                        news.Content = article.getDescription();
                        news.Date = article.getPubDate();
                                //.removeRange(article.getPubDate().length() - 12, article.getPubDate().length());
                        if (article.getImage() != null) {
                            news.ImageUrl = article.getImage();
                        }
                        news.Url = article.getLink();

                        newsList.add(news);
                        if (i++ < 10) {
                            news.save();
                        }
                    }

                    runOnUiThread(() -> adapter.notifyDataSetChanged());
                }

                @Override
                public void onError(Exception e) {
                    if (e instanceof XmlPullParserException) {
                        runOnUiThread(() -> Toast.makeText(MainActivity.this, "No valid RSS found", Toast.LENGTH_SHORT).show());
                    }
                    runOnUiThread(() -> Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_SHORT).show());
                    Log.e("Exception", e.toString());
                    e.printStackTrace();
                }

            });
            parser.execute(rssUrl);

            if (isSwipe) {
                runOnUiThread(() -> swipeRefreshLayout.setRefreshing(false));
            }
            else{
                runOnUiThread(() -> progressBar.setVisibility(View.INVISIBLE));

            }

        }).start();
    }

    private void askUrl() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();

        View v = inflater.inflate(R.layout.dialog_url, null);
        builder.setView(v)
                .setOnDismissListener(dialog -> {
                    if (rssUrl.equals(""))
                    {
                        Toast.makeText(MainActivity.this, "Please, set URL first", Toast.LENGTH_SHORT).show();
                        askUrl();
                    }
                })

            .setPositiveButton("Set", (dialog, which) -> {
                EditText urlText = v.findViewById(R.id.url_text);
                String url = urlText.getText().toString();
                if (URLUtil.isValidUrl(url)) {
                    rssUrl = url;
                    SharedPreferences preferences = getSharedPreferences("prefName", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("URL", rssUrl);
                    editor.apply();
                    getFeed();
                }
                else if (rssUrl.equals("")) {
                    Toast.makeText(MainActivity.this, "Invalid url. Try again", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    askUrl();
                }
                else {
                    Toast.makeText(MainActivity.this, "Invalid url. Try again", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            })

            .setNegativeButton("Cancel", (dialog, which) -> {
            if (rssUrl.equals("")) {
                Toast.makeText(this, "You should set URL first", Toast.LENGTH_SHORT).show();
            }
            else {
                dialog.dismiss();
            }
            });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private boolean isOnline() {
        try {
            int timeoutMs = 1500;
            Socket socket = new Socket();
            InetSocketAddress socketAddress = new InetSocketAddress("8.8.8.8", 53);

            socket.connect(socketAddress, timeoutMs);
            socket.close();

            return true;
        }
        catch (IOException e) {
            return false;
        }
    }
}
