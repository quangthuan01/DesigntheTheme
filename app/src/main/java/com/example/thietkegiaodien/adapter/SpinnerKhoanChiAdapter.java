package com.example.thietkegiaodien.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.thietkegiaodien.R;
import com.example.thietkegiaodien.model.LoaiChi;

import java.util.List;

public class SpinnerKhoanChiAdapter extends BaseAdapter {
    List<LoaiChi> data;
    Context context;

    public SpinnerKhoanChiAdapter(List<LoaiChi> data, Context context) {
        this.data = data;
        this.context = context;
    }
    public  int getPosition(String Title) {
        int index  = -1;
        for (int i  = 0; i<data.size(); i++){
            LoaiChi loaiChi = data.get(i);
            if (loaiChi.getTitleChi().equals(Title)){
                index = i;
                break;
            }
        }
        return index;
    }

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
        if (convertView == null) {
            view = View.inflate(parent.getContext(), R.layout.item_spinner_khoanchi, null);
        }
        TextView spn_loaichi = ((TextView) view.findViewById(R.id.item_spinner_KhoanChi));
        LoaiChi loaiChi = (LoaiChi) getItem(position);
        spn_loaichi.setText(loaiChi.getTitleChi());
        return view;
    }
}
