/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.HoaDon;
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
public class DAO extends Connections{
    // Truy xuất thông tin hóa đơn
    public static List<HoaDon> getInfor(HoaDon hd){
        try {
            String sql = "SELECT * FROM HOADON";
            List<HoaDon> lst = new ArrayList<>();
            PreparedStatement pstm = conn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                hd.setMaHD(rs.getString("MAHD"));
                hd.setMaKT(rs.getString("MAKT"));
                hd.setMaPhong(rs.getString("MAPHONG"));
                hd.setDate(rs.getString("TGIAN"));
                hd.setGiaThue(rs.getLong("GIA"));
                lst.add(hd);
                return lst;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    // Tìm kiếm theo mã hóa đơn 
    public static List<HoaDon> getHD(String mahd){
        try {
            String sql = "SELECT * FROM HOADON WHERE MAHD = ?";
            List<HoaDon> lst = new ArrayList<>();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, mahd);
            ResultSet rs = pstm.executeQuery();
            if(rs.next()){
                HoaDon hd = new HoaDon();
                hd.setMaHD(rs.getString("MAHD"));
                hd.setMaKT(rs.getString("MAKT"));
                hd.setMaPhong(rs.getString("MAPHONG"));
                hd.setDate(rs.getString("TGIAN"));
                hd.setGiaThue(rs.getLong("GIA"));
                lst.add(hd);
                return lst;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}

