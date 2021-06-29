package com.example.thietkegiaodien.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import android.os.Bundle;
import com.example.thietkegiaodien.R;
import com.example.thietkegiaodien.adapter.LoginAdapterFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;


public class Login extends AppCompatActivity {

    //Initialize variable
    TabLayout tabLayout;
    ViewPager mviewPager;
    FloatingActionButton google, twitter;
    float y = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //Assign variable
        tabLayout = findViewById(R.id.tab_layout);
        mviewPager = findViewById(R.id.mview_pager);
        google = findViewById(R.id.fab_google);
        twitter = findViewById(R.id.fab_twitter);

        //Initialize adapter
        LoginAdapterFragment adapter = new LoginAdapterFragment(getSupportFragmentManager());
        //add fragment
        adapter.addFragmentLogin(new LoginTabFragment(), "Login");
        adapter.addFragmentLogin(new RegisterTabFragment(), "Register");
        //set adapter
        mviewPager.setAdapter(adapter);
        //connect tablayout with viewpager
        tabLayout.setupWithViewPager(mviewPager);

    }

}