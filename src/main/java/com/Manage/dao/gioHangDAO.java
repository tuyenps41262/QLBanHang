
package com.Manage.dao;
import com.Manage.entity.GioHang;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.Manage.utils.JdbcHelper;

public class gioHangDAO extends HomeDAO<GioHang, String>{

    @Override
    public void insert(GioHang entity) {
       
    }

    @Override
    public void update(GioHang entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(String key) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<GioHang> selectAll() {
        return this.selectBySQL("select * from GioHang");
    }

    @Override
   // public GioHang selectById(String key) {
//        List<GioHang> list = new ArrayList<GioHang>();
//         try {
//            ResultSet rs = null;
//            try {
//                rs = JdbcHelper.query(sql, args);
//                while(rs.next()){
//                    GioHang sp = new GioHang();
//                        sp.setIdKH(rs.getInt("IdSP"));
//                        sp.setIdSP(rs.getInt("IdSP"));
//                        sp.setTenSp(rs.getString("TenSP"));
//                        sp.setSoLuong(rs.getInt("SoLuong"));
//                        sp.setMoTa(rs.getString("MoTa"));
//                        sp.setGiaBan(rs.getFloat("GiaBan"));
//                        sp.setLoaiSp(rs.getString("LoaiSp"));
//                    list.add(sp);
//                }
//                
//            } finally {
//                rs.getStatement().getConnection().close();
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return list;   
 //   }

  //  @Override
    protected List<GioHang> selectBySQL(String sql, Object... args) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public GioHang selectById(String key) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
