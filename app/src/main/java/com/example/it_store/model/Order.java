package com.example.it_store.model;


public class Order {

    private String id_san_pham;
    private String id_category;
    private String ten_san_pham;
    private int so_luong;
    private int tien;

    public Order() {
    }

    public Order(String id_san_pham, String id_category, String ten_san_pham, int so_luong, int tien) {
        this.id_san_pham = id_san_pham;
        this.id_category = id_category;
        this.ten_san_pham = ten_san_pham;
        this.so_luong = so_luong;
        this.tien = tien;
    }

    public String getId_san_pham() {
        return id_san_pham;
    }

    public void setId_san_pham(String id_san_pham) {
        this.id_san_pham = id_san_pham;
    }

    public String getId_category() {
        return id_category;
    }

    public void setId_category(String id_category) {
        this.id_category = id_category;
    }

    public String getTen_san_pham() {
        return ten_san_pham;
    }

    public void setTen_san_pham(String ten_san_pham) {
        this.ten_san_pham = ten_san_pham;
    }

    public int getSo_luong() {
        return so_luong;
    }

    public void setSo_luong(int so_luong) {
        this.so_luong = so_luong;
    }

    public int getTien() {
        return tien;
    }

    public void setTien(int tien) {
        this.tien = tien;
    }
}
