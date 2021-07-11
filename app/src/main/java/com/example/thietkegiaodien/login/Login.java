package com.example.thietkegiaodien.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;

import com.example.thietkegiaodien.MainActivity;
import com.example.thietkegiaodien.R;
import com.example.thietkegiaodien.adapter.LoginAdapterFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class Login extends AppCompatActivity {

    //Initialize variable
    TabLayout tabLayout;
    ViewPager mviewPager;
    FloatingActionButton google, twitter;


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
//    @Override
//    public void onStart() {
//        super.onStart();
//        // Check if user is signed in (non-null) and update UI accordingly.
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        if (currentUser != null) {
//            startActivity(new Intent(Login.this, MainActivity.class));
//            finish();
//        }
//    }

}