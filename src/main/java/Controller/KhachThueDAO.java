/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.HoaDon;
import Model.KhachThue;
import Model.phongTro;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class KhachThueDAO extends Connections{
    
    // Truy vấn ds từ SQL 
//     static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    public static List<KhachThue> getInforKhach(){
          List<KhachThue> lkt = new ArrayList<>();
        try {
          
            String sql =" SELECT * FROM ROOM R, KHACHHANG K WHERE R.MAPHONG=K.MAPHONG AND TINHTRANG=N'Đã thuê' ";
            PreparedStatement pstm = conn.prepareStatement(sql);
            ResultSet rs= pstm.executeQuery();
            while(rs.next()){
                KhachThue kt = new KhachThue();
                kt.setMaKT(rs.getString("MAKT"));
                kt.setHoTen(rs.getString("HOTEN"));
                kt.setNgaySinh(""+ rs.getDate("NGAYSINH"));
                kt.setGioiTinh(rs.getString("GIOITINH"));
                kt.setSdt(rs.getString("SDT"));
                kt.setQueQuan(rs.getString("QUEQUAN"));
                kt.setMaPhong(rs.getString("MAPHONG"));
                lkt.add(kt);
                
            }
        } catch (SQLException ex) {
          
            ex.printStackTrace();
            System.out.println("Truy cap Khach Thue That bai");
        }
        return lkt;
    }
     public static List<KhachThue> getInforKhachTKN(){
          List<KhachThue> lkt = new ArrayList<>();
        try {
          
            String sql =" SELECT * FROM ROOM R, KHACHHANG K WHERE R.MAPHONG=K.MAPHONG AND TINHTRANG=N'Thanh toán' ";
            PreparedStatement pstm = conn.prepareStatement(sql);
            ResultSet rs= pstm.executeQuery();
            while(rs.next()){
                KhachThue kt = new KhachThue();
                kt.setMaKT(rs.getString("MAKT"));
                kt.setHoTen(rs.getString("HOTEN"));
                kt.setNgaySinh(""+ rs.getDate("NGAYSINH"));
                kt.setGioiTinh(rs.getString("GIOITINH"));
                kt.setSdt(rs.getString("SDT"));
                kt.setQueQuan(rs.getString("QUEQUAN"));
                kt.setMaPhong(rs.getString("MAPHONG"));
                lkt.add(kt);
                
            }
        } catch (SQLException ex) {
          
            ex.printStackTrace();
            System.out.println("Truy cap Khach Thue That bai");
        }
        return lkt;
    }
   // Hàm lấy thông tin khách thuê theo mã khách
