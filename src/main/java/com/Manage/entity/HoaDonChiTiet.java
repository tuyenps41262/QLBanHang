
package com.Manage.entity;

public class HoaDonChiTiet {
    private int soLuong;
    private double giaBan;
    private String idHD;
    private String idSP;

    public HoaDonChiTiet() {
    }

    public HoaDonChiTiet(int soLuong, float giaBan, String idHD, String idSP) {
        this.soLuong = soLuong;
        this.giaBan = giaBan;
        this.idHD = idHD;
        this.idSP = idSP;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(double giaBan) {
        this.giaBan = giaBan;
    }

    public String getIdHD() {
        return idHD;
    }

    public void setIdHD(String idHD) {
        this.idHD = idHD;
    }

    public String getIdSP() {
        return idSP;
    }

    public void setIdSP(String idSP) {
        this.idSP = idSP;
    }

    
}
