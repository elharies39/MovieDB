package com.appku.elharies.moviedb;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    public static final String API_KEY = "cfca9d4452fa2c08f658bda6fc1525ca";
    private Fragment fragment = null;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        manageTab(savedInstanceState);
    }

    private void setSupportActionBar(Toolbar toolbar) {
    }

    public void manageTab(Bundle savedInstanceState){
        TabLayout tabLayout;
        fragmentManager = getSupportFragmentManager();

        if (savedInstanceState == null){
            fragmentManager.beginTransaction()
                    .replace(R.id.container_fragment, new NowPlayingFragment()).commit();
        }

        tabLayout = findViewById(R.id.tab_Layout);
        tabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);
        tabLayout.addTab(tabLayout.newTab().setText("Now Playing").setIcon(R.drawable.ic_play_arrow_white_18dp));
        tabLayout.addTab(tabLayout.newTab().setText("Latest").setIcon(R.drawable.ic_new_releases_white_18dp));
        tabLayout.addTab(tabLayout.newTab().setText("Upcoming").setIcon(R.drawable.ic_update_white_18dp));
        tabLayout.setTabTextColors(ColorStateList.valueOf(getResources()
                .getColor(android.R.color.white)));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                        fragment = new NowPlayingFragment();
                        break;
                    case 1:
                        fragment = new LastestFragment();
                        break;
                    case 2:
                        fragment = new UpComingFragment();
                        break;
                    default:
                        break;
                }

                if (fragment != null){
                    final FragmentTransaction transaction = fragmentManager.beginTransaction();
                    transaction.replace(R.id.container_fragment, fragment).commit();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
