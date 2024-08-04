package com.Manage.dao;

import com.Manage.entity.NhanVien;
import com.Manage.utils.JdbcHelper;
import com.Manage.entity.SanPham;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class SanPhamDAO extends HomeDAO<SanPham, String> {

    String INSERT_SQL = "INSERT INTO SanPham(IdSP, TenSP, SoLuong, MoTa, GiaBan, LoaiSp, Hinh) VALUES(?,?,?,?,?,?,?)";
    String UPDATE_SQL = "UPDATE SanPham SET TenSP =? ,SoLuong = ?, MoTa = ?,GiaBan=?,LoaiSp=?, Hinh= ? WHERE IdSP =?";
    String DELETE_SQL = "DELETE FROM SanPham WHERE IdSP =?";
    String SELECT_ALL_SQL = "SELECT * FROM SanPham";
    String SELECT_BY_ID_SQL = "SELECT * FROM SanPham WHERE IdSP = ?"; 

    @Override
    public void insert(SanPham entity) {
        JdbcHelper.update(INSERT_SQL, entity.getIdSp(), entity.getTenSp(), entity.getSoLuong(), entity.getMoTa(), entity.getGiaBan(), entity.getLoaiSp(), entity.getHinh());

    }

    @Override
    public void update(SanPham entity) {
        JdbcHelper.update(UPDATE_SQL, entity.getTenSp(), entity.getSoLuong(), entity.getMoTa(), entity.getGiaBan(), entity.getLoaiSp(), entity.getHinh(), entity.getIdSp());
    }

    @Override
    public void delete(String IdSP) {
        JdbcHelper.update(DELETE_SQL, IdSP);
    }

    public void save(SanPham entity) {
        String updateSql = "UPDATE sanpham SET TenSP = ?, SoLuong = ?, MoTa = ?, GiaBan = ?, LoaiSP = ?, Hinh = ? WHERE IdSP = ?";
        JdbcHelper.update(updateSql, entity.getTenSp(), entity.getSoLuong(), entity.getMoTa(), entity.getGiaBan(), entity.getLoaiSp(), entity.getHinh(), entity.getIdSp());
    }

//    @Override
//    public SanPham selectById(String IdSP) {
//           List<SanPham> list = selectBySQL(SELECT_BY_ID_SQL, IdSP);
//        return list.size() > 0 ? list.get(0) : null;
//    }
    public List<SanPham> SearchByID(String key) {
        String sql = "SELECT * FROM SanPham WHERE IdSP LIKE ? OR TenSP LIKE ?";
        return this.selectBySQL(sql, "%" + key + "%", "%" + key + "%");
    }

    @Override
    public List<SanPham> selectBySQL(String sql, Object... args) {
        List<SanPham> list = new ArrayList<SanPham>();
        try {
            ResultSet rs = null;
            try {
                rs = JdbcHelper.query(sql, args);
                while (rs.next()) {
                    SanPham sp = new SanPham();
                    sp.setIdSp(rs.getString("IdSP"));
                    sp.setTenSp(rs.getString("TenSP"));
                    sp.setSoLuong(rs.getInt("SoLuong"));
                    sp.setMoTa(rs.getString("MoTa"));
                    sp.setGiaBan(rs.getFloat("GiaBan"));
                    sp.setLoaiSp(rs.getString("LoaiSp"));
                    sp.setHinh(rs.getString("Hinh"));
                    list.add(sp);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public List<SanPham> selectAll() {
        return this.selectBySQL(SELECT_ALL_SQL);
    }

    @Override
    public SanPham selectById(String key) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
