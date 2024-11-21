/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import Controller.HoaDonDAO;
import Controller.KhachThueDAO;
import Controller.RoomDAO;
import Controller.ThongKeDAO;
import Model.CurrentUser;
import Model.HoaDon;
import Model.KhachThue;
import Model.phongTro;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public final class JfrmMainAdmin extends javax.swing.JFrame {

    /**
     * Creates new form JfrmMainAdmin
     */
    
   DefaultTableModel tblModelRoom = new DefaultTableModel() {
    @Override
    public boolean isCellEditable(int row, int column) {
        // Không cho phép chỉnh sửa bất kỳ ô nào
        return false;
    }
};
    int month = LocalDate.now().getMonthValue();
    int year = LocalDate.now().getYear();
    private final DefaultTableModel tblTableModelKhachThue = new DefaultTableModel(){
        @Override
    public boolean isCellEditable(int row, int column) {
        // Không cho phép chỉnh sửa bất kỳ ô nào
        return false;
    }
    };
    private  final DefaultTableModel tblModelHoaDon = new DefaultTableModel(){
        @Override
    public boolean isCellEditable(int row, int column) {
        // Không cho phép chỉnh sửa bất kỳ ô nào
        return false;
    }
    };
    
    private final DefaultTableModel tblModelDien = new DefaultTableModel(){
         public boolean isCellEditable(int row, int column) {
        // Không cho phép chỉnh sửa bất kỳ ô nào
        return false;
    }
    };
    private final  DefaultTableModel tblModelNuoc = new DefaultTableModel(){
         public boolean isCellEditable(int row, int column) {
        // Không cho phép chỉnh sửa bất kỳ ô nào
        return false;
    }
    };
    phongTro pt = new phongTro();
   private final RoomDAO room = new RoomDAO();
    HoaDonDAO hdd = new HoaDonDAO();
    
    //Jfr_Tab_Thong_Ke thongke = new Jfr_Tab_Thong_Ke();
    
   //private final  RoomDAO rooms = null;
 
//    private JTabbedPane tabbed;
    public JfrmMainAdmin() {
        try {
            initComponents();
            this.setLocationRelativeTo(null);
            initTableRoom();
            fillTableRoom();
            //InitHoaDon();
            // Khởi tạo table Điện-Nước :
            initTableDien();
            initTableNuoc();
            // Thông tin điện nước :
            fillTableDien("N");
            group.add(cbKFemale);
            group.add(cbKMale);
            group1.add(cbMale);
            group1.add(cbFeMale);
            // Thông tin Khách hàng
            InitTableKhach();
            fillTableKhach();
            // Hóa Đơn :
            InitHoaDon();
            fillHoaDon(HoaDonDAO.getAllHD());
               
           
        } catch (SQLException ex) {
            Logger.getLogger(JfrmMainAdmin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JfrmMainAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
            // Hiển thị thống kê khách hàng 
            int  countTK_Phongtrong = ThongKeDAO.getPhongTrong();
            lbPhongtrong.setText("" + countTK_Phongtrong +" Phòng");
            lbTKdangthue.setText(""+ ThongKeDAO.getPhongThue() + " Phòng");
            lbTKcountKNo.setText(""+ThongKeDAO.getCountKhachNo() + " Khách");
             DecimalFormat decimal = new DecimalFormat("###,###,###");
            lbTKTongDT.setText(""+ decimal.format(ThongKeDAO.Count_DoanhThu())+ " VNĐ");
            // Hóa Đơn
            Init_cbx();
          // tabbed = new JTabbedPane();
    }

    public JTabbedPane getTabbed() {
        return tabbed;
    }
    // Khởi tạo Table Room
   // Khởi tạo Table Room
public void initTableRoom() {
    // Tạo một DefaultTableModel tùy chỉnh   
    // Khai báo tiêu đề cột
    String[] colsName = {"Mã phòng", "Tên", "Khu vực", "Loại phòng", "Số người", "Giá", "Đối tượng", "Diện tích", "Tình Trạng"};
    tblModelRoom.setColumnIdentifiers(colsName);

    // Gắn mô hình (model) vào bảng (table)
    tbRoom.setModel(tblModelRoom);
}

   public void fillTableRoom() throws SQLException, ClassNotFoundException {
    tblModelRoom.setRowCount(0);  // Xóa tất cả các dòng cũ trong bảng
             List<phongTro> rooms = RoomDAO.getInforRoom();
    // Nếu không có phòng nào trong danh sách
    if (rooms == null || rooms.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Không có dữ liệu phòng trọ!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        return;
    }

    // Thêm từng phòng vào bảng
    for (phongTro ptro : rooms) {
        
        // Kiểm tra trường hợp null hoặc dữ liệu không hợp lệ (nếu cần)
        if (ptro.getMa() != null && ptro.getTen() != null) {
            tblModelRoom.addRow(new String[] {
                ptro.getMa(),                 // Mã phòng
                ptro.getTen(),                // Tên phòng
                ptro.getKhuvuc(),             // Khu vực
                ptro.getLoai(),               // Loại phòng
                String.valueOf(ptro.getSoluong()), // Số lượng phòng
                String.valueOf(ptro.getGia()),     // Giá
                ptro.getGioitinh(),           // Giới tính
                String.valueOf(ptro.getDientich()), // Diện tích
                 ptro.getTinhtrang(), //  Tình trạng
            });
        }
    }
    }
    // Lấy thông tin các phòng từ DAO

    // Khởi Tạo Table Khách Thuê 
    public void InitTableKhach(){
        String[] obj ={ "Mã Khách", "Họ Tên","DOB","Giới tính ","SDT", "Quê Quán ", " Mã Phòng"};
        tblTableModelKhachThue.setColumnIdentifiers(obj);
        tbKhachThue.setModel(tblTableModelKhachThue);
        
    }
    // Truy xuất ra bảng table Khách 
    public void fillTableKhach(){
        List<KhachThue> lkt = KhachThueDAO.getInforKhach();
        for(KhachThue kt : lkt){
            tblTableModelKhachThue.addRow(new Object[]{
                kt.getMaKT(),
                kt.getHoTen(),
                kt.getNgaySinh(),             
                kt.getGioiTinh(),
                kt.getSdt(),
                kt.getQueQuan(), // Xíu chỉnh sửa thành Nơi Thuê 
                kt.getMaPhong(),
            });
        }
    }
    
    // Khởi tạo bảng table Hóa điện  
    public void initTableDien(){
        String[] obj = {"STT","Mã Phòng","Chỉ Số điện mới","Chỉ số điện cũ","Thành tiền"};
        tblModelDien.setColumnIdentifiers(obj);
        tbDien.setModel(tblModelDien);
    }
    // Thêm giá trị vào bảng điện 
    public void fillTableDien(String txt){
        try {
            tblModelDien.setRowCount(0);
          //  clearData();
            List<phongTro> lst = RoomDAO.getDienNuoc();
           // JOptionPane.showConfirmDialog(null, ""+lst);
            int i=1;
            if(lst.isEmpty()) System.out.println("Hàm rỗng");
            else{
            switch(txt){
                case "O":
                  for(phongTro pt : lst){
                tblModelDien.addRow(new String[]{
                    ""+i++,
                    pt.getMa(),
                   ""+  pt.getSodiennew(),
                    "" + pt.getSodienold(),
                   ""+ pt.tienDien(),
                }
                );
            }
                  break;
                case "N": 
                      for(phongTro pt : lst){
                 tblModelDien.addRow(new String[]{
                    ""+i++,
                    pt.getMa(),
                   ""+  pt.getSodiennew(),
                    ""+ 0,
                   ""+ pt.tienDien(),
                }
                );
                    }
                      break;
               // i++;
            }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Them table hoa don dien that bai");
        }
    }
 // Thêm giá trị vào bảng Nước
    public void fillTableNuoc(String txt){
        try {
            clearData();
            List<phongTro> lst = RoomDAO.getInforRoom();
            int i=0;
            if(txt.equals("")){
                 for(phongTro pt : lst){
                tblModelDien.addRow(new String[]{
                    ""+i,
                    pt.getMa(),
                   ""+  pt.getSonuocnew(),
                    "" + pt.getSonuocold(),
                   ""+ pt.tienNuoc(),
                });
            }
                }else {
                 for(phongTro pt : lst){
                tblModelDien.addRow(new String[]{
                    ""+i,
                    pt.getMa(),
                   ""+  pt.getSonuocnew(),
                   ""+ 0,
                   ""+ pt.tienNuoc(),
                });
                 }}
                i++;
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Them table hoa don dien that bai");
        }
    }    
    // Khởi tạo bảng table hóa đơn nước
     public void initTableNuoc(){
        String[] obj = {"STT","Mã Phòng","Chỉ Số nước mới","Chỉ số nước cũ","Thành tiền"};
        tblModelNuoc.setColumnIdentifiers(obj);
        tbNuoc.setModel(tblModelNuoc);
    }
  //    Tạo bảng Hóa Đơn
//     public void initHoaDon(){
//         String[] obj ={"Mã Khách","Mã Phòng","Mã Hợp Đồng"};
//         tblModelHoaDon.setColumnIdentifiers(obj);
//         tbHoaDon.setModel(tblModelHoaDon);
//
//     }
     
//     public void setError_D(String txt){
//         switch(txt){ // Enabke
//            case("E"):
//                btnNewMonthD.setVisible(true);
//                btnResetD.setVisible(true);
//                break;
//            
//            case("D"): // Disable
//                btnNewMonthD.setVisible(false);
//                btnResetD.setVisible(false);
//                break;
//            
//         }
//         
//     }
//      public void setError_N(String txt){
//         switch(txt){ // Enabke
//            case("E"):
//                btnNewmonthNuoc.setVisible(true);
//                btnResetN.setVisible(true);
//                break;
//            
//            case("D"): // Disable
//                btnNewmonthNuoc.setVisible(false);
//                btnResetN.setVisible(false);
//                break;
//            
//         }
//         
//     }

   //    Khởi tạo bảng Hóa Đơn
    public void InitHoaDon(){
        String[] obj = {
            "Mã Hoá Đơn","Phòng","Tên Khách","Tiền Phòng","Tiền Điện","Tiền Nước","Tiền Dịch Vụ","Tổng Cộng","Thời Gian"
        };
        tblModelHoaDon.setColumnIdentifiers(obj);
        tbHoaDon.setModel(tblModelHoaDon);
//        fillHoaDon(hdd.getAllHD());
    }
    // Tạo data Hóa Đơn
    public   void fillHoaDon(List<HoaDon> hoadon) {
    //    List<HoaDon> hoadon = HoaDonDAO.getAllHD();
        tblModelHoaDon.setRowCount(0);
        for (HoaDon hd : hoadon) {
            tblModelHoaDon.addRow(new Object[]{
                hd.getMaHD(),
                hd.getMaPhong(),
                hd.getTenKT(),
                hd.getGiaThue(),
                hd.getTienDien(),
                hd.getTienNuoc(),
                hd.getTienDV(),
                hd.getTongTien(),
                hd.getDate()
            });
        }
    }
     private void Init_cbx() {
        for (int i = 1; i <= 12; i++) {
            cbxMonth.addItem("" + i);
        }
        cbxMonth.setSelectedItem("" + month);
        for (int i = year; i >= 2000; i--) {
            cbxYear.addItem("" + i);
        }
        cbxYear.setSelectedItem("" + year);
    }

    public void clearData(){
        int n=tblModelRoom.getRowCount()-1;
        for (int i=n;i>=0;i--)
                tblModelRoom.removeRow(i);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        group = new javax.swing.ButtonGroup();
        group1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtClose = new javax.swing.JLabel();
        tabbed = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        lbTKTongDT = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        lbPhongtrong = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        lbTKdangthue = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        lbTKcountKNo = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbRoom = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtMa = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtTen = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtKhuvuc = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtLoaiPhong = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtSoLuong = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtGia = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        cbMale = new javax.swing.JCheckBox();
        cbFeMale = new javax.swing.JCheckBox();
        jLabel7 = new javax.swing.JLabel();
        txtDientich = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        btnXoaupdateRoom = new javax.swing.JButton();
        btnChinhsua = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        btnDeleteRoom = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbKhachThue = new javax.swing.JTable();
        jButton5 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jPanel21 = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        txtMaKhach = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        txtNameK = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        cbKMale = new javax.swing.JCheckBox();
        cbKFemale = new javax.swing.JCheckBox();
        jLabel30 = new javax.swing.JLabel();
        txtNgay = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        jPanel23 = new javax.swing.JPanel();
        btnUpdate = new javax.swing.JButton();
        btnUpdate1 = new javax.swing.JButton();
        btnUpdate2 = new javax.swing.JButton();
        jLabel29 = new javax.swing.JLabel();
        txtStartday = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        txtMaP = new javax.swing.JTextField();
        txtEndday = new javax.swing.JTextField();
        jPanel17 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbDien = new javax.swing.JTable();
        btnNewMonthD = new javax.swing.JButton();
        btnResetD = new javax.swing.JButton();
        btnTinhTien = new javax.swing.JButton();
        jPanel20 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbNuoc = new javax.swing.JTable();
        btnNewmonthNuoc = new javax.swing.JButton();
        btnResetN = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jPanel15 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        cbxMonth = new javax.swing.JComboBox<>();
        jLabel25 = new javax.swing.JLabel();
        cbxYear = new javax.swing.JComboBox<>();
        btnSearch_HD = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        btnDel1 = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        tbHoaDon = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(0, 153, 153));

        jLabel1.setBackground(new java.awt.Color(0, 153, 153));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 26)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Ứng Dụng Quản Lý Phòng Trọ");

        txtClose.setBackground(new java.awt.Color(255, 102, 0));
        txtClose.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtClose.setForeground(new java.awt.Color(255, 255, 255));
        txtClose.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtClose.setText("X");
        txtClose.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        txtClose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtCloseMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(txtClose, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 968, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(txtClose, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        jPanel6.setBackground(new java.awt.Color(0, 153, 153));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Thống Kê Phòng Trọ");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Tổng Doanh Thu");

        jPanel8.setBackground(new java.awt.Color(0, 153, 153));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        lbTKTongDT.setBackground(new java.awt.Color(255, 255, 255));
        lbTKTongDT.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lbTKTongDT.setForeground(new java.awt.Color(0, 153, 153));
        lbTKTongDT.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTKTongDT.setText("jLabel15");
        lbTKTongDT.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lbTKTongDT.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbTKTongDT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbTKTongDTMouseClicked(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Số Phòng Còn Trống");

        jPanel9.setBackground(new java.awt.Color(0, 153, 153));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 57, Short.MAX_VALUE)
        );

        lbPhongtrong.setBackground(new java.awt.Color(255, 255, 255));
        lbPhongtrong.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lbPhongtrong.setForeground(new java.awt.Color(0, 153, 153));
        lbPhongtrong.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbPhongtrong.setText("JLabel15");
        lbPhongtrong.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lbPhongtrong.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbPhongtrong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbPhongtrongMouseClicked(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("Tổng Khách Nợ");

        jPanel10.setBackground(new java.awt.Color(0, 153, 153));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 60, Short.MAX_VALUE)
        );

        lbTKdangthue.setBackground(new java.awt.Color(255, 255, 255));
        lbTKdangthue.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lbTKdangthue.setForeground(new java.awt.Color(0, 153, 153));
        lbTKdangthue.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTKdangthue.setText("jLabel15");
        lbTKdangthue.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lbTKdangthue.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbTKdangthue.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbTKdangthueMouseClicked(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("Số Phòng Đang Thuê");

        jPanel11.setBackground(new java.awt.Color(0, 153, 153));

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        lbTKcountKNo.setBackground(new java.awt.Color(255, 255, 255));
        lbTKcountKNo.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lbTKcountKNo.setForeground(new java.awt.Color(0, 153, 153));
        lbTKcountKNo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTKcountKNo.setText("jLabel15");
        lbTKcountKNo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lbTKcountKNo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbTKcountKNo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbTKcountKNoMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(141, 141, 141)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbTKTongDT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbTKdangthue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE))
                .addGap(198, 198, 198)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
                    .addComponent(lbTKcountKNo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbPhongtrong, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(90, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbPhongtrong, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbTKTongDT, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbTKdangthue, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbTKcountKNo, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(101, 101, 101))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        tabbed.addTab("Thống Kê", jPanel2);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jPanel4.setBackground(new java.awt.Color(0, 153, 153));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh Sách Phòng Trọ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(255, 255, 255))); // NOI18N

        tbRoom.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tbRoom.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã phòng", "Tên ", "Khu vực", "Loại phòng", "Số người", "Giá", "Đối tượng", "Diện tích", "Tình trạng"
            }
        ));
        tbRoom.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbRoomMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tbRoom);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 753, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(0, 153, 153));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chỉnh sửa phòng trọ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(255, 255, 255))); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Mã phòng :");
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel14.setText("Tên phòng :");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel13.setText("Khu vực :");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Loại phòng :");
        jLabel4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Số lượng :");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Giá thành :");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Đối tượng :");

        cbMale.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cbMale.setForeground(new java.awt.Color(255, 255, 255));
        cbMale.setText("Nam");

        cbFeMale.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cbFeMale.setForeground(new java.awt.Color(255, 255, 255));
        cbFeMale.setText("Nữ");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Diện tích :");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel11.setText("m²");

        jButton3.setBackground(new java.awt.Color(0, 204, 204));
        jButton3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Lưu");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        btnXoaupdateRoom.setBackground(new java.awt.Color(204, 0, 0));
        btnXoaupdateRoom.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnXoaupdateRoom.setForeground(new java.awt.Color(255, 255, 255));
        btnXoaupdateRoom.setText("Xóa ");
        btnXoaupdateRoom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaupdateRoomActionPerformed(evt);
            }
        });

        btnChinhsua.setBackground(new java.awt.Color(51, 255, 204));
        btnChinhsua.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnChinhsua.setForeground(new java.awt.Color(255, 255, 255));
        btnChinhsua.setText("Chỉnh sửa");
        btnChinhsua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChinhsuaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(112, 112, 112)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txtLoaiPhong))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txtKhuvuc, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(61, 61, 61)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtSoLuong))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbMale, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbFeMale, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDientich, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtGia, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnChinhsua, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnXoaupdateRoom, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(59, 59, 59))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel5Layout.createSequentialGroup()
                                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(42, 42, 42))
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtKhuvuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbMale)
                            .addComponent(cbFeMale)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(btnChinhsua, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtLoaiPhong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDientich, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(42, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnXoaupdateRoom, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23))))
        );

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 153, 153));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Thông Tin Phòng Trọ");

        jButton2.setBackground(new java.awt.Color(0, 153, 153));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Thêm phòng trọ");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        btnDeleteRoom.setBackground(new java.awt.Color(204, 0, 0));
        btnDeleteRoom.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnDeleteRoom.setForeground(new java.awt.Color(255, 255, 255));
        btnDeleteRoom.setText("Xóa phòng trọ");
        btnDeleteRoom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteRoomActionPerformed(evt);
            }
        });

        jButton6.setBackground(new java.awt.Color(0, 204, 204));
        jButton6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("Cập nhập");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 957, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(20, 20, 20))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(205, 205, 205)
                .addComponent(jButton2)
                .addGap(76, 76, 76)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(97, 97, 97)
                .addComponent(btnDeleteRoom)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnDeleteRoom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );

        tabbed.addTab("Quản Lý Phòng Trọ", jPanel3);

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(0, 153, 153));
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("Thông Tin Khách Thuê");

        jPanel14.setBackground(new java.awt.Color(0, 153, 153));
        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin Khách hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(255, 255, 255))); // NOI18N

        tbKhachThue.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbKhachThue.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbKhachThueMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbKhachThue);

        jButton5.setBackground(new java.awt.Color(0, 204, 204));
        jButton5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("Cập Nhập");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton7.setBackground(new java.awt.Color(0, 204, 204));
        jButton7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setText("Thanh Toán");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 859, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(181, 181, 181)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(199, 199, 199))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(102, 102, 102))
        );

        jPanel21.setBackground(new java.awt.Color(0, 204, 204));
        jPanel21.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chỉnh sửa ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(255, 255, 255))); // NOI18N

        jPanel22.setBackground(new java.awt.Color(0, 153, 153));

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel23.setText("Mã Khách Hàng :");

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel26.setText("Họ Tên :");

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel27.setText("SDT :");

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel28.setText("Giới tính :");

        cbKMale.setBackground(new java.awt.Color(0, 153, 153));
        cbKMale.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cbKMale.setForeground(new java.awt.Color(255, 255, 255));
        cbKMale.setText("Nam");

        cbKFemale.setBackground(new java.awt.Color(0, 153, 153));
        cbKFemale.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cbKFemale.setForeground(new java.awt.Color(255, 255, 255));
        cbKFemale.setText("Nữ");

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel30.setText("Ngày Sinh :");

        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 255, 255));
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel32.setText("Mã Phòng :");

        jPanel23.setBackground(new java.awt.Color(0, 153, 153));
        jPanel23.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnUpdate.setBackground(new java.awt.Color(0, 204, 204));
        btnUpdate.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnUpdate.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdate.setText("Chỉnh Sửa");

        btnUpdate1.setBackground(new java.awt.Color(255, 0, 0));
        btnUpdate1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnUpdate1.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdate1.setText("Xóa");

        btnUpdate2.setBackground(new java.awt.Color(0, 255, 204));
        btnUpdate2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnUpdate2.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdate2.setText("Lưu");

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnUpdate2, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUpdate1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(66, Short.MAX_VALUE))
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnUpdate2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addComponent(btnUpdate1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel29.setText("Ngày Kết Thúc :");

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel33.setText("Ngày Thuê :");

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtStartday, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createSequentialGroup()
                        .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNameK, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createSequentialGroup()
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMaKhach, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57)
                        .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addComponent(cbKMale, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbKFemale, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaP, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEndday, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbKMale, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtMaKhach, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cbKFemale, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNameK, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMaP, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtStartday, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEndday, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(55, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 957, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        tabbed.addTab("Thông tin Khách Thuê", jPanel12);

        jPanel18.setBackground(new java.awt.Color(255, 255, 255));

        jLabel10.setBackground(new java.awt.Color(0, 204, 204));
        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 204, 204));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Hóa Đơn Điện-Nước");

        jPanel19.setBackground(new java.awt.Color(0, 153, 153));
        jPanel19.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hóa Đơn Điện", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(255, 255, 255))); // NOI18N

        tbDien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã Phòng", "Chỉ số điện mới", "Chỉ số điện cũ", "Thành tiền"
            }
        ));
        jScrollPane2.setViewportView(tbDien);

        btnNewMonthD.setBackground(new java.awt.Color(0, 204, 204));
        btnNewMonthD.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnNewMonthD.setForeground(new java.awt.Color(255, 255, 255));
        btnNewMonthD.setText("Tính Tháng Mới");
        btnNewMonthD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewMonthDActionPerformed(evt);
            }
        });

        btnResetD.setBackground(new java.awt.Color(153, 153, 153));
        btnResetD.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnResetD.setForeground(new java.awt.Color(255, 255, 255));
        btnResetD.setText("Reset");
        btnResetD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetDActionPerformed(evt);
            }
        });

        btnTinhTien.setBackground(new java.awt.Color(0, 204, 204));
        btnTinhTien.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnTinhTien.setForeground(new java.awt.Color(255, 255, 255));
        btnTinhTien.setText("Tính Tiền Điện");
        btnTinhTien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTinhTienActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 815, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(65, Short.MAX_VALUE))
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(btnNewMonthD)
                        .addGap(148, 148, 148)
                        .addComponent(btnResetD, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnTinhTien)
                        .addGap(85, 85, 85))))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnTinhTien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addComponent(btnNewMonthD, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 4, Short.MAX_VALUE))
                    .addComponent(btnResetD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel20.setBackground(new java.awt.Color(0, 153, 153));
        jPanel20.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hóa Đơn Điện", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(255, 255, 255))); // NOI18N

        tbNuoc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã Phòng", "Chỉ số nước mới", "Chỉ số nước cũ", "Thành tiền"
            }
        ));
        jScrollPane3.setViewportView(tbNuoc);

        btnNewmonthNuoc.setBackground(new java.awt.Color(0, 204, 204));
        btnNewmonthNuoc.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnNewmonthNuoc.setForeground(new java.awt.Color(255, 255, 255));
        btnNewmonthNuoc.setText("Tính Tháng Mới");

        btnResetN.setBackground(new java.awt.Color(153, 153, 153));
        btnResetN.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnResetN.setForeground(new java.awt.Color(255, 255, 255));
        btnResetN.setText("Reset");

        jButton9.setBackground(new java.awt.Color(0, 204, 204));
        jButton9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton9.setForeground(new java.awt.Color(255, 255, 255));
        jButton9.setText("Tính Tiền Nước");

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(btnNewmonthNuoc)
                .addGap(178, 178, 178)
                .addComponent(btnResetN, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton9)
                .addGap(80, 80, 80))
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 824, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(51, Short.MAX_VALUE))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNewmonthNuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnResetN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 930, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(48, 48, 48))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(185, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        tabbed.addTab("Điện-Nước", jPanel17);

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));

        jLabel31.setBackground(new java.awt.Color(255, 255, 255));
        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(0, 204, 204));
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel31.setText("CHI TIẾT HOÁ ĐƠN THEO THÁNG");

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel24.setText("Chọn Tháng :");

        cbxMonth.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cbxMonth.setFocusable(false);
        cbxMonth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxMonthActionPerformed(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel25.setText("Chọn Năm :");

        cbxYear.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cbxYear.setFocusable(false);
        cbxYear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxYearActionPerformed(evt);
            }
        });

        btnSearch_HD.setBackground(new java.awt.Color(0, 153, 153));
        btnSearch_HD.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnSearch_HD.setForeground(new java.awt.Color(255, 255, 255));
        btnSearch_HD.setText("Tìm Hoá Đơn");
        btnSearch_HD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearch_HDActionPerformed(evt);
            }
        });

        btnReset.setBackground(new java.awt.Color(0, 153, 153));
        btnReset.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnReset.setForeground(new java.awt.Color(255, 255, 255));
        btnReset.setText("Cập nhập");
        btnReset.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        btnAdd.setBackground(new java.awt.Color(0, 153, 153));
        btnAdd.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnAdd.setForeground(new java.awt.Color(255, 255, 255));
        btnAdd.setText("Xem thông tin");
        btnAdd.setToolTipText("");
        btnAdd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnDel1.setBackground(new java.awt.Color(255, 51, 51));
        btnDel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnDel1.setForeground(new java.awt.Color(255, 255, 255));
        btnDel1.setText("Xoá");
        btnDel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDel1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDel1ActionPerformed(evt);
            }
        });

        tbHoaDon.setBackground(new java.awt.Color(0, 153, 153));
        tbHoaDon.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tbHoaDon.setForeground(new java.awt.Color(255, 255, 255));
        tbHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Hoá Đơn", "Phòng", "Tên Khách", "Tiền Phòng", "Tiền Điện", "Tiền Nước", "Tiền Dịch Vụ", "Tổng Cộng", "Thời Gian"
            }
        ));
        tbHoaDon.setGridColor(new java.awt.Color(153, 153, 255));
        tbHoaDon.setRowHeight(25);
        tbHoaDon.setSelectionBackground(new java.awt.Color(255, 153, 153));
        tbHoaDon.getTableHeader().setResizingAllowed(false);
        tbHoaDon.getTableHeader().setReorderingAllowed(false);
        tbHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbHoaDonMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tbHoaDon);

        jButton1.setBackground(new java.awt.Color(0, 153, 153));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Xuất Hóa Đơn");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(cbxMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbxYear, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)
                                .addComponent(btnSearch_HD))
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addGap(133, 133, 133)
                                .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(326, 326, 326)
                                .addComponent(btnDel1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 942, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addGap(127, 127, 127)
                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel31)
                .addGap(36, 36, 36)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxYear, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch_HD, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabbed.addTab("Hóa Đơn", jPanel15);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(tabbed, javax.swing.GroupLayout.PREFERRED_SIZE, 968, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(tabbed, javax.swing.GroupLayout.PREFERRED_SIZE, 720, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtCloseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtCloseMouseClicked
        // TODO add your handling code here:
       int confirm = JOptionPane.showConfirmDialog(null, "Bạn có muốn thoát khỏi chương trình", "Thông Báo", JOptionPane.YES_NO_OPTION);
         if(confirm == JOptionPane.YES_OPTION)
             this.dispose();
    }//GEN-LAST:event_txtCloseMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        int row = tbHoaDon.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(rootPane, "Chưa chọn hóa đơn!");
        } else {
            String maHD = String.valueOf(tbHoaDon.getValueAt(row, 0));
            List<HoaDon> result = HoaDonDAO.Search_HD(maHD);
            if (result.isEmpty()) {
                JOptionPane.showMessageDialog(rootPane, "Không tìm thấy hóa đơn với mã: " + maHD);
                return;
            }
            try {
                String filePath = HoaDonDAO.Export(result); // Xuất file và lấy đường dẫn
                JOptionPane.showMessageDialog(rootPane, "Xuất hóa đơn thành công! File được lưu tại: " + filePath);
                HoaDonDAO.deleteHoaDon(maHD);
                KhachThueDAO.deleteKhachThue(maHD);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane, "Lỗi khi xuất hóa đơn: " + e.getMessage());
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tbHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbHoaDonMouseClicked

    }//GEN-LAST:event_tbHoaDonMouseClicked

    private void btnDel1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDel1ActionPerformed
        //        int choose = JOptionPane.showConfirmDialog(rootPane, "Bạn Có Muốn Xoá Không?", "Xác Nhận", JOptionPane.YES_NO_OPTION);
        //        int row = tbHoaDon.getSelectedRow();
        //        if (choose == JOptionPane.YES_OPTION) {
            //            String maHD = String.valueOf(tbHoaDon.getValueAt(row, 0));
            //            hdd.deleteHoaDon(maHD);
            //            if (hdd.rs > 0) {
                //                Init_tbHoaDon();
                //                hoadon.rs = 0;
                //            }
            //        }
    }//GEN-LAST:event_btnDel1ActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        //       JfrmAddHoaDon add = new JfrmAddHoaDon();
        //        add.setVisible(true);
        //        this.dispose();
        // TODO add your handling code here:
        int row = tbHoaDon.getSelectedRow();
        String mahd = tblModelHoaDon.getValueAt(row, 0).toString();
        CurrentUser.setMahd(mahd);
        HoaDon p = HoaDonDAO.Search_HD3(mahd);
        JfrmHoaDon hd = new JfrmHoaDon();
        hd.getHoadon(p);
        hd.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        //       InitHoaDon();
        tblModelHoaDon.setRowCount(0);
        fillHoaDon(HoaDonDAO.getAllHD());
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnSearch_HDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearch_HDActionPerformed
        String thang = cbxMonth.getSelectedItem().toString();
        String nam = cbxYear.getSelectedItem().toString();
        fillHoaDon(HoaDonDAO.searchHD2(thang, nam));
    }//GEN-LAST:event_btnSearch_HDActionPerformed

    private void cbxYearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxYearActionPerformed
        //        String thang = cbxMonth.getSelectedItem().toString();
        //        String nam = cbxYear.getSelectedItem().toString();
        //        setDataTableHoaDon(hoadon.Search_HD2(thang, nam));
    }//GEN-LAST:event_cbxYearActionPerformed

    private void cbxMonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxMonthActionPerformed
        //        String thang = cbxMonth.getSelectedItem().toString();
        //        String nam = "2021";
        //        String nam = cbxYear.getSelectedItem().toString();
        //        setDataTableHoaDon(hoadon.Search_HD2(thang, nam));
    }//GEN-LAST:event_cbxMonthActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        try {
            // TODO add your handling code here:
            // Sử dụng khi muốn thanh toán phòng
            int row = tbKhachThue.getSelectedRow(); // Lấy chỉ số dòng được chọn
            if (row == -1) {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn phòng cần thanh toán", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            int rowmaPhong = tbKhachThue.getSelectedRow(); // lấy chỉ số mã Phòng
            String maphong = tbKhachThue.getValueAt(row, 6).toString();
            KhachThueDAO.updateTinhTrangPhong(pt, maphong);

            String makt = tbKhachThue.getValueAt(row, 0).toString();
            int confirm = JOptionPane.showConfirmDialog(null,
                "Bạn có chắc muốn thanh toán với khách có mã là: " + makt + " không?",
                "Xác nhận thanh toán khách", JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                KhachThueDAO.getHoadon(makt); // Gọi phương thức tạo hóa đơn
                tblTableModelKhachThue.setRowCount(0);

                fillTableKhach(); // Cập nhật lại bảng
            } else {
                JOptionPane.showMessageDialog(null, "Không thể tạo hóa đơn. Vui lòng kiểm tra lại.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(JfrmMainAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        tblTableModelKhachThue.setRowCount(0);
        fillTableKhach();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void tbKhachThueMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbKhachThueMouseClicked
        // TODO add your handling code here:
        int row = tbKhachThue.getSelectedRow(); // Khi người dùng click vào table thì sẽ hiện ra thông tin người đó
        String maKT= tbKhachThue.getValueAt(row, 0).toString();
        KhachThue kt= null;
        kt = new KhachThue();
        kt = KhachThueDAO.getKhachThue(maKT);
        if(kt != null){
            txtMaKhach.setText(kt.getMaKT());
            txtNameK.setText(kt.getHoTen());
            txtNgay.setText(kt.getNgaySinh());
            txtStartday.setText(kt.getStartDay());
            txtEndday.setText(kt.getEndDay());
            txtSDT.setText(kt.getSdt());
            txtMaP.setText(kt.getMaPhong());
            if(kt.getGioiTinh().equals("Nam")){
                    cbKMale.setSelected(true);
                    cbKFemale.setSelected(false);
                }
                else{
                  cbKFemale.setSelected(true);
                   cbKMale.setSelected(false);
                }
        }
    }//GEN-LAST:event_tbKhachThueMouseClicked

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        try {
            // TODO add your handling code here:
            fillTableRoom();
        } catch (SQLException ex) {
            Logger.getLogger(JfrmMainAdmin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JfrmMainAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton6ActionPerformed

// Event xóa thông tin phòng trọ
    private void btnDeleteRoomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteRoomActionPerformed
        // TODO add your handling code here:
        int row = tbRoom.getSelectedRow();
        String maphong = tbRoom.getModel().getValueAt(row, 0).toString();
        if(row==-1)
        JOptionPane.showMessageDialog(null, " Vui lòng chọn phòng cần xóa", "Title", JOptionPane.INFORMATION_MESSAGE);
        else{

            int confirm =  JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa phòng có mã là : "+ maphong+ " không","Xác nhận xóa phòng",JOptionPane.YES_NO_OPTION);
            if(confirm == JOptionPane.YES_OPTION){
                try {
                    // Object maphong = tbRoom.getValueAt(row, 0);
                    room.deleteRoom(maphong);
                    clearData();
                    try {
                        fillTableRoom();
                        //    rooms.deleteRoom(maphong);
                    } catch (ClassNotFoundException ex) {
                        ex.printStackTrace();
                    }

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Phòng " + maphong + " vẫn còn khách đang thuê", "Lỗi", JOptionPane.ERROR_MESSAGE);          }

            }
        }
    }//GEN-LAST:event_btnDeleteRoomActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            // TODO add your handling code here:
            new JfrmAddroom().setVisible(true);
            clearData();
            fillTableRoom();
        } catch (SQLException ex) {
            ex.printStackTrace();

        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            System.out.println("Khong tim thay class main");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnXoaupdateRoomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaupdateRoomActionPerformed
        // TODO add your handling code here:
        txtMa.setText("");
        txtTen.setText("");
        txtKhuvuc.setText("");
        txtLoaiPhong.setText("");
        txtSoLuong.setText("");
        txtGia.setText("");
        if(cbFeMale.isSelected() || cbMale.isSelected()){
            cbMale.setSelected(false);
            cbFeMale.setSelected(false);
        }
        txtDientich.setText("");
    }//GEN-LAST:event_btnXoaupdateRoomActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
       try {
            // Lấy mã phòng đã chọn từ JTable
            int row = tbRoom.getSelectedRow();  // Lấy chỉ số dòng đã chọn
            String maphong = tbRoom.getModel().getValueAt(row, 0).toString();  // Lấy mã phòng từ cột đầu tiên

            // Lấy các giá trị từ các trường nhập liệu trên giao diện
            String ten = txtTen.getText().trim();
            String khuVuc = txtKhuvuc.getText().trim();
            int soLuong = Integer.parseInt(txtSoLuong.getText().trim());
            double gia = Double.parseDouble(txtGia.getText().trim());
            String gioitinh = cbMale.isSelected() ? "Nam" : "Nữ";
            String loai = txtLoaiPhong.getText().trim();
            double dientich = Double.parseDouble(txtDientich.getText().trim());

            // Gọi phương thức updateRoom trong lớp DAO
            RoomDAO.updateRoom(maphong, ten, khuVuc, soLuong, gia, gioitinh, loai, dientich);
            JOptionPane.showMessageDialog(null, "Cập nhật thông tin phòng thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Có lỗi xảy ra khi cập nhật phòng!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    
    }//GEN-LAST:event_jButton3ActionPerformed

    private void tbRoomMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbRoomMouseClicked
        // TODO add your handling code here:
        int row = tbRoom.getSelectedRow();
        String maphong = tbRoom.getModel().getValueAt(row, 0).toString(); // Chuyển đổi giá trị cột đang là Object sang dạng chuỗi
        phongTro pt = new phongTro();
        try {
            pt = room.getRoom(maphong);
            if(pt != null){
                txtMa.setText(pt.getMa());
                txtTen.setText(pt.getTen());
                txtKhuvuc.setText(pt.getKhuvuc());
                txtLoaiPhong.setText(String.valueOf(pt.getLoai()));
                txtSoLuong.setText(String.valueOf(pt.getSoluong()));
                txtGia.setText(String.valueOf(pt.getGia()));
                if(pt.getGioitinh().equals("Nam")){
                    cbMale.setSelected(true);
                    cbFeMale.setSelected(false);
                }
                else{
                    cbFeMale.setSelected(true);
                    cbMale.setSelected(false);
                }
                txtDientich.setText(String.valueOf(pt.getDientich()));
               txtMa.setEditable(false);  
                txtTen.setEditable(false);  
                txtKhuvuc.setEditable(false);  
                txtLoaiPhong.setEditable(false);  
                txtSoLuong.setEditable(false);  
                txtGia.setEditable(false);  
                txtDientich.setEditable(false);  
                cbMale.setEnabled(false);  
                cbFeMale.setEnabled(false);  
                btnAdd.setEnabled(false);
                btnXoaupdateRoom.setEnabled(false);
            }
        } catch (SQLException ex) {
            Logger.getLogger(JfrmMainAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tbRoomMouseClicked

    private void lbTKcountKNoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbTKcountKNoMouseClicked
        // TODO add your handling code here:
        new Jfr_Tab_Thong_Ke_KhachNo().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_lbTKcountKNoMouseClicked

    private void lbTKdangthueMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbTKdangthueMouseClicked
        // TODO add your handling code here:
        new Jfr_Tab_Thong_Ke_Phongdangthue().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_lbTKdangthueMouseClicked

    private void lbPhongtrongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbPhongtrongMouseClicked
        // TODO add your handling code here:
        new Jfr_Tab_Thong_Ke_PhongTrong().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_lbPhongtrongMouseClicked

    private void lbTKTongDTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbTKTongDTMouseClicked
        // TODO add your handling code here:
        new Jfr_Tab_Thong_Ke_TongDT().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_lbTKTongDTMouseClicked

    private void btnTinhTienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTinhTienActionPerformed
        // TODO add your handling code here:
        // Khởi tạo các biến
        int row,max=tbDien.getRowCount();
        String checkResult=""; // Biến dùng để check trường hợp
        String checkRow = "";
        for( row =0;row<max;row++){
            try {
                String maphong = String.valueOf(tbDien.getValueAt(row ,1));
                pt=room.getRoom(maphong);
                //                long csm = Long.parseLong(tblModelDien.getValueAt(row, 3));
                long csm=Long.parseLong(""+ tbDien.getValueAt(row, 3));
                long csc = Long.parseLong(""+ tbDien.getValueAt(row, 4));
                if( csm>=0 && csc>=0){
                    if(csm<csc){
                        checkResult = (row+1)+"";
                        continue;
                    }else {
                        pt.setSodiennew(csm);
                        pt.setSodienold(csc);
                        room.updateCSDN(pt);
                    }
                }else {
                    checkRow = (row+1)+"";
                }
                tblModelDien.setRowCount(0);
                fillTableDien("O");
                if(!checkResult.isEmpty()){
                    JOptionPane.showMessageDialog(null, "Lỗi kết quả âm tại hàng "+ checkResult, "Error",JOptionPane.ERROR_MESSAGE);
                }
                if(!checkRow.isEmpty()){
                    JOptionPane.showMessageDialog(null, "Lỗi nhập giá trị âm tại hàng "+ checkRow, "Error",JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                System.out.println("Tinh tien that bai");
            }

        }
    }//GEN-LAST:event_btnTinhTienActionPerformed

    private void btnResetDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetDActionPerformed
        // TODO add your handling code here:
        fillTableDien("O");
        //        setError_D("E");
    }//GEN-LAST:event_btnResetDActionPerformed

    private void btnNewMonthDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewMonthDActionPerformed
        //  TODO add your handling code here:
        fillTableDien("N");
        // setError_D("D");
    }//GEN-LAST:event_btnNewMonthDActionPerformed

    private void btnChinhsuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChinhsuaActionPerformed
                    // TODO add your handling code here:
                    txtMa.setEditable(true);  
            txtTen.setEditable(true);  
            txtKhuvuc.setEditable(true);  
            txtLoaiPhong.setEditable(true);  
            txtSoLuong.setEditable(true);  
            txtGia.setEditable(true);  
            txtDientich.setEditable(true);  
            cbMale.setEnabled(true);  
            cbFeMale.setEnabled(true);  
            btnAdd.setEnabled(true);
            btnXoaupdateRoom.setEnabled(true);

    }//GEN-LAST:event_btnChinhsuaActionPerformed

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
            java.util.logging.Logger.getLogger(JfrmMainAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JfrmMainAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JfrmMainAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JfrmMainAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JfrmMainAdmin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnChinhsua;
    private javax.swing.JButton btnDel1;
    private javax.swing.JButton btnDeleteRoom;
    private javax.swing.JButton btnNewMonthD;
    private javax.swing.JButton btnNewmonthNuoc;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnResetD;
    private javax.swing.JButton btnResetN;
    private javax.swing.JButton btnSearch_HD;
    private javax.swing.JButton btnTinhTien;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton btnUpdate1;
    private javax.swing.JButton btnUpdate2;
    private javax.swing.JButton btnXoaupdateRoom;
    private javax.swing.JCheckBox cbFeMale;
    private javax.swing.JCheckBox cbKFemale;
    private javax.swing.JCheckBox cbKMale;
    private javax.swing.JCheckBox cbMale;
    private javax.swing.JComboBox<String> cbxMonth;
    private javax.swing.JComboBox<String> cbxYear;
    private javax.swing.ButtonGroup group;
    private javax.swing.ButtonGroup group1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JLabel lbPhongtrong;
    private javax.swing.JLabel lbTKTongDT;
    private javax.swing.JLabel lbTKcountKNo;
    private javax.swing.JLabel lbTKdangthue;
    private javax.swing.JTabbedPane tabbed;
    private javax.swing.JTable tbDien;
    private javax.swing.JTable tbHoaDon;
    private javax.swing.JTable tbKhachThue;
    private javax.swing.JTable tbNuoc;
    private javax.swing.JTable tbRoom;
    private javax.swing.JLabel txtClose;
    private javax.swing.JTextField txtDientich;
    private javax.swing.JTextField txtEndday;
    private javax.swing.JTextField txtGia;
    private javax.swing.JTextField txtKhuvuc;
    private javax.swing.JTextField txtLoaiPhong;
    private javax.swing.JTextField txtMa;
    private javax.swing.JTextField txtMaKhach;
    private javax.swing.JTextField txtMaP;
    private javax.swing.JTextField txtNameK;
    private javax.swing.JTextField txtNgay;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtStartday;
    private javax.swing.JTextField txtTen;
    // End of variables declaration//GEN-END:variables
}
