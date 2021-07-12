package com.example.thietkegiaodien.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.thietkegiaodien.R;
import com.example.thietkegiaodien.model.KhoanChi;
import com.example.thietkegiaodien.model.LoaiChi;

import java.util.List;

public class KhoanChiAdapter extends BaseAdapter {
    List<KhoanChi> khoanChiList;
    Context context;

    public KhoanChiAdapter(List<KhoanChi> khoanChiList, Context context) {
        this.khoanChiList = khoanChiList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return khoanChiList.size();
    }

    @Override
    public Object getItem(int position) {
        return khoanChiList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = View.inflate(parent.getContext(), R.layout.item_list_khoanchi, null);

        }
        KhoanChi khoanChi = (KhoanChi) getItem(position);
        TextView textKhoanChi = view.findViewById(R.id.item_textKhoanChi);
        TextView textSoTien = view.findViewById(R.id.item_textSoTienChi);
        TextView textDate = view.findViewById(R.id.item_DateKhoanChi);

        textKhoanChi.setText(khoanChi.getTheLoaiKhoanChi());
        textSoTien.setText(khoanChi.getMoney());
        textDate.setText(khoanChi.getDateKhoanChi());

        return view;
    }
}
