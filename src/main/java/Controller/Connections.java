/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class Connections {
    protected static Connection conn;

  
    public Connections() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); // cầu nối tới SQL Server 
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Connections.class.getName()).log(Level.SEVERE, null, ex);
        }
        String dbUrl = "jdbc:sqlserver://localhost:1433;databasename=Java;encrypt=false";
        String username ="sa";
        String password ="123456";
        if(conn == null)
        {
        try {
             conn = DriverManager.getConnection(dbUrl, username, password); // Liên kết tới SQL 
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Kết nối tới CSDL thất bại", "Error", 0);
        }
        }
    }
      // Đảm bảo đóng kết nối khi không sử dụng nữa
    public static void closeConnection() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                conn = null; // Giải phóng kết nối sau khi đóng
                System.out.println("Kết nối đã được đóng");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Lỗi khi đóng kết nối");
        }
    }
        
}
