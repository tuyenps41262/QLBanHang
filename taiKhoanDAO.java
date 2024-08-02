/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package qlbh.DAO;

import Entity.TaiKhoan;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import qlbh.until.JdbcHelper;

/**
 *
 * @author thaohien
 */
public class taiKhoanDAO extends HomeDAO<TaiKhoan, String>{ 

    public static taiKhoanDAO getInstance() {
         return new taiKhoanDAO();
    }
    
    private String username;
    
    @Override
    public void insert(TaiKhoan entity) {
    }

    @Override
    public void update(TaiKhoan entity) {
        JdbcHelper.excuteupdate("Update TaiKhoan set  username=?, matkhau=? , vaitro=? , trangthai=? where idnv=?", 
            entity.getUserName(),entity.getPassWord(),entity.getVaiTro(), entity.isTrangThai(),entity.getIdmanv());
    }

    @Override
    public void delete(String key) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<TaiKhoan> selectAll() {
        String sql= "Select * from taikhoan";
        return this.selectBySQL(sql);
    }

    @Override
    public TaiKhoan selectById(String username) {
            String sql = " Select * from taikhoan where username=?";
            List<TaiKhoan> list= this. selectBySQL(sql, username);
            return list.size() >0 ? list.get(0): null;
    }
    public TaiKhoan selectById1 (String username) {
            String sql = " Select * from taikhoan where IDNV=?";
            List<TaiKhoan> list= this. selectBySQL(sql, username);
            return list.size() >0 ? list.get(0): null;
    }

    @Override
    protected List<TaiKhoan> selectBySQL(String sql, Object... args) {
        List<TaiKhoan> list=new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JdbcHelper.query(sql, args);
                while(rs.next()){
                    TaiKhoan entity=new TaiKhoan();
                    entity.setUserName(rs.getString("username"));
                    entity.setPassWord(rs.getString("matkhau"));
                    entity.setVaiTro(rs.getString("VaiTro"));
                    entity.setTrangThai(rs.getBoolean("trangthai"));
                    entity.setIdmanv(rs.getInt("IdNV"));
//                entity.setIdmakh(rs.getInt("IdKH"));
//                
                    list.add(entity);
                }
            } 
            finally{
                if(rs != null)
                    rs.getStatement().getConnection().close();
            }
        } 
        catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        return list;
    }
    
    public List<TaiKhoan> getUserByID(String HoTen) {
        List<TaiKhoan> users = new ArrayList<>();
        PreparedStatement sttm = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM TaiKhoan WHERE username = ?";
            sttm.setString(1, username);

            rs = sttm.executeQuery();

            while (rs.next()) {
                TaiKhoan user = new TaiKhoan();
                user.setUserName(rs.getString("username"));
                user.setPassWord(rs.getString("matkhau"));
                user.setVaiTro(rs.getString("VaiTro"));
                //
                user.setTrangThai(rs.getBoolean("trangthai"));
                user.setIdmanv(rs.getInt("IdNV"));
//                user.setIdmakh(rs.getInt("IdKH"));
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
     public boolean checkLogin(String username, String MatKhau) {
        List<TaiKhoan> users = getUserByID(username);
        for (TaiKhoan user : users) {
            if (user.getPassWord().equals(MatKhau)) {
                return true;
            }
        }
        return false;
    }
//    public int updatePassword(String email, String password) {
//        int kq = 0;
//        PreparedStatement sttm = null;
//        ResultSet rs = null;
//
//        try {
//            String sql = "UPDATE TaiKhoan SET password=? WHERE email=?";
//            sttm.setString(1, password);
//            sttm.setString(2, email);
//
//            kq = sttm.executeUpdate();
//            rs.close();
//        } catch (SQLException ex) {
//            Logger.getLogger(taiKhoanDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return kq;
//
//    }
     
}
