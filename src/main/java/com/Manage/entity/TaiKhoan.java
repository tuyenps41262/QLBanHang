package com.Manage.entity;

public class TaiKhoan {
// các thuộc tính đều được khai báo là private để đảm bảo chúng chỉ có thể truy cập và thay đổi qua các phương thức get và set 
    private String username;
    private String matkhau;
    private String vaitro;
    private boolean trangthai;
    private int idNV;
    private int idKH;

    public TaiKhoan() {
    }

    public TaiKhoan(String username, String matkhau, String vaitro, boolean trangthai, int idNV, int idKH) {
        this.username = username;
        this.matkhau = matkhau;
        this.vaitro = vaitro;
        this.trangthai = trangthai;
        this.idNV = idNV;
        this.idKH = idKH;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }

    public String getVaitro() {
        return vaitro;
    }

    public void setVaitro(String vaitro) {
        this.vaitro = vaitro;
    }

    public boolean isTrangthai() {
        return trangthai;
    }

    public void setTrangthai(boolean trangthai) {
        this.trangthai = trangthai;
    }

    public int getIdNV() {
        return idNV;
    }

    public void setIdNV(int idNV) {
        this.idNV = idNV;
    }

    public int getIdKH() {
        return idKH;
    }

    public void setIdKH(int idKH) {
        this.idKH = idKH;
    }

    
}
