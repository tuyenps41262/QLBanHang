
package com.Manage.ui;
import com.Manage.dao.SanPhamDAO;
import com.Manage.entity.SanPham;
import java.util.List;
import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.setSize(850, 680);
        f.setLocationRelativeTo(null);
        //ThongTinNhanVen nvp = new ThongTinNhanVen();
        //NhanVienPanel nvp = new NhanVienPanel();
      //SanPhamNewJPanel nvp = new SanPhamNewJPanel();
       //KhachHangNewJPanel nvp = new KhachHangNewJPanel();
       Sp_GioHang nvp = new Sp_GioHang();
        f.add(nvp);
        f.setVisible(true);
//        SanPhamDAO sp = new SanPhamDAO();
//        List<SanPham> list = sp.selectAll();
//        for (SanPham sanPham : list) {
//            System.out.println(sanPham.toString());
//        }
    }
}
