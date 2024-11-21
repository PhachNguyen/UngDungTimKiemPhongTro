/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import static Controller.HoaDonDAO.getTienDien;
import static Controller.HoaDonDAO.getTienNuoc;
import static Controller.HoaDonDAO.getTienPhong;
import Model.HoaDon;
import Model.ThongKe;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class ThongKeDAO extends Connections{
    // Tạo số lượng phòng Trống 
    String query = "SELECT * FROM ROOM R, KHACHHANG K WHERE R.MAPHONG=K.MAPHONG";
    
    // Đếm thông tin khách hàng chưa đặt phòng 
    public static int getPhongTrong(){ 
        try {
            String sql = "SELECT COUNT(MAPHONG) AS TONG FROM ROOM WHERE TINHTRANG=N'CHƯA THUÊ'";
            PreparedStatement pstm = conn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            if(rs.next()){
                int count= rs.getInt("TONG");
            System.out.println("So luong phong trong "+ count);
            return count;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ThongKeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
     
        return 0;
    }
       // Hiển thị số phòng đang thuê  
    public static int getPhongThue(){
         
        try {
            String sql = "SELECT COUNT(MAPHONG) AS TONG FROM ROOM WHERE TINHTRANG=N'ĐÃ THUÊ'";
            PreparedStatement pstm = conn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            if(rs.next())
                return rs.getInt("TONG");
            
        } catch (SQLException ex) {
           ex.printStackTrace();
        } 
        return 0;
}
    public static int getCountKhachNo(){
        try {
            String sql = "SELECT COUNT(MAPHONG) AS KHACHNO FROM ROOM WHERE TINHTRANG= N'THANH TOÁN'";
            PreparedStatement pstm = conn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            if(rs.next())
                return rs.getInt("KHACHNO");
        } catch (SQLException ex) {
           ex.printStackTrace();
        }
        return 0;
    }
     public static long Count_DoanhThu() {
        long tong = 0;
        List<HoaDon> list_hoadon = new ArrayList<HoaDon>();

        String sql = "SELECT * FROM HOADON";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Long tienphong = getTienPhong(rs.getString("maPhong"));
                Long tiendien = getTienDien(rs.getString("maPhong"));
                Long tiennuoc = getTienNuoc(rs.getString("maPhong"));
                Long tiendv = 70000l; // 70.000 VND
                Long tongtien = tienphong + tiendien + tiennuoc + tiendv;
                HoaDon hoadon = new HoaDon();       
                hoadon.setTongTien(tongtien);
                list_hoadon.add(hoadon);
                tong += hoadon.getTongTien();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }         
        return tong;
    }
}
