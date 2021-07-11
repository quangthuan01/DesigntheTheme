package com.example.thietkegiaodien.model;

public class LoaiChi {
    String IdChi ;
    String TitleChi;
    String Date;

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public LoaiChi() {
    }

    public LoaiChi(String idChi, String titleChi, String Date) {
        IdChi = idChi;
        TitleChi = titleChi;
        Date = Date;
    }

    public String getIdChi() {
        return IdChi;
    }

    public void setIdChi(String idChi) {
        IdChi = idChi;
    }

    public String getTitleChi() {
        return TitleChi;
    }

    public void setTitleChi(String titleChi) {
        TitleChi = titleChi;
    }
}
