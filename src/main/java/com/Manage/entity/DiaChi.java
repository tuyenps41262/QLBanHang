
package com.Manage.entity;

public class DiaChi {
    private String id;
    private String diaChi;
    private String diaMacDinh;
    private int idKH;

    public DiaChi() {
    }

    public DiaChi(String id, String diaChi, String diaMacDinh, int idKH) {
        this.id = id;
        this.diaChi = diaChi;
        this.diaMacDinh = diaMacDinh;
        this.idKH = idKH;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getDiaMacDinh() {
        return diaMacDinh;
    }

    public void setDiaMacDinh(String diaMacDinh) {
        this.diaMacDinh = diaMacDinh;
    }

    public int getIdKH() {
        return idKH;
    }

    public void setIdKH(int idKH) {
        this.idKH = idKH;
    }
}