public static KhachThue getKhachThue(String maKhach) {
    KhachThue kt = null;
    try {
        String sql = "SELECT * FROM KHACHHANG WHERE MAKT = ?";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, maKhach);
        ResultSet rs = pstm.executeQuery();
        if (rs.next()) {
            kt = new KhachThue();
            kt.setMaKT(rs.getString("MAKT"));
            kt.setHoTen(rs.getString("HOTEN"));
            
            // Lấy và set ngày sinh
            kt.setNgaySinh(rs.getDate("NGAYSINH").toString());
            
            kt.setGioiTinh(rs.getString("GIOITINH"));
            kt.setSdt(rs.getString("SDT"));
            kt.setQueQuan(rs.getString("QUEQUAN"));
            kt.setMaPhong(rs.getString("MAPHONG"));
            
            // Lấy và set ngày kết thúc và ngày thuê
            kt.setEndDay(rs.getDate("NGAYKT").toString());
            kt.setStartDay(rs.getDate("NGAYTHUE").toString());
            return kt;
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return null;
}
// Hàm hiển thị thông tin đã thuê 
public static List<KhachThue> getKhachThueDaThue() {
    List<KhachThue> danhSachKhachThue = new ArrayList<>();
    try {
        String sql = "SELECT * FROM KHACHHANG WHERE TINHTRANG = 'Đã Thuê'";
        PreparedStatement pstm = conn.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        
        while (rs.next()) {
            KhachThue kt = new KhachThue();
            kt.setMaKT(rs.getString("MAKT"));
            kt.setHoTen(rs.getString("HOTEN"));
            kt.setNgaySinh(rs.getDate("NGAYSINH").toString());
            kt.setGioiTinh(rs.getString("GIOITINH"));
            kt.setSdt(rs.getString("SDT"));
            kt.setQueQuan(rs.getString("QUEQUAN"));
            kt.setMaPhong(rs.getString("MAPHONG"));
            kt.setStartDay(rs.getDate("NGAYTHUE").toString());
            kt.setEndDay(rs.getDate("NGAYKT").toString());
            
            // Thêm khách thuê vào danh sách
            danhSachKhachThue.add(kt);
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return danhSachKhachThue;
}

    // Hàm để xóa khách thuê 
   public static boolean deleteKhachThue(String makt) {
    try {
        // Câu lệnh SQL để xóa khách thuê
        String sql = "DELETE FROM KHACHHANG WHERE MAKT = ?";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, makt);  // Đặt giá trị MAKT vào tham số câu lệnh SQL
        
        // Thực thi câu lệnh
        int rowsAffected = pstm.executeUpdate();
        
        // Kiểm tra số dòng bị ảnh hưởng
        return rowsAffected > 0; // Trả về true nếu xóa thành công
    } catch (SQLException ex) {
        ex.printStackTrace();
        System.out.println("Lỗi khi xóa khách thuê");
    }
    return false; // Trả về false nếu có lỗi
}
// Câu lệnh update
   public static void updateTinhTrangPhong(phongTro p, String maphong) throws SQLException {
            p.setTinhtrang("Thanh toán");
//               String makt = CurrentUser.getMakt();
//               System.out.println(makt);
//    if (makt == null || makt.isEmpty()) {
//        JOptionPane.showMessageDialog(null,"Không xác định được khách hàng đang thực hiện đặt phòng.");
//    }
    // Câu lệnh SQL để cập nhật tình trạng phòng
            String query = "UPDATE ROOM SET TINHTRANG = ?  WHERE MAPHONG = ?";
                PreparedStatement prst = conn.prepareStatement(query);             
                prst.setString(1, p.getTinhtrang());
                prst.setString(2, maphong);
                prst.executeUpdate();
   }
    // Hàm tạo hóa đơn
public static void getHoadon(String makt) {
    try {
        // Lấy thông tin khách thuê
        KhachThue kt = KhachThueDAO.getKhachThue(makt);
        System.out.println(kt);
        // Kiểm tra nếu không tìm thấy khách thuê
        if (kt == null) {
            System.out.println("Không tìm thấy khách thuê với mã: " + makt);
            return;
        }
        
        String sql = "INSERT INTO HOADON(MAHD, MAKT, MAPHONG, TGIAN) VALUES(?, ?, ?, ?)";
        PreparedStatement pstm = conn.prepareStatement(sql);
        
        // Tạo mã hóa đơn mới
        String mahd = KhachThueDAO.TaoMaHD();
        pstm.setString(1, mahd);
        pstm.setString(2, makt);
        pstm.setString(3, kt.getMaPhong());
        // Lấy ngày hiện tại
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); // Đảm bảo định dạng ngày
        Date date = formatter.parse(kt.getStartDay()); // Parse ngày thuê
        java.sql.Date sqldate = new java.sql.Date(date.getTime());
        
        // Set ngày vào câu lệnh SQL
        pstm.setDate(4, sqldate);
        
        // Thực hiện câu lệnh INSERT
        pstm.executeUpdate();
        
    } catch (SQLException ex) {
        ex.printStackTrace();
    } catch (ParseException ex) {
        Logger.getLogger(KhachThueDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
}
    // Tạo mã hóa đơn
     public  static String TaoMaHD(){
            
                String maHoaDonMoi = "HD001"; // Mặc định nếu không có khách hàng nào
                try {
                    // Truy vấn mã khách hàng lớn nhất hiện có
                    String query = "SELECT MAX(MAHD) AS MAX_MAHD FROM HOADON";
                    PreparedStatement prst = conn.prepareStatement(query);
                    ResultSet rs = prst.executeQuery();

                    if (rs.next()) {
                        String maxHDKhachHang = rs.getString("MAX_MAHD"); // Lấy mã lớn nhất
                        if (maxHDKhachHang != null) {
                            // Tách phần số từ mã khách hàng, ví dụ "KH001" -> 1
                            int soThuTu = Integer.parseInt(maxHDKhachHang.substring(2));
                            // Tăng số thứ tự lên 1
                            soThuTu++;
                            // Ghép lại thành mã mới, đảm bảo đủ 3 chữ số
                            maHoaDonMoi = String.format("HD%03d", soThuTu);
                        }
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                return maHoaDonMoi;
            }
     
}
