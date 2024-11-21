/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.HoaDon;
import View.JfrmHoaDon;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
/**
 *
 * @author Admin
 */
public class HoaDonDAO extends Connections{
    public static int rs=0;
    // Tạo câu truy vấn dữ liệu liên kết tới các bảng
   public static String query = " SELECT * FROM ROOM R,KHACHHANG K,HOADON H"
           +" WHERE R.MAPHONG=H.MAPHONG AND K.MAKT=H.MAKT ";
    // Xuất các đối tượng HoaDon ra file 
   public static String Export(List<HoaDon> listhoadon) throws IOException {
    File file = new File("HoaDon.txt");
    FileWriter fw = new FileWriter(file);
    BufferedWriter bw = new BufferedWriter(fw);
    for (HoaDon hoadon : listhoadon) {
        bw.write(hoadon.toString());
        bw.newLine();
    }
    bw.close();
    fw.close();
    return file.getAbsolutePath(); // Trả về đường dẫn đầy đủ
}
   
    public static List<HoaDon> Search_HD(String maHD) {
        List<HoaDon> listhoadon = new ArrayList<HoaDon>();
        String sql =query + "and " + "maHD = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, maHD);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDon hoadon = new HoaDon();
                setHoaDon(rs, hoadon); // Thêm các dữ liệu sql vào list
                listhoadon.add(hoadon);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listhoadon;
    }
     public static HoaDon Search_HD3(String maHD) {
   //     List<HoaDon> listhoadon = new ArrayList<HoaDon>();
        String sql =query + "and " + "maHD = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, maHD);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                HoaDon hoadon = new HoaDon();
                setHoaDon(rs, hoadon); // Thêm các dữ liệu sql vào list
               return hoadon;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
     return null;
    }
    // Lấy tiền điện 
   
