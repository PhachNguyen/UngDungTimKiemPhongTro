/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


/**
 *
 * @author Admin
 */
public class connection {
//    protected static connection conn;
    public Connection connect() throws SQLException{
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String dbUrl = "jdbc:sqlserver://localhost:1433;databaseName=Java; encrypt = false";
            String name = "sa";
            String password = "123456";
            Connection conn = DriverManager.getConnection(dbUrl, name,password);
            return conn;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,"Kết nối CSDL thất bại", "Thông báo", 1);
            ex.printStackTrace();
        }
        return null;
    }

    public PreparedStatement prepareStatement(String query) throws SQLException {
        Connection conn = connect();
        return conn.prepareStatement(query);
    }
}