package com.Manage.entity;

import com.Manage.utils.XDate;
import java.sql.Date;

public class HoaDon {

    private String idHD;
    private String soDienThoai;
    private String trangThaiThanhToan;
    private Date ngayLap;
    private String diaChi;
    private String tenNguoiNhan;
    private int idNV;
    private int idKH;

    public HoaDon() {
    }

    public HoaDon(String idHD, String soDienThoai, String trangThaiThanhToan, Date ngayLap, String diaChi, String tenNguoiNhan, int idNV, int idKH) {
        this.idHD = idHD;
        this.soDienThoai = soDienThoai;
        this.trangThaiThanhToan = trangThaiThanhToan;
        this.ngayLap = ngayLap;
        this.diaChi = diaChi;
        this.tenNguoiNhan = tenNguoiNhan;
        this.idNV = idNV;
        this.idKH = idKH;
    }

    public String getIdHD() {
        return idHD;
    }

    public void setIdHD(String idHD) {
        this.idHD = idHD;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getTrangThaiThanhToan() {
        return trangThaiThanhToan;
    }

    public void setTrangThaiThanhToan(String trangThaiThanhToan) {
        this.trangThaiThanhToan = trangThaiThanhToan;
    }

    public Date getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(Date ngayLap) {
        this.ngayLap = ngayLap;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getTenNguoiNhan() {
        return tenNguoiNhan;
    }

    public void setTenNguoiNhan(String tenNguoiNhan) {
        this.tenNguoiNhan = tenNguoiNhan;
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
