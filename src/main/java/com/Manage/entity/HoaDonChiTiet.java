
package com.Manage.entity;

public class HoaDonChiTiet {
    private int soLuong;
    private float giaBan;
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

    public float getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(float giaBan) {
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
