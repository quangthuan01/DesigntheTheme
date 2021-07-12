package com.example.thietkegiaodien.model;

public class LoaiThu {
    String IdLoaiThu;
    String TitleLoaiThu;
    String DateLoaiThu;

    public LoaiThu(String idLoaiThu, String titleLoaiThu, String dateLoaiThu) {
        IdLoaiThu = idLoaiThu;
        TitleLoaiThu = titleLoaiThu;
        DateLoaiThu = dateLoaiThu;
    }

    public LoaiThu() {
    }

    public String getIdLoaiThu() {
        return IdLoaiThu;
    }

    public void setIdLoaiThu(String idLoaiThu) {
        IdLoaiThu = idLoaiThu;
    }

    public String getTitleLoaiThu() {
        return TitleLoaiThu;
    }

    public void setTitleLoaiThu(String titleLoaiThu) {
        TitleLoaiThu = titleLoaiThu;
    }

    public String getDateLoaiThu() {
        return DateLoaiThu;
    }

    public void setDateLoaiThu(String dateLoaiThu) {
        DateLoaiThu = dateLoaiThu;
    }
}
