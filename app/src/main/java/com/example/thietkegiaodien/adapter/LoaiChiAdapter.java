package com.example.thietkegiaodien.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.thietkegiaodien.R;
import com.example.thietkegiaodien.model.LoaiChi;

import java.util.List;

public class LoaiChiAdapter extends BaseAdapter {
    private   List<LoaiChi> data;
    private Context context;

    public LoaiChiAdapter(List<LoaiChi> data, Context context) {
        this.data = data;
        this.context = context;
    }

//    public void updateData(List<LoaiChi> _data){
//        data.clear();
//        data.addAll(_data);
//        notifyDataSetChanged();
//    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = View.inflate(parent.getContext(), R.layout.item_list_loaichi, null);

        }
        LoaiChi loaiChi = (LoaiChi) getItem(position);
        TextView textTheLoai = view.findViewById(R.id.item_textLoaiChi);
        TextView textDate = view.findViewById(R.id.item_textDateLoaiChi);
        textTheLoai.setText(loaiChi.getTitleChi());
        textDate.setText(loaiChi.getDate());

        return view;
    }
}
