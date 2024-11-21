/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Controller.Connections;
import Model.CurrentUser;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Admin
 */
public class UserDAO extends Connections{
    public UserDAO() throws ClassNotFoundException{
        super();
    }
    public boolean checkLogin(String tk, String mk) throws SQLException{
        String sql = "SELECT * FROM USERS WHERE USERNAME = ? AND PASSWORDS =?";
        try{
           
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, tk);
        pstm.setString(2, mk);
        ResultSet rs = pstm.executeQuery();
        if(rs.next())   
                return true;
        else    
            return false;
            }catch (SQLException ex){
                ex.printStackTrace();
            }
        return false;
    }
    public boolean Register(String tk, String mk) throws SQLException{
        String sql ="INSERT INTO USERS(USERNAME,PASSWORDS,MAKT) VALUES(?,?,?)";
        PreparedStatement pstm = conn.prepareStatement(sql);
        String makt=UserDAO.TaoMaKH();
        CurrentUser.setMakt(makt);
        CurrentUser.setUsername(tk);
        pstm.setString(1, tk);
        pstm.setString(2, mk);
        pstm.setString(3, makt);
        pstm.executeUpdate();
        return true;
//  String sqlKhachHang = "INSERT INTO KHACHHANG (MAKT) VALUES(?)";
//    PreparedStatement pstmKhachHang = conn.prepareStatement(sqlKhachHang);
//      pstmKhachHang.setString(1, makt);
//      pstmKhachHang.executeUpdate();
//          return true;
    }
  //  public boolean  getMaKT()
//      public boolean Register(String tk, String mk, String makt) throws SQLException{
//            conn = new connection();
//            String makt = 
//            String sql ="INSERT INTO USERS(USERNAME,PASSWORDS,MAKT) VALUES(?,?,?)";
//            PreparedStatement pstm = conn.prepareStatement(sql);
//            pstm.setString(1, tk);
//            pstm.setString(2, mk);
//            pstm.setString(3, makt);
//            pstm.executeUpdate();
//            return true;   
//        }
            public  static String TaoMaKH(){
            
                String maKhachHangMoi = "KH001"; // Mặc định nếu không có khách hàng nào
                try {
                    // Truy vấn mã khách hàng lớn nhất hiện có
                    String query = "SELECT MAX(MAKT) AS MAX_MAKH FROM USERS";
                    PreparedStatement prst = conn.prepareStatement(query);
                    ResultSet rs = prst.executeQuery();

                    if (rs.next()) {
                        String maxMaKhachHang = rs.getString("MAX_MAKH"); // Lấy mã lớn nhất
                        if (maxMaKhachHang != null) {
                            // Tách phần số từ mã khách hàng, ví dụ "KH001" -> 1
                            int soThuTu = Integer.parseInt(maxMaKhachHang.substring(2));
                            // Tăng số thứ tự lên 1
                            soThuTu++;
                            // Ghép lại thành mã mới, đảm bảo đủ 3 chữ số
                            maKhachHangMoi = String.format("KH%03d", soThuTu);
                        }
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                return maKhachHangMoi;
            }
            
            // Hàm để check user
    public static boolean checkUser(String username) {
    try {
        // Câu lệnh SQL kiểm tra xem username đã tồn tại trong bảng USERS chưa
        String sql = "SELECT COUNT(*) AS COUNT FROM USERS WHERE USERNAME = ?";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, username); // Gán giá trị username vào câu lệnh SQL
        ResultSet rs = pstm.executeQuery();
        
        if (rs.next()) {
            int count = rs.getInt("COUNT");
            return count == 0; // Trả về true nếu không tồn tại username
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return false; // Trả về false nếu có lỗi
}
// Hàm hiển thị ai đang đăng nhập 
    public static void showUser(String makt){
        try {
            String sql = "SELECT USERNAME FROM USERS WHERE MAKT=?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, makt);
            pstm.executeQuery();
        } catch (SQLException ex) {
           ex.printStackTrace();
        }
    }
}
