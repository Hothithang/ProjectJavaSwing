
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;
import java.sql.CallableStatement;
import java.sql.Types;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author MSI-OFFICE
 */
public class HoaDonJFrame extends javax.swing.JFrame {

    /**
     * Creates new form HoaDOnJFrame
     */ 
    public HoaDonJFrame() {
        initComponents();
        ganThongTin();
    }
    
    public HoaDonJFrame(String admin) {
        initComponents();
        ganDuLieu();
    }

    protected MySQLConnect c = new MySQLConnect();
    protected SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

    protected String msg_error_setdate = "Co loi xay ra khi them ngay thang.";
    
    boolean blnThemHD = false;
    boolean blnThemCTHD = false;
    boolean blnXoaHD = false;
    boolean blnXoaCTHD = false;
    boolean blnSuaHD = false;
    boolean blnSuaCTHD = false;
    
    public void ganThongTin() {
        String tendangnhapkh = TrangChuJFrame.jLabelTenDangNhap.getText();
        CallableStatement stmt = null;
        PreparedStatement pst1 = null;
        Connection conn = null;
        
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/qlvattu","root","Thang123!");
            stmt = conn.prepareCall("{CALL KHACHHANG_HOADON(?)}");
            stmt.setString(1, tendangnhapkh);
            stmt.executeUpdate();
            ResultSet resultSet = stmt.executeQuery();
            jTableHoaDon.setModel(DbUtils.resultSetToTableModel(resultSet));
            
            DefaultTableModel tblModel1 = (DefaultTableModel) jTableHoaDon.getModel();
            String mahd = tblModel1.getValueAt(0, 0).toString();
            
            try{
                    String sql1 = "SELECT * FROM CHITIETHOADON WHERE MAHD='"+(String)mahd+"';";
                    pst1 = conn.prepareStatement(sql1);
                    ResultSet resultSet1 = pst1.executeQuery();

                    jTableCTHoaDon.setModel(DbUtils.resultSetToTableModel(resultSet1));

                } catch (SQLException e){
                  System.out.println("Co loi xay ra khi lay CTHD: "+ e);
                }

            jTextFieldMaKH.setVisible(false);
            jDateChooserNgay.setVisible(false);
            jTextFieldMaHD.setVisible(false);
            jTextFieldTGT.setVisible(false);
            jLabel2.setVisible(false);
            jLabel4.setVisible(false);
            jLabel7.setVisible(false);
            jLabel8.setVisible(false);
            jTextFieldMaCTHD.setVisible(false);
            jTextFieldMaVT.setVisible(false);
            jTextFieldSL.setVisible(false);
            jTextFieldGiaBan.setVisible(false);
            jTextFieldThanhTien.setVisible(false);
            jLabel5.setVisible(false);
            jLabel6.setVisible(false);
            jLabel9.setVisible(false);
            jLabel11.setVisible(false);
            jLabel12.setVisible(false);
            jButtonThemHD.setVisible(false);
            jButtonThemCTHD.setVisible(false);
            jButtonSua.setVisible(false);
            jButtonLuu.setVisible(false);
            jButtonKhongLuu.setVisible(false);
            jButtonXoa.setVisible(false);
        } catch (SQLException ex) {
            String msg = "MyModel(): Error Connecting to Database:\n" + ex.getMessage();
            System.out.println(msg);
        } 
    }
    
    public void ganDuLieu() {
        jButtonXem.setVisible(false);
        
        jButtonXem.setVisible(false);
        
        CallableStatement cstmt = null;
        PreparedStatement pst = null;
        PreparedStatement pst1 = null;
        Connection conn = null;
    
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/qlvattu","root","Thang123!");
            String sql = "SELECT * FROM HOADON";
            pst = conn.prepareStatement(sql);
            ResultSet resultSet = pst.executeQuery();

            jTableHoaDon.setModel(DbUtils.resultSetToTableModel(resultSet));
            

            if (jTableHoaDon.getRowCount() > 0) {
                DefaultTableModel tblModel = (DefaultTableModel) jTableHoaDon.getModel();
                String mahd = tblModel.getValueAt(0, 0).toString();
                
                Date ngay;
                try {
                  ngay = sdf.parse((String) tblModel.getValueAt(0, 1).toString());
                  jDateChooserNgay.setDate(ngay);
                } catch (Exception ex) {
                  System.out.println(msg_error_setdate + ex);
                }
                
                String makh = tblModel.getValueAt(0, 2).toString();
               
                cstmt = conn.prepareCall("{? = call TGT_HOADON(?)}");
                cstmt.registerOutParameter(1, Types.INTEGER);
                cstmt.setString(2, mahd);
                cstmt.execute();
                jTextFieldTGT.setText(Integer.toString(cstmt.getInt(1)));
                
                jTextFieldMaHD.setText(mahd);
                jTextFieldMaHD.setEditable(false);
                jTextFieldMaKH.setText(makh);
                jTextFieldMaKH.setEditable(false);
                
                try{
                    String sql1 = "SELECT * FROM CHITIETHOADON WHERE MAHD='"+(String)mahd+"'";
                    pst1 = conn.prepareStatement(sql1);
                    ResultSet resultSet1 = pst1.executeQuery();

                    jTableCTHoaDon.setModel(DbUtils.resultSetToTableModel(resultSet1));

                    DefaultTableModel tblModel1 = (DefaultTableModel) jTableCTHoaDon.getModel();
                    String mavt = tblModel1.getValueAt(0, 1).toString();
                    
                    cstmt = conn.prepareCall("{? = call TT_HOADON(?,?)}");
                    cstmt.registerOutParameter(1, Types.INTEGER);
                    cstmt.setString(2, mahd);
                    cstmt.setString(3, mavt);
                    cstmt.execute();
                    jTextFieldMaCTHD.setText(mahd);
                    jTextFieldMaVT.setText(tblModel1.getValueAt(0, 1).toString());
                    jTextFieldGiaBan.setText(tblModel1.getValueAt(0, 2).toString());
                    jTextFieldSL.setText(tblModel1.getValueAt(0, 3).toString());
                    jTextFieldThanhTien.setText(Integer.toString(cstmt.getInt(1)));

                } catch (SQLException e){
                  System.out.println("Co loi xay ra khi lay CTHD: "+ e);
                }

                jTextFieldMaCTHD.setText(mahd);

            } else {
                jTextFieldMaHD.setText("");
                jTextFieldMaHD.setEditable(false);
                jDateChooserNgay.setDate(new Date());
                jDateChooserNgay.setEnabled(false);
                jTextFieldMaKH.setText("");
                jTextFieldMaKH.setEditable(false);
            }
            jButtonThemHD.setEnabled(true);
            jButtonThemCTHD.setEnabled(false);
            jButtonSua.setEnabled(false);
            jButtonSua.setEnabled(false);
            jButtonXoa.setEnabled(false);
            jButtonLuu.setEnabled(false);
            jButtonKhongLuu.setEnabled(false);

        } catch (SQLException ex) {
            String msg = "MyModel(): Error Connecting to Database:\n" + ex.getMessage();
            System.out.println(msg);
        } 
    }
    
    public boolean kiemTraKhoaChinh(String table)
    {
        PreparedStatement pst = null;
        MySQLConnect c = new MySQLConnect();
        Connection conn = null;
        
        
        if(table.equalsIgnoreCase("HD")){
          
          try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/qlvattu","root","Thang123!");
            String mahd = jTextFieldMaHD.getText();
            String sql = "Select * From HOADON Where MAHD= ?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, mahd);
            ResultSet resultSet = pst.executeQuery();
            if(resultSet.next()) {
                String s1 = resultSet.getString("MAHD");
                if (s1.equalsIgnoreCase(mahd)) {
                    JOptionPane.showMessageDialog(null, "Mã hóa đơn đã tồn tại!");
                    return false;
                }
            }

            String makh = jTextFieldMaKH.getText();
            sql = "Select * From KHACHHANG Where MAKH= ?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, makh);
            resultSet = pst.executeQuery();
            if(!resultSet.next()) {
                JOptionPane.showMessageDialog(null, "Mã khách hàng không tồn tại");
                return false;
            }
          } catch(SQLException ex) {
                System.out.println("Loi: ket noi CSDL tai ham kiemTraKhoaChinh()"+ex);
                return false;
          }
          return true;

        } else if(table.equalsIgnoreCase("CTHD")){
            
            try{
                conn = DriverManager.getConnection("jdbc:mysql://localhost/qlvattu","root","Thang123!");
                String mavt = jTextFieldMaVT.getText();
                String sql = "Select * From VATTU where MAVT= ?";
                pst = conn.prepareStatement(sql);

                pst.setString(1, mavt);
                ResultSet resultSet = pst.executeQuery();
                if(!resultSet.next()) {
                    JOptionPane.showMessageDialog(null, "Mã vật tư không tồn tại!");
                    return false;
                }
            }catch( SQLException e){
                System.out.println("Loi: ket noi CSDL tai ham kiemTraKhoaChinh()"+e);
                return false;
            }
            return true;
        } else {
          JOptionPane.showMessageDialog(null, "Code lỗi: hàm kiemTraKhoaChinh()");
          return false;
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
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTextFieldMaKH = new javax.swing.JTextField();
        jDateChooserNgay = new com.toedter.calendar.JDateChooser();
        jTextFieldMaHD = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextFieldTGT = new javax.swing.JTextField();
        jScrollPaneHoaDon = new javax.swing.JScrollPane();
        jTableHoaDon = new javax.swing.JTable();
        jButtonThemHD = new javax.swing.JButton();
        jButtonDongForm = new javax.swing.JButton();
        jButtonXem = new javax.swing.JButton();
        jScrollPaneCTHoaDon = new javax.swing.JScrollPane();
        jTableCTHoaDon = new javax.swing.JTable();
        jTextFieldMaCTHD = new javax.swing.JTextField();
        jTextFieldMaVT = new javax.swing.JTextField();
        jTextFieldGiaBan = new javax.swing.JTextField();
        jTextFieldSL = new javax.swing.JTextField();
        jTextFieldThanhTien = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jButtonThemCTHD = new javax.swing.JButton();
        jButtonSua = new javax.swing.JButton();
        jButtonLuu = new javax.swing.JButton();
        jButtonKhongLuu = new javax.swing.JButton();
        jButtonXoa = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPane.setBackground(new java.awt.Color(204, 255, 255));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 204, 102));
        jLabel1.setText("HÓA ĐƠN");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Mã khách hàng:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Mã hóa đơn:");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("Ngày mua:");

        jTextFieldMaKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldMaKHActionPerformed(evt);
            }
        });

        jTextFieldMaHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldMaHDActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setText("Tổng giá trị:");

        jTextFieldTGT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldTGTActionPerformed(evt);
            }
        });

        jTableHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Tên Đăng Nhập", "Tên Khách Hàng", "Mã Hóa Đơn", "Ngày Mua", "Tổng Giá Trị"
            }
        ));
        jTableHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableHoaDonMouseClicked(evt);
            }
        });
        jScrollPaneHoaDon.setViewportView(jTableHoaDon);

        jButtonThemHD.setBackground(new java.awt.Color(236, 236, 236));
        jButtonThemHD.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButtonThemHD.setForeground(new java.awt.Color(0, 204, 102));
        jButtonThemHD.setText("Thêm HD");
        jButtonThemHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonThemHDActionPerformed(evt);
            }
        });

        jButtonDongForm.setBackground(new java.awt.Color(236, 236, 236));
        jButtonDongForm.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButtonDongForm.setForeground(new java.awt.Color(204, 51, 0));
        jButtonDongForm.setText("Đóng Form");
        jButtonDongForm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDongFormActionPerformed(evt);
            }
        });

        jButtonXem.setText("Xem Chi Tiết");
        jButtonXem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonXemActionPerformed(evt);
            }
        });

        jTableCTHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Tên Đăng Nhập", "Tên Khách Hàng", "Mã Hóa Đơn", "Ngày Mua", "Tên Vật Tư", "Đơn Vị Tính", "Giá Bán", "Số Lượng", "Thành Tiền"
            }
        ));
        jTableCTHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableCTHoaDonMouseClicked(evt);
            }
        });
        jScrollPaneCTHoaDon.setViewportView(jTableCTHoaDon);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Mã hóa đơn:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Giá bán:");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setText("Mã vật tư:");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setText("Số lượng:");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel12.setText("Thành tiền:");

        jButtonThemCTHD.setBackground(new java.awt.Color(236, 236, 236));
        jButtonThemCTHD.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButtonThemCTHD.setForeground(new java.awt.Color(0, 204, 102));
        jButtonThemCTHD.setText("Thêm CTHD");
        jButtonThemCTHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonThemCTHDActionPerformed(evt);
            }
        });

        jButtonSua.setBackground(new java.awt.Color(236, 236, 236));
        jButtonSua.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButtonSua.setForeground(new java.awt.Color(255, 51, 0));
        jButtonSua.setText("Chỉnh Sửa");
        jButtonSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSuaActionPerformed(evt);
            }
        });

        jButtonLuu.setBackground(new java.awt.Color(236, 236, 236));
        jButtonLuu.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButtonLuu.setForeground(new java.awt.Color(0, 204, 204));
        jButtonLuu.setText("Lưu");
        jButtonLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLuuActionPerformed(evt);
            }
        });

        jButtonKhongLuu.setBackground(new java.awt.Color(236, 236, 236));
        jButtonKhongLuu.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButtonKhongLuu.setForeground(new java.awt.Color(0, 204, 204));
        jButtonKhongLuu.setText("Không Lưu");
        jButtonKhongLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonKhongLuuActionPerformed(evt);
            }
        });

        jButtonXoa.setBackground(new java.awt.Color(236, 236, 236));
        jButtonXoa.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButtonXoa.setForeground(new java.awt.Color(204, 51, 0));
        jButtonXoa.setText("Xóa");
        jButtonXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonXoaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPaneLayout = new javax.swing.GroupLayout(jPane);
        jPane.setLayout(jPaneLayout);
        jPaneLayout.setHorizontalGroup(
            jPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPaneLayout.createSequentialGroup()
                .addGap(156, 156, 156)
                .addGroup(jPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPaneLayout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(jDateChooserNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPaneLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextFieldMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(36, 36, 36)
                .addGroup(jPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldTGT, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPaneLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(jPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPaneLayout.createSequentialGroup()
                        .addGroup(jPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPaneLayout.createSequentialGroup()
                                .addGap(279, 279, 279)
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldSL, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPaneLayout.createSequentialGroup()
                                .addGroup(jPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(jPaneLayout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jTextFieldGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPaneLayout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jTextFieldMaCTHD, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(23, 23, 23)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldMaVT, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextFieldThanhTien, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPaneHoaDon, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(234, 234, 234))
            .addGroup(jPaneLayout.createSequentialGroup()
                .addGroup(jPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPaneLayout.createSequentialGroup()
                        .addComponent(jButtonXem)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonThemHD, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonThemCTHD, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonSua, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonKhongLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonDongForm, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPaneLayout.createSequentialGroup()
                            .addGap(373, 373, 373)
                            .addComponent(jLabel1))
                        .addGroup(jPaneLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jScrollPaneCTHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 875, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPaneLayout.setVerticalGroup(
            jPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPaneLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addGroup(jPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPaneLayout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(jLabel8))
                    .addGroup(jPaneLayout.createSequentialGroup()
                        .addGroup(jPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2))
                        .addGroup(jPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPaneLayout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(jPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel7)
                                    .addComponent(jDateChooserNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPaneLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jTextFieldTGT, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(28, 28, 28)
                .addComponent(jScrollPaneHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(jPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldMaCTHD, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldMaVT, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel9))
                .addGap(27, 27, 27)
                .addGroup(jPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldThanhTien, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldSL, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12))
                .addGap(31, 31, 31)
                .addComponent(jScrollPaneCTHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addGroup(jPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonDongForm, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButtonXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonKhongLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonSua, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonThemCTHD, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonThemHD, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButtonXem, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45))
        );

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 927, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonDongFormActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDongFormActionPerformed
        // TODO add your handling code here:
        dispose();
        this.toBack();
    }//GEN-LAST:event_jButtonDongFormActionPerformed

    private void jButtonThemHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonThemHDActionPerformed
        // TODO add your handling code here:
        blnThemHD = true;
        blnThemCTHD = false;
        
        jTextFieldMaKH.setText("");
        jTextFieldMaKH.setEditable(true);
        jTextFieldMaHD.requestFocus();
        jTextFieldMaHD.setText("");
        jTextFieldMaHD.setEditable(true);
        jDateChooserNgay.setDate(new Date());
        jTextFieldTGT.setText("");
        jTextFieldTGT.setEditable(false);
        
        jButtonThemHD.setEnabled(false);
        jButtonThemCTHD.setEnabled(false);
        jButtonSua.setEnabled(false);
        jButtonXoa.setEnabled(false);
        jButtonLuu.setEnabled(true);
        jButtonKhongLuu.setEnabled(true);
    }//GEN-LAST:event_jButtonThemHDActionPerformed

    private void jTableHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableHoaDonMouseClicked
        // TODO add your handling code here:
        blnSuaHD = true;
        blnSuaCTHD = false;
        PreparedStatement pst = null;
        CallableStatement cstmt = null;
        Connection conn = null;
        
        DefaultTableModel tblModel = (DefaultTableModel) jTableHoaDon.getModel();

        String mahd = tblModel.getValueAt(jTableHoaDon.getSelectedRow(), 0).toString();
        String makh = tblModel.getValueAt(jTableHoaDon.getSelectedRow(), 2).toString();

        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost/qlvattu","root","Thang123!");
            String sql = "SELECT * FROM CHITIETHOADON WHERE MAHD='"+(String)mahd+"';";
            pst = conn.prepareStatement(sql);
            ResultSet resultSet = pst.executeQuery();

            jTableCTHoaDon.setModel(DbUtils.resultSetToTableModel(resultSet));
        } catch (SQLException e){
            System.out.println("Có lỗi xảy ra khi lấy chi tiết hóa đơn: "+ e);
        }

        try {
            Date ngay = sdf.parse((String) tblModel.getValueAt(jTableHoaDon.getSelectedRow(), 1).toString());
            jDateChooserNgay.setDate(ngay);
        } catch (Exception ex) {
            System.out.println(msg_error_setdate + ex);
        }

        try {
            cstmt = conn.prepareCall("{? = call TGT_HOADON(?)}");
            cstmt.registerOutParameter(1, Types.INTEGER);
            cstmt.setString(2, mahd);
            cstmt.execute();
            jTextFieldTGT.setText(Integer.toString(cstmt.getInt(1)));
       } catch (SQLException ex) {
            String msg = "MyModel(): Error Connecting to Database:\n" + ex.getMessage();
                System.out.println(msg);
        }
        
                
        jTextFieldMaHD.setText(mahd);
        jTextFieldMaHD.setEditable(false);
        jTextFieldMaKH.setText(makh);
        jTextFieldMaKH.setEditable(false);

        jButtonThemHD.setEnabled(true);
        jButtonThemCTHD.setEnabled(true);
        jButtonXoa.setEnabled(true);
        jButtonSua.setEnabled(true);
    }//GEN-LAST:event_jTableHoaDonMouseClicked

    private void jTextFieldMaKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldMaKHActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldMaKHActionPerformed

    private void jTextFieldMaHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldMaHDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldMaHDActionPerformed

    private void jTextFieldTGTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldTGTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldTGTActionPerformed

    private void jButtonXemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonXemActionPerformed
        // TODO add your handling code here:
        
        String tendangnhapkh = TrangChuJFrame.jLabelTenDangNhap.getText();
        CallableStatement stmt = null;
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/qlvattu","root","Thang123!");
            stmt = conn.prepareCall("{CALL KHACHHANG_CTHOADON(?)}");
            stmt.setString(1, tendangnhapkh);
            stmt.executeUpdate();
            ResultSet resultSet = stmt.executeQuery();
            jTableCTHoaDon.setModel(DbUtils.resultSetToTableModel(resultSet));
            jTextFieldMaKH.setVisible(false);
            jDateChooserNgay.setVisible(false);
            jTextFieldMaHD.setVisible(false);
            jTextFieldTGT.setVisible(false);
            jLabel2.setVisible(false);
            jLabel4.setVisible(false);
            jLabel7.setVisible(false);
            jLabel8.setVisible(false);
            jButtonThemHD.setVisible(false);
            jButtonSua.setVisible(false);
            jButtonLuu.setVisible(false);
            jButtonKhongLuu.setVisible(false);
            jButtonXoa.setVisible(false);
 
        } catch (SQLException ex) {
            String msg = "MyModel(): Error Connecting to Database:\n" + ex.getMessage();
            System.out.println(msg);
        }
    }//GEN-LAST:event_jButtonXemActionPerformed

    private void jTableCTHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableCTHoaDonMouseClicked
        // TODO add your handling code here:
        blnSuaHD = false;
        blnSuaCTHD = true;
        PreparedStatement pst = null;
        CallableStatement cstmt = null;
        Connection conn = null;

        DefaultTableModel tblModel = (DefaultTableModel) jTableCTHoaDon.getModel();
        String mahd = tblModel.getValueAt(jTableCTHoaDon.getSelectedRow(), 0).toString();
        String mavt = tblModel.getValueAt(jTableCTHoaDon.getSelectedRow(), 1).toString();
        String sl = (tblModel.getValueAt(jTableCTHoaDon.getSelectedRow(), 2)!=null) ? tblModel.getValueAt(jTableCTHoaDon.getSelectedRow(), 2).toString() : "(none)";
        String giaban = tblModel.getValueAt(jTableCTHoaDon.getSelectedRow(), 3).toString();
        // show CTHD table:
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost/qlvattu","root","Thang123!");
            String sql = "SELECT * FROM CHITIETHOADON WHERE MAHD='"+(String)mahd+"';";
            pst = conn.prepareStatement(sql);
            ResultSet resultSet = pst.executeQuery();

            jTableCTHoaDon.setModel(DbUtils.resultSetToTableModel(resultSet));
            
            cstmt = conn.prepareCall("{? = call TT_HOADON(?,?)}");
            cstmt.registerOutParameter(1, Types.INTEGER);
            cstmt.setString(2, mahd);
            cstmt.setString(3, mavt);
            cstmt.execute();
            jTextFieldThanhTien.setText(Integer.toString(cstmt.getInt(1)));
            
        } catch (SQLException e){
          System.out.println("Co loi xay ra khi lay CTHD: "+ e);
        }
        
        jTextFieldMaCTHD.setText(mahd);
        jTextFieldMaCTHD.setEditable(false);
        jTextFieldMaVT.setText(mavt);
        jTextFieldMaVT.setEditable(false);
        jTextFieldSL.setText(sl);
        jTextFieldSL.setEditable(false);
        jTextFieldGiaBan.setText(giaban);
        jTextFieldGiaBan.setEditable(false);

        jButtonThemCTHD.setEnabled(true);
        jButtonXoa.setEnabled(true);
        jButtonSua.setEnabled(true);
    }//GEN-LAST:event_jTableCTHoaDonMouseClicked

    private void jButtonThemCTHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonThemCTHDActionPerformed
        // TODO add your handling code here:
        blnSuaHD = false;
        blnThemHD = false;
        blnSuaCTHD = false;
        blnThemCTHD = true;
        
        
        DefaultTableModel tblModel = (DefaultTableModel) jTableHoaDon.getModel();

        String mahd = tblModel.getValueAt(jTableHoaDon.getSelectedRow(), 0).toString();
        jTextFieldMaCTHD.setText(mahd);
        jTextFieldMaCTHD.setEditable(false);
        
        jTextFieldMaVT.requestFocus();
        jTextFieldMaVT.setEditable(true);
        jTextFieldMaVT.setText("");
        jTextFieldGiaBan.setEditable(true);
        jTextFieldGiaBan.setText("");
        jTextFieldSL.setEditable(true);
        jTextFieldSL.setText("");
        jTextFieldThanhTien.setText("");
        jTextFieldThanhTien.setEditable(false);
        
        jButtonThemHD.setEnabled(false);
        jButtonThemCTHD.setEnabled(false);
        jButtonSua.setEnabled(false);
        jButtonXoa.setEnabled(false);
        jButtonLuu.setEnabled(true);
        jButtonKhongLuu.setEnabled(true);
    }//GEN-LAST:event_jButtonThemCTHDActionPerformed

    private void jButtonSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSuaActionPerformed
        // TODO add your handling code here:
        if(blnSuaHD) {
            jTextFieldMaHD.setEditable(false);
            jTextFieldMaKH.setEditable(true);
            jTextFieldMaKH.requestFocus();
            
        } else if(blnSuaCTHD) {
            jTextFieldMaCTHD.setEditable(false);
            jTextFieldMaVT.setEditable(false);
            jTextFieldGiaBan.requestFocus();
            jTextFieldSL.setEditable(true);
            jTextFieldGiaBan.setEditable(true);
        }
        jButtonThemHD.setEnabled(false);
            jButtonThemCTHD.setEnabled(false);
            jButtonSua.setEnabled(false);
            jButtonXoa.setEnabled(false);
            jButtonLuu.setEnabled(true);
            jButtonKhongLuu.setEnabled(true);
    }//GEN-LAST:event_jButtonSuaActionPerformed

    private void jButtonLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLuuActionPerformed
        // TODO add your handling code here:
        PreparedStatement pst = null;
        Connection conn = null;
        if(blnThemHD) {
            String mahd = jTextFieldMaHD.getText();
            String makh = jTextFieldMaKH.getText();
            String ngay = sdf.format(new Date());
            
            if (mahd.equals("") || makh.equals("") || ngay.equals(null)) {
                JOptionPane.showMessageDialog(rootPane, "Trường không được bỏ trống", "Lỗi", 1);
            } else {
                
                if(kiemTraKhoaChinh("HD")){
                    try {
                        conn = DriverManager.getConnection("jdbc:mysql://localhost/qlvattu","root","Thang123!");
                        String sql = "INSERT INTO HOADON (mahd, makh, ngay) VALUES(?,?,?)";

                        pst = conn.prepareStatement(sql);
                        pst.setString(1, mahd);
                        pst.setString(2, makh);
                        pst.setString(3, ngay);

                        int resultSet = pst.executeUpdate();

                        if (resultSet >= 1) {
                            ganDuLieu();
                            JOptionPane.showMessageDialog(null, "Hóa đơn được thêm thành công!");
                        } else {
                            JOptionPane.showMessageDialog(null, "Không thể thêm hóa đơn mới!");
                        }

                    } catch (SQLException ex) {
                        String msg = "MyModel():Lỗi kết nối cơ sở dữ liệu:\n" + ex.getMessage();
                        System.out.println(msg);
                    }
                }
            }
        } else if(blnThemCTHD) {
            String mahd = jTextFieldMaCTHD.getText();
            String mavt = jTextFieldMaVT.getText();
            int giaban =  Integer.parseInt(jTextFieldGiaBan.getText());
            int sl =  Integer.parseInt(jTextFieldSL.getText());
            
            if (mahd.equals("") || mavt.equals("")) {
                JOptionPane.showMessageDialog(rootPane, "Trường không được bỏ trống", "Lỗi", 1);
            } else {
                
                if(kiemTraKhoaChinh("CTHD")){
                    try {
                        conn = DriverManager.getConnection("jdbc:mysql://localhost/qlvattu","root","Thang123!");
                        String sql = "INSERT INTO CHITIETHOADON (mahd, mavt, sl, giaban) VALUES(?,?,?,?)";

                        pst = conn.prepareStatement(sql);
                        pst.setString(1, mahd);
                        pst.setString(2, mavt);
                        pst.setInt(3, sl);
                        pst.setInt(4, giaban);
                        
                        int resultSet = pst.executeUpdate();

                        if (resultSet >= 1) {
                            ganDuLieu();
                            JOptionPane.showMessageDialog(null, "Chi tiết hóa đơn được thêm thành công!");
                        } else {
                            JOptionPane.showMessageDialog(null, "Không thể thêm chi tiết hóa đơn mới!");
                        }

                    } catch (SQLException ex) {
                        String msg = "MyModel():Lỗi kết nối cơ sở dữ liệu:\n" + ex.getMessage();
                        System.out.println(msg);
                    }
                }
            }
        } else if(blnSuaHD) {
            String mahd = jTextFieldMaHD.getText();
            String makh = jTextFieldMaKH.getText();
            String ngay = sdf.format(new Date());
            
            if (mahd.equals("") || makh.equals("") || ngay.equals(null)) {
                JOptionPane.showMessageDialog(rootPane, "Trường không được bỏ trống", "Lỗi", 1);
            } else {
                
                
                    try {
                        conn = DriverManager.getConnection("jdbc:mysql://localhost/qlvattu","root","Thang123!");
                        String sql = "UPDATE HOADON SET MAKH=?, ngay=? where MAHD='"+mahd+"'";

                        pst = conn.prepareStatement(sql);
                        pst.setString(1, makh);
                        pst.setString(2, ngay);

                        int resultSet = pst.executeUpdate();

                        if (resultSet >= 1) {
                            JOptionPane.showMessageDialog(null, "Hóa đơn cập nhật thành công!");
                            ganDuLieu();
                        } else {
                            JOptionPane.showMessageDialog(null, "Không thể cập nhật hóa đơn!");
                        }

                    } catch (SQLException ex) {
                        String msg = "MyModel():Lỗi kết nối cơ sở dữ liệu:\n" + ex.getMessage();
                        System.out.println(msg);
                    }
            }
        } else if(blnSuaCTHD) {
            String mahd = jTextFieldMaCTHD.getText();
            String mavt = jTextFieldMaVT.getText();
            int giaban =  Integer.parseInt(jTextFieldGiaBan.getText());
            int sl =  Integer.parseInt(jTextFieldSL.getText());
            
            if (mahd.equals("") || mavt.equals("")) {
                JOptionPane.showMessageDialog(rootPane, "Trường không được bỏ trống", "Lỗi", 1);
            } else {
                
                    try {
                        conn = DriverManager.getConnection("jdbc:mysql://localhost/qlvattu","root","Thang123!");
                        String sql = "UPDATE CHITIETHOADON SET sl=? , giaban=? where MAHD='"+mahd+"' AND MAVT ='"+mavt+"'";

                        pst = conn.prepareStatement(sql);
                        pst.setInt(1, sl);
                        pst.setInt(2, giaban);
                        
                        int resultSet = pst.executeUpdate();

                        if (resultSet >= 1) {
                            JOptionPane.showMessageDialog(null, "Chi tiết hóa đơn cập nhật thành công!");
                            ganDuLieu();
                        } else {
                            JOptionPane.showMessageDialog(null, "Không thể cập nhật chi tiết hóa đơn!");
                        }

                    } catch (SQLException ex) {
                        String msg = "MyModel():Lỗi kết nối cơ sở dữ liệu:\n" + ex.getMessage();
                        System.out.println(msg);
                    }
            }
        }
    }//GEN-LAST:event_jButtonLuuActionPerformed

    private void jButtonKhongLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonKhongLuuActionPerformed
        // TODO add your handling code here:
        ganDuLieu();

        jButtonThemHD.setEnabled(true);
        jButtonSua.setEnabled(true);
    }//GEN-LAST:event_jButtonKhongLuuActionPerformed

    private void jButtonXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonXoaActionPerformed
        // TODO add your handling code here:
        PreparedStatement pst = null;
        PreparedStatement pst1 = null;
        PreparedStatement pst2 = null;
        CallableStatement cstmt = null;
        Connection conn = null;
        if(blnXoaHD) {
            DefaultTableModel tblModelHD = (DefaultTableModel)jTableHoaDon.getModel();
            String mahd = tblModelHD.getValueAt(jTableHoaDon.getSelectedRow(), 0).toString();
            try {
                conn = DriverManager.getConnection("jdbc:mysql://localhost/qlvattu","root","Thang123!");
                String sql1 = "SELECT * FROM HOADON WHERE MAHD = ?";
                pst1 = conn.prepareStatement (sql1);
                pst1.setString(1, mahd);
                ResultSet resultSet1 = pst1.executeQuery();

                if(resultSet1.next() ) {
                    String sql = "DELETE FROM HOADON WHERE MAHD = ?";
                    pst = conn.prepareStatement (sql);
                    pst.setString(1, mahd);

                    int respone = JOptionPane.showConfirmDialog(null, "Bạn có muốn hóa đơn " + mahd +" cùng tất cả các chi tiết hóa đơn có liên quan?", "Xác nhận", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if(respone == JOptionPane.YES_OPTION) {
                        pst.executeUpdate();
                        ganDuLieu();
                        JOptionPane.showMessageDialog(null, "Hóa đơn và tất cả chi tiết hóa đơn đã được xóa!");
                    }
                }  else { 
                    ganDuLieu();
                    JOptionPane.showMessageDialog(null, "Hóa đơn không tìm thấy!");
                }  
            } catch (SQLException ex) {
                    String msg = "MyModel():Lỗi kết nối cơ sở dữ liệu:\n" + ex.getMessage();
                    System.out.println(msg);
            }
        }
        else if(blnXoaCTHD) {
            DefaultTableModel tblModel = (DefaultTableModel)jTableCTHoaDon.getModel();
            String macthd = tblModel.getValueAt(jTableCTHoaDon.getSelectedRow(), 0).toString();
            String mavt = tblModel.getValueAt(jTableCTHoaDon.getSelectedRow(), 1).toString();
            try {
                conn = DriverManager.getConnection("jdbc:mysql://localhost/qlvattu","root","Thang123!");
                String sql1 = "SELECT * FROM CHITIETHOADON WHERE MAHD = ?";
                pst1 = conn.prepareStatement (sql1);
                pst1.setString(1, macthd);
                ResultSet resultSet1 = pst1.executeQuery();

                if(resultSet1.next() ) {
                    String sql = "DELETE FROM CHITIETHOADON WHERE MAHD = ? AND MAVT = ?";
                    pst = conn.prepareStatement (sql);
                    pst.setString(1, macthd);
                    pst.setString(2, mavt);

                    int respone = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa chi tiết hóa đơn " + macthd +" của vật tư " +mavt, "Xác nhận", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if(respone == JOptionPane.YES_OPTION) {
                        pst.executeUpdate();
                        ganDuLieu();
                        JOptionPane.showMessageDialog(null, "Chi tiết hóa đơn đã được xóa thành công!");
                    }
                } else { 
                    ganDuLieu();
                    JOptionPane.showMessageDialog(null, "CHi tiết hóa đơn không tìm thấy!");
                } 
                
            } catch (SQLException ex) {
                    String msg = "MyModel():Lỗi kết nối cơ sở dữ liệu:\n" + ex.getMessage();
                    System.out.println(msg);
            } 
        }
    }//GEN-LAST:event_jButtonXoaActionPerformed

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
            java.util.logging.Logger.getLogger(HoaDonJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HoaDonJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HoaDonJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HoaDonJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HoaDonJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonDongForm;
    private javax.swing.JButton jButtonKhongLuu;
    private javax.swing.JButton jButtonLuu;
    private javax.swing.JButton jButtonSua;
    private javax.swing.JButton jButtonThemCTHD;
    private javax.swing.JButton jButtonThemHD;
    private javax.swing.JButton jButtonXem;
    private javax.swing.JButton jButtonXoa;
    private com.toedter.calendar.JDateChooser jDateChooserNgay;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPane;
    private javax.swing.JScrollPane jScrollPaneCTHoaDon;
    private javax.swing.JScrollPane jScrollPaneHoaDon;
    private javax.swing.JTable jTableCTHoaDon;
    private javax.swing.JTable jTableHoaDon;
    private javax.swing.JTextField jTextFieldGiaBan;
    private javax.swing.JTextField jTextFieldMaCTHD;
    private javax.swing.JTextField jTextFieldMaHD;
    private javax.swing.JTextField jTextFieldMaKH;
    private javax.swing.JTextField jTextFieldMaVT;
    private javax.swing.JTextField jTextFieldSL;
    private javax.swing.JTextField jTextFieldTGT;
    private javax.swing.JTextField jTextFieldThanhTien;
    // End of variables declaration//GEN-END:variables
}
