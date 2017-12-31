package com.example.tommywahyu44.cekin.Search;

/**
 * Created by tommywahyu44 on 4/12/2017.
 */

public class ItemForum {

    private String Ukuran;
    private String Harga;
    private String Discount;
    private String Brand;
    private String Tempat;
    private String ID;


    public ItemForum(String Ukuran, String Harga, String Discount, String Brand,String Tempat,String ID) {
        this.Brand = Brand;
        this.Discount = Discount;
        this.Harga = Harga;
        this.Tempat = Tempat;
        this.Ukuran = Ukuran;
        this.ID = ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getID() {
        return ID;
    }

    public String getBrand() {
        return Brand;
    }

    public String getDiscount() {
        return Discount;
    }

    public String getHarga() {
        return Harga;
    }

    public String getTempat() {
        return Tempat;
    }

    public String getUkuran() {
        return Ukuran;
    }

    public void setBrand(String brand) {
        Brand = brand;
    }

    public void setDiscount(String discount) {
        Discount = discount;
    }

    public void setHarga(String harga) {
        Harga = harga;
    }

    public void setTempat(String tempat) {
        Tempat = tempat;
    }

    public void setUkuran(String ukuran) {
        Ukuran = ukuran;
    }
}

