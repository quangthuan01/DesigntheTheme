package com.example.thietkegiaodien.model;

public class KhoanChi {
    String IdKhoanChi;
    String TheLoaiKhoanChi;
    String Money;
    String DateKhoanChi;

    public KhoanChi() {
    }

    public KhoanChi(String idKhoanChi, String theLoaiKhoanChi, String money, String dateKhoanChi) {
        IdKhoanChi = idKhoanChi;
        TheLoaiKhoanChi = theLoaiKhoanChi;
        Money = money;
        DateKhoanChi = dateKhoanChi;
    }

    public String getIdKhoanChi() {
        return IdKhoanChi;
    }

    public void setIdKhoanChi(String idKhoanChi) {
        IdKhoanChi = idKhoanChi;
    }

    public String getTheLoaiKhoanChi() {
        return TheLoaiKhoanChi;
    }

    public void setTheLoaiKhoanChi(String theLoaiKhoanChi) {
        TheLoaiKhoanChi = theLoaiKhoanChi;
    }

    public String getMoney() {
        return Money;
    }

    public void setMoney(String money) {
        Money = money;
    }

    public String getDateKhoanChi() {
        return DateKhoanChi;
    }

    public void setDateKhoanChi(String dateKhoanChi) {
        DateKhoanChi = dateKhoanChi;
    }
}
