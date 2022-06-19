package com.example.it_store.model;

public class Product {
    private String id;
    private String Color, Gia, Image, Mota, Name;
    public Product(){

    }
    public Product(Product targetProductToCopyFrom) {
        this.Color = targetProductToCopyFrom.Color;
        this.Gia = targetProductToCopyFrom.Gia;
        this.Image = targetProductToCopyFrom.Image;
        this.Mota = targetProductToCopyFrom.Mota;
        this.Name = targetProductToCopyFrom.Name;
    }
    public Product(String id, String Color, String Gia, String Image, String Mota, String Name) {
        this.id = id;
        this.Color = Color;
        this.Gia = Gia;
        this.Image = Image;
        this.Mota = Mota;
        this.Name = Name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
         this.id = id;
    }

    public void setColor(String Color) {
        this.Color = Color;
    }

    public String getGia() {
        return Gia;
    }

    public void setGia(String Gia) {
        this.Gia = Gia;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }

    public String getMota() {
        return Mota;
    }

    public void setMota(String Mota) {
        this.Mota = Mota;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }
}
