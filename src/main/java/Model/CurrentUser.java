/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Admin
 */
public class CurrentUser {
    public static String makt;
    public static String username;
    public static String maPhong;
    public static String startDay;
    public static String endDay;
    public static String mahd;

    public CurrentUser() {
    }

    public static String getMahd() {
        return mahd;
    }

    public static void setMahd(String mahd) {
        CurrentUser.mahd = mahd;
    }
    

    public static String getMakt() {
        return makt;
    }

    public static void setMakt(String makt) {
        CurrentUser.makt = makt;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        CurrentUser.username = username;
    }

    public static String getMaPhong() {
        return maPhong;
    }

    public static void setMaPhong(String maPhong) {
        CurrentUser.maPhong = maPhong;
    }

    public static String getStartDay() {
        return startDay;
    }

    public static void setStartDay(String startDay) {
        CurrentUser.startDay = startDay;
    }

    public static String getEndDay() {
        return endDay;
    }

    public static void setEndDay(String endDay) {
        CurrentUser.endDay = endDay;
    }

    
  
}
