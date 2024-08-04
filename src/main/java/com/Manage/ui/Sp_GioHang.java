/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.Manage.ui;

import com.Manage.dao.HoaDonChiTietDAO;
import com.Manage.dao.HoaDonDAO;
import com.Manage.dao.SanPhamDAO;

import com.Manage.entity.HoaDon;
import com.Manage.entity.HoaDonChiTiet;
import com.Manage.entity.SanPham;
import java.awt.FlowLayout;
import java.awt.Image;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import com.Manage.utils.Auth;
import com.Manage.utils.MsgBox;
import com.Manage.utils.XDate;

/**
 *
 * @author thaohien
 */
public class Sp_GioHang extends javax.swing.JPanel {

    DecimalFormat formatter = new DecimalFormat("###,###,###");
    SanPhamDAO spdao;
    HoaDonDAO hddao = new HoaDonDAO();
    SanPham sp;
    List<SanPham> dssp;
    List<HoaDon> dshd;
    int vitri = 0;
    int row = 0;
    HoaDon hd = new HoaDon();
    HoaDonChiTietDAO hdctdao = new HoaDonChiTietDAO();

    public Sp_GioHang() {
        initComponents();
        spdao = new SanPhamDAO();
        dssp = (ArrayList<SanPham>) spdao.selectAll();
        dshd = (ArrayList<HoaDon>) hddao.selectAll();

        fillto_Table();

    }

    void fillto_Table() {
        String[] headers = {"IdSP", "TenSP", "SoLuong", "MoTa", "GiaBan", "LoaiSp", "Hinh"};
        DefaultTableModel model = new DefaultTableModel(headers, 0);
        List<SanPham> dssp = spdao.SearchByID((txtTimKiem.getText()));
        for (SanPham s : dssp) {
            Object[] row = {s.getIdSp(), s.getTenSp(), s.getSoLuong(), s.getMoTa(), s.getGiaBan(), s.getLoaiSp(), s.getHinh()};
            model.addRow(row);
        }
        tblSanPham.setModel(model);
    }
    String flag = "";

