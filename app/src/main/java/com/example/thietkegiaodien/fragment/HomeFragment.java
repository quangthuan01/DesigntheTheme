package com.example.thietkegiaodien.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.thietkegiaodien.R;
import com.example.thietkegiaodien.adapter.LoginAdapterFragment;
import com.example.thietkegiaodien.adapter.TabViewPagerFragmentChi;
import com.example.thietkegiaodien.login.LoginTabFragment;
import com.example.thietkegiaodien.login.RegisterTabFragment;
import com.google.android.material.tabs.TabLayout;

public class HomeFragment extends Fragment {
    private TabLayout tabLayoutHome;
    private ViewPager viewPagerHome;
    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup mview = (ViewGroup) inflater.inflate(R.layout.fragment_home,container,false);

        tabLayoutHome = mview.findViewById(R.id.tabLayoutHome);
        viewPagerHome = mview.findViewById(R.id.viewPagerHome);

        //Initialize adapter
        TabViewPagerFragmentChi adapter = new TabViewPagerFragmentChi(getParentFragmentManager());
        //add fragment
        adapter.addFragmentChi(new KhoanChiFragment(), "Khoan Chi");
        adapter.addFragmentChi(new LoaiChiFragment(), "Loai Chi");
        //set adapter
        viewPagerHome.setAdapter(adapter);
        //connect tablayout with viewpager
        tabLayoutHome.setupWithViewPager(viewPagerHome);
        return mview;
    }
}
