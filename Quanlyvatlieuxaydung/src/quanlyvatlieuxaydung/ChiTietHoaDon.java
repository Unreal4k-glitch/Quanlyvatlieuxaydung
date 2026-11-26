/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlyvatlieuxaydung;

import java.math.BigDecimal;
/**
 *
 * @author V
 */
public class ChiTietHoaDon {
    private int maChiTietHD;
    private int maHoaDon;
    private int maSanPham;
    private int soLuong;
    private BigDecimal donGiaBan;
    private BigDecimal thanhTien;
    public ChiTietHoaDon(){
    }
    public ChiTietHoaDon(int maChiTietHD, int maHoaDon,
                         int maSanPham, int soLuong,
                         BigDecimal donGiaBan,
                         BigDecimal thanhTien) {

        this.maChiTietHD = maChiTietHD;
        this.maHoaDon = maHoaDon;
        this.maSanPham = maSanPham;
        this.soLuong = soLuong;
        this.donGiaBan = donGiaBan;
        this.thanhTien = thanhTien;
    }
    public int getMaChiTietHD() {
        return maChiTietHD;
    }
    public void setMaChiTietHD(int maChiTietHD) {
        this.maChiTietHD = maChiTietHD;
    }
    public int getMaHoaDon() {
        return maHoaDon;
    }
    public void setMaHoaDon(int maHoaDon) {
        this.maHoaDon = maHoaDon;
    }
    public int getMaSanPham() {
        return maSanPham;
    }
    public void setMaSanPham(int maSanPham) {
        this.maSanPham = maSanPham;
    }
    public int getSoLuong() {
        return soLuong;
    }
    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
    public BigDecimal getDonGiaBan() {
        return donGiaBan;
    }
    public void setDonGiaBan(BigDecimal donGiaBan) {
        this.donGiaBan = donGiaBan;
    }
    public BigDecimal getThanhTien() {
        return thanhTien;
    }
    public void setThanhTien(BigDecimal thanhTien) {
        this.thanhTien = thanhTien;
    }
}
