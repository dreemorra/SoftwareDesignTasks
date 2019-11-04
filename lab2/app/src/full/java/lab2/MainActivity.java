package lab2;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.res.Configuration;
import android.os.Bundle;


public class MainActivity extends AppCompatActivity {

    //TODO: add demo flavor

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        int orientation = this.getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            ViewPager viewPager = findViewById(R.id.view_pager);
            viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
        }
    }

}

class ViewPagerAdapter extends FragmentPagerAdapter {

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                return new ButtonsFragment(); //ButtonsFragment at position 0
            case 1:
                return new SciFragment(); //SciFragment at position 1
        }
        return null; //does not happen
    }

    @Override
    public int getCount() {
        return 2; //two fragments
    }
}

