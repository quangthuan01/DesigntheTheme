package com.example.thietkegiaodien.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.thietkegiaodien.R;
import com.example.thietkegiaodien.adapter.TabLayoutViewPagerFragment;
import com.google.android.material.tabs.TabLayout;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class ThuFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Nullable
    @Override
    public View onCreateView(@NonNull  LayoutInflater inflater, @Nullable  ViewGroup container, @Nullable  Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_thu, container, false);

        tabLayout = view.findViewById(R.id.tabLayoutThu);
        viewPager = view.findViewById(R.id.viewPagerThu);
        //Initialize adapter
        TabLayoutViewPagerFragment adapter = new TabLayoutViewPagerFragment(getParentFragmentManager());
        //add fragment
        adapter.addFragmentChi(new KhoanThuFragment(), "Khoan Thu");
        adapter.addFragmentChi(new LoaiThuFragment(), "Loai Thu");
        //set adapter
        viewPager.setAdapter(adapter);
        //connect tablayout with viewpager
        tabLayout.setupWithViewPager(viewPager);


        return  view;
    }
}