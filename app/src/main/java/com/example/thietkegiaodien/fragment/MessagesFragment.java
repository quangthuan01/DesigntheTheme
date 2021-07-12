package com.example.thietkegiaodien.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.thietkegiaodien.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MessagesFragment extends Fragment {
    private BottomNavigationView bottomNavigationView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup mview = (ViewGroup) inflater.inflate(R.layout.fragment_messages,container,false);
        bottomNavigationView = mview.findViewById(R.id.bottomnavigationView);
        bottomNavigationView.setBackground(null);
        return mview;
    }
}
