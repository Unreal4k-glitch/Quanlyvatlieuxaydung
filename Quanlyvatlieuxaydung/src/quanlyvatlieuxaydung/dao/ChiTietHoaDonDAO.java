/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlyvatlieuxaydung.dao;
import quanlyvatlieuxaydung.DatabaseConnection;
import quanlyvatlieuxaydung.ChiTietHoaDon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
public class ChiTietHoaDonDAO {
    public List<ChiTietHoaDon> getAll() {

        List<ChiTietHoaDon> danhSach = new ArrayList<>();

        String sql = "SELECT * FROM ChiTietHoaDon";

        try (Connection conn = DatabaseConnection.getConnection()) {

            if (conn == null) {
                System.out.println("Khong the ket noi database!");
                return danhSach;
            }

            try (PreparedStatement ps = conn.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    ChiTietHoaDon ct = new ChiTietHoaDon();

                    ct.setMaChiTietHD(rs.getInt("MaChiTietHD"));
                    ct.setMaHoaDon(rs.getInt("MaHoaDon"));
                    ct.setMaSanPham(rs.getInt("MaSanPham"));
                    ct.setSoLuong(rs.getInt("SoLuong"));
                    ct.setDonGiaBan(rs.getBigDecimal("DonGiaBan"));
                    ct.setThanhTien(rs.getBigDecimal("ThanhTien"));

                    danhSach.add(ct);
                }
            }

        } catch (Exception e) {
            System.out.println("Loi khi lay danh sach chi tiet hoa don:");
            e.printStackTrace();
        }

        return danhSach;
    }
}
