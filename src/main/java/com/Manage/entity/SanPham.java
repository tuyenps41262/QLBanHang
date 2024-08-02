
package com.Manage.entity;

public class SanPham {
    private String idSp;
    private String tenSp;
    private int soLuong;
    private String moTa;
    private double giaBan;
    private String loaiSp;
    private String hinh;

    public SanPham() {
    }

    public SanPham(String idSp, String tenSp, int soLuong, String moTa, double giaBan, String loaiSp, String hinh) {
        this.idSp = idSp;
        this.tenSp = tenSp;
        this.soLuong = soLuong;
        this.moTa = moTa;
        this.giaBan = giaBan;
        this.loaiSp = loaiSp;
        this.hinh = hinh;
    }
    
    public String getIdSp() {
        return idSp;
    }

    public void setIdSp(String idSp) {
        this.idSp = idSp;
    }

    public String getTenSp() {
        return tenSp;
    }

    public void setTenSp(String tenSp) {
        this.tenSp = tenSp;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public double getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(double giaBan) {
        this.giaBan = giaBan;
    }

    public String getLoaiSp() {
        return loaiSp;
    }

    public void setLoaiSp(String loaiSp) {
        this.loaiSp = loaiSp;
    }

    public String getHinh() {
        return hinh;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }

    @Override
    public String toString() {
        return "SanPham{" + "idSp=" + idSp + ", tenSp=" + tenSp + ", soLuong=" + soLuong + ", moTa=" + moTa + ", giaBan=" + giaBan + ", loaiSp=" + loaiSp + ", hinh=" + hinh + '}';
    }
    
}

