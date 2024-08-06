package com.Manage.entity;

public class GioHang {

    private int idKH;
    private String idSP;
    private String tenSP;
    private double giaBan;
    private int soLuong;

    public GioHang() {
    }

    public GioHang(int soLuong, int idKH, String idSP, String tenSP, double giaBan) {
        this.soLuong = soLuong;
        this.idKH = idKH;
        this.idSP = idSP;
        this.tenSP = tenSP;
        this.giaBan = giaBan;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getIdKH() {
        return idKH;
    }

    public void setIdKH(int idKH) {
        this.idKH = idKH;
    }

    public String getIdSP() {
        return idSP;
    }

    public void setIdSP(String idSP) {
        this.idSP = idSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public double getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(double giaBan) {
        this.giaBan = giaBan;
    }

    
}