    public void loadDataTogiohang() {
        int soluong;
        try {
            soluong = Integer.parseInt(txtSoluong.getText().trim());
            if (soluong > 0) {
                DefaultTableModel tblCart1 = (DefaultTableModel) this.tblCart.getModel();
                if (tblCart1.getRowCount() == 0) {
                    //MsgBox.alert(this, String.valueOf(Auth.user.getIdKH()));
                    //hd= new HoaDon();1 
                    hd.setIdHD(phatsinh());
                    hd.setIdKH(Auth.user.getIdKH());
                    hddao.insert(hd);
                }
                SanPham sp1 = spdao.selectById(String.valueOf(tblSanPham.getValueAt(tblSanPham.getSelectedRow(), 0)));
                HoaDonChiTiet hdct = new HoaDonChiTiet();
                flag = hd.getIdHD();
                hdct.setIdHD(hd.getIdHD());
                hdct.setIdSP(sp1.getIdSp());
                hdct.setSoLuong(soluong);
                hdct.setGiaBan(sp1.getGiaBan());
                hdctdao.insert(hdct);
                tblCart1.setRowCount(0);
                List<HoaDonChiTiet> listhdct = hdctdao.selectBYID1(hd.getIdHD());
                List<HoaDon> listhd = hddao.selectById1(hd.getIdHD());
                try {
                    for (HoaDonChiTiet ccn : listhdct) {
                        for (HoaDon hd : listhd) {
                            if (ccn.getIdHD().equals(hd.getIdHD())) {
                                tblCart1.addRow(new Object[]{
                                    ccn.getIdSP(), hd.getIdKH(), ccn.getSoLuong()
                                });
                            }
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Error adding row: " + e.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập số lượng lớn hơn 0");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số lượng ở dạng số nguyên!");
        }
    }

//    private void thanhToanAction() {
//    // Gọi phương thức Thanhtoan() để tính tổng tiền
//    Thanhtoan();
//
//    // Thêm các xử lý khác sau khi tính tổng tiền
//    // Ví dụ: hiển thị hộp thoại xác nhận, cập nhật cơ sở dữ liệu, in hóa đơn, v.v.
//    int option = JOptionPane.showConfirmDialog(this,
//        "Tổng tiền: " + txtTongTien.getText() + "\nBạn có muốn thanh toán không?",
//        "Xác nhận thanh toán",
//        JOptionPane.YES_NO_OPTION);
//
//    if (option == JOptionPane.YES_OPTION) {
//        // Xử lý thanh toán
//        // Ví dụ: cập nhật trạng thái hóa đơn, xóa giỏ hàng, v.v.
//        JOptionPane.showMessageDialog(this, "Thanh toán thành công!");
//        // Gọi các phương thức khác để cập nhật UI hoặc dữ liệu
//        updateAfterPayment();
//    }
//}
//    private void updateAfterPayment() {
//    try {
//        // 1. Cập nhật trạng thái hóa đơn trong cơ sở dữ liệu
//        hd.setTrangThaiThanhToan("Đã thanh toán");
//        hd.setNgayLap(XDate.now()); // Thêm ngày thanh toán
//        hddao.update(hd);
//
//        // 2. Xóa các mục trong giỏ hàng (HoaDonChiTiet)
//        hdctdao.delete(hd.getIdHD());
//
//        // 3. Cập nhật lại bảng giỏ hàng
//        DefaultTableModel model = (DefaultTableModel) tblCart.getModel();
//        model.setRowCount(0); // Xóa tất cả các hàng trong bảng
//
//        // 4. Reset các trường nhập liệu
//        txtTongTien.setText("0đ");
//        // Reset các trường khác nếu cần
//        // txtOtherField.setText("");
//
//        // 5. Tạo hóa đơn mới cho lần mua hàng tiếp theo
//        hd = new HoaDon();
//        hd.setIdHD(phatsinh()); // Giả sử bạn có phương thức phatsinh() để tạo ID mới
//        hd.setIdKH(Auth.user.getIdKH());
//        hd.setNgayLap(XDate.now());
//        hd.setTrangThaiThanhToan("Chưa thanh toán");
//        hddao.insert(hd);
//
//        // 6. Cập nhật số lượng sản phẩm trong kho (nếu cần)
//        updateProductInventory();
//
//        // 7. Thông báo thành công
//        JOptionPane.showMessageDialog(this, "Thanh toán thành công và dữ liệu đã được cập nhật!");
//
//    } catch (Exception e) {
//        e.printStackTrace();
//        JOptionPane.showMessageDialog(this, "Lỗi khi cập nhật sau thanh toán: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
//    }
//}
//private void updateProductInventory() {
//    try {
//        DefaultTableModel model = (DefaultTableModel) tblCart.getModel();
//        for (int i = 0; i < model.getRowCount(); i++) {
//            String idSP = model.getValueAt(i, 0).toString();
//            int soLuongMua = Integer.parseInt(model.getValueAt(i, 2).toString());
//
//            SanPham sp = spdao.selectById(idSP);
//            if (sp != null) {
//                int soLuongMoi = sp.getSoLuong() - soLuongMua;
//                sp.setSoLuong(soLuongMoi);
//                spdao.update(sp);
//            }
//        }
//    } catch (Exception e) {
//        e.printStackTrace();
//        JOptionPane.showMessageDialog(this, "Lỗi khi cập nhật số lượng sản phẩm: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
//    }
//}
    String phatsinh() {
        if (dssp.size() <= 0) {
            return "HD001";
        }
        String masv = dshd.get(dshd.size() - 1).getIdHD();
        int so = Integer.parseInt(masv.substring(3, 5));
        if (so < 9) {
            return "HD00" + (so + 1);
        } else if (so < 99) {
            return "HD0" + (so + 1);
        } else {
            return "HD" + (so + 1);
        }
    }

    public void xoasp() {
        hdctdao.delete1(flag, String.valueOf(tblCart.getValueAt(tblCart.getSelectedRow(), 0)));
    }

    public void loadSauXoa() {
        DefaultTableModel tblCart1 = (DefaultTableModel) this.tblCart.getModel();
        tblCart1.setRowCount(0);
        List<HoaDonChiTiet> listhdct = hdctdao.selectBYID1(hd.getIdHD());
        List<HoaDon> listhd = hddao.selectById1(hd.getIdHD());
        try {
            for (HoaDonChiTiet ccn : listhdct) {
                for (HoaDon hd : listhd) {
                    if (ccn.getIdHD().equals(hd.getIdHD())) {
                        tblCart1.addRow(new Object[]{
                            ccn.getIdSP(), hd.getIdKH(), ccn.getSoLuong()
                        });
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error adding row: " + e.getMessage());
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        txtTimKiem = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblCart = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtSoluong = new javax.swing.JTextField();
        btnAddCart = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtTongTien = new javax.swing.JLabel();

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 153, 0));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("SẢN PHẨM ");

        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã Sản Phẩm", "Tên Sản Phẩm", "Số lượng", "Mô tả", " giá ", "Loại sp", "hinh"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.Double.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblSanPham);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtTimKiem.setBorder(javax.swing.BorderFactory.createTitledBorder("Tìm Kiếm Sản Phẩm"));
        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 431, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtTimKiem)
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tblCart.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên khách hàng", "Tên sản phẩm", "Số lượng"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblCart.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCartMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblCart);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 439, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jLabel3.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 153, 0));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("GIỎ HÀNG");

        jLabel4.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel4.setText("Số lượng ");

        btnAddCart.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        btnAddCart.setText("Thêm vào giỏ hàng");
        btnAddCart.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnAddCart.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAddCartMouseClicked(evt);
            }
        });
        btnAddCart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddCartActionPerformed(evt);
            }
        });

        jButton2.setText("Sửa Số Lượng ");

        jButton3.setText("Xoá Sản Phẩm Khỏi Đơn");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Thanh Toán");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel1.setText("TỔNG TIỀN");

        txtTongTien.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtTongTien.setText("0 đ ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 439, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(30, 30, 30)
                                    .addComponent(jLabel4)
                                    .addGap(56, 56, 56)
                                    .addComponent(txtSoluong, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(30, 30, 30)
                                    .addComponent(btnAddCart))))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(txtTongTien))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jButton2)
                                        .addGap(32, 32, 32)
                                        .addComponent(jButton3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton4))))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton4)
                    .addComponent(jLabel4)
                    .addComponent(txtSoluong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAddCart))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtTongTien))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddCartMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddCartMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_btnAddCartMouseClicked

    private void btnAddCartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddCartActionPerformed
        loadDataTogiohang();

        //txtTongTien.setText("0");
    }//GEN-LAST:event_btnAddCartActionPerformed

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked
        // TODO add your handling code here:

        //MsgBox.alert(this, "hjksdjajfjefjw");
    }//GEN-LAST:event_tblSanPhamMouseClicked

    private void tblCartMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCartMouseClicked
        // TODO add your handling code here:
        //filltoTableCart();
    }//GEN-LAST:event_tblCartMouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        xoasp();
        loadSauXoa();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_jButton4ActionPerformed

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased
        // TODO add your handling code here:
        fillto_Table();
    }//GEN-LAST:event_txtTimKiemKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddCart;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblCart;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTextField txtSoluong;
    private javax.swing.JTextField txtTimKiem;
    private javax.swing.JLabel txtTongTien;
    // End of variables declaration//GEN-END:variables
}
