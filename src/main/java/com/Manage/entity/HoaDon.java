
package com.Manage.entity;

import com.Manage.utils.XDate;

public class HoaDon {
    private String idHD;
    private String dienThoai;
    private String trangThaiThanhToan;
    private XDate ngayLap;
    private String diaChi;
    private int idNV;
    private int idKH;

    public HoaDon() {
    }

    public HoaDon(String idHD, String dienThoai, String trangThaiThanhToan, XDate ngayLap, String diaChi, int idNV, int idKH) {
        this.idHD = idHD;
        this.dienThoai = dienThoai;
        this.trangThaiThanhToan = trangThaiThanhToan;
        this.ngayLap = ngayLap;
        this.diaChi = diaChi;
        this.idNV = idNV;
        this.idKH = idKH;
    }

    public String getIdHD() {
        return idHD;
    }

    public void setIdHD(String idHD) {
        this.idHD = idHD;
    }

    public String getDienThoai() {
        return dienThoai;
    }

    public void setDienThoai(String dienThoai) {
        this.dienThoai = dienThoai;
    }

    public String getTrangThaiThanhToan() {
        return trangThaiThanhToan;
    }

    public void setTrangThaiThanhToan(String trangThaiThanhToan) {
        this.trangThaiThanhToan = trangThaiThanhToan;
    }

    public XDate getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(XDate ngayLap) {
        this.ngayLap = ngayLap;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
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
