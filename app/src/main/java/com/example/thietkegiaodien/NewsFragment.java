package com.example.thietkegiaodien;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class NewsFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup mview = (ViewGroup) inflater.inflate(R.layout.fragment_news,container,false);

        return mview;
    }
}
