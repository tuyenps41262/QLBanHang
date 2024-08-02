
package com.Manage.dao;
import com.Manage.entity.khachHang;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.Manage.utils.JdbcHelper;


public class KhachHangDAO extends HomeDAO<khachHang, String>{
     String INSERT_SQL ="INSERT INTO KhachHang(IdKH, TenKH, email, DienThoai, GioiTinh, DiaChi) values (?,?,?,?,?,?) ";
     String UPDATE_SQL="UPDATE KhachHang set TenKH=?, email=?, DienThoai=?, GioiTinh=?, DiaChi=? where IdKH=?";
     String DELETE_SQL="DELETE FROM KhachHang WHERE IdKH=? ";
     String SELECT_ALL="SELECT * FROM KhachHang";
     String SELECT_ALL_BYID = "SELECT FROM KhachHang WHERE IdKH=?";
     
     int khachHangid;
    @Override
    public void insert(khachHang entity) {
      JdbcHelper.update(INSERT_SQL, entity.getTenKh(),entity.getIdKh(), entity.getGioiTinh(), entity.getDienThoai(), entity.getEmail(), entity.getDiachi());
    }

    @Override
    public void update(khachHang entity) {
      JdbcHelper.update(UPDATE_SQL,  entity.getTenKh(), entity.getEmail(), entity.getDienThoai(), entity.getGioiTinh(), entity.getIdKh(), entity.getDiachi());
    }

    @Override
    public void delete(String key) {
       
    }

    @Override
    public List<khachHang> selectAll() {
          return this.selectBySQL(SELECT_ALL);
    }

    @Override
    public khachHang selectById(String IdKH) {
       List<khachHang> list = this.selectBySQL(SELECT_ALL_BYID, IdKH);
        return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    protected List<khachHang> selectBySQL(String sql, Object... args) {
        List<khachHang> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
               rs = JdbcHelper.query(sql, args);
                while (rs.next()) {
                    khachHang kh = new khachHang();
                    kh.setIdKh(rs.getInt("IdKH"));
                    kh.setTenKh(rs.getString("TenKH"));
                    kh.setEmail(rs.getString("email"));
                    kh.setDienThoai(rs.getString("DienThoai"));
                    kh.setGioiTinh(rs.getString("GioiTinh"));
                    kh.setDiachi(rs.getString("DiaChi"));
                    list.add(kh);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    } 
    
     public int getKhachHangID() {
        return khachHangid;
    }
    
     public void createKhachHangAndUser(String hoTen, String email,  String dienthoai, String gioitinh, String diachi,
            String username, String password, String vaitro, boolean trangthai) throws SQLException {
        String sql = "{CALL sp_CreateKHandUser(?,?,?,?,?,?,?,?,?)}";
        JdbcHelper.update(sql, hoTen,  email, dienthoai, gioitinh, diachi,
                username, password, vaitro, trangthai);
    }
}
