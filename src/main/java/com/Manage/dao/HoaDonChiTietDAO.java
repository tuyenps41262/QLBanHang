
package com.Manage.dao;
import com.Manage.entity.HoaDonChiTiet;
import com.Manage.utils.JdbcHelper;
import java.util.List;

public class HoaDonChiTietDAO  extends HomeDAO<HoaDonChiTiet, String>{
            String INSERT_SQL ="INSERT INTO NhanVien(SoLuong, GiaBan, IdHD, IdSP) values (?,?,?,?) ";
            String UPDATE_SQL="UPDATE NhanVien set SoLuong=?, GiaBan=?, IdHD =?, IdSP=? where IdNV=?";
            String DELETE_SQL="DELETE FROM NhanVien WHERE IdNV=? ";
            String SELECT_ALL="SELECT * FROM NhanVien";
            String SELECT_ALL_BYID = "SELECT FROM NhanVien WHERE IdHD=?";
    @Override
    public void insert(HoaDonChiTiet entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(HoaDonChiTiet entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(String key) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    public void delete1(String key, String key1) {
        JdbcHelper.update("delete from hoadonchitiet where idhd =? and idsp=?", key, key1);
    }

    @Override
    public List<HoaDonChiTiet> selectAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public HoaDonChiTiet selectById(String key) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected List<HoaDonChiTiet> selectBySQL(String sql, Object... args) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public List<HoaDonChiTiet> selectBYID1(String key) {
          return this.selectBySQL( SELECT_ALL_BYID,key);
    }
}
