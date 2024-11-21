/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Admin
 */
public class HoaDon {
     String maHD, maKT, maPhong, tenKT,loaiPhong,ngaybd,ngaykt,quequan;

    public String getQuequan() {
        return quequan;
    }

    public void setQuequan(String quequan) {
        this.quequan = quequan;
    }

    public String getNgaybd() {
        return ngaybd;
    }

    public void setNgaybd(String ngaybd) {
        this.ngaybd = ngaybd;
    }

    public String getNgaykt() {
        return ngaykt;
    }

    public void setNgaykt(String ngaykt) {
        this.ngaykt = ngaykt;
    }

    public String getLoaiPhong() {
        return loaiPhong;
    }

    public void setLoaiPhong(String loaiPhong) {
        this.loaiPhong = loaiPhong;
    }
    Long sdc, sdm, snc, snm;
    Long giaThue, tienDien, tongTien, tienNuoc, tienDV;
    String date;

    public HoaDon(String maHD, String maKT, String maPhong, String tenKT, Long sdc, Long sdm, Long snc, Long snm, Long giaThue, Long tienDien, Long tongTien, Long tienNuoc, Long tienDV, String date) {
        this.maHD = maHD;
        this.maKT = maKT;
        this.maPhong = maPhong;
        this.tenKT = tenKT;
        this.sdc = sdc;
        this.sdm = sdm;
        this.snc = snc;
        this.snm = snm;
        this.giaThue = giaThue;
        this.tienDien = tienDien;
        this.tongTien = tongTien;
        this.tienNuoc = tienNuoc;
        this.tienDV = tienDV;
        this.date = date;
    }

    public HoaDon() {
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public String getMaKT() {
        return maKT;
    }

    public void setMaKT(String maKT) {
        this.maKT = maKT;
    }

    public String getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(String maPhong) {
        this.maPhong = maPhong;
    }

    public String getTenKT() {
        return tenKT;
    }

    public void setTenKT(String tenKT) {
        this.tenKT = tenKT;
    }

    public Long getSdc() {
        return sdc;
    }

    public void setSdc(Long sdc) {
        this.sdc = sdc;
    }

    public Long getSdm() {
        return sdm;
    }

    public void setSdm(Long sdm) {
        this.sdm = sdm;
    }

    public Long getSnc() {
        return snc;
    }

    public void setSnc(Long snc) {
        this.snc = snc;
    }

    public Long getSnm() {
        return snm;
    }

    public void setSnm(Long snm) {
        this.snm = snm;
    }

    public Long getGiaThue() {
        return giaThue;
    }

    public void setGiaThue(Long giaThue) {
        this.giaThue = giaThue;
    }

    public Long getTienDien() {
        return tienDien;
    }

    public void setTienDien(Long tienDien) {
        this.tienDien = tienDien;
    }

    public Long getTongTien() {
        return tongTien;
    }

    public void setTongTien(Long tongTien) {
        this.tongTien = tongTien;
    }

    public Long getTienNuoc() {
        return tienNuoc;
    }

    public void setTienNuoc(Long tienNuoc) {
        this.tienNuoc = tienNuoc;
    }

    public Long getTienDV() {
        return tienDV;
    }

    public void setTienDV(Long tienDV) {
        this.tienDV = tienDV;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
      public String toString() {
        return "Mã Hoá Đơn : " + maHD 
                + "\t\tNgày Tạo Hoá Đơn : " + date 
                + "\nMã Khách Thuê : " + maKT 
                + "\t\tTên Khách Thuê : " + tenKT 
                + "\nMã Phòng : " + maPhong 
                + "\nGiá Phòng : " + giaThue 
                + "\nSố Chữ Điện Cũ : " + sdc 
                +"\t\tSố Chữ Điện Mới : " + sdm 
                +"\nTổng Tiền Điện : " + tienDien 
                + "\nSố Chữ Nước Cũ : " + snc 
                +"\t\tSố Chữ Nước Mới : " + snm 
                + "\nTổng Tiền Nước : " + tienNuoc 
                + "\nTiền Dịch Vụ : " + tienDV 
                + "\nTổng Tiền : " + tongTien;
    }
}
