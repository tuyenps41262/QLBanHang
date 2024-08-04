
package com.Manage.entity;

public class GioHang {
    private int soLuong;
    private int idKH;
    private String idSP;
    private String tenSP;

    public GioHang() {
    }

    public GioHang(int soLuong, int idKH, String idSP) {
        this.soLuong = soLuong;
        this.idKH = idKH;
        this.idSP = idSP;
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
    
}
