package com.example.thietkegiaodien.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.thietkegiaodien.R;
import com.example.thietkegiaodien.model.LoaiThu;
import java.util.List;

public class LoaiThuAdapter extends BaseAdapter {
    List<LoaiThu> data;
    Context context;

    public LoaiThuAdapter(List<LoaiThu> data, Context context) {
        this.data = data;
        this.context = context;
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
        if (view == null) {
            view = View.inflate(parent.getContext(), R.layout.item_list_loaithu, null);

        }
        LoaiThu loaiThu = (LoaiThu) getItem(position);
        TextView textTheLoai = view.findViewById(R.id.item_list_loaithuLoai);
        TextView textDate = view.findViewById(R.id.item_list_loaithuDate);
        textTheLoai.setText(loaiThu.getTitleLoaiThu());
        textDate.setText(loaiThu.getDateLoaiThu());

        return view;
    }
}
