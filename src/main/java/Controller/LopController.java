/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;



import Controller.UserDAO;
import Model.CurrentUser;
import Model.KhachThue;
import Model.phongTro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.*;
import java.util.Date;  
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.User;
import View.JfrmFastSearching;


/**
 *
 * @author Admin
 */
public class LopController {
    Connection conn;
       SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    public LopController() {
        try {
            conn = new connection().connect();
        } catch (SQLException ex) {
            Logger.getLogger(LopController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<phongTro> getAll() throws SQLException{
        List<phongTro> lst = new ArrayList<>();
        String query = "Select * from ROOM";
        PreparedStatement prst = conn.prepareStatement(query);
        ResultSet rs = prst.executeQuery();
        while(rs.next()){
            String maphong = rs.getString("MAPHONG");
            String tenphong = rs.getString("TENPHONG");
            int dientich = rs.getInt("DIENTICH");
            int gia = rs.getInt("GIA"); 
            String tinhtrang = rs.getString("TINHTRANG");
            phongTro p = new phongTro();
            p.setMa(maphong);
            p.setTen(tenphong);
            p.setDientich(dientich);
            p.setGia(gia);
            p.setTinhtrang(tinhtrang);
            //obj.setmaphong(rs.getString("maphong"));
            lst.add(p);
        }
        return lst;
    }
    public List<phongTro> getMaphong(String khuvuc){    
            try {
                List<phongTro> lst = new ArrayList<>();
                String query = "SELECT * FROM ROOM WHERE khuvuc = ?";
                PreparedStatement prst = conn.prepareStatement(query);
                // TODO add your handling code here:
                connection conn = new connection();
                prst.setString(1, khuvuc);
                ResultSet rs = prst.executeQuery();
                while(rs.next()){
                    String maphong = rs.getString("MAPHONG");
                    String tenphong = rs.getString("TENPHONG");
                    int songuoi = rs.getInt("SONGUOI");
                    int dientich = rs.getInt("DIENTICH");
                    String doituong = rs.getString("DOITUONG");
                    String loai = rs.getString("LOAI");
                    String tinhtrang = rs.getString("TINHTRANG");
                    long gia = rs.getLong("GIA");
                    long sodiennew = rs.getLong("SODIENNEW");
                    long sodienold = rs.getLong("SODIENOLD");
                    long sonuocnew = rs.getLong("SONUOCNEW");
                    long sonuocold = rs.getLong("SONUOCOLD");

                //System.out.println("Mã Phòng: " +maphong+ "Số Người: " +songuoi+ "Diện Tích: " +dientich+ "Giá: " +gia);
//               phongTro p = new phongTro(maphong,tenphong, khuvuc, songuoi, dientich, doituong, loai, tinhtrang, gia, sodiennew, sodienold, sonuocnew, sonuocold );
                phongTro p = new phongTro(maphong, tenphong, khuvuc, loai, songuoi, gia, doituong, dientich, sodiennew, sodienold, sonuocnew, sonuocold, tinhtrang);
               lst.add(p);
                }
                return lst;
            } catch (SQLException ex) {
                Logger.getLogger(JfrmFastSearching.class.getName()).log(Level.SEVERE, null, ex);
            }
        return null;
    }
        public phongTro getMP(String maphong){    
            try {
                String query = "SELECT * FROM ROOM WHERE maphong = ?";
                PreparedStatement prst = conn.prepareStatement(query);
                // TODO add your handling code here:
                connection conn = new connection();          
                prst.setString(1, maphong);
                ResultSet rs = prst.executeQuery();
                if(rs.next()){
                    //String maphong = rs.getString("maphong");
                    String tenphong = rs.getString("TENPHONG");
                    String khuvuc = rs.getString("KHUVUC");
                    int songuoi = rs.getInt("SONGUOI");
                    int dientich = rs.getInt("DIENTICH");
                    String doituong = rs.getString("DOITUONG");
                    String loai = rs.getString("LOAI");
                    String tinhtrang = rs.getString("TINHTRANG");
                     long gia = rs.getLong("GIA");
                   long sodiennew = rs.getLong("SODIENNEW");
                    long sodienold = rs.getLong("SODIENOLD");
                    long sonuocnew = rs.getLong("SONUOCNEW");
                    long sonuocold = rs.getLong("SONUOCOLD");
                //System.out.println("Mã Phòng: " +maphong+ "Số Người: " +songuoi+ "Diện Tích: " +dientich+ "Giá: " +gia);
                phongTro p = new phongTro(maphong, tenphong, khuvuc, loai, songuoi, gia, doituong, dientich, sodiennew, sodienold, sonuocnew, sonuocold, tinhtrang);
                return p;
                }
//                else JOptionPane.showMessageDialog(null, "KHU VỰC ĐÃ NHẬP KHÔNG HỢP LỆ!","Thông báo",1);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        return null;
    }
        
        public List<phongTro> SapxepGia(){
        try {
            List<phongTro> lst = new ArrayList<>();
            String query = "";
            query = "SELECT * FROM ROOM ORDER BY gia ASC";
            connection conn = new connection();
            PreparedStatement prst = conn.prepareStatement(query);
            ResultSet rs = prst.executeQuery();
            while(rs.next()){
                String maphong = rs.getString("MAPHONG");
                String tenphong = rs.getString("TENPHONG");
                String khuvuc = rs.getString("KHUVUC");
                int songuoi = rs.getInt("SONGUOI");
                int dientich = rs.getInt("DIENTICH");
                String doituong = rs.getString("DOITUONG");
                String loai = rs.getString("LOAI");
                String tinhtrang = rs.getString("TINHTRANG");
                long gia = rs.getLong("GIA");
                   long sodiennew = rs.getLong("SODIENNEW");
                    long sodienold = rs.getLong("SODIENOLD");
                    long sonuocnew = rs.getLong("SONUOCNEW");
                    long sonuocold = rs.getLong("SONUOCOLD");
                phongTro p = new phongTro(maphong, tenphong, khuvuc, loai, songuoi, gia, doituong, dientich, sodiennew, sodienold, sonuocnew, sonuocold, tinhtrang);
            lst.add(p);
            }
            return lst;
        } catch (SQLException ex) {
            Logger.getLogger(LopController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        }
        public List<phongTro> SapxepDientich(){
        try {
            List<phongTro> lst = new ArrayList<>();
            String query = "";
            query = "SELECT * FROM ROOM ORDER BY dientich ASC";
            connection conn = new connection();
            PreparedStatement prst = conn.prepareStatement(query);
            ResultSet rs = prst.executeQuery();
            while(rs.next()){
                String maphong = rs.getString("MAPHONG");
                String tenphong = rs.getString("TENPHONG");
                String khuvuc = rs.getString("KHUVUC");
                int songuoi = rs.getInt("SONGUOI");
                int dientich = rs.getInt("DIENTICH");
                String doituong = rs.getString("DOITUONG");
                String loai = rs.getString("LOAI");
                String tinhtrang = rs.getString("TINHTRANG");
                 long gia = rs.getLong("GIA");
                   long sodiennew = rs.getLong("SODIENNEW");
                    long sodienold = rs.getLong("SODIENOLD");
                    long sonuocnew = rs.getLong("SONUOCNEW");
                    long sonuocold = rs.getLong("SONUOCOLD");
                phongTro p = new phongTro(maphong, tenphong, khuvuc, loai, songuoi, gia, doituong, dientich, sodiennew, sodienold, sonuocnew, sonuocold, tinhtrang);
            lst.add(p);
            }
            return lst;
        } catch (SQLException ex) {
            Logger.getLogger(LopController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        }
        public List<KhachThue> getUser(String makh) throws SQLException{
        List<KhachThue> lst = new ArrayList<>();
        String query = "Select * from KHACHHANG where MAKT = ?";
        PreparedStatement prst = conn.prepareStatement(query);
        connection conn = new connection();          
        prst.setString(1, makh);
        ResultSet rs = prst.executeQuery();
        while(rs.next()){
            String maKT = rs.getString("MAKT");
            String HoTen = rs.getString("HOTEN");
            String NgaySinh = rs.getDate("NGAYSINH") != null 
                                  ? rs.getDate("NGAYSINH").toString() 
                                  : null;
            String GioiTinh = rs.getString("GIOITINH");   
            String Sdt = rs.getString("SDT");
            String QueQuan = rs.getString("QUEQUAN");
            String maPhong = rs.getString("MAPHONG");
//            String startDay = rs.getString("NGAYTHUE");
//            String endDay = rs.getString("NgAYKT");

            KhachThue k = new KhachThue();
            k.setNgaySinh(NgaySinh);
            k.setGioiTinh(GioiTinh);
            //obj.setmaphong(rs.getString("maphong"));
            lst.add(k);
        }
        return lst;
    }
        public void updateTinhTrangPhong(phongTro p, String maphong) throws SQLException {
            p.setTinhtrang("Đã thuê");
               String makt = CurrentUser.getMakt();
               System.out.println(makt);
    if (makt == null || makt.isEmpty()) {
        JOptionPane.showMessageDialog(null,"Không xác định được khách hàng đang thực hiện đặt phòng.");
    }
    // Câu lệnh SQL để cập nhật tình trạng phòng
            String query = "UPDATE ROOM SET TINHTRANG = ? , MAKT =? WHERE MAPHONG = ?";
                PreparedStatement prst = conn.prepareStatement(query);
                connection conn = new connection();          
                prst.setString(1, p.getTinhtrang());
               prst.setString(2, makt);
                prst.setString(3, maphong);
                prst.executeUpdate();  
               
               //    Cập nhật thông tin khách hàng
//        String sqlKhachHang = "INSERT INTO ROOM(MAKT) VALUES(?)";
//        PreparedStatement pstmKhachHang = conn.prepareStatement(sqlKhachHang);
//        pstmKhachHang.setString(1, makt); // Mã khách hàng đã đăng nhập
//        //pstmKhachHang.setString(2, maphong); // Phòng đang được thuê
//        pstmKhachHang.executeUpdate();

        }
        // Hàm thêm mã phòng, ngày kết thúc và ngày bắt đầu với Khách 
        public void getMa(String makt,String maphong){
        try {
           // String maphong = CurrentUser.getMaPhong();
             makt = CurrentUser.getMakt();
            String sql ="UPDATE KHACHHANG SET MAPHONG=?,NGAYTHUE=?,NGAYKT=? WHERE MAKT = ?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, maphong);
            pstm.setString(4, makt);
            Date dateStart = formatter.parse(CurrentUser.getStartDay());
            java.sql.Date sqldateStart = new java.sql.Date(dateStart.getTime());
             Date dateEnd = formatter.parse(CurrentUser.getEndDay());
            java.sql.Date sqldateEnd = new java.sql.Date(dateEnd.getTime());
             pstm.setDate(2, sqldateStart);
             pstm.setDate(3, sqldateEnd);
            pstm.executeLargeUpdate();
        } catch (SQLException ex) {
           ex.printStackTrace();
        } catch (ParseException ex) {
            Logger.getLogger(LopController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        }
        // Hàm tìm khách hàng 
        public KhachThue kt(String makt){
        try {
            String sql = "SELECT * FROM KHACHHANG WHERE MAKT = ?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, makt);
            ResultSet rs = pstm.executeQuery();
            KhachThue p = new KhachThue();
            if(rs.next()){
                p.setHoTen(rs.getString("HOTEN"));
                p.setNgaySinh(""+rs.getDate("NGAYSINH"));
                p.setGioiTinh(rs.getString("GIOITINH"));
                p.setSdt(rs.getString("SDT"));
                p.setQueQuan(rs.getString("QUEQUAN"));
                return p;
            }
        } catch (SQLException ex) {
           ex.printStackTrace();
        }
        return null;
        }
        // Hàm chỉnh sửa thông tin khách hàng 
        public void updateKhachHang(KhachThue k) throws SQLException, ParseException {
    // Câu lệnh SQL để cập nhật thông tin khách hàng
    String sql = "UPDATE KHACHHANG SET HOTEN = ?, NGAYSINH = ?, GIOITINH = ?, SDT = ?, QUEQUAN = ?, MAPHONG = ? WHERE MAKT = ?";
    
    connection conn = new connection(); // Tạo kết nối
    
    try {
        // Chuẩn bị câu lệnh SQL
        PreparedStatement pstmt = conn.prepareStatement(sql);
        
        // Cài đặt giá trị cho các tham số trong câu lệnh UPDATE
        pstmt.setString(1, k.getHoTen());  // HOTEN
        Date date = formatter.parse(k.getNgaySinh());
        java.sql.Date sqldate = new java.sql.Date(date.getTime());
        pstmt.setDate(2, sqldate);  // NGAYSINH
        pstmt.setString(3, k.getGioiTinh());  // GIOITINH
        pstmt.setString(4, k.getSdt());  // SDT
        pstmt.setString(5, k.getQueQuan());  // QUEQUAN
        pstmt.setString(6, CurrentUser.getMaPhong());  // MAPHONG
        pstmt.setString(7, CurrentUser.getMakt());  // MAKT
        
        // Thực thi câu lệnh
        int rowsAffected = pstmt.executeUpdate();
        if (rowsAffected > 0) {
            JOptionPane.showMessageDialog(null, "Cập nhật thông tin khách thuê thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Không tìm thấy khách thuê để cập nhật", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Lỗi khi cập nhật thông tin khách thuê: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
    }
}

        // So sánh mã Khách Thuê đã tồn tại hay chưa
        public boolean KtramaKT(String makt){
        try {
            String query = "Select * from KHACHHANG where MAKT = ?";
            PreparedStatement prst = conn.prepareStatement(query);
            connection conn = new connection();          
            prst.setString(1, makt);
            ResultSet rs = prst.executeQuery();
            if(rs.next()) return false;
        } catch (SQLException ex) {
            Logger.getLogger(LopController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
        }
        
        // thêm khách hàng
        public void addKhachHang(KhachThue k, String makt) throws SQLException, ParseException {
//            p.setTinhtrang("Đã thuê");
        makt=CurrentUser.getMakt();
       // String maphong = CurrentUser.getMaPhong();
      //      System.out.println(maphong);
    // Câu lệnh SQL để cập nhật tình trạng phòng
    String sql = "INSERT INTO KHACHHANG (MAKT, HOTEN, NGAYSINH, GIOITINH, SDT, QUEQUAN, MAPHONG) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?)";
           connection conn = new connection();
        // Tạo kết nối và thực thi câu lệnh
        try {
            
             PreparedStatement pstmt = conn.prepareStatement(sql);
            // Cài đặt giá trị cho các tham số trong câu lệnh INSERT
            pstmt.setString(1, makt);  // MAKT
            pstmt.setString(2, k.getHoTen());  // HOTEN
            // Ép kiểu sang DATE 
            Date date = formatter.parse(k.getNgaySinh());
            java.sql.Date sqldate = new java.sql.Date(date.getTime());
            pstmt.setDate(3, sqldate);  // NGAYSINH
            pstmt.setString(4, k.getGioiTinh());  // GIOITINH
            pstmt.setString(5, k.getSdt());  // SDT
            pstmt.setString(6, k.getQueQuan());  // QUEQUAN
            pstmt.setString(7, CurrentUser.getMaPhong());  // MAPHONG
            
            // Thực thi câu lệnh
            int rowsAffected = pstmt.executeUpdate();
//            System.out.println("Số bản ghi bị ảnh hưởng: " + rowsAffected);
            JOptionPane.showMessageDialog(null, "Thêm thông tin khách thuê thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        }
//        public void getBooking(phongTro p, String makt){
//        try {
//            String sql = "UPDATE KHACHHANG K\n" +"SET K.MAPHONG = (\n" +"SELECT R.MAPHONG\n" +"FROM ROOM R\n" +"WHERE K.MAKT = R.IDKHACHHANG\n" +");";
////            connection conn = new connection();
//            PreparedStatement pstmt = conn.prepareStatement(sql);
//            pstmt.setString(1, p.getMa());  // MAKT
//            pstmt.setString(2, makt);  // HOTEN
//        } catch (SQLException ex) {
//           ex.printStackTrace();
//        }
//        }
}
