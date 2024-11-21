/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Admin
 */
public class KhachThue {
    String maKT,HoTen,NgaySinh,NgheNghiep,GioiTinh,Sdt,QueQuan,maPhong,startDay,endDay;

    public KhachThue(String maKT, String HoTen, String NgaySinh, String NgheNghiep, String GioiTinh, String Sdt, String QueQuan, String maPhong, String startDay, String endDay) {
        this.maKT = maKT;
        this.HoTen = HoTen;
        this.NgaySinh = NgaySinh;
        this.NgheNghiep = NgheNghiep;
        this.GioiTinh = GioiTinh;
        this.Sdt = Sdt;
        this.QueQuan = QueQuan;
        this.maPhong = maPhong;
        this.startDay = startDay;
        this.endDay = endDay;
    }

    public KhachThue() {
    }

    public String getMaKT() {
        return maKT;
    }

    public void setMaKT(String maKT) {
        this.maKT = maKT;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String HoTen) {
        this.HoTen = HoTen;
    }

    public String getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(String NgaySinh) {
        this.NgaySinh = NgaySinh;
    }

    public String getNgheNghiep() {
        return NgheNghiep;
    }

    public void setNgheNghiep(String NgheNghiep) {
        this.NgheNghiep = NgheNghiep;
    }

    public String getGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(String GioiTinh) {
        this.GioiTinh = GioiTinh;
    }

    public String getSdt() {
        return Sdt;
    }

    public void setSdt(String Sdt) {
        this.Sdt = Sdt;
    }

    public String getQueQuan() {
        return QueQuan;
    }

    public void setQueQuan(String QueQuan) {
        this.QueQuan = QueQuan;
    }

    public String getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(String maPhong) {
        this.maPhong = maPhong;
    }

    public String getStartDay() {
        return startDay;
    }

    public void setStartDay(String startDay) {
        this.startDay = startDay;
    }

    public String getEndDay() {
        return endDay;
    }

    public void setEndDay(String endDay) {
        this.endDay = endDay;
    }

}