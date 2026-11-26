/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quan.ly.vat.lieu.xay.dung.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import quan.ly.vat.lieu.xay.dung.DatabaseConnection;
import quan.ly.vat.lieu.xay.dung.HoaDon;
import quan.ly.vat.lieu.xay.dung.ChiTietHoaDon;

public class BanHangDAO {
    public boolean themHoaDonVaChiTiet(
            HoaDon hd, List<ChiTietHoaDon> dsChiTiet) {
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) {
            return false;
        }
        try {
            conn.setAutoCommit(false);
            int maHoaDonMoi = 1;
            String sqlMaxHD =
                    "SELECT ISNULL(MAX(MaHoaDon), 0) + 1 FROM HoaDon";
            try (PreparedStatement ps = conn.prepareStatement(sqlMaxHD);
                 ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    maHoaDonMoi = rs.getInt(1);
                }
            }
            String sqlHD = """
                INSERT INTO HoaDon
                (MaHoaDon, MaKhachHang, MaNhanVien, NgayLap,
                 PhuongThucThanhToan, TongTien, GiamGia, ThanhToan)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?)
                """;
            try (PreparedStatement ps = conn.prepareStatement(sqlHD)) {
                ps.setInt(1, maHoaDonMoi);
                ps.setInt(2, hd.getMaKhachHang());
                ps.setInt(3, hd.getMaNhanVien());
                if (hd.getNgayLap() != null) {
                    ps.setTimestamp(
                        4, Timestamp.valueOf(hd.getNgayLap()));
                } else {
                    ps.setTimestamp(4, null);
                }
                ps.setString(5, hd.getPhuongThucThanhToan());
                ps.setBigDecimal(6, hd.getTongTien());
                ps.setBigDecimal(7, hd.getGiamGia());
                ps.setBigDecimal(8, hd.getThanhToan());
                ps.executeUpdate();
            }
            int maChiTietMoi = 1;
            String sqlMaxCT =
                    "SELECT ISNULL(MAX(MaChiTietHD), 0) + 1 "
                    + "FROM ChiTietHoaDon";
            try (PreparedStatement ps = conn.prepareStatement(sqlMaxCT);
                 ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    maChiTietMoi = rs.getInt(1);
                }
            }
            String sqlCT = """
                INSERT INTO ChiTietHoaDon
                (MaChiTietHD, MaHoaDon, MaSanPham,
                 SoLuong, DonGiaBan, ThanhTien)
                VALUES (?, ?, ?, ?, ?, ?)
                """;
            try (PreparedStatement ps = conn.prepareStatement(sqlCT)) {
                for (ChiTietHoaDon ct : dsChiTiet) {
                    ps.setInt(1, maChiTietMoi++);
                    ps.setInt(2, maHoaDonMoi);
                    ps.setInt(3, ct.getMaSanPham());
                    ps.setInt(4, ct.getSoLuong());
                    ps.setBigDecimal(5, ct.getDonGiaBan());
                    ps.setBigDecimal(6, ct.getThanhTien());
                    ps.addBatch();
                }
                ps.executeBatch();
            }
            conn.commit();
            hd.setMaHoaDon(maHoaDonMoi);
            System.out.println("Them hoa don thanh cong!");
            System.out.println("Ma hoa don: " + maHoaDonMoi);
            return true;
        } catch (Exception e) {
            try {
                conn.rollback();
                System.out.println("Da rollback giao dich!");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
            return false;
        } finally {
            try {
                conn.setAutoCommit(true);
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
