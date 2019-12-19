package com.example.lab4;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lab4.News;
import com.example.lab4.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NewsFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        return view;
    }
}

class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(News item, int position);
    }

    private final List<News> items;
    private final OnItemClickListener listener;

    public NewsAdapter(List<News> items, OnItemClickListener listener) {
        this.items = items;
        this.listener = listener;
    }

    @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_element, parent, false);
        return new ViewHolder(v);
    }

    @Override public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(items.get(position), listener);
    }

    @Override public int getItemCount() {
        return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView titleView;
        public TextView dateView;
        public WebView contentView;
        public ImageView imageView;


        public ViewHolder(View itemView) {
            super(itemView);
            titleView = itemView.findViewById(R.id.title);
            dateView = itemView.findViewById(R.id.date);
            contentView = itemView.findViewById(R.id.content_preview);
            imageView = itemView.findViewById(R.id.imageView);
        }

        public void bind(final News item, final OnItemClickListener listener) {
            titleView.setText(item.Title);
            dateView.setText(item.Date);
            contentView.loadData(item.Content, "text/HTML", "UTF-8");
            if (item.ImageUrl != null)
                Picasso.get().load(item.ImageUrl).into(imageView);
            itemView.setOnClickListener(v -> listener.onItemClick(item, getAdapterPosition()));
        }
    }
}