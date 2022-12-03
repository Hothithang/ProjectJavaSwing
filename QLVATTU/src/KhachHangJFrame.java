
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author MSI-OFFICE
 */
public class KhachHangJFrame extends javax.swing.JFrame {

    /**
     * Creates new form KhachHangJFrame
     */ 
    public KhachHangJFrame() {
        initComponents();
        ganThongTin();
    }
    
    public KhachHangJFrame(String admin) {
        initComponents();
        ganDuLieu();
    }
   
    boolean blnThem = false;
    boolean blnSua = false;
    String vaitro = TrangChuJFrame.jLabelVaiTro.getText();
    
    public void ganThongTin() {
        jTextFieldTKNoiDung.setVisible(false);
        jButtonTimKiem.setVisible(false);
        String tendangnhapkh = TrangChuJFrame.jLabelTenDangNhap.getText();
        CallableStatement stmt = null;
        Connection conn = null;
        
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/qlvattu","root","Thang123!");
            stmt = conn.prepareCall("{CALL HIENTHI_KHACHHANG(?)}");
            stmt.setString(1, tendangnhapkh);
            stmt.executeUpdate();
            ResultSet resultSet = stmt.executeQuery();
            
            if(resultSet.next()) {
                jScrollPaneTable.setVisible(false);
                
                String tendangnhap = String.valueOf(resultSet.getString("username"));
                String matkhau = String.valueOf(resultSet.getString("password"));
                String tenkh = String.valueOf(resultSet.getString("tenkh"));
                String diachi = String.valueOf(resultSet.getString("diachi"));
                String dienthoai = String.valueOf(resultSet.getString("dt"));
                String email = String.valueOf(resultSet.getString("email"));
                
                jTextFieldTenDangNhap.setText(tendangnhap);
                jTextFieldTenDangNhap.setEditable(false);
                jPasswordFieldMatKhau.setText(matkhau);
                jPasswordFieldMatKhau.setEditable(false);
                jTextFieldTen.setText(tenkh);
                jTextFieldTen.setEditable(false);
                jTextFieldDiaChi.setText(diachi);
                jTextFieldDiaChi.setEditable(false);
                jTextFieldDienThoai.setText(dienthoai);
                jTextFieldDienThoai.setEditable(false);
                jTextFieldEmail.setText(email);
                jTextFieldEmail.setEditable(false);
            } else {
                jButtonSua.setEnabled(false);
                jButtonThem.setVisible(false);
                jTextFieldTenDangNhap.setText("");
                jPasswordFieldMatKhau.setText("");
                jTextFieldTen.setText("");
                jTextFieldDiaChi.setText("");
                jTextFieldDienThoai.setText("");
                jTextFieldEmail.setText("");
                
            }
            
            jButtonThem.setVisible(false);
            jButtonXoa.setVisible(false);
            jButtonLuu.setEnabled(false);
            jButtonKhongLuu.setEnabled(false);
            jButtonXoa.setEnabled(false);
                
        } catch (SQLException ex) {
            String msg = "MyModel(): Error Connecting to Database:\n" + ex.getMessage();
            System.out.println(msg);
        } 
    }
    
    
    public void ganDuLieu() {
        PreparedStatement pst = null;
        Connection conn = null;
        
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/qlvattu","root","Thang123!");
            String sql = "SELECT * FROM KHACHHANG";
            pst = conn.prepareStatement(sql);
            ResultSet resultSet = pst.executeQuery();
            jTableKhachHang.setModel(DbUtils.resultSetToTableModel(resultSet));
            
            if(jTableKhachHang.getRowCount() > 0) {
                DefaultTableModel tblModel = (DefaultTableModel)jTableKhachHang.getModel();
                String tendangnhap = tblModel.getValueAt(0,1).toString();
                String matkhau = tblModel.getValueAt(0,2).toString();
                String tenkh = tblModel.getValueAt(0,3).toString();
                String diachi = tblModel.getValueAt(0,4).toString();
                String dienthoai = tblModel.getValueAt(0,5).toString();
                String email = tblModel.getValueAt(0,6).toString();
                
                jTableKhachHang.getColumnModel().getColumn(2).setMinWidth(0);
                jTableKhachHang.getColumnModel().getColumn(2).setMaxWidth(0);
                jTextFieldTenDangNhap.setText(tendangnhap);
                jTextFieldTenDangNhap.setEditable(false);
                jPasswordFieldMatKhau.setText(matkhau);
                jPasswordFieldMatKhau.setEditable(false);
                jTextFieldTen.setText(tenkh);
                jTextFieldTen.setEditable(false);
                jTextFieldDiaChi.setText(diachi);
                jTextFieldDiaChi.setEditable(false);
                jTextFieldDienThoai.setText(dienthoai);
                jTextFieldDienThoai.setEditable(false);
                jTextFieldEmail.setText(email);
                jTextFieldEmail.setEditable(false);
            } else {
                jButtonSua.setEnabled(false);
                jButtonXoa.setEnabled(false);
                jTextFieldTen.setText("");
                jTextFieldDiaChi.setText("");
                jTextFieldDienThoai.setText("");
                jTextFieldEmail.setText("");
                
            }
            jButtonThem.setEnabled(true);
            jButtonLuu.setEnabled(false);
            jButtonKhongLuu.setEnabled(false);
            jButtonXoa.setEnabled(false);
                
        } catch (SQLException ex) {
            String msg = "MyModel(): Error Connecting to Database:\n" + ex.getMessage();
            System.out.println(msg);
        } 
    }
    

    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPane = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jTextFieldTen = new javax.swing.JTextField();
        jTextFieldDiaChi = new javax.swing.JTextField();
        jTextFieldDienThoai = new javax.swing.JTextField();
        jTextFieldEmail = new javax.swing.JTextField();
        jScrollPaneTable = new javax.swing.JScrollPane();
        jTableKhachHang = new javax.swing.JTable();
        jButtonThem = new javax.swing.JButton();
        jButtonSua = new javax.swing.JButton();
        jButtonLuu = new javax.swing.JButton();
        jButtonXoa = new javax.swing.JButton();
        jButtonDongForm = new javax.swing.JButton();
        jButtonKhongLuu = new javax.swing.JButton();
        jTextFieldTenDangNhap = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPasswordFieldMatKhau = new javax.swing.JPasswordField();
        jTextFieldTKNoiDung = new javax.swing.JTextField();
        jButtonTimKiem = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPane.setBackground(new java.awt.Color(204, 255, 255));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 204, 102));
        jLabel1.setText("KHÁCH HÀNG");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Tên khách hàng:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Địa chỉ:");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("Điện thoại:");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setText("Email:");

        jTextFieldTen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldTenActionPerformed(evt);
            }
        });

        jTextFieldDienThoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldDienThoaiActionPerformed(evt);
            }
        });

        jTextFieldEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldEmailActionPerformed(evt);
            }
        });

        jTableKhachHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã Khách Hàng", "Tên Đăng Nhập", "Mật Khẩu", "Tên Khách Hàng", "Địa Chỉ", "Số Điện Thoại", "Email"
            }
        ));
        jTableKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableKhachHangMouseClicked(evt);
            }
        });
        jScrollPaneTable.setViewportView(jTableKhachHang);

        jButtonThem.setBackground(new java.awt.Color(229, 229, 229));
        jButtonThem.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButtonThem.setForeground(new java.awt.Color(255, 0, 0));
        jButtonThem.setText("Thêm");
        jButtonThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonThemActionPerformed(evt);
            }
        });

        jButtonSua.setBackground(new java.awt.Color(229, 229, 229));
        jButtonSua.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButtonSua.setForeground(new java.awt.Color(255, 51, 0));
        jButtonSua.setText("Chỉnh Sửa");
        jButtonSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSuaActionPerformed(evt);
            }
        });

        jButtonLuu.setBackground(new java.awt.Color(229, 229, 229));
        jButtonLuu.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButtonLuu.setForeground(new java.awt.Color(0, 204, 204));
        jButtonLuu.setText("Lưu");
        jButtonLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLuuActionPerformed(evt);
            }
        });

        jButtonXoa.setBackground(new java.awt.Color(229, 229, 229));
        jButtonXoa.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButtonXoa.setForeground(new java.awt.Color(204, 51, 0));
        jButtonXoa.setText("Xóa");
        jButtonXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonXoaActionPerformed(evt);
            }
        });

        jButtonDongForm.setBackground(new java.awt.Color(229, 229, 229));
        jButtonDongForm.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButtonDongForm.setForeground(new java.awt.Color(204, 51, 0));
        jButtonDongForm.setText("Đóng Form");
        jButtonDongForm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDongFormActionPerformed(evt);
            }
        });

        jButtonKhongLuu.setBackground(new java.awt.Color(229, 229, 229));
        jButtonKhongLuu.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButtonKhongLuu.setForeground(new java.awt.Color(0, 204, 204));
        jButtonKhongLuu.setText("Không Lưu");
        jButtonKhongLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonKhongLuuActionPerformed(evt);
            }
        });

        jTextFieldTenDangNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldTenDangNhapActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Tên đăng nhập:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Mật khẩu:");

        jTextFieldTKNoiDung.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jTextFieldTKNoiDung.setText("Nhập tên khách hàng");
        jTextFieldTKNoiDung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldTKNoiDungActionPerformed(evt);
            }
        });

        jButtonTimKiem.setText("Tìm Kiếm");
        jButtonTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTimKiemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPaneLayout = new javax.swing.GroupLayout(jPane);
        jPane.setLayout(jPaneLayout);
        jPaneLayout.setHorizontalGroup(
            jPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPaneLayout.createSequentialGroup()
                .addGroup(jPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPaneLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPaneTable))
                    .addGroup(jPaneLayout.createSequentialGroup()
                        .addGroup(jPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPaneLayout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addComponent(jButtonThem, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButtonSua, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButtonLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButtonKhongLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButtonXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButtonDongForm, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPaneLayout.createSequentialGroup()
                                .addGap(298, 298, 298)
                                .addComponent(jLabel1))
                            .addGroup(jPaneLayout.createSequentialGroup()
                                .addGroup(jPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPaneLayout.createSequentialGroup()
                                        .addGap(76, 76, 76)
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jTextFieldTenDangNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPaneLayout.createSequentialGroup()
                                        .addGap(78, 78, 78)
                                        .addGroup(jPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jTextFieldDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextFieldTen, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(53, 53, 53)
                                .addGroup(jPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPaneLayout.createSequentialGroup()
                                        .addComponent(jLabel9)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jTextFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPaneLayout.createSequentialGroup()
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextFieldDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPaneLayout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jPasswordFieldMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jTextFieldTKNoiDung))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButtonTimKiem)))
                        .addGap(0, 14, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPaneLayout.setVerticalGroup(
            jPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPaneLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addGroup(jPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldTKNoiDung, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(jPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldTenDangNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jPasswordFieldMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(28, 28, 28)
                .addGroup(jPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldTen, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                    .addComponent(jLabel3)
                    .addComponent(jTextFieldDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(30, 30, 30)
                .addGroup(jPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel9))
                .addGap(40, 40, 40)
                .addComponent(jScrollPaneTable, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addGroup(jPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonThem, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonSua, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonDongForm, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonKhongLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35))
        );

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonKhongLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonKhongLuuActionPerformed
        // TODO add your handling code here:
        if(vaitro.equals("Quản trị viên")) {
            ganDuLieu();
        } else {
            ganThongTin();
        }

        jButtonThem.setEnabled(true);
        jButtonSua.setEnabled(true);
    }//GEN-LAST:event_jButtonKhongLuuActionPerformed

    private void jButtonDongFormActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDongFormActionPerformed
        // TODO add your handling code here:
        dispose();
        this.toBack();
    }//GEN-LAST:event_jButtonDongFormActionPerformed

    private void jButtonXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonXoaActionPerformed
        // TODO add your handling code here:
        PreparedStatement pst = null;
        PreparedStatement pst1 = null;
        Connection conn = null;

        DefaultTableModel tblModel = (DefaultTableModel)jTableKhachHang.getModel();

        String makh = tblModel.getValueAt(jTableKhachHang.getSelectedRow(), 0).toString();

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/qlvattu","root","Thang123!");
            String sql1 = "SELECT * FROM KHACHHANG WHERE MAKH = ?";
            pst1 = conn.prepareStatement (sql1);
            pst1.setString(1, makh);
            ResultSet resultSet1 = pst1.executeQuery();
            
            if(resultSet1.next() ) {
                conn = DriverManager.getConnection("jdbc:mysql://localhost/qlvattu","root","Thang123!");
                String sql = "DELETE FROM KHACHHANG WHERE MAKH = ?";

                pst = conn.prepareStatement (sql);
                pst.setString(1, makh);

                int respone = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa khách hàng " + makh + " cùng tất cả các hóa đơn có liên quan?", "Xác nhận", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if(respone == JOptionPane.YES_OPTION) {
                    pst.executeUpdate();
                    ganDuLieu();
                    JOptionPane.showMessageDialog(null, "Khách hàng và tất cả các hóa đơn đã được xóa thành công!");
                }
            }  else {
                ganDuLieu();
                JOptionPane.showMessageDialog(null, "Không tìm thấy khách hàng "+makh+"!");
            }

        } catch (SQLException ex) {
            String msg = "MyModel():Lỗi kết nối cơ sở dữ liệu:\n" + ex.getMessage();
            System.out.println(msg);
        }

    }//GEN-LAST:event_jButtonXoaActionPerformed

    private void jButtonLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLuuActionPerformed
        // TODO add your handling code here:

        String tendangnhap = jTextFieldTenDangNhap.getText();
        String matkhau = new String(jPasswordFieldMatKhau.getPassword());
        String tenkh = jTextFieldTen.getText();
        String diachi = jTextFieldDiaChi.getText();
        String dienthoai = jTextFieldDienThoai.getText();
        String email = jTextFieldEmail.getText();
        if (tendangnhap.equals("") || matkhau.equals("") || tenkh.equals("") || diachi.equals("") || dienthoai.equals("") || email.equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Trường không được bỏ trống", "Lỗi", 1);
        } else {

            PreparedStatement pst = null;
            Connection conn = null;
            if(blnThem) {
                try {
                    conn = DriverManager.getConnection("jdbc:mysql://localhost/qlvattu","root","Thang123!");
                    String sql = "INSERT KHACHHANG(USERNAME,PASSWORD,TENKH,DIACHI,DT,EMAIL) VALUES(?,?,?,?,?,?)";

                    pst = conn.prepareStatement(sql);
                    pst.setString(1, tendangnhap);
                    pst.setString(2, matkhau);
                    pst.setString(3, tenkh);
                    pst.setString(4, diachi);
                    pst.setString(5, dienthoai);
                    pst.setString(6, email);

                    int resultSet = pst.executeUpdate();

                    if(resultSet>=1) {
                        ganDuLieu();
                        JOptionPane.showMessageDialog(null, "Khách hàng được thêm thành công!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Không thể thêm khách hàng mới!");
                    }

                }  catch (SQLException ex) {
                        String msg = ex.getMessage();
                        JOptionPane.showMessageDialog(null, msg);
                } 
            } else if(blnSua) {

                try {
                    conn = DriverManager.getConnection("jdbc:mysql://localhost/qlvattu","root","Thang123!");
                    String sql = "UPDATE KHACHHANG SET TENKH=?, DIACHI=?, DT=?, EMAIL=? where USERNAME='"+tendangnhap+"'";

                    pst = conn.prepareStatement(sql);
                    pst.setString(1, tenkh);
                    pst.setString(2, diachi);
                    pst.setString(3, dienthoai);
                    pst.setString(4, email);

                    int resultSet = pst.executeUpdate();

                    if(resultSet>=1) {
                        JOptionPane.showMessageDialog(null, "Khách hàng được cật nhật thành công!");
                        if(vaitro.equals("Quản trị viên")) {
                            ganDuLieu();
                        } else {
                            ganThongTin();
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Không thể cập nhật khách hàng" + tenkh);
                    }

                }  catch (SQLException ex) {
                        String msg = ex.getMessage();
                        JOptionPane.showMessageDialog(null, msg);
                } 

            }

        }
    }//GEN-LAST:event_jButtonLuuActionPerformed

    private void jButtonSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSuaActionPerformed
        // TODO add your handling code here:
        blnThem = false;
        blnSua = true;
        jTextFieldTenDangNhap.setEditable(false);
        jPasswordFieldMatKhau.setEditable(false);
        jTextFieldTen.requestFocus();
        jTextFieldTen.setEditable(true);
        jTextFieldDiaChi.setEditable(true);
        jTextFieldDienThoai.setEditable(true);
        jTextFieldEmail.setEditable(true);

        jButtonThem.setEnabled(false);
        jButtonSua.setEnabled(false);
        jButtonXoa.setEnabled(false);
        jButtonLuu.setEnabled(true);
        jButtonKhongLuu.setEnabled(true);
    }//GEN-LAST:event_jButtonSuaActionPerformed

    private void jButtonThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonThemActionPerformed
        // TODO add your handling code here:
        blnThem = true;

        jTextFieldTenDangNhap.setText("");
        jTextFieldTenDangNhap.setEditable(true);
        jTextFieldTenDangNhap.requestFocus();
        jTextFieldTen.setText("");
        jTextFieldTen.setEditable(true);
        jPasswordFieldMatKhau.setText("");
        jPasswordFieldMatKhau.setEditable(true);
        jTextFieldDiaChi.setText("");
        jTextFieldDiaChi.setEditable(true);
        jTextFieldDienThoai.setText("");
        jTextFieldDienThoai.setEditable(true);
        jTextFieldEmail.setText("");
        jTextFieldEmail.setEditable(true);

        jButtonThem.setEnabled(false);
        jButtonSua.setEnabled(false);
        jButtonXoa.setEnabled(false);
        jButtonLuu.setEnabled(true);
        jButtonKhongLuu.setEnabled(true);
    }//GEN-LAST:event_jButtonThemActionPerformed

    private void jTableKhachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableKhachHangMouseClicked
        // TODO add your handling code here:
        DefaultTableModel tblModel = (DefaultTableModel)jTableKhachHang.getModel();

        String makh = tblModel.getValueAt(jTableKhachHang.getSelectedRow(), 0).toString();
        String tendangnhap = tblModel.getValueAt(jTableKhachHang.getSelectedRow(), 1).toString();
        String matkhau = tblModel.getValueAt(jTableKhachHang.getSelectedRow(), 2).toString();
        String tenkh = tblModel.getValueAt(jTableKhachHang.getSelectedRow(), 3).toString();
        String diachi = tblModel.getValueAt(jTableKhachHang.getSelectedRow(), 4).toString();
        String dienthoai = tblModel.getValueAt(jTableKhachHang.getSelectedRow(), 5).toString();
        String email = tblModel.getValueAt(jTableKhachHang.getSelectedRow(), 6).toString();
 
        jTextFieldTenDangNhap.setText(tendangnhap);
        jTextFieldTenDangNhap.setEditable(false);
        jPasswordFieldMatKhau.setText(matkhau);
        jPasswordFieldMatKhau.setEditable(false);
        jTextFieldTen.setText(tenkh);
        jTextFieldTen.setEditable(false);
        jTextFieldDiaChi.setText(diachi);
        jTextFieldDiaChi.setEditable(false);
        jTextFieldDienThoai.setText(dienthoai);
        jTextFieldDienThoai.setEditable(false);
        jTextFieldEmail.setText(email);
        jTextFieldEmail.setEditable(false);

        jButtonXoa.setEnabled(true);
        jButtonSua.setEnabled(true);

    }//GEN-LAST:event_jTableKhachHangMouseClicked

    private void jTextFieldEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldEmailActionPerformed

    private void jTextFieldDienThoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldDienThoaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldDienThoaiActionPerformed

    private void jTextFieldTenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldTenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldTenActionPerformed

    private void jTextFieldTenDangNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldTenDangNhapActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldTenDangNhapActionPerformed

    private void jTextFieldTKNoiDungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldTKNoiDungActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldTKNoiDungActionPerformed

    private void jButtonTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTimKiemActionPerformed
        // TODO add your handling code here:
        String tenkh = jTextFieldTKNoiDung.getText();
        PreparedStatement pst = null;
        Connection conn = null;
        
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/qlvattu","root","Thang123!");
            String sql = "SELECT * FROM KHACHHANG WHERE TENKH LIKE '%"+tenkh+"%'";
            pst = conn.prepareStatement(sql);
            ResultSet resultSet = pst.executeQuery();
            jTableKhachHang.setModel(DbUtils.resultSetToTableModel(resultSet));
            
            if(jTableKhachHang.getRowCount() > 0) {
                jTextFieldTenDangNhap.setText("");
                jPasswordFieldMatKhau.setText("");
                jTextFieldTen.setText("");
                jTextFieldDiaChi.setText("");
                jTextFieldDienThoai.setText("");
                jTextFieldEmail.setText("");
            } else {
                if(!resultSet.next()) {
                    JOptionPane.showMessageDialog(null, "Tên khách hàng không tìm thấy");
                    jTextFieldTKNoiDung.setText("");
                    ganDuLieu();
                }
            }
            
                
        } catch (SQLException ex) {
            String msg = "MyModel(): Error Connecting to Database:\n" + ex.getMessage();
            System.out.println(msg);
        } 
        
    }//GEN-LAST:event_jButtonTimKiemActionPerformed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(KhachHangJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(KhachHangJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(KhachHangJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(KhachHangJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new KhachHangJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonDongForm;
    private javax.swing.JButton jButtonKhongLuu;
    private javax.swing.JButton jButtonLuu;
    private javax.swing.JButton jButtonSua;
    private javax.swing.JButton jButtonThem;
    private javax.swing.JButton jButtonTimKiem;
    private javax.swing.JButton jButtonXoa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPane;
    private javax.swing.JPasswordField jPasswordFieldMatKhau;
    private javax.swing.JScrollPane jScrollPaneTable;
    private javax.swing.JTable jTableKhachHang;
    private javax.swing.JTextField jTextFieldDiaChi;
    private javax.swing.JTextField jTextFieldDienThoai;
    private javax.swing.JTextField jTextFieldEmail;
    private javax.swing.JTextField jTextFieldTKNoiDung;
    private javax.swing.JTextField jTextFieldTen;
    public static javax.swing.JTextField jTextFieldTenDangNhap;
    // End of variables declaration//GEN-END:variables
}
