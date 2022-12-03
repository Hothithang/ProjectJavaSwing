
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class ThongKeJFrame extends javax.swing.JFrame {

    /**
     * Creates new form HoaDOnJFrame
     */ 
    public ThongKeJFrame() {
        initComponents();
        DieuKienBinhThuong();
    }

    boolean DTThang = false;
    boolean DTNam = false;
    boolean LSMH = false;
    boolean LSThang = false;
    boolean LSNam = false;
        
    public void DieuKienBinhThuong() {
        jTextFieldTimKiem1.setEditable(false);
        jTextFieldTimKiem2.setEditable(false);
        jTextFieldKetQua.setEditable(false);
    }
    
    private boolean KiemTraThang(String text) {
        try {
            if(Integer.parseInt(text)>0 && Integer.parseInt(text)<13) {
                return true;
            } else {
                return false;
            }
        } catch (NumberFormatException e) {
         return false;
        }
    }
    
    private boolean KiemTraNam(String text) {
        try {
            if(Integer.parseInt(text)>2020 && Integer.parseInt(text)<2023) {
                return true;
            } else {
                return false;
            }
        } catch (NumberFormatException e) {
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
        jButtonDTThang = new javax.swing.JButton();
        jButtonDTNam = new javax.swing.JButton();
        jButtonLaiMH = new javax.swing.JButton();
        jButtonLaiThang = new javax.swing.JButton();
        jButtonLaiNam = new javax.swing.JButton();
        jTextFieldTimKiem1 = new javax.swing.JTextField();
        jButtonTimKiem = new javax.swing.JButton();
        jLabelMa1 = new javax.swing.JLabel();
        jTextFieldKetQua = new javax.swing.JTextField();
        jLabelKetQua = new javax.swing.JLabel();
        jButtonDongForm = new javax.swing.JButton();
        jButtonTroVe = new javax.swing.JButton();
        jTextFieldTimKiem2 = new javax.swing.JTextField();
        jLabelMa2 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPane.setBackground(new java.awt.Color(204, 255, 255));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 204, 102));
        jLabel1.setText("THỐNG KÊ");

        jButtonDTThang.setText("Doanh Thu Theo Tháng");
        jButtonDTThang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDTThangActionPerformed(evt);
            }
        });

        jButtonDTNam.setText("Doanh Thu Theo Năm");
        jButtonDTNam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDTNamActionPerformed(evt);
            }
        });

        jButtonLaiMH.setText("Lãi Từng Mặt Hàng");
        jButtonLaiMH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLaiMHActionPerformed(evt);
            }
        });

        jButtonLaiThang.setText("Lãi Theo Tháng");
        jButtonLaiThang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLaiThangActionPerformed(evt);
            }
        });

        jButtonLaiNam.setText("Lãi Theo Năm");
        jButtonLaiNam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLaiNamActionPerformed(evt);
            }
        });

        jButtonTimKiem.setText("Tìm Kiếm");
        jButtonTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTimKiemActionPerformed(evt);
            }
        });

        jLabelMa1.setText("Tháng (1-12):");

        jLabelKetQua.setText("Kết quả:");

        jButtonDongForm.setText("Đóng Form");
        jButtonDongForm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDongFormActionPerformed(evt);
            }
        });

        jButtonTroVe.setText("Trở về");
        jButtonTroVe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTroVeActionPerformed(evt);
            }
        });

        jLabelMa2.setText("Năm (2021-2022):");

        javax.swing.GroupLayout jPaneLayout = new javax.swing.GroupLayout(jPane);
        jPane.setLayout(jPaneLayout);
        jPaneLayout.setHorizontalGroup(
            jPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPaneLayout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addGroup(jPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jButtonLaiThang, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonLaiMH, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonDTNam, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonDTThang, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
                    .addComponent(jButtonLaiNam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 259, Short.MAX_VALUE)
                .addGroup(jPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabelMa1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelMa2, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldTimKiem1)
                    .addGroup(jPaneLayout.createSequentialGroup()
                        .addComponent(jButtonTimKiem)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonTroVe)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonDongForm))
                    .addComponent(jTextFieldTimKiem2)
                    .addComponent(jTextFieldKetQua, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelKetQua, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(102, 102, 102))
            .addGroup(jPaneLayout.createSequentialGroup()
                .addGap(348, 348, 348)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPaneLayout.setVerticalGroup(
            jPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPaneLayout.createSequentialGroup()
                .addGroup(jPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPaneLayout.createSequentialGroup()
                        .addGap(124, 124, 124)
                        .addComponent(jButtonDTThang, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonDTNam, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonLaiMH, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonLaiThang, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19)
                        .addComponent(jButtonLaiNam, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPaneLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63)
                        .addComponent(jLabelMa1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldTimKiem1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jLabelMa2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldTimKiem2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabelKetQua)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldKetQua, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addGroup(jPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonTroVe, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonDongForm, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(178, Short.MAX_VALUE))
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
            .addComponent(jPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonDTThangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDTThangActionPerformed
        // TODO add your handling code here:
        jTextFieldTimKiem1.setEditable(true);
        jTextFieldTimKiem2.setEditable(true);
        jTextFieldTimKiem1.requestFocus();
        DTThang = true;
        
        jButtonDTNam.setEnabled(false);
        jButtonLaiMH.setEnabled(false);
        jButtonLaiThang.setEnabled(false);
        jButtonLaiNam.setEnabled(false);
    }//GEN-LAST:event_jButtonDTThangActionPerformed

    
    
    private void jButtonTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTimKiemActionPerformed
        // TODO add your handling code here:
        CallableStatement cstmt = null;
        Connection conn = null;
        
        if(DTThang) {
            try {
                if(KiemTraThang(jTextFieldTimKiem1.getText()) && KiemTraNam(jTextFieldTimKiem2.getText())) {
                    int timkiem1 =  Integer.parseInt(jTextFieldTimKiem1.getText());
                    int timkiem2 =  Integer.parseInt(jTextFieldTimKiem2.getText());
                    conn = DriverManager.getConnection("jdbc:mysql://localhost/qlvattu","root","Thang123!");
                    cstmt = conn.prepareCall("{? = call DOANHTHUTHANG(?,?)}");
                    cstmt.registerOutParameter(1, Types.INTEGER);
                    cstmt.setInt(2, timkiem1);
                    cstmt.setInt(3, timkiem2);
                    cstmt.execute();
                    jLabelKetQua.setText("Kết quả doanh thu tháng "+ timkiem1 + " năm "+timkiem2 +":");
                    jTextFieldKetQua.setText(Integer.toString(cstmt.getInt(1)));
                } else {
                    JOptionPane.showMessageDialog(null, "Tháng hoặc năm không hợp lệ");
                }
            } catch (SQLException ex) {
                String msg = "MyModel(): Error Connecting to Database:\n" + ex.getMessage();
                System.out.println(msg);
            }
        } else if(DTNam) {
            try {
                if(KiemTraNam(jTextFieldTimKiem2.getText())) {
                    int timkiem =  Integer.parseInt(jTextFieldTimKiem2.getText());
                    conn = DriverManager.getConnection("jdbc:mysql://localhost/qlvattu","root","Thang123!");
                    cstmt = conn.prepareCall("{? = call DOANHTHUNAM(?)}");
                    cstmt.registerOutParameter(1, Types.INTEGER);
                    cstmt.setInt(2, timkiem);
                    cstmt.execute();
                    jLabelKetQua.setText("Kết quả doanh thu năm "+timkiem +":");
                    jTextFieldKetQua.setText(Integer.toString(cstmt.getInt(1)));
                } else {
                    JOptionPane.showMessageDialog(null, "Năm không hợp lệ");
                }
            } catch (SQLException ex) {
                String msg = "MyModel(): Error Connecting to Database:\n" + ex.getMessage();
                System.out.println(msg);
            } 
        } else if(LSMH) {
            try {
                String timkiem =  jTextFieldTimKiem1.getText();
                conn = DriverManager.getConnection("jdbc:mysql://localhost/qlvattu","root","Thang123!");
                cstmt = conn.prepareCall("{? = call LAI_MATHANG(?)}");
                cstmt.registerOutParameter(1, Types.INTEGER);
                cstmt.setString(2, timkiem);
                cstmt.execute();
                jLabelKetQua.setText("Kết quả lãi suất mặt hàng "+ timkiem +":");
                jTextFieldKetQua.setText(Integer.toString(cstmt.getInt(1)));
            } catch (SQLException ex) {
                String msg = "MyModel(): Error Connecting to Database:\n" + ex.getMessage();
                System.out.println(msg);
            } 
        } else if(LSThang) {
            try {
                if(KiemTraThang(jTextFieldTimKiem1.getText()) && KiemTraNam(jTextFieldTimKiem2.getText())) {
                    String timkiem1 =  jTextFieldTimKiem1.getText();
                    String timkiem2 =  jTextFieldTimKiem2.getText();
                    conn = DriverManager.getConnection("jdbc:mysql://localhost/qlvattu","root","Thang123!");
                    cstmt = conn.prepareCall("{? = call LAI_THANG(?,?)}");
                    cstmt.registerOutParameter(1, Types.INTEGER);
                    cstmt.setString(2, timkiem1);
                    cstmt.setString(3, timkiem2);
                    cstmt.execute();
                    jLabelKetQua.setText("Kết quả lãi suất tháng "+ timkiem1 + " năm "+timkiem2 +":");
                    jTextFieldKetQua.setText(Integer.toString(cstmt.getInt(1)));
                } else {
                    JOptionPane.showMessageDialog(null, "Tháng hoặc Năm không hợp lệ");
                }   
            } catch (SQLException ex) {
                String msg = "MyModel(): Error Connecting to Database:\n" + ex.getMessage();
                System.out.println(msg);
            } 
        } else if(LSNam) {
            try {
                if(KiemTraNam(jTextFieldTimKiem2.getText())) {
                    String timkiem =  jTextFieldTimKiem2.getText();
                    conn = DriverManager.getConnection("jdbc:mysql://localhost/qlvattu","root","Thang123!");
                    cstmt = conn.prepareCall("{? = call LAI_NAM(?)}");
                    cstmt.registerOutParameter(1, Types.INTEGER);
                    cstmt.setString(2, timkiem);
                    cstmt.execute();
                    jLabelKetQua.setText("Kết quả lãi suất năm "+timkiem +":");
                    jTextFieldKetQua.setText(Integer.toString(cstmt.getInt(1)));
                } else {
                    JOptionPane.showMessageDialog(null, "Năm không hợp lệ");
                }    
            } catch (SQLException ex) {
                String msg = "MyModel(): Error Connecting to Database:\n" + ex.getMessage();
                System.out.println(msg);
            } 
        }
    }//GEN-LAST:event_jButtonTimKiemActionPerformed

    
    private void jButtonDTNamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDTNamActionPerformed
        // TODO add your handling code here:
        jTextFieldTimKiem1.setEditable(true);
        jTextFieldTimKiem2.setEditable(true);
        jTextFieldTimKiem2.requestFocus();
        DTNam = true;
        
        jLabelMa1.setEnabled(false);
        jTextFieldTimKiem1.setEnabled(false);
        jButtonDTThang.setEnabled(false);
        jButtonLaiMH.setEnabled(false);
        jButtonLaiThang.setEnabled(false);
        jButtonLaiNam.setEnabled(false);
    }//GEN-LAST:event_jButtonDTNamActionPerformed

    private void jButtonDongFormActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDongFormActionPerformed
        // TODO add your handling code here:
        dispose();
        this.toBack();
    }//GEN-LAST:event_jButtonDongFormActionPerformed

    private void jButtonTroVeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTroVeActionPerformed
        // TODO add your handling code here:
        DTThang = false;
        DTNam = false;
        LSMH = false;
        LSThang = false;
        LSNam = false;
        
        DieuKienBinhThuong();
        jLabelMa1.setEnabled(true);
        jTextFieldTimKiem1.setEnabled(true);
        jLabelMa1.setText("Tháng (6-12):");
        jTextFieldTimKiem1.setText("");
        jLabelMa2.setEnabled(true);
        jTextFieldTimKiem2.setEnabled(true);
        jLabelMa2.setText("Năm (2000-2022):");
        jTextFieldTimKiem2.setText("");
        jTextFieldKetQua.setText("");
        jLabelKetQua.setText("Kết quả:");
        jButtonDTThang.setEnabled(true);
        jButtonDTNam.setEnabled(true);
        jButtonLaiMH.setEnabled(true);
        jButtonLaiThang.setEnabled(true);
        jButtonLaiNam.setEnabled(true);
    }//GEN-LAST:event_jButtonTroVeActionPerformed

    private void jButtonLaiMHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLaiMHActionPerformed
        // TODO add your handling code here:
        jTextFieldTimKiem1.setEditable(true);
        jTextFieldTimKiem1.requestFocus();
        jLabelMa1.setText("Mã vật tư:");
        LSMH = true;
        
        jLabelMa2.setEnabled(false);
        jTextFieldTimKiem2.setEnabled(false);
        jButtonDTNam.setEnabled(false);
        jButtonDTThang.setEnabled(false);
        jButtonLaiThang.setEnabled(false);
        jButtonLaiNam.setEnabled(false);
    }//GEN-LAST:event_jButtonLaiMHActionPerformed

    private void jButtonLaiNamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLaiNamActionPerformed
        // TODO add your handling code here:
        jTextFieldTimKiem2.setEditable(true);
        jTextFieldTimKiem2.requestFocus();
        LSNam = true;
        
        jLabelMa1.setEnabled(false);
        jTextFieldTimKiem1.setEnabled(false);
        jButtonDTNam.setEnabled(false);
        jButtonDTThang.setEnabled(false);
        jButtonLaiThang.setEnabled(false);
        jButtonLaiMH.setEnabled(false);
    }//GEN-LAST:event_jButtonLaiNamActionPerformed

    private void jButtonLaiThangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLaiThangActionPerformed
        // TODO add your handling code here:
        jTextFieldTimKiem1.setEditable(true);
        jTextFieldTimKiem2.setEditable(true);
        jTextFieldTimKiem1.requestFocus();
        LSThang = true;
        
        jButtonDTNam.setEnabled(false);
        jButtonDTThang.setEnabled(false);
        jButtonLaiNam.setEnabled(false);
        jButtonLaiMH.setEnabled(false);
    }//GEN-LAST:event_jButtonLaiThangActionPerformed

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
    private javax.swing.JButton jButtonDTNam;
    private javax.swing.JButton jButtonDTThang;
    private javax.swing.JButton jButtonDongForm;
    private javax.swing.JButton jButtonLaiMH;
    private javax.swing.JButton jButtonLaiNam;
    private javax.swing.JButton jButtonLaiThang;
    private javax.swing.JButton jButtonTimKiem;
    private javax.swing.JButton jButtonTroVe;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelKetQua;
    private javax.swing.JLabel jLabelMa1;
    private javax.swing.JLabel jLabelMa2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPane;
    private javax.swing.JTextField jTextFieldKetQua;
    private javax.swing.JTextField jTextFieldTimKiem1;
    private javax.swing.JTextField jTextFieldTimKiem2;
    // End of variables declaration//GEN-END:variables
}
