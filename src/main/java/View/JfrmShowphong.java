/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import Model.CurrentUser;
import Model.phongTro;
import controller.LopController;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import model.phong;

/**
 *
 * @author Admin
 */
public class JfrmShowphong extends javax.swing.JFrame {
//    public void showData{
//        
//    }

    /**
     * Creates new form JfrmShowphong
     */
    String makt = CurrentUser.getMakt();
    
    
    public JfrmShowphong() {
        initComponents();
        buttonGroup1.add(cbnam);
        buttonGroup1.add(cbnu);
        txtmaphong.setEditable(false);
        txttenphong.setEditable(false);
        txtkhuvuc.setEditable(false);
        txtsonguoi.setEditable(false);
        txtdientich.setEditable(false);
        txtloai.setEditable(false);
        txtgia.setEditable(false);
        txtsodien.setEditable(false);
        txtsonuoc.setEditable(false);
        java.util.Date ngayDatUtil = jdngaydat.getDate();
        java.util.Date ngayKTUtil = jdngaykt.getDate();
        System.out.println(""+ ngayDatUtil);
    }
    public void getphong(phongTro p){
        txtmaphong.setText(p.getMa());
        txttenphong.setText(p.getTen());
        txtkhuvuc.setText(p.getKhuvuc());
        txtsonguoi.setText(""+p.getSoluong());
        txtdientich.setText(""+p.getDientich());
        txtloai.setText(p.getLoai());
        txtgia.setText(""+p.getGia());
        txtsodien.setText(""+p.getSodienold());
        txtsonuoc.setText(""+p.getSonuocold());
        if("Nam".equals(p.getGioitinh())){
              cbnam.setSelected(true);
        } else cbnu.setSelected(true);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnthue = new javax.swing.JButton();
        btnthoat = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtmaphong = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txttenphong = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtkhuvuc = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtsonguoi = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtdientich = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtsodien = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtsonuoc = new javax.swing.JTextField();
        txtloai = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtgia = new javax.swing.JTextField();
        cbnam = new javax.swing.JCheckBox();
        cbnu = new javax.swing.JCheckBox();
        jdngaykt = new com.toedter.calendar.JDateChooser();
        jdngaydat = new com.toedter.calendar.JDateChooser();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();

        jLabel2.setText("jLabel2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 153, 153));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("THÔNG TIN PHÒNG ĐÃ CHỌN");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        btnthue.setText("Đặt  phòng");
        btnthue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthueActionPerformed(evt);
            }
        });

        btnthoat.setText("Thoát");
        btnthoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthoatActionPerformed(evt);
            }
        });

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Mã phòng");

        txtmaphong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtmaphongActionPerformed(evt);
            }
        });

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Tên phòng");

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Khu vực");

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Số người");

        txtsonguoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtsonguoiActionPerformed(evt);
            }
        });

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Diện tích");

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Đối tượng");

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("Số điện");

        txtsodien.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("Số nước");

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setText("Loại phòng");

        txtsonuoc.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtsonuoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtsonuocActionPerformed(evt);
            }
        });

        txtloai.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtloai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtloaiActionPerformed(evt);
            }
        });

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel13.setText("Giá");

        txtgia.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        cbnam.setText("Nam");
        cbnam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbnamActionPerformed(evt);
            }
        });

        cbnu.setText("Nữ");
        cbnu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbnuActionPerformed(evt);
            }
        });

        jdngaydat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jdngaydatMouseClicked(evt);
            }
        });

        jLabel12.setText("Chọn ngày đặt");

        jLabel14.setText("Chọn ngày kết thúc");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(109, 109, 109)
                        .addComponent(btnthue)
                        .addGap(128, 128, 128)
                        .addComponent(btnthoat, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtkhuvuc, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtsonguoi, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(3, 3, 3)
                                    .addComponent(jLabel3)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtmaphong, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txttenphong, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtdientich, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(78, 78, 78)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtsodien, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtloai, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtsonuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbnam)
                                .addGap(19, 19, 19)
                                .addComponent(cbnu))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtgia, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(89, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(jdngaydat, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(66, 66, 66)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addComponent(jdngaykt, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(59, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtmaphong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txttenphong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtkhuvuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtsonguoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtdientich, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(txtloai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(txtgia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtsodien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtsonuoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(cbnam)
                            .addComponent(cbnu))))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jdngaydat, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jdngaykt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnthue)
                    .addComponent(btnthoat))
                .addGap(15, 15, 15))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnthueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthueActionPerformed
        // TODO add your handling code here:
