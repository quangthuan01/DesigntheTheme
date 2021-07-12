package com.example.thietkegiaodien.model;

public class LoaiChi {
    String IdChi ;
    String TitleChi;
    String DateChi;

    public LoaiChi() {
    }

    public LoaiChi(String idChi, String titleChi, String dateChi) {
        IdChi = idChi;
        TitleChi = titleChi;
        DateChi = dateChi;
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

    public String getDateChi() {
        return DateChi;
    }

    public void setDateChi(String dateChi) {
        DateChi = dateChi;
    }
}