       public static String getmaKT(String name, String maPhong) {
        String sql = "select maKT from tblKhachThue where HoTen = ? and maPhong = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, maPhong);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("maKT");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    // Tìm hóa Đơn từ 
    public  static List<HoaDon> getAllHD(){
        List<HoaDon> lsthd= new ArrayList<>();
        try {
            PreparedStatement stm = conn.prepareStatement(query);
            ResultSet rs = stm.executeQuery(); // khởi động câu lệnh
            while(rs.next()){
                HoaDon hd = new HoaDon();
                setHoaDon(rs, hd);
                lsthd.add(hd);
            }
            
        } catch (SQLException ex) {
           ex.printStackTrace();
            System.out.println("Thêm danh sách hóa đơn thất bại");
        }
        return lsthd;
    }
    public static List<HoaDon> getAllHDTK() {
    List<HoaDon> lsthd = new ArrayList<>();
   // String query = "SELECT * FROM HOADON";  // Thêm câu lệnh SQL ở đây
    try {
        PreparedStatement stm = conn.prepareStatement(query);
        ResultSet rs = stm.executeQuery(); // Khởi động câu lệnh
        while (rs.next()) {
            HoaDon hd = new HoaDon();
            setHoaDon(rs, hd); // Gán giá trị vào đối tượng HoaDon
            lsthd.add(hd);
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
        System.out.println("Thêm danh sách hóa đơn thất bại");
    }
    return lsthd;
}

    // Tìm hóa đơn có mã phòng gần giống
    public static List<HoaDon> searchHD(String maphong){
        List<HoaDon> lhd = new ArrayList<HoaDon>();
        String sql = query + "and H.MAPHONG LIKE '%" + maphong +"%'";
        PreparedStatement pstm;
        try {
            pstm = conn.prepareStatement(sql);
            ResultSet  rs = pstm.executeQuery();
            while(rs.next()){
                HoaDon hd = new HoaDon();
                setHoaDon(rs, hd);
                lhd.add(hd);
            }
        } catch (SQLException ex) {
         ex.printStackTrace();
            System.out.println("Tim kiem Hoa Don that bai");
        }
       
        
        return lhd;
}
    
   // Tìm kiếm hóa đơn theo tháng năm :
    public static List<HoaDon> searchHD2(String month,String year){
        try {
            String date = year + "-"+ month + "-01";
            List<HoaDon> lst = new ArrayList<>();
            String sql = query + "and DATEDIFF(MONTH,?, TGIAN)=0";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, date);
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                HoaDon hoadon = new HoaDon();
                setHoaDon(rs, hoadon);
                 lst.add(hoadon);
            }
            return lst;
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
   //  Hàm tạo các chỉ số của phòng 
    public static long chiso(String s,String maphong){
        String sql = " SELECT "+ s + " FROM ROOM WHERE MAPHONG = ?";
        PreparedStatement pstm;
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1,maphong );
            ResultSet rs = pstm.executeQuery();
            if(rs.next())
                return rs.getLong(s);
        } catch (SQLException ex) {
          ex.printStackTrace();
            System.out.println("Lay chi so that bai");
        }
        return 0;
        
    }
    public static long getTienPhong(String maphong) throws SQLException{
        String sql = "SELECT GIA FROM ROOM WHERE MAPHONG=?";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, maphong);
        ResultSet rs= pstm.executeQuery();
        if(rs.next())
            return rs.getLong("GIA");
        return 0;
        
    }
    // Lấy loại phòng 
    public static String getLoaiPhong(String maphong){
        try {
            String sql = "SELECT LOAI FROM ROOM WHERE MAPHONG=?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, maphong);
            ResultSet rs= pstm.executeQuery();
            if(rs.next())
                return rs.getString("LOAI");
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public static String getQueQuan(String makt){
        try {
            String sql = "SELECT QUEQUAN FROM KHACHHANG WHERE MAPHONG=?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, makt);
            ResultSet rs= pstm.executeQuery();
            if(rs.next())
                return rs.getString("QUEQUAN");
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    // Lấy ngày bắt đầu của khách hàng từ bảng KHACHHANG
    public static Date getNgayBatDau(String maphong){
          try {
        String sql = "SELECT NGAYTHUE FROM KHACHHANG WHERE MAPHONG=?";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, maphong); // Sử dụng MAPHONG để tra cứu thông tin
        ResultSet rs = pstm.executeQuery();
        if (rs.next()) {
            return rs.getDate("NGAYTHUE"); // Trả về ngày bắt đầu dưới dạng Date
        }
        return null;
    } catch (SQLException ex) {
        Logger.getLogger(HoaDonDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    return null;
    }
     public static Date getNgayKetThuc(String maphong){
          try {
        String sql = "SELECT NGAYKT FROM KHACHHANG WHERE MAPHONG=?";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, maphong); // Sử dụng MAPHONG để tra cứu thông tin
        ResultSet rs = pstm.executeQuery();
        if (rs.next()) {
            return rs.getDate("NGAYKT"); // Trả về ngày bắt đầu dưới dạng Date
        }
        return null;
    } catch (SQLException ex) {
        Logger.getLogger(HoaDonDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    return null;
    }
//     public static long getTienDien(String maphong){
//        try {
//            String sql = "SELECT SODIENNEW FROM ROOM WHERE MAPHONG = ?";
//            PreparedStatement pstm = conn.prepareStatement(sql);
//            pstm.setString(1, maphong);
//            ResultSet rs = pstm.executeQuery();
//            if(rs.next())
//                return rs.getLong("SODIENNEW");
//        } catch (SQLException ex) {
//            Logger.getLogger(HoaDonDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return 0;
//    }
    public  static long getTienDien(String maphong){
        long chisoDien = chiso("SODIENNEW",maphong)-chiso("SODIENOLD",maphong);
        return chisoDien*3500;
    }
     public static long getTienNuoc(String maPhong) {
        long chisoNuoc = chiso("SONUOCNEW", maPhong) - chiso("SONUOCOLD", maPhong);
        return chisoNuoc * 2500;
    }
         public static Long soChuDien(String maphong) {
        // Lấy chỉ số điện của tháng này
        String sql = "SELECT SODIENNEW -  SODIENOLD  chiso "
                + "from tblQlyPhongTro "
                + "where maPhong = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, maphong);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getLong("chiso"); // Lấy chỉ số từ bảng chiso thành kdl long
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0l;
           }
         public static Long soChuNuoc(String maphong) {
        String sql = "select chiSoNuocMoi -  chiSoNuocCu sochu "
                + "from tblQlyPhongTro "
                + "where maPhong = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, maphong);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getLong("chiso");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0l;
    }
         // Thiết lập hóa đơn
     public static void setHoaDon(ResultSet rs, HoaDon hoadon) {
        try {
            String maPhong = rs.getString("maPhong");
            String loaiPhong = getLoaiPhong(maPhong);
            Date ngayThue= getNgayBatDau(maPhong);
             SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // Định dạng ngày theo "yyyy-MM-dd"
            String ngayThueStr = sdf.format(ngayThue);  // Ép kiểu
              Date ngayKetThuc= getNgayKetThuc(maPhong);
              String ngayKTStr = sdf.format(ngayKetThuc);  
              String quequan= getQueQuan(maPhong);
            Long tienphong = getTienPhong(rs.getString("maPhong"));
            Long tiendien = getTienDien(rs.getString("maPhong"));
            Long tiennuoc = getTienNuoc(rs.getString("maPhong"));
            Long tiendv = 70000l;// Đặt tiền dịch vụ cố định
            Long tongtien = tienphong + tiendien + tiennuoc + tiendv; // Tổng tiền
            hoadon.setSdc(chiso("SODIENOLD", maPhong));
            hoadon.setSdm(chiso("SODIENNEW", maPhong));
            hoadon.setSnc(chiso("SONUOCNEW", maPhong));
            hoadon.setSnm(chiso("SONUOCOLD", maPhong));
            hoadon.setMaHD(rs.getString("maHD"));
            hoadon.setTenKT(rs.getString("HoTen"));
            hoadon.setMaKT(rs.getString("maKT"));
            hoadon.setMaPhong(rs.getString("maPhong"));
            hoadon.setLoaiPhong(loaiPhong);
            // Thêm các giá tiền vào đối tượng hoadon
            hoadon.setQuequan(quequan);
            hoadon.setGiaThue(tienphong);
            hoadon.setNgaybd(ngayThueStr);
            hoadon.setNgaykt(ngayKTStr);
            hoadon.setTienDien(tiendien);
            hoadon.setTienNuoc(tiennuoc);
            hoadon.setTienDV(tiendv);
            hoadon.setTongTien(tongtien);
            hoadon.setDate(rs.getString("tgian"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     // hàm thêm hóa đơn
     public static void Add_HoaDon(HoaDon hoadon) {
        String sql = "Insert Into HOADON(MAKT, MAPHONG, TGIAN)"
                + "Values (?, ?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, hoadon.getMaKT());
            ps.setString(2, hoadon.getMaPhong());
            ps.setString(3, "" + hoadon.getDate());
             rs = ps.executeUpdate();
            if (rs > 0) {
                JOptionPane.showMessageDialog(null, "Thêm hóa đơn thành công");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     // hàm Xóa Hóa Đơn 
     public static void deleteHoaDon(String maHD){
        try {
            String sql="DELETE FROM HOADON WHERE MAHD=?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, maHD);
            rs= pstm.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Xoa hoa don that bai");
        }
         
     }
     // Kiểm tra xem đã nhập Hóa Đơn chưa
     public static boolean KtraNhap(JTextField Phong) {
        if (Phong.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(new JfrmHoaDon(), "Chưa Nhập Tên Phòng");
            Phong.requestFocus();
            return false;
        }
        return true;
    }
     
//  public static void Init_NameKH(String maPhong, JComboBox<String> cbx) {
//    if (conn == null) {
//        System.err.println("Kết nối tới CSDL chưa được thiết lập.");
//        return;
//    }
//    String sql = "SELECT HOTEN FROM ROOM R,KHACHHANG K,HOADON H"
//           +" WHERE R.MAPHONG=H.MAPHONG AND K.MAKT=H.MAKT AND K.MAPHONG = ?";
//    try {
//        cbx.removeAllItems();
//        cbx.addItem("Chọn khách hàng"); // Mục placeholder
//        PreparedStatement pstm = conn.prepareStatement(sql);
//        pstm.setString(1, maPhong);
//        ResultSet rs = pstm.executeQuery();
//        while (rs.next()) {
//            cbx.addItem(rs.getString("HOTEN"));
//        }
//    } catch (SQLException e) {
//       
//    }
//}


}



