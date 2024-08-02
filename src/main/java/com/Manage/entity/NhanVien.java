
package com.Manage.entity;

public class NhanVien {
    private int idNv;
    private String tenNv;
    private String dienThoai;
    private String Email;
    private String gioiTinh;
    private String diaChi;

    public NhanVien() {
    }

    public NhanVien(int idNv, String tenNv, String dienThoai, String Email, String gioiTinh, String diaChi) {
        this.idNv = idNv;
        this.tenNv = tenNv;
        this.dienThoai = dienThoai;
        this.Email = Email;
        this.gioiTinh = gioiTinh;
        this.diaChi = diaChi;
    }

    public int getIdNv() {
        return idNv;
    }

    public void setIdNv(int idNv) {
        this.idNv = idNv;
    }

    public String getTenNv() {
        return tenNv;
    }

    public void setTenNv(String tenNv) {
        this.tenNv = tenNv;
    }

    public String getDienThoai() {
        return dienThoai;
    }

    public void setDienThoai(String dienThoai) {
        this.dienThoai = dienThoai;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

   
}

