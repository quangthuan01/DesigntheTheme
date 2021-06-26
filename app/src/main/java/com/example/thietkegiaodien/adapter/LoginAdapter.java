package com.example.thietkegiaodien.adapter;

import android.content.Context;
import android.widget.BaseAdapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.thietkegiaodien.login.LoginTabFragment;
import com.example.thietkegiaodien.login.RegisterTabFragment;

public class LoginAdapter extends FragmentPagerAdapter {
    private Context context;
    int mtotalTabs;

    public LoginAdapter(FragmentManager fragmentManager, Context context, int totalTabs) {
        super(fragmentManager);
        this.context = context;
        this.mtotalTabs = totalTabs;

    }

    @Override
    public int getCount() {
        return mtotalTabs;
    }

    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                LoginTabFragment loginTabFragment = new LoginTabFragment();
                return loginTabFragment;
            case 1:
                RegisterTabFragment registerTabFragment = new RegisterTabFragment();
                return registerTabFragment;
            default:
                return null;
        }
    }
}
