/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import static Controller.Connections.conn;
import Model.phongTro;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class RoomDAO extends Connections{

  
    public static List<phongTro> getInforRoom() throws SQLException{
        ArrayList<phongTro> lst = new ArrayList<>();
        String sql =" SELECT * FROM ROOM";
        Statement stm = conn.createStatement();
        ResultSet rs =stm.executeQuery(sql);
        while(rs.next()){
            phongTro lpt = new phongTro();
            lpt.setMa(rs.getString("MAPHONG"));
             lpt.setTen(rs.getString("TENPHONG"));
              lpt.setKhuvuc(rs.getString("KHUVUC"));
               lpt.setSoluong(rs.getInt("SONGUOI"));
                lpt.setGia(rs.getInt("GIA"));
                 lpt.setGioitinh(rs.getString("DOITUONG"));
                  lpt.setLoai(rs.getString("LOAI"));
                   lpt.setDientich(rs.getInt("DIENTICH"));
                   lpt.setTinhtrang(rs.getString("TINHTRANG"));
                   lst.add(lpt);
                   
                   
        }
         return lst;
    }
    public static List<phongTro> getInforRoomTinhTrangTK() throws SQLException{
        ArrayList<phongTro> lst = new ArrayList<>();
        String sql =" SELECT * FROM ROOM WHERE TINHTRANG =N'Đã Thuê'";
        Statement stm = conn.createStatement();
        ResultSet rs =stm.executeQuery(sql);
        while(rs.next()){
            phongTro lpt = new phongTro();
            lpt.setMa(rs.getString("MAPHONG"));
             lpt.setTen(rs.getString("TENPHONG"));
              lpt.setKhuvuc(rs.getString("KHUVUC"));
               lpt.setSoluong(rs.getInt("SONGUOI"));
                lpt.setGia(rs.getInt("GIA"));
                 lpt.setGioitinh(rs.getString("DOITUONG"));
                  lpt.setLoai(rs.getString("LOAI"));
                   lpt.setDientich(rs.getInt("DIENTICH"));
                   lpt.setTinhtrang(rs.getString("TINHTRANG"));
                   lst.add(lpt);
                   
                   
                   
        }
        return lst; 
    }
      public static List<phongTro> getInforRoomTinhTrangTKT() throws SQLException{
        ArrayList<phongTro> lst = new ArrayList<>();
        String sql =" SELECT * FROM ROOM WHERE TINHTRANG =N'Chưa thuê'";
        Statement stm = conn.createStatement();
        ResultSet rs =stm.executeQuery(sql);
        while(rs.next()){
            phongTro lpt = new phongTro();
            lpt.setMa(rs.getString("MAPHONG"));
             lpt.setTen(rs.getString("TENPHONG"));
              lpt.setKhuvuc(rs.getString("KHUVUC"));
               lpt.setSoluong(rs.getInt("SONGUOI"));
                lpt.setGia(rs.getInt("GIA"));
                 lpt.setGioitinh(rs.getString("DOITUONG"));
                  lpt.setLoai(rs.getString("LOAI"));
                   lpt.setDientich(rs.getInt("DIENTICH"));
                   lpt.setTinhtrang(rs.getString("TINHTRANG"));
                   lst.add(lpt);
                   
                   
                   
        }
        return lst; 
    }
    // Kiểm tra mã phòng 
    public static boolean checkMaPhong(String pt) {
    String sql = "SELECT COUNT(MAPHONG) FROM ROOM WHERE MAPHONG = ?";
    try (PreparedStatement pstm = conn.prepareStatement(sql)) {
        pstm.setString(1, pt);
        try (ResultSet rs = pstm.executeQuery()) {
            if (rs.next()) {
                int count = rs.getInt(1); // Lấy giá trị COUNT
                return count == 0; // Trả về true nếu không tìm thấy mã phòng
            }
        }
    } catch (SQLException ex) {
        Logger.getLogger(RoomDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    return false; // Mặc định trả về false nếu có lỗi
}

    // Tạo phương thức lấy phòng theo mã phòng :
    public phongTro getRoom(String maphong) throws SQLException{
        String sql = "SELECT * FROM ROOM WHERE MAPHONG=?";
//        List<phongTro> lpt = new ArrayList<>();
            phongTro pt = null;
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, maphong);
        ResultSet rs = pstm.executeQuery();
        while(rs.next()){
            pt = new phongTro();
             pt.setMa(rs.getString("MAPHONG"));
             pt.setTen(rs.getString("TENPHONG"));
             pt.setKhuvuc(rs.getString("KHUVUC"));
             pt.setSoluong(rs.getInt("SONGUOI"));
             pt.setGia(rs.getInt("GIA"));
             pt.setGioitinh(rs.getString("DOITUONG"));
             pt.setLoai(rs.getString("LOAI"));
              pt.setDientich(rs.getInt("DIENTICH"));
            //  pt.setTinhtrang("Chưa Thuê");
            return pt;
        }
        return null;
        
    }
    
   public void addRoom(phongTro pt) throws SQLException {
       
       // Thiết lập mặt định phòng trọ chưa thuê 
       pt.setTinhtrang("Chưa thuê");
       
    String sql = "INSERT INTO ROOM(MAPHONG, TENPHONG, KHUVUC, SONGUOI, GIA, DOITUONG, LOAI, DIENTICH, SODIENNEW, SODIENOLD, SONUOCNEW, SONUOCOLD,TINHTRANG)"
               + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
    PreparedStatement pstm = conn.prepareStatement(sql);
    pstm.setString(1, pt.getMa());
    pstm.setString(2, pt.getTen());
    pstm.setString(3, pt.getKhuvuc());
    pstm.setInt(4, pt.getSoluong());
    pstm.setDouble(5, pt.getGia());
    pstm.setString(6, pt.getGioitinh());
    pstm.setString(7, pt.getLoai());
    pstm.setDouble(8, pt.getDientich());
    pstm.setLong(9, pt.getSodiennew());
    pstm.setLong(10, pt.getSodienold());
    pstm.setLong(11, pt.getSonuocnew());
    pstm.setLong(12, pt.getSonuocold());
    pstm.setString(13, pt.getTinhtrang());
    int i = pstm.executeUpdate();
    if (i > 0)
        System.out.println("Them phong thanh cong");
    else
        JOptionPane.showMessageDialog(null, "Thêm mới vào CSDL thất bại", "Title", 0);
}

     public void deleteRoom(String maphong) throws SQLException{
        
        String sql = "DELETE FROM ROOM WHERE MAPHONG = ? ";
        phongTro pt = null;
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, maphong);
        int i = pstm.executeUpdate();
//        ResultSet rs = pstm.executeUpdate();
//        while(rs.next()){
//            pt = new phongTro();
//             pt.setMa(rs.getString("MAPHONG"));
//             pt.setTen(rs.getString("TENPHONG"));
//             pt.setKhuvuc(rs.getString("KHUVUC"));
//             pt.setSoluong(rs.getInt("SONGUOI"));
//             pt.setGia(rs.getFloat("GIA"));
//             pt.setGioitinh(rs.getString("DOITUONG"));
//             pt.setLoai(rs.getString("LOAI"));
//              pt.setDientich(rs.getInt("DIENTICH"));
//            return pt;
        
       if(i>0)
            System.out.println("Xoa thanh cong !!");
       else
           JOptionPane.showMessageDialog(null, "Xóa thông tin thất bại", "Thông báo", 0);
    
    }
   public static void updateRoom(String maphong, String ten, String khuVuc, int soLuong, double gia, String gioitinh, String loai, double dientich) throws SQLException {
    String sql = "UPDATE ROOM SET TENPHONG=?, KHUVUC=?, SONGUOI=?, GIA=?, DOITUONG=?, LOAI=?, DIENTICH=? WHERE MAPHONG=?";
    PreparedStatement pstm = conn.prepareStatement(sql);

    // Đặt các giá trị vào PreparedStatement
    pstm.setString(1, ten);
    pstm.setString(2, khuVuc);
    pstm.setInt(3, soLuong);
    pstm.setDouble(4, gia);
    pstm.setString(5, gioitinh);
    pstm.setString(6, loai);
    pstm.setDouble(7, dientich);
    pstm.setString(8, maphong);

    // Thực hiện cập nhật
    int i = pstm.executeUpdate();
    if (i > 0) {
        System.out.println("Cập nhật CSDL thành công!");
    } else {
        JOptionPane.showMessageDialog(null, "Cập nhật vào CSDL thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
    }
}

     // Tạo một DS xem các chỉ số điện nước 
     public static List<phongTro> getDienNuoc() throws SQLException{
         List<phongTro> ldn = new ArrayList<>();
         String sql = "SELECT * FROM ROOM WHERE TINHTRANG =N'Đã Thuê'";
        PreparedStatement pstm = conn.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
         while(rs.next()){
             phongTro pt =new phongTro();
             pt.setMa(rs.getString("MAPHONG"));
             pt.setSodiennew(rs.getInt("SODIENNEW"));
             pt.setSodienold(rs.getInt("SODIENOLD"));
             pt.setSonuocnew(rs.getInt("SONUOCNEW"));
             pt.setSonuocold(rs.getLong("SONUOCOLD"));
             ldn.add(pt);
             
         }
        return ldn;
         
     }
     
     // Update lại CSDN
     public void updateCSDN(phongTro pt){
        try {
            String sql = " UPDATE ROOM SET SODIENNEW = ?, SODIENOLD=? ,SONUOCNEW = ?, SONUOCOLD=? WHERE MAPHONG = ?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setLong(1, pt.getSodiennew());
            pstm.setLong(2, pt.getSodienold());
            pstm.setLong(3, pt.getSonuocnew());
            pstm.setLong(4, pt.getSonuocold());
            pstm.setString(5, pt.getMa());
            int i =pstm.executeUpdate();
            if(i>0) 
                System.out.println("Cap nhap CSDN thanh cong");
        } catch (SQLException ex) {
           ex.printStackTrace();
            System.out.println("Cap nhap hoa don that bai");
        }
                 }
     
}
