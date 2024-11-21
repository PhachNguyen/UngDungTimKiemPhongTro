/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import Controller.RoomDAO;
import Model.phongTro;
import java.awt.Color;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class JfrmAddroom extends javax.swing.JFrame {

    /**
     * Creates new form JfrmInforUsers
     */
    phongTro pt ;
    RoomDAO themPT;
   // JfrmMainAdmin main ;
//   // private final DefaultTableModel tblModel = new DefaultTableModel();
   
    public JfrmAddroom() throws SQLException, ClassNotFoundException {
        initComponents();
     //   main = new JfrmMainAdmin();
        group.add(cbMale);
        group.add(cbFeMale);
        pt =new phongTro();
        themPT = new RoomDAO();
        this.setLocationRelativeTo(null);
        lbErrorDien.setText("");
         lbErrorDientich.setText("");
          lbErrorDoituong.setText("");
           lbErrorGia.setText("");
            lbErrorKhuvuc.setText("");
             lbErrorLoai.setText("");
              lbErrorNuoc.setText("");
               lbErrorSoluong.setText("");
                lbErrorTenPhong.setText("");
                // lbErrorDien.setText("");
                lbErrorMaPhong.setText("");
                 
      //  initTable();
       
        
    }
    
     
//    // Khởi tạo bảng Table :
//        public void initTable(){
//            String[] colsName = {"Mã phòng", "Tên","Khu vực","Loại phòng","Số người","Giá","Đối tượng","Diện tích"};
//            tblModel.setColumnIdentifiers(colsName);
//            tbRoom.setModel(tblModel);
//        }
//        // Điền vào bảng table
//        public void fillTable() throws ClassNotFoundException, SQLException{
//        try {
//            List<phongTro> lst = new RoomDAO().getInforRoom();
//            for(int i=0;i<lst.size();i++){ // Sử dụng vòng lặp for thêm đối tượng
//                String[] row ={
//                    ""+ lst.get(i).getMa(),
//                    lst.get(i).getTen(),
//                    lst.get(i).getKhuvuc(),
//                    lst.get(i).getLoai(),
//                    ""+ lst.get(i).getSoluong(),
//                   ""+  lst.get(i).getGia(),
//                    lst.get(i).getGioitinh(),
//                   ""+  lst.get(i).getDientich()       
//                };
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(JfrmAddroom.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        }
//        public boolean checkInfor(){
//             if(txtDientich.getText().equals("")){
//            JOptionPane.showMessageDialog(null, "Chưa nhập diện tích", "Lỗi", JOptionPane.ERROR_MESSAGE);
//            return false;
//        }else if(txtSoLuong.getText().equals("")){
//            JOptionPane.showMessageDialog(null, "Chưa nhập số người ở", "Lỗi", JOptionPane.ERROR_MESSAGE);
//            return false;
//        }else if(txtGia.getText().equals("")){
//            JOptionPane.showMessageDialog(null, "Chưa nhập giá phòng", "Lỗi", JOptionPane.ERROR_MESSAGE);
//            return false;
//        }else if(!cbFeMale.isSelected() && !cbMale.isSelected()){
//            JOptionPane.showMessageDialog(null, "Chưa chọn giới tính", "Lỗi", JOptionPane.ERROR_MESSAGE);
//            return false;
//        }
//        
//        return true;
//    
//        }
        public boolean validateForm(){
            boolean isValid =true;
            String maphong = txtMa.getText();
            String ten = txtTen.getText();
            String khuvuc = txtKhuvuc.getText();
            String loaiphong = txtLoaiPhong.getText();
            String Soluong = txtSoLuong.getText();
            String gia = txtGia.getText();
            String dientich= txtDientich.getText();
            
            // Kiểm tra trường Mã Phòng
                if(maphong.trim().isEmpty()){ // Dùng trim xóa khoảng trắng đầu cuối và xem có rỗng k
                    lbErrorMaPhong.setText("Vui lòng nhập mã phòng");
                    isValid=false;
                }else if (maphong.length() !=4){
                    lbErrorMaPhong.setText("Mã Phòng chỉ chứa 4 ký tự");
                    isValid = false;
                }
                else
                    lbErrorMaPhong.setText("");
                  
                // Kiểm tra tên phòng 
                 if(ten.trim().isEmpty()){
                    lbErrorTenPhong.setText("Vui lòng nhập Tên phòng");
                    isValid=false;
                 }
                 else
                    lbErrorTenPhong.setText("");
                 
                 // Kiểm tra khu vực
                  if(khuvuc.trim().isEmpty()){
                    lbErrorKhuvuc.setText("Vui lòng nhập khu vực");
                    isValid=false;
                 }             
                  else
                    lbErrorKhuvuc.setText("");
                  
                  // kiểm tra loại phòng
                   if(loaiphong.trim().isEmpty()){
                      lbErrorLoai.setText("Vui lòng đặt loại phòng");
                      isValid=false;
                  }
                   else
                    lbErrorLoai.setText("");
                   
                   if(Soluong.trim().isEmpty()){
                    lbErrorSoluong.setText("Vui lòng nhập số lượng");
                    isValid=false;
                 }
                   else
                    lbErrorSoluong.setText("");
                   if(gia.trim().isEmpty()){
                       lbErrorGia.setText("Vui lòng nhập giá phòng");
                       isValid=false;
                   }
                   else
                    lbErrorGia.setText("");
                   if(!cbFeMale.isSelected() && !cbMale.isSelected()){
                    lbErrorDoituong.setText("Vui lòng chọn đối tượng đặt phòng");
                    isValid=false;
                     }
                   else
                    lbErrorDoituong.setText("");
                   if(dientich.trim().isEmpty()){
                       lbErrorDientich.setText("Vui lòng nhập diện tích phòng");
                       isValid=false;
                   }
                    else{
                       try{
                           Integer dientich1= Integer.parseInt(dientich);
                           if(dientich1<0){
                               lbErrorDientich.setText("Diện tích không được âm");
                               isValid=false;
                           }
                           else {
                               lbErrorDientich.setText("");
                           }
                       }catch(NumberFormatException ex){
                           lbErrorDientich.setText("Vui lòng nhập số nguyên");
                           isValid = false;
                       }
                   }
                   
//                    lbErrorDientich.setText("");
                // Kiểm tra điện 
                if(txtDien.getText().equals("")){
                    lbErrorDien.setText("Vui lòng nhập số Điện");
                    isValid=false;
                } else {
                    try
                    {
                    long soDien = Long.parseLong(txtDien.getText());
                    if(soDien<0){
                        lbErrorDien.setText("Số Điện không được âm");
                        isValid=false;
                    }
                    else {
                        lbErrorDien.setText("");
                    }
                    }catch(NumberFormatException ex){
                        lbErrorDien.setText("Nhập đúng số nguyên dương");
                        isValid= false;
                    }
                }
                // Check tiền nước
                if(txtNuoc.getText().equals("")){
                    lbErrorNuoc.setText("Vui lòng nhập số Nước");
                    isValid=false;
                } else {
                    try
                    {
                    long soNuoc = Long.parseLong(txtNuoc.getText());
                    if(soNuoc<0){
                        lbErrorDien.setText("Số Nước không được âm");
                        isValid=false;
                    }
                    else{
                         lbErrorNuoc.setText("");
                            }
                    }catch(NumberFormatException ex){
                        lbErrorDien.setText("Nhập đúng số nguyên dương");
                        isValid= false;
                    }
                }
        return isValid;
           
        }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        group = new javax.swing.ButtonGroup();
        jLabel17 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtKhuvuc = new javax.swing.JTextField();
        txtSoLuong = new javax.swing.JTextField();
        txtGia = new javax.swing.JTextField();
        cbMale = new javax.swing.JCheckBox();
        cbFeMale = new javax.swing.JCheckBox();
        txtDientich = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtLoaiPhong = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtTen = new javax.swing.JTextField();
        btnAdd = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        lbErrorTenPhong = new javax.swing.JLabel();
        lbErrorKhuvuc = new javax.swing.JLabel();
        lbErrorLoai = new javax.swing.JLabel();
        lbErrorSoluong = new javax.swing.JLabel();
        lbErrorGia = new javax.swing.JLabel();
        lbErrorDoituong = new javax.swing.JLabel();
        lbErrorDientich = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtDien = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtNuoc = new javax.swing.JTextField();
        lbErrorDien = new javax.swing.JLabel();
        lbErrorNuoc = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtMa = new javax.swing.JTextField();
        lbErrorMaPhong = new javax.swing.JLabel();

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 0, 0));
        jLabel17.setText("jLabel7");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 204, 204));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Thêm mới phòng trọ");

        jPanel3.setBackground(new java.awt.Color(0, 153, 153));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)), "Thêm mới phòng trọ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(255, 255, 255))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Loại phòng :");
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Số lượng :");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Diện tích :");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Đối tượng :");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Giá thành :");

        txtKhuvuc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtKhuvucMouseClicked(evt);
            }
        });

        txtSoLuong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtSoLuongMouseClicked(evt);
            }
        });

        txtGia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtGiaMouseClicked(evt);
            }
        });

        cbMale.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cbMale.setForeground(new java.awt.Color(255, 255, 255));
        cbMale.setText("Nam");
        cbMale.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbMaleMouseClicked(evt);
            }
        });

        cbFeMale.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cbFeMale.setForeground(new java.awt.Color(255, 255, 255));
        cbFeMale.setText("Nữ");
        cbFeMale.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbFeMaleMouseClicked(evt);
            }
        });

        txtDientich.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtDientichMouseClicked(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel11.setText("m²");

        txtLoaiPhong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtLoaiPhongMouseClicked(evt);
            }
        });
        txtLoaiPhong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLoaiPhongActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel13.setText("Khu vực :");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel14.setText("Tên phòng :");

        txtTen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtTenMouseClicked(evt);
            }
        });

        btnAdd.setBackground(new java.awt.Color(0, 204, 204));
        btnAdd.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnAdd.setForeground(new java.awt.Color(255, 255, 255));
        btnAdd.setText("Thêm mới");
        btnAdd.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAdd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAddMouseEntered(evt);
            }
        });
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(0, 204, 204));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Quay Lại");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        lbErrorTenPhong.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        lbErrorTenPhong.setForeground(new java.awt.Color(255, 0, 0));
        lbErrorTenPhong.setText("Lavke");

        lbErrorKhuvuc.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        lbErrorKhuvuc.setForeground(new java.awt.Color(255, 0, 0));
        lbErrorKhuvuc.setText("Label");

        lbErrorLoai.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        lbErrorLoai.setForeground(new java.awt.Color(255, 0, 0));
        lbErrorLoai.setText("lable");

        lbErrorSoluong.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        lbErrorSoluong.setForeground(new java.awt.Color(255, 0, 0));
        lbErrorSoluong.setText("labek");

        lbErrorGia.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        lbErrorGia.setForeground(new java.awt.Color(255, 0, 0));
        lbErrorGia.setText("lable");

        lbErrorDoituong.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        lbErrorDoituong.setForeground(new java.awt.Color(255, 0, 0));
        lbErrorDoituong.setText("Label");

        lbErrorDientich.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        lbErrorDientich.setForeground(new java.awt.Color(255, 0, 0));
        lbErrorDientich.setText("Label");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("Số Điện :");

        txtDien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtDienMouseClicked(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("Số Nước :");

        txtNuoc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtNuocMouseClicked(evt);
            }
        });

        lbErrorDien.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        lbErrorDien.setForeground(new java.awt.Color(255, 0, 0));
        lbErrorDien.setText("Label");

        lbErrorNuoc.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        lbErrorNuoc.setForeground(new java.awt.Color(255, 0, 0));
        lbErrorNuoc.setText("Label");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel15.setText("Mã Phòng :");

        txtMa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtMaMouseClicked(evt);
            }
        });

        lbErrorMaPhong.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        lbErrorMaPhong.setForeground(new java.awt.Color(255, 0, 0));
        lbErrorMaPhong.setText("Lavke");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtLoaiPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(80, 80, 80)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtDien, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(169, 169, 169)
                        .addComponent(txtGia, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbErrorLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbErrorTenPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(btnAdd)
                                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(txtKhuvuc, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(lbErrorKhuvuc, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txtDientich, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(lbErrorDientich, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(19, 19, 19)
                                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbErrorMaPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtNuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lbErrorDien, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lbErrorNuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(lbErrorSoluong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(txtSoLuong, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lbErrorDoituong, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(cbMale, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(cbFeMale, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGap(123, 123, 123)
                                .addComponent(lbErrorGia, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(57, 57, 57)
                                .addComponent(jButton2)))))
                .addGap(0, 59, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbErrorSoluong, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbErrorMaPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbErrorGia)
                    .addComponent(lbErrorTenPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtKhuvuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbErrorKhuvuc, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbMale)
                            .addComponent(cbFeMale)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbErrorDoituong, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLoaiPhong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbErrorLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbErrorDien, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDientich, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbErrorDientich, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNuoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbErrorNuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(62, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 15, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddMouseEntered
        // TODO add your handling code here:
        this.setForeground(Color.red);
    }//GEN-LAST:event_btnAddMouseEntered

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
         // TODO add your handling code here:
        JfrmMainAdmin main;
        main = new JfrmMainAdmin();
        main.getTabbed().setSelectedIndex(1);
        main.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
//
    if(RoomDAO.checkMaPhong(txtMa.getText())){
        if(validateForm()){
            pt.setMa(txtMa.getText());
          pt.setDientich(Integer.parseInt(txtDientich.getText()));
            pt.setGia(Long.parseLong(txtGia.getText()));
            pt.setSoluong(Integer.parseInt(txtSoLuong.getText()));
      //      pt.setMa(txtMa.getText());
            pt.setKhuvuc(txtKhuvuc.getText());
            pt.setTen(txtTen.getText());
            pt.setLoai(txtLoaiPhong.getText());
            pt.setSodiennew(Long.parseLong(txtDien.getText()));
            pt.setSonuocnew(Long.parseLong(txtNuoc.getText()));
        
        String sex="";
        if(cbFeMale.isSelected())
            sex="Nữ";
        else
            sex="Nam";
        pt.setGioitinh(sex);
        try {
            themPT.addRoom(pt);
               
            JOptionPane.showMessageDialog(null, "Thêm phòng trọ thành công", "Success", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
        } catch (SQLException ex) {
            Logger.getLogger(JfrmAddroom.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
//         // Gọi phương thức cập nhật bảng từ form chính
//            if (main != null) {
//            try {
//                main.clearData();
//                main.fillTableRoom();
//            } catch (SQLException ex) {
//                Logger.getLogger(JfrmAddroom.class.getName()).log(Level.SEVERE, null, ex);
//                System.out.println("Lam moi bang that bai");
//            } catch (ClassNotFoundException ex) {
//                Logger.getLogger(JfrmAddroom.class.getName()).log(Level.SEVERE, null, ex);
//                System.out.println("Lam moi bang that bai");
//            }
//            }

      }else
         JOptionPane.showMessageDialog(null, "Không được trùng Mã Phòng", "Thông báo ", JOptionPane.ERROR_MESSAGE);
    }//GEN-LAST:event_btnAddActionPerformed

    private void txtLoaiPhongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLoaiPhongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLoaiPhongActionPerformed

    private void txtTenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTenMouseClicked
        // TODO add your handling code here:
        lbErrorTenPhong.setText("");
    }//GEN-LAST:event_txtTenMouseClicked

    private void txtKhuvucMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtKhuvucMouseClicked
        // TODO add your handling code here:
        lbErrorKhuvuc.setText("");
    }//GEN-LAST:event_txtKhuvucMouseClicked

    private void txtLoaiPhongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtLoaiPhongMouseClicked
        // TODO add your handling code here:
         lbErrorLoai.setText("");
    }//GEN-LAST:event_txtLoaiPhongMouseClicked

    private void txtSoLuongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtSoLuongMouseClicked
        // TODO add your handling code here:
        lbErrorSoluong.setText("");
    }//GEN-LAST:event_txtSoLuongMouseClicked

    private void txtGiaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtGiaMouseClicked
        // TODO add your handling code here:
        lbErrorGia.setText("");
    }//GEN-LAST:event_txtGiaMouseClicked

    private void txtDientichMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtDientichMouseClicked
        // TODO add your handling code here:
        lbErrorDientich.setText("");
    }//GEN-LAST:event_txtDientichMouseClicked

    private void cbMaleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbMaleMouseClicked
        // TODO add your handling code here:
        lbErrorDoituong.setText("");
    }//GEN-LAST:event_cbMaleMouseClicked

    private void cbFeMaleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbFeMaleMouseClicked
        // TODO add your handling code here:
          lbErrorDoituong.setText("");
    }//GEN-LAST:event_cbFeMaleMouseClicked

    private void txtDienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtDienMouseClicked
        // TODO add your handling code here:
        lbErrorDien.setText("");
    }//GEN-LAST:event_txtDienMouseClicked

    private void txtNuocMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtNuocMouseClicked
        // TODO add your handling code here:
        lbErrorNuoc.setText("");
    }//GEN-LAST:event_txtNuocMouseClicked

    private void txtMaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtMaMouseClicked
        // TODO add your handling code here:
        lbErrorMaPhong.setText("");
    }//GEN-LAST:event_txtMaMouseClicked

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
            java.util.logging.Logger.getLogger(JfrmAddroom.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JfrmAddroom.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JfrmAddroom.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JfrmAddroom.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new JfrmAddroom().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(JfrmAddroom.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(JfrmAddroom.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JCheckBox cbFeMale;
    private javax.swing.JCheckBox cbMale;
    private javax.swing.ButtonGroup group;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lbErrorDien;
    private javax.swing.JLabel lbErrorDientich;
    private javax.swing.JLabel lbErrorDoituong;
    private javax.swing.JLabel lbErrorGia;
    private javax.swing.JLabel lbErrorKhuvuc;
    private javax.swing.JLabel lbErrorLoai;
    private javax.swing.JLabel lbErrorMaPhong;
    private javax.swing.JLabel lbErrorNuoc;
    private javax.swing.JLabel lbErrorSoluong;
    private javax.swing.JLabel lbErrorTenPhong;
    private javax.swing.JTextField txtDien;
    private javax.swing.JTextField txtDientich;
    private javax.swing.JTextField txtGia;
    private javax.swing.JTextField txtKhuvuc;
    private javax.swing.JTextField txtLoaiPhong;
    private javax.swing.JTextField txtMa;
    private javax.swing.JTextField txtNuoc;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTen;
    // End of variables declaration//GEN-END:variables
}
