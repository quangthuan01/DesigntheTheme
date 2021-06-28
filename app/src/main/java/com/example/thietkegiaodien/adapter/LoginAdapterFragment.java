package com.example.thietkegiaodien.adapter;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.thietkegiaodien.login.LoginTabFragment;
import com.example.thietkegiaodien.login.RegisterTabFragment;

import java.util.ArrayList;
import java.util.List;

public class LoginAdapterFragment extends FragmentPagerAdapter {


    //Initialize array list
    List<Fragment> fragmentList = new ArrayList<>();
    List<String> stringList = new ArrayList<>();

    //Create constructor
    public void addFragmentLogin(Fragment fragment, String s) {
        //add fragment
        fragmentList.add(fragment);
        //add String
        stringList.add(s);
    }

    public LoginAdapterFragment(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        //return Selected fragment
        return fragmentList.get(position);

    }

    @Override
    public int getCount() {
        //return total fragment

        return fragmentList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        //return tab title
        return stringList.get(position);
    }
}
