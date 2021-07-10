package com.example.thietkegiaodien.model;

public class Category {
    String  Id;
    String  phoneName;
    String  phoneOperatingsystem;
    String  phonePrice;
    String  phoneBrand;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getPhoneName() {
        return phoneName;
    }

    public void setPhoneName(String phoneName) {
        this.phoneName = phoneName;
    }

    public String getPhoneOperatingsystem() {
        return phoneOperatingsystem;
    }

    public void setPhoneOperatingsystem(String phoneOperatingsystem) {
        this.phoneOperatingsystem = phoneOperatingsystem;
    }

    public String getPhonePrice() {
        return phonePrice;
    }

    public void setPhonePrice(String phonePrice) {
        this.phonePrice = phonePrice;
    }

    public String getPhoneBrand() {
        return phoneBrand;
    }

    public void setPhoneBrand(String phoneBrand) {
        this.phoneBrand = phoneBrand;
    }

    public Category(String Id,String phoneName, String phoneOperatingsystem, String phonePrice, String phoneBrand) {
        this.Id = Id;
        this.phoneName = phoneName;
        this.phoneOperatingsystem = phoneOperatingsystem;
        this.phonePrice = phonePrice;
        this.phoneBrand = phoneBrand;
    }

    public Category() {
    }
}