//        JfrmDatphong frmDP = new JfrmDatphong();
//        frmDP.setLocationRelativeTo(null);
//        frmDP.setVisible(true);
             java.util.Date ngayDatUtil = jdngaydat.getDate();
             java.util.Date ngayKTUtil = jdngaykt.getDate();
              SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
       //   // Chuyển đổi ngày thành chuỗi theo định dạng
    String start = sdf.format(ngayDatUtil);
     String ngaykt1 = sdf.format(ngayKTUtil);
     
             // In ra chuỗi ngày
             CurrentUser.setStartDay(start);
             CurrentUser.setEndDay(ngaykt1);
           
//              System.out.println(ngaybd);
//              System.out.println(ngaykt1);
             System.out.println(ngayKTUtil);
               Date ngaydat = jdngaydat.getDate();
               
        Date ngaykt = jdngaykt.getDate();
        //            LocalDate localngaydat = convertTo
        Date today = new Date();
       
        if (ngaydat == null || ngaykt == null) {
            JOptionPane.showMessageDialog(
                this,
                "Vui lòng chọn đầy đủ ngày đặt và ngày kết thúc!",
                "Thông báo ",
                JOptionPane.ERROR_MESSAGE
            );
        }
        else if (ngaydat.before(today)){
            JOptionPane.showMessageDialog(this, "Ngày đặt phải là hôm nay hoặc sau hôm nay!", "Thông báo ", JOptionPane.ERROR_MESSAGE );
        }
        else if (ngaydat.after(ngaykt)){
            JOptionPane.showMessageDialog(
                this,
                "Ngày kết phải là ngày đặt hoặc sau ngày đặt!",
                "Thông báo ",
                JOptionPane.ERROR_MESSAGE
            );
        }
        // Chuyển đổi ngày sang chuỗi
        else{   try {
            String ngayDatFormatted = sdf.format(ngaydat);
            String ngayKtFormatted = sdf.format(ngaykt);
            long diffInMillies = ngaykt.getTime() - ngaydat.getTime(); // Khoảng cách tính bằng milliseconds
            double songay = (double) diffInMillies / (24 * 60 * 60 * 1000);
            if(songay < 30){
                JOptionPane.showMessageDialog(this, "Số ngày đặt ít nhất là 1 tháng", "Thông báo ", JOptionPane.ERROR_MESSAGE );
            }
            else{
                phongTro p = new phongTro();
              //  CurrentUser.setMaPhong(p.getMa());
//                String maphong = txtgia.getText();
                //int Getgia = Integer.parseInt(txtgia.getText());
                double giaTien = Double.parseDouble(txtgia.getText());
                long Getgia = (long)giaTien;
                double sothangthue = songay / 30;
                System.out.println("" +sothangthue);
                float sothangtinh = (float) Math.ceil(sothangthue);
                System.out.println(""+sothangtinh);
                // Hiển thị kết quả
                JOptionPane.showMessageDialog(
                    this,
                    "\nNgày đặt: " + ngayDatFormatted + "\nNgày kết thúc: " + ngayKtFormatted +"\nSố tiền cần thanh toán: " + sothangtinh*Getgia,
                    "Thông báo", JOptionPane.INFORMATION_MESSAGE );
                LopController lc = new LopController();
                lc.updateTinhTrangPhong(p,txtmaphong.getText());
                lc.getMa(makt,txtmaphong.getText());
                // Cập nhật tình trạng phòng
            //    lc.getBooking(p, txtmaphong.getText());
                this.dispose();
                
            }


        } catch (SQLException ex) {
//            Logger.getLogger(JfrmDatphong.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }//GEN-LAST:event_btnthueActionPerformed

    private void btnthoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthoatActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnthoatActionPerformed

    private void txtsonguoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtsonguoiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtsonguoiActionPerformed

    private void cbnuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbnuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbnuActionPerformed

    private void cbnamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbnamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbnamActionPerformed

    private void txtmaphongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtmaphongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtmaphongActionPerformed

    private void txtloaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtloaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtloaiActionPerformed

    private void txtsonuocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtsonuocActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtsonuocActionPerformed

    private void jdngaydatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jdngaydatMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_jdngaydatMouseClicked

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
            java.util.logging.Logger.getLogger(JfrmShowphong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JfrmShowphong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JfrmShowphong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JfrmShowphong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JfrmShowphong().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnthoat;
    private javax.swing.JButton btnthue;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox cbnam;
    private javax.swing.JCheckBox cbnu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private com.toedter.calendar.JDateChooser jdngaydat;
    private com.toedter.calendar.JDateChooser jdngaykt;
    private javax.swing.JTextField txtdientich;
    private javax.swing.JTextField txtgia;
    private javax.swing.JTextField txtkhuvuc;
    private javax.swing.JTextField txtloai;
    private javax.swing.JTextField txtmaphong;
    private javax.swing.JTextField txtsodien;
    private javax.swing.JTextField txtsonguoi;
    private javax.swing.JTextField txtsonuoc;
    private javax.swing.JTextField txttenphong;
    // End of variables declaration//GEN-END:variables
}
