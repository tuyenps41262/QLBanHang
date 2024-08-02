
package com.Manage.entity;

public class khachHang {
    private int idKh;
    private String tenKh;
    private String email;
    private String Diachi;
    private String dienThoai;
    private String gioiTinh;

    public khachHang() {
    }

    public khachHang(String tenKh, String email,String DiaChi, String dienThoai, String gioiTinh) {
        this.tenKh = tenKh;
        this.email = email;
        this.Diachi = DiaChi;
        this.dienThoai = dienThoai;
        this.gioiTinh = gioiTinh;
    }

    public khachHang(int idKh, String tenKh, String email, String dienThoai, String gioiTinh) {
        this.idKh = idKh;
        this.tenKh = tenKh;
        this.email = email;
        this.dienThoai = dienThoai;
        this.gioiTinh = gioiTinh;
    }

    public String getDiachi() {
        return Diachi;
    }

    public void setDiachi(String Diachi) {
        this.Diachi = Diachi;
    }

    
    public int getIdKh() {
        return idKh;
    }

    public void setIdKh(int idKh) {
        this.idKh = idKh;
    }

    public String getTenKh() {
        return tenKh;
    }

    public void setTenKh(String tenKh) {
        this.tenKh = tenKh;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDienThoai() {
        return dienThoai;
    }

    public void setDienThoai(String dienThoai) {
        this.dienThoai = dienThoai;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }
}

