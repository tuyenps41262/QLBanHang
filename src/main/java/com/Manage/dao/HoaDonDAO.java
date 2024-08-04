
package com.Manage.dao;
import com.Manage.entity.HoaDon;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.Manage.utils.JdbcHelper;
import com.Manage.utils.XDate;

public class HoaDonDAO  extends HomeDAO<HoaDon, String>{
        String INSERT_SQL ="INSERT INTO HoaDon(IdHD, DienThoai, TrangThaiThanhToan, NgayLap, DiaChi, IdNV, IdKH) values (?,?,?,?,?,?,?) ";
        String UPDATE_SQL="UPDATE HoaDon set DienThoai=?, TrangThaiThanhToan=?, NgayLap =?, DiaChi=?, IdNV=?, IdKH =?, where IdHD=?";
        String DELETE_SQL="DELETE FROM HoaDon WHERE IdHD=? ";
        String SELECT_ALL="SELECT * FROM HoaDon";
        String SELECT_ALL_BYID = "SELECT FROM HoaDon WHERE IdHD=?";
    
    @Override
    public void insert(HoaDon entity) {
      JdbcHelper.update(INSERT_SQL, entity.getIdHD(), entity.getDienThoai(), entity.getTrangThaiThanhToan(), entity.getNgayLap(), entity.getDiaChi(), entity.getIdNV(), entity.getIdKH());
    }

    @Override
    public void update(HoaDon entity) {
      JdbcHelper.update(UPDATE_SQL, entity.getIdHD(), entity.getDienThoai(), entity.getTrangThaiThanhToan(), entity.getNgayLap(), entity.getDiaChi(), entity.getIdNV(), entity.getIdKH());
    }

    public void delete(Integer id) {
      JdbcHelper.update(DELETE_SQL, id);
    }

    @Override
    public List<HoaDon> selectAll() {
       return this.selectBySQL(SELECT_ALL);
    }

    public HoaDon selectById(Integer id) {
         List<HoaDon> list = this.selectBySQL(SELECT_ALL_BYID, id);
        return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    protected List<HoaDon> selectBySQL(String sql, Object... args) {
         List<HoaDon> list = new ArrayList<>();
            ResultSet rs = null;
            try {
                rs = JdbcHelper.query(sql, args);
                while(rs.next()){
                     HoaDon hd = new HoaDon();
                        hd.setIdHD(rs.getString("IdHD"));
                        hd.setDienThoai(rs.getString("DienThoai"));
                        hd.setTrangThaiThanhToan(rs.getString("TrangThaiThanhToan"));
                        hd.setNgayLap(new XDate(rs.getDate("NgayLap").getTime()));
                        hd.setDiaChi(rs.getString("DiaChi"));
                        hd.setIdNV(rs.getInt("IdNV"));
                        hd.setIdKH(rs.getInt("IdKH"));
                        
                    list.add(hd);
                }
                } catch (SQLException e) {
            throw new RuntimeException("Error selecting HoaDon: " + e.getMessage(), e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new RuntimeException("Error closing ResultSet: " + e.getMessage(), e);
                }
            }
        }
        return list;
    }


    @Override
    public void delete(String key) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public HoaDon selectById(String key) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    public List<HoaDon> selectById1(String id) {
         return this.selectBySQL(SELECT_ALL_BYID, id);
        
    }
    
}
