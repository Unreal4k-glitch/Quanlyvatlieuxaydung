/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlyvatlieuxaydung;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 *
 * @author V
 */
public class HoaDon {
    private int maHoaDon;
    private int maKhachHang;
    private int maNhanVien;
    private LocalDateTime ngayLap;
    private String phuongThucThanhToan;
    private BigDecimal tongTien;
    private BigDecimal giamGia;
    private BigDecimal thanhToan;
    public HoaDon(){
    }
    public HoaDon(int maHoaDon, int maKhachHang,
                  int maNhanVien, LocalDateTime ngayLap,
                  String phuongThucThanhToan,
                  BigDecimal tongTien, BigDecimal giamGia,
                  BigDecimal thanhToan) {

        this.maHoaDon = maHoaDon;
        this.maKhachHang = maKhachHang;
        this.maNhanVien = maNhanVien;
        this.ngayLap = ngayLap;
        this.phuongThucThanhToan = phuongThucThanhToan;
        this.tongTien = tongTien;
        this.giamGia = giamGia;
        this.thanhToan = thanhToan;
    }
    public int getMaHoaDon() {
        return maHoaDon;
    }
    public void setMaHoaDon(int maHoaDon) {
        this.maHoaDon = maHoaDon;
    }
    public int getMaKhachHang() {
        return maKhachHang;
    }
    public void setMaKhachHang(int maKhachHang) {
        this.maKhachHang = maKhachHang;
    }
    public int getMaNhanVien() {
        return maNhanVien;
    }
    public void setMaNhanVien(int maNhanVien) {
        this.maNhanVien = maNhanVien;
    }
    public LocalDateTime getNgayLap() {
        return ngayLap;
    }
    public void setNgayLap(LocalDateTime ngayLap) {
        this.ngayLap = ngayLap;
    }
    public String getPhuongThucThanhToan() {
        return phuongThucThanhToan;
    }
    public void setPhuongThucThanhToan(String phuongThucThanhToan) {
        this.phuongThucThanhToan = phuongThucThanhToan;
    }
    public BigDecimal getTongTien() {
        return tongTien;
    }
    public void setTongTien(BigDecimal tongTien) {
        this.tongTien = tongTien;
    }
    public BigDecimal getGiamGia() {
        return giamGia;
    }
    public void setGiamGia(BigDecimal giamGia) {
        this.giamGia = giamGia;
    }
    public BigDecimal getThanhToan() {
        return thanhToan;
    }
    public void setThanhToan(BigDecimal thanhToan) {
        this.thanhToan = thanhToan;
    }
}
