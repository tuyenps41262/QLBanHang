package com.Manage.dao;

import com.Manage.entity.TaiKhoan;
import java.sql.Connection;
import java.sql.PreparedStatement;
import com.Manage.utils.JdbcHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.Manage.utils.MsgBox;

public class TaiKhoanDAO_1 extends HomeDAO<TaiKhoan, String> {

    String INSERT_SQL = "INSERT INTO TaiKhoan(username, matkhau, vaitro, trangthai, IDNV, IDKH) values (?,?,?,?,?,?) ";
    String SELECT_ALL = "SELECT * FROM TaiKhoan";
    String SELECT_ALL_BYID = "SELECT * FROM TaiKhoan WHERE username = ?";

    private String username;

    @Override
    public List<TaiKhoan> selectAll() {
        return this.selectBySQL(SELECT_ALL);
    }

    public TaiKhoan selectByUsername(String username) {
        String sql = "SELECT * FROM TaiKhoan WHERE username = ?";
        List<TaiKhoan> list = this.selectBySQL(sql, username);
        return list.size() > 0 ? list.get(0) : null;
    }
    
        public TaiKhoan selectById(String id) {
        String sql = "SELECT * FROM TaiKhoan WHERE idnv = ?";
        List<TaiKhoan> list = this.selectBySQL(sql, id);
        return list.size() > 0 ? list.get(0) : null;
    }

    public boolean checkLogin(String username, String MatKhau) {
        List<TaiKhoan> users = getUserByID(username);
        for (TaiKhoan user : users) {
            if (user.getMatkhau().equals(MatKhau)) {
                return true;
            }
        }
        return false;
    }

    public List<TaiKhoan> getUserByID(String HoTen) {
        List<TaiKhoan> users = new ArrayList<>();
        PreparedStatement sttm = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM TaiKhoan WHERE username = ?";
//            sttm = PreparedStatement(sql);
            sttm.setString(1, username);

            rs = sttm.executeQuery();

            while (rs.next()) {
                TaiKhoan user = new TaiKhoan();
                user.setUsername(rs.getString("username"));
                user.setMatkhau(rs.getString("matkhau"));
                user.setVaitro(rs.getString("VaiTro"));
                user.setTrangthai(rs.getBoolean("trangthai"));
                user.setIdNV(rs.getInt("IdNV"));
                user.setIdKH(rs.getInt("IdKH"));
                users.add(user);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.toString());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (sttm != null) {
                    sttm.close();
                }
            } catch (SQLException e) {
                System.out.println("Error closing database resources: " + e.toString());
            }
        }
        return users;
    }

    @Override
    protected List<TaiKhoan> selectBySQL(String sql, Object... args) {
        List<TaiKhoan> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JdbcHelper.query(sql, args);
                while (rs.next()) {
                    TaiKhoan entity = new TaiKhoan();
                    entity.setUsername(rs.getString("username"));
                    entity.setMatkhau(rs.getString("matkhau"));
                    entity.setVaitro(rs.getString("VaiTro"));
                    entity.setTrangthai(rs.getBoolean("trangthai"));
                    entity.setIdNV(rs.getInt("IdNV"));
                    entity.setIdKH(rs.getInt("IdKH"));
                    list.add(entity);
                }
            } finally {
                if (rs != null) {
                    rs.getStatement().getConnection().close();
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        return list;
    }

    public void insert(TaiKhoan tk) {
        JdbcHelper.update(INSERT_SQL, tk.getMatkhau(), tk.getUsername());
    }

    public void update(TaiKhoan entity) {
    }

    public void delete(String id) {
    }
}
