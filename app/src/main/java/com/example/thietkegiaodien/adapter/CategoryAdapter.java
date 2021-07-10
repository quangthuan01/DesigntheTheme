package com.example.thietkegiaodien.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.thietkegiaodien.R;
import com.example.thietkegiaodien.model.Category;

import org.w3c.dom.Text;

import java.util.List;

public class CategoryAdapter extends ArrayAdapter {
    private Activity mContext;
    List<Category> categoryList;
    public CategoryAdapter( Activity mContext, List<Category>categoryList) {
        super(mContext, R.layout.categorys_1row,categoryList );
        this.mContext = mContext;
        this.categoryList = categoryList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = mContext.getLayoutInflater();
        View listItemView = layoutInflater.inflate(R.layout.categorys_1row,null);
        TextView Brand = listItemView.findViewById(R.id.tv_Brand);
        TextView Name = listItemView.findViewById(R.id.tv_NamePhone);
        TextView Sytem = listItemView.findViewById(R.id.tv_Sytem);
        TextView Price = listItemView.findViewById(R.id.tv_Price);

        Category categoryModel =categoryList.get(position);
        Brand.setText(categoryModel.getPhoneBrand());
        Name.setText(categoryModel.getPhoneName());
        Sytem.setText(categoryModel.getPhoneOperatingsystem());
        Price.setText(categoryModel.getPhonePrice());

        return listItemView;
    }
}
