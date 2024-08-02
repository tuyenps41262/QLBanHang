
package com.Manage.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import qlbh.until.ConnectionDatabase;
//import qlbh.until.JdbcHelper;
import com.Manage.utils.RandomSNN;
//import qlbh.until.xEmail;

/**
 *
 * @author thaohien
 */
public class forgotpassDAO {
    
//    public boolean checkUser(String email) throws Exception {
//        String sql = " select * from nhanvien where email like? ";
////        Connection conn = ConnectionDatabase.getInstance().getConnection();
////        PreparedStatement stmt = conn.prepareStatement(sql);
//        stmt.setString(1, email);
//        ResultSet rs = stmt.executeQuery();
//        int count = 0;
//        while (rs.next()) {
//            count++;
//        }
//        if (count == 1) {
//            return true;
//        }
//        return false;
//    }
//
//    public boolean updatePass(String email) throws Exception {
//        try {
//            String sql = "update NhanVien set matkhau=? where email like ? ";
//            Connection conn = ConnectionDatabase.getInstance().getConnection();
//            PreparedStatement stmt = conn.prepareStatement(sql);
//            String password = new RandomSNN().soNgauNhien(6);
//            stmt.setString(1, password);
//            stmt.setString(2, email);
//
//            xEmail e = new xEmail();
//            e.sendEmail(email, password);
//            return stmt.executeUpdate() > 0;
//        } catch (SQLException e) {
//
//        }
//        return false;
//    }
}
