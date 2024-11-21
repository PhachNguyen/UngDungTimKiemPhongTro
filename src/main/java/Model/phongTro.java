/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Admin
 */
public class phongTro {
    private String ma;
    private String ten;
    private String khuvuc;
    private String loai;
    private int soluong;
    private long gia;
    private String gioitinh;
    private int dientich;
    private long sodiennew,sodienold;
    private long sonuocnew,sonuocold;
    private String tinhtrang;

    public phongTro(String ma, String ten, String khuvuc, String loai, int soluong, long gia, String gioitinh, int dientich, long sodiennew, long sodienold, long sonuocnew, long sonuocold,String tinhtrang) {
        this.ma = ma;
        this.ten = ten;
        this.khuvuc = khuvuc;
        this.loai = loai;
        this.soluong = soluong;
        this.gia = gia;
        this.gioitinh = gioitinh;
        this.dientich = dientich;
        this.sodiennew = sodiennew;
        this.sodienold = sodienold;
        this.sonuocnew = sonuocnew;
        this.sonuocold = sonuocold;
        this.tinhtrang = tinhtrang;
    }

    public long getSodiennew() {
        return sodiennew;
    }

    public void setSodiennew(long sodiennew) {
        this.sodiennew = sodiennew;
    }

    public long getSodienold() {
        return sodienold;
    }

    public void setSodienold(long sodienold) {
        this.sodienold = sodienold;
    }

    public long getSonuocnew() {
        return sonuocnew;
    }

    public void setSonuocnew(long sonuocnew) {
        this.sonuocnew = sonuocnew;
    }

    public long getSonuocold() {
        return sonuocold;
    }

    public void setSonuocold(long sonuocold) {
        this.sonuocold = sonuocold;
    }

    public phongTro() {
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getKhuvuc() {
        return khuvuc;
    }

    public void setKhuvuc(String khuvuc) {
        this.khuvuc = khuvuc;
    }

    public String getLoai() {
        return loai;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public long getGia() {
        return gia;
    }

    public void setGia(long gia) {
        this.gia = gia;
    }

    public String getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }

    public int getDientich() {
        return dientich;
    }

    public void setDientich(int dientich) {
        this.dientich = dientich;
    }
    public long tienDien(){
        return 4000*(sodiennew-sodienold);
    }
    public long tienNuoc(){
        return 3500*(sonuocnew-sonuocold);
    }

    public String getTinhtrang() {
        return tinhtrang;
    }

    public void setTinhtrang(String tinhtrang) {
        this.tinhtrang = tinhtrang;
    }
   
    
}
