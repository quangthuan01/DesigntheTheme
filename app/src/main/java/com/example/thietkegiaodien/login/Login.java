package com.example.thietkegiaodien.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.thietkegiaodien.R;
import com.example.thietkegiaodien.adapter.LoginAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

public class Login extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager mviewPager;
    FloatingActionButton facebook, google, twitter;
    float y = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tabLayout = findViewById(R.id.tab_layout);
        mviewPager = findViewById(R.id.mview_pager);
        facebook = findViewById(R.id.fab_facebook);
        google = findViewById(R.id.fab_google);
        twitter = findViewById(R.id.fab_twitter);

        tabLayout.addTab(tabLayout.newTab().setText("Login"));
        tabLayout.addTab(tabLayout.newTab().setText("Register"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final LoginAdapter adapter = new LoginAdapter(getSupportFragmentManager(), this, tabLayout.getTabCount());
        mviewPager.setAdapter(adapter);

        mviewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));


//        facebook.setTranslationX(300);
//        google.setTranslationX(300);
//        twitter.setTranslationX(300);
//        tabLayout.setTranslationX(300);
//
//        facebook.setAlpha(y);
//        google.setAlpha(y);
//        twitter.setAlpha(y);
//        tabLayout.setAlpha(y);
//
//        facebook.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(400).start();
//        google.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(600).start();
//        twitter.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(800).start();
//        tabLayout.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(1000).start();

    }
}