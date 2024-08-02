package com.Manage.ui;

import javax.swing.table.DefaultTableModel;
import com.Manage.entity.SanPham;
import java.util.ArrayList;
import com.Manage.dao.SanPhamDAO;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.io.File;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import com.Manage.utils.Auth;
import com.Manage.utils.JdbcHelper;
import com.Manage.utils.MsgBox;
import com.Manage.utils.XImage;

public class SanPhamJDialog extends javax.swing.JDialog {
    
    JFileChooser fileChooser = new JFileChooser();
        ArrayList<SanPham> dssp = new ArrayList<>();
        SanPhamDAO dao;
        int vitri = 0;
        private int row;
        int flag = 0;
   // private SanPham e;
   // private String IdSP;
    SanPham sp= new SanPham();

    public SanPhamJDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        dao = new SanPhamDAO();
        dssp = (ArrayList<SanPham>) dao.selectAll();
        HienThiTable();
        showDetail();
    }

    void HienThiTable() {
        String[] header = {"IdSP", "TenSP", "SoLuong", "MoTa", "GiaBan", "LoaiSp", "Hinh"};
        DefaultTableModel model = new DefaultTableModel(header, 0);
        for (SanPham x : dssp) {
            Object[] row = {x.getIdSp(), x.getTenSp(), x.getSoLuong(), x.getMoTa(), x.getGiaBan(), x.getLoaiSp(), x.getHinh()};
            model.addRow(row);
        }
        tblkhoSanPham.setModel(model);
    } // hiển thị table kho sản phẩm

    void showDetail() {
        if (vitri >= 0 && vitri < dssp.size()) {
            SanPham sp = dssp.get(vitri);
            txtMaSP.setText(sp.getIdSp());
            txtTenSP.setText(sp.getTenSp());
            txtSoLuong.setText(String.valueOf(sp.getSoLuong()));
            txtMota.setText(sp.getMoTa());
            txtgiaBan.setText(String.valueOf(sp.getGiaBan()));
            txtLoaisp.setText(sp.getLoaiSp());
            UploaHinh(sp.getHinh());
        }
    } // hiển thị lên text Field

    void UploaHinh(String hinh) {
//        ImageIcon image1 = new ImageIcon("D:\\DuAn1_ps39536\\src\\main\\resources\\anhCSDL\\" + hinh);
        ImageIcon image1 = new ImageIcon("C:\\Users\\nguye\\OneDrive\\Desktop\\DuAn1\\QuanLyBanHangQuanAo\\QLBanHang\\src\\main\\resources\\anhCSDL" + hinh);
        Image img = image1.getImage();
        ImageIcon icon = new ImageIcon(img.getScaledInstance(lblAnh.getWidth(), lblAnh.getHeight(),
                Image.SCALE_SMOOTH));//tỉ lệ khung hình SCALE_SMOOTH
        lblAnh.setIcon(icon);
    } //uploa hình
    
    private String ChonHinh() {
        JFileChooser open = new JFileChooser();
        // Thiết lập đường dẫn mặc định cho  JFileChooser
        File defaultDirectory = new File("D:\\DuAn1_ps39536\\src\\main\\resources\\anhCSDL\\");
        open.setCurrentDirectory(defaultDirectory); //open hộp thoại lên
        int kq = open.showOpenDialog(open);
        String hinh = ""; // lấy đg dẫn của mình ra là chuỗi 
        if (kq == JFileChooser.APPROVE_OPTION) {
            File f = open.getSelectedFile();
            String filename = f.getAbsolutePath();
            String[] chuoi = filename.split("\\\\");
            hinh = chuoi[chuoi.length - 1];// lấy đc hình dùng chuỗi lenght - 1            
        }
        return hinh;
    }

    void edit() {
        try {
            String IdSP = (String) tblkhoSanPham.getValueAt(tblkhoSanPham.getSelectedRow(), 0);
            SanPham model = dao.selectById(IdSP);
            if (model != null) {
                setForm(model);
            //    updateStatus();
                Tabs.setSelectedIndex(0);
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu!");
        }
    }

      void XuLyChucNang(boolean t) {
        btnThem.setEnabled(t);
        btnSua.setEnabled(t);
        btnLuu.setEnabled(!t);
    }
      
    public void xulytext(boolean a) {
        txtMaSP.setEnabled(!a);
        txtTenSP.setEnabled(a);
        txtSoLuong.setEnabled(a);
        txtgiaBan.setEnabled(a);
        txtMota.setEnabled(a);
        txtLoaisp.setEnabled(a);
    }

    public void reset() {
        txtLoaisp.setText("");
        txtTenSP.setText("");
        txtSoLuong.setText("");
        txtgiaBan.setText("");
        txtMota.setText("");
        txtMaSP.requestFocus();
    }

    private void setForm(SanPham sp) {
        // Điền thông tin sản phẩm vào các trường trong form
        txtMaSP.setText(sp.getIdSp());
        txtTenSP.setText(sp.getTenSp());
        txtSoLuong.setText(String.valueOf(sp.getSoLuong()));
        txtMota.setText(sp.getMoTa());
        txtgiaBan.setText(String.valueOf(sp.getGiaBan()));
        txtLoaisp.setText(sp.getLoaiSp());
        lblAnh.setText(sp.getHinh());
    }
    
    SanPham getForm() {
        sp.setIdSp(txtMaSP.getText());
        sp.setTenSp(txtTenSP.getText());
        sp.setGiaBan(Double.parseDouble(txtgiaBan.getText()));
        sp.setSoLuong(Integer.parseInt(txtSoLuong.getText())); 
        sp.setMoTa(txtMota.getText());
        sp.setLoaiSp(txtLoaisp.getText());
        sp.setHinh(lblAnh.getText());
      return sp;
}

    String phatsinh() {
        if (dssp.size() <= 0) {
            return "SP001";
        }
        if (!(dssp.get(0).getIdSp().equals("SP001"))) {
            return "SP001";
        }
        for (int i = 0; i < dssp .size() - 1; i++) {
            int j = i + 1;
            String masp1 = dssp.get(i).getIdSp();
            int so1 = Integer.parseInt(masp1.substring(3, 5));
            String masv2 = dssp.get(j).getIdSp();
            int so2 = Integer.parseInt(masv2.substring(3, 5));
            if ((so1 + 1) != so2) {
                if (so1 < 9) {
                    return "SP00" + (so1 + 1);
                } else if (so1 < 99) {
                    return "SP0" + (so1 + 1);
                } else {
                    return "SP" + (so1 + 1);
                }

            }
        }
        String masv = dssp.get(dssp.size() - 1).getIdSp();
        int so = Integer.parseInt(masv.substring(3, 5));
        if (so < 9) {
            return "SP00" + (so + 1);
        } else if (so < 99) {
            return "SP0" + (so + 1);
        } else {
            return "SP" + (so + 1);
        }
     }

    private String validateForm(String idSp, String tenSp, int soLuong, String moTa, double giaBan, String loaiSp, String hinh) {
        // Khai báo biến result để lưu trữ thông báo lỗi
        String result = "";
        // Kiểm tra và xử lý lỗi cho trường mã sản phẩm
        if (idSp.isBlank()) {
            result += "Không được để trống mã sản phẩm\n"; // Thêm thông báo lỗi vào result
        }
        // Kiểm tra và xử lý lỗi cho trường tên sản phẩm
        if (tenSp.isBlank()) {
            result += "Không được để trống tên sản phẩm\n"; // Thêm thông báo lỗi vào result
        }
        // Kiểm tra và xử lý lỗi cho trường số lượng sản phẩm
        if (soLuong <= 0) {
            result += "Số lượng sản phẩm phải lớn hơn 0\n"; // Thêm thông báo lỗi vào result
        }
        // Kiểm tra và xử lý lỗi cho trường mô tả
        if (moTa.isEmpty()) {
            result += "Xin vui lòng nhập mô tả sản phẩm\n"; // Thêm thông báo lỗi vào result
        }
        // Kiểm tra và xử lý lỗi cho trường giá bán
        if (giaBan <= 0) {
            result += "Giá bán sản phẩm phải lớn hơn 0\n"; // Thêm thông báo lỗi vào result
        }
        // Kiểm tra và xử lý lỗi cho trường loại sản phẩm
        if (loaiSp.isEmpty()) {
            result += "Xin vui lòng chọn loại sản phẩm\n"; // Thêm thông báo lỗi vào result
        }
        // Kiểm tra và xử lý lỗi cho trường link hình ảnh
        if (hinh.isBlank()) {
            result += "Xin vui lòng nhập link hình ảnh\n"; // Thêm thông báo lỗi vào result
        }
        // Trả về chuỗi result chứa các thông báo lỗi (nếu có) sau khi kiểm tra
        return result;
    }

      void clearForm() {
      this.setForm(new SanPham());
     //  this.updateStatus();
         row = -1;
//       updateStatus();
     }
      
     void insert() {
        try {
            dao.insert(getForm()); // Thêm sản phẩm vào cơ sở dữ liệu
            this.HienThiTable(); // Cập nhật bảng hiển thị danh sách sản phẩm
           // this.clearForm(); // Xóa dữ liệu trong các trường nhập liệu
            MsgBox.alert(this, "Thêm mới thành công"); // Hiển thị thông báo thành công
        } catch (Exception e) {
            MsgBox.alert(this, "Thêm mới thất bại!"); // Hiển thị thông báo lỗi
        }
}
      
    void update() {
        try {
            dao.update(getForm());
            this.HienThiTable();
            // MsgBox.alert(this, "Cập nhật thành công!");
        } catch (Exception e) {
            MsgBox.alert(this, "Cập nhật thất bại!");
        }
    }
        
    void save() {
        SanPham model = getForm();
        try {
            dao.save(model);
            this.HienThiTable();
            MsgBox.alert(this, "Lưu thành công!");
        } catch (Exception e) {
            MsgBox.alert(this, "Lưu thất bại!");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu1 = new javax.swing.JMenu();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        Tabs = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lblAnh = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtMaSP = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtTenSP = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtSoLuong = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtgiaBan = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        btnFirst = new javax.swing.JButton();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnLuu = new javax.swing.JButton();
        btnPrev = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        txtMota = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtLoaisp = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblkhoSanPham = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        txtTimKiem1 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();

        jMenu1.setText("jMenu1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 153, 0));
        jLabel1.setText("Quản Lý Kho - Sản Phẩm");

        Tabs.setForeground(new java.awt.Color(255, 204, 0));
        Tabs.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        lblAnh.setForeground(new java.awt.Color(51, 0, 0));
        lblAnh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 255)));
        lblAnh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAnhMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblAnhMousePressed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Mã Sản Phẩm:");

        txtMaSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaSPActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Tên Sản Phẩm :");

        txtTenSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenSPActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Số Lượng :");

        txtSoLuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSoLuongActionPerformed(evt);
            }
        });
        txtSoLuong.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSoLuongKeyPressed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Giá Bán : ");

        txtgiaBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtgiaBanActionPerformed(evt);
            }
        });
        txtgiaBan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtgiaBanKeyTyped(evt);
            }
        });

        btnFirst.setText("|<");
        btnFirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstActionPerformed(evt);
            }
        });

        btnThem.setIcon(new javax.swing.ImageIcon("D:\\DuAn1_ps39536\\src\\main\\resources\\icons\\Add.png")); // NOI18N
        btnThem.setText("Add");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setIcon(new javax.swing.ImageIcon("D:\\DuAn1_ps39536\\src\\main\\resources\\icons\\Edit.png")); // NOI18N
        btnSua.setText("Update");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnLuu.setIcon(new javax.swing.ImageIcon("D:\\DuAn1_ps39536\\src\\main\\resources\\icons\\Save.png")); // NOI18N
        btnLuu.setText("Save");
        btnLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuActionPerformed(evt);
            }
        });

        btnPrev.setText("<<");
        btnPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevActionPerformed(evt);
            }
        });

        btnNext.setText(">>");
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        btnLast.setText(">|");
        btnLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastActionPerformed(evt);
            }
        });

        txtMota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMotaActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setText("Mô Tả: ");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setText("Loại Sản Phẩm :");

        jLabel10.setIcon(new javax.swing.ImageIcon("D:\\DuAn1_ps39536\\src\\main\\resources\\anhCSDL\\cart2.png")); // NOI18N
        jLabel10.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Giỏ Hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(255, 102, 0))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMaSP)
                            .addComponent(txtTenSP)
                            .addComponent(txtSoLuong)
                            .addComponent(txtgiaBan)
                            .addComponent(txtMota)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(txtLoaisp))
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap(317, Short.MAX_VALUE)
                        .addComponent(btnFirst, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPrev, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(85, 85, 85)
                        .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(64, 64, 64))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(btnThem)
                        .addGap(24, 24, 24)
                        .addComponent(btnSua)
                        .addGap(27, 27, 27)
                        .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLast, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel2))
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtgiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblAnh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addComponent(txtMota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addComponent(txtLoaisp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnFirst)
                    .addComponent(btnPrev)
                    .addComponent(btnNext)
                    .addComponent(btnLast))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnThem, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btnSua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnLuu)))
                        .addContainerGap(38, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        Tabs.addTab("SẢN PHẨM", jPanel2);

        tblkhoSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Sản Phẩm", "Tên Sản Phẩm", "Số Lượng", "Giá Bán", "Mô Tả", "Hình"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblkhoSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblkhoSanPhamMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblkhoSanPhamMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblkhoSanPham);

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "TÌM KIẾM", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        txtTimKiem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiem1ActionPerformed(evt);
            }
        });

        jButton2.setText("Tìm");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(txtTimKiem1)
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addGap(23, 23, 23))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(txtTimKiem1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 6, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 763, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Tabs.addTab("KHO SẢN PHẨM", jPanel3);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(Tabs, javax.swing.GroupLayout.PREFERRED_SIZE, 775, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Tabs, javax.swing.GroupLayout.PREFERRED_SIZE, 557, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(7, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        flag = 1;
        reset();
        XuLyChucNang(false); 
        xulytext(true); 
       txtMaSP.setText(phatsinh());
       tblkhoSanPham.setRowSelectionInterval(vitri, vitri);
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        flag = 2;
        XuLyChucNang(false);
        xulytext(true); 
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
        // TODO add your handling code here:
        if (flag == 1) {
            insert();
            JOptionPane.showMessageDialog(this, "Đã lưu thông tin sản phẩm mới!");
        } if (flag == 2) {
            update();
            JOptionPane.showMessageDialog(this, "Đã cập nhật thông tin sản phẩm!");
        }
        flag = 0;
        dssp.clear();
        dssp = (ArrayList<SanPham>) dao.selectAll();
        showDetail();
        HienThiTable();
        XuLyChucNang(false);
      //  tblkhoSanPham.setRowSelectionInterval(vitri, vitri);
        XuLyChucNang(true);
        
    }//GEN-LAST:event_btnLuuActionPerformed

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        // TODO add your handling code here:
        vitri = dssp.size() - 1;
        btnFirst.setEnabled(true);
        btnNext.setEnabled(false);
        btnPrev.setEnabled(true);
        btnLast.setEnabled(false);
        tblkhoSanPham.setRowSelectionInterval(vitri, vitri);
       showDetail();
    }//GEN-LAST:event_btnLastActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        // TODO add your handling code here:
           vitri++;
        if (vitri == dssp.size()) {
            vitri = dssp.size() - 1;
            btnNext.setEnabled(false);
            btnLast.setEnabled(false);
            JOptionPane.showMessageDialog(null, "Đang ở cuối danh sách!");
        }
        btnFirst.setEnabled(true);
        btnPrev.setEnabled(true);
        txtMaSP.setEnabled(false);
        showDetail();
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevActionPerformed
        // TODO add your handling code here:
       vitri--;
        if (vitri > 0) {
             showDetail();
            btnNext.setEnabled(true);
            btnLast.setEnabled(true);
        } else {
            vitri = 0;
            btnFirst.setEnabled(false);
            btnPrev.setEnabled(false);
            JOptionPane.showMessageDialog(null, "Đang ở đầu danh sách!");
        }
    }//GEN-LAST:event_btnPrevActionPerformed

    private void btnFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstActionPerformed
        // TODO add your handling code here:
         vitri = 0;
        showDetail();
        btnFirst.setEnabled(false);
        btnPrev.setEnabled(true);
        btnNext.setEnabled(false);
        btnLast.setEnabled(true);
        JOptionPane.showMessageDialog(rootPane, "Bạn đang ở đầu danh sách");
    }//GEN-LAST:event_btnFirstActionPerformed

    private void tblkhoSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblkhoSanPhamMouseClicked
        // TODO add your handling code here:
       vitri = tblkhoSanPham.getSelectedRow();
       showDetail();
         txtMaSP.setEnabled(false);
         txtTenSP.setEnabled(false);
         txtSoLuong.setEnabled(false);
         txtgiaBan.setEnabled(false);
         txtMota.setEnabled(false);
         txtLoaisp.setEnabled(false);
         XuLyChucNang(true);
        if (evt.getClickCount() == 1) {
            this.row = tblkhoSanPham.rowAtPoint(evt.getPoint());
            edit();
        }
    }//GEN-LAST:event_tblkhoSanPhamMouseClicked

    private void tblkhoSanPhamMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblkhoSanPhamMousePressed
        // TODO add your handling code here:

    }//GEN-LAST:event_tblkhoSanPhamMousePressed

    private void lblAnhMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAnhMousePressed
        // TODO add your handling code here:
        String hinh = ChonHinh();
        lblAnh.setText(hinh);
        UploaHinh(hinh);
    }//GEN-LAST:event_lblAnhMousePressed

    private void txtTimKiem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiem1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiem1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txtMotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMotaActionPerformed
        // TODO add your handling code here:
        txtMota.addActionListener((ActionEvent e) -> {
            txtLoaisp.requestFocus(); // sử dụng enter 
        });
    }//GEN-LAST:event_txtMotaActionPerformed

    private void lblAnhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAnhMouseClicked
        // TODO add your handling code here:
        String hinh = ChonHinh();
        lblAnh.setText(hinh);
        UploaHinh(hinh);
    }//GEN-LAST:event_lblAnhMouseClicked

    private void txtSoLuongKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSoLuongKeyPressed
        // TODO add your handling code here:
         char c = evt.getKeyChar();//lấy kí tự của ngta nhấn
        if (Character.isLetter(c)) //ktra kí tự chữ,chặn ký tự chữ isLetter
            evt.consume();
    }//GEN-LAST:event_txtSoLuongKeyPressed

    private void txtgiaBanKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtgiaBanKeyTyped
        // TODO add your handling code here:
         char c = evt.getKeyChar();//lấy kí tự của ngta nhấn
        if (Character.isLetter(c)) //ktra kí tự chữ,chặn ký tự chữ isLetter
            evt.consume();
    }//GEN-LAST:event_txtgiaBanKeyTyped

    private void txtMaSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaSPActionPerformed
        // TODO add your handling code here:
        txtMaSP.addActionListener((ActionEvent e) -> {
            txtTenSP.requestFocus(); // sử dụng enter 
        });
    }//GEN-LAST:event_txtMaSPActionPerformed

    private void txtTenSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenSPActionPerformed
        // TODO add your handling code here:
        txtTenSP.addActionListener((ActionEvent e) -> {
            txtSoLuong.requestFocus(); // sử dụng enter 
        });
    }//GEN-LAST:event_txtTenSPActionPerformed

    private void txtSoLuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSoLuongActionPerformed
        // TODO add your handling code here:
        txtSoLuong.addActionListener((ActionEvent e) -> {
            txtgiaBan.requestFocus(); // sử dụng enter 
        });
    }//GEN-LAST:event_txtSoLuongActionPerformed

    private void txtgiaBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtgiaBanActionPerformed
        // TODO add your handling code here:
        txtgiaBan.addActionListener((ActionEvent e) -> {
            txtMota.requestFocus(); // sử dụng enter 
        });
    }//GEN-LAST:event_txtgiaBanActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SanPhamJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SanPhamJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SanPhamJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SanPhamJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                SanPhamJDialog dialog = new SanPhamJDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane Tabs;
    private javax.swing.JButton btnFirst;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnLuu;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrev;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAnh;
    private javax.swing.JTable tblkhoSanPham;
    private javax.swing.JTextField txtLoaisp;
    private javax.swing.JTextField txtMaSP;
    private javax.swing.JTextField txtMota;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTenSP;
    private javax.swing.JTextField txtTimKiem1;
    private javax.swing.JTextField txtgiaBan;
    // End of variables declaration//GEN-END:variables

}
