/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Admin
 */
public class phong {
    private String maphong;
    private String tenphong;
    private String khuvuc;
    private int songuoi;
    private int dientich;
    private String doituong;
    private String loai;
    private String tinhtrang;
    private int gia;
    private int sodiennew;
    private int sodienold;
    private int sonuocnew;
    private int sonuocold;

    public void setMaphong(String maphong) {
        this.maphong = maphong;
    }

    public void setTenphong(String tenphong) {
        this.tenphong = tenphong;
    }

    public void setKhuvuc(String khuvuc) {
        this.khuvuc = khuvuc;
    }

    public void setSonguoi(int songuoi) {
        this.songuoi = songuoi;
    }

    public void setDientich(int dientich) {
        this.dientich = dientich;
    }

    public void setDoituong(String doituong) {
        this.doituong = doituong;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }

    public void setTinhtrang(String tinhtrang) {
        this.tinhtrang = tinhtrang;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public void setSodiennew(int sodiennew) {
        this.sodiennew = sodiennew;
    }

    public void setSodienold(int sodienold) {
        this.sodienold = sodienold;
    }

    public void setSonuocnew(int sonuocnew) {
        this.sonuocnew = sonuocnew;
    }

    public void setSonuocold(int sonuocold) {
        this.sonuocold = sonuocold;
    }

    public String getMaphong() {
        return maphong;
    }

    public String getTenphong() {
        return tenphong;
    }

    public String getKhuvuc() {
        return khuvuc;
    }

    public int getSonguoi() {
        return songuoi;
    }

    public int getDientich() {
        return dientich;
    }

    public String getDoituong() {
        return doituong;
    }

    public String getLoai() {
        return loai;
    }
    
    public String getTinhtrang() {
        return tinhtrang;
    }

    public int getGia() {
        return gia;
    }

    public int getSodiennew() {
        return sodiennew;
    }

    public int getSodienold() {
        return sodienold;
    }

    public int getSonuocnew() {
        return sonuocnew;
    }

    public int getSonuocold() {
        return sonuocold;
    }


    public phong(){
        
    }
    public phong(String maphong, String tenphong, String khuvuc, int songuoi, int dientich, String doituong, String loai, String tinhtrang, int gia, int sodiennew, int sodienold, int sonuocnew, int sonuocold ){
        this.maphong = maphong;
        this.tenphong = tenphong;
        this.khuvuc = khuvuc;
        this.songuoi = songuoi;
        this.dientich = dientich;
        this.doituong = doituong;
        this.loai = loai;
        this.tinhtrang = tinhtrang;
        this.gia = gia;
        this.sodiennew = sodiennew;
        this.sodienold = sodienold;
        this.sonuocnew = sonuocnew;
        this.sodienold = sonuocold;
    }
    
    public phong(ResultSet rs) throws SQLException{
        this.maphong = rs.getString("MAPHONG");
        this.tenphong = rs.getString("TENPHONG");
        this.khuvuc = rs.getString("KHUVUC");
        this.songuoi = rs.getInt("SONGUOI");
        this.dientich = rs.getInt("DIENTICH");
        this.doituong = rs.getString("DOITUONG");
        this.loai = rs.getString("LOAI");
        this.tinhtrang = rs.getString("TINHTRANG");
        this.gia = rs.getInt("GIA");
        this.sodiennew = rs.getInt("SODIENNEW");
        this.sodienold = rs.getInt("SODIENOLD");
        this.sonuocnew = rs.getInt("SONUOCNEW");
        this.sonuocold = rs.getInt("SONUOCOLD");
    }
}
