package com.example.thietkegiaodien.adapter;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class TabViewPagerFragmentChi extends FragmentPagerAdapter {

    //Initialize array list
    List<Fragment> fragmentList = new ArrayList<>();
    List<String> stringList = new ArrayList<>();

    //Create constructor
    public void addFragmentChi(Fragment fragment, String s) {
        //add fragment
        fragmentList.add(fragment);
        //add String
        stringList.add(s);
    }

    public TabViewPagerFragmentChi(FragmentManager fragmentManager) {
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
