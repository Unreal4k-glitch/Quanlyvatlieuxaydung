
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlyvatlieuxaydung.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import quanlyvatlieuxaydung.DatabaseConnection;
public class SanPhamDAO {
    public List<Object[]> getDanhSachSanPham() {

        List<Object[]> danhSach = new ArrayList<>();

        String sql = """
            SELECT MaSanPham, TenSanPham, DonViTinh, GiaBan
            FROM SanPham
            ORDER BY MaSanPham
            """;

        try (Connection conn = DatabaseConnection.getConnection()) {

            if (conn == null) {
                return danhSach;
            }

            try (PreparedStatement ps = conn.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    int maSP = rs.getInt("MaSanPham");
                    String tenSP = rs.getString("TenSanPham");
                    String donViTinh = rs.getString("DonViTinh");
                    java.math.BigDecimal giaBan =
                            rs.getBigDecimal("GiaBan");

                    danhSach.add(
                        new Object[]{maSP, tenSP, donViTinh, giaBan}
                    );
                }
            }

        } catch (Exception e) {
            System.out.println("Loi lay danh sach san pham!");
            e.printStackTrace();
        }

        return danhSach;
    }
    public boolean themSanPham(
        String tenSP,
        String danhMuc,
        String donViTinh,
        java.math.BigDecimal giaBan,
        String moTa) {

    String sqlMa = """
        SELECT ISNULL(MAX(MaSanPham), 0) + 1
        FROM SanPham
        """;

    String sql = """
        INSERT INTO SanPham
        (MaSanPham, TenSanPham, DanhMuc, DonViTinh, GiaBan, MoTa)
        VALUES (?, ?, ?, ?, ?, ?)
        """;

    try (Connection conn = DatabaseConnection.getConnection()) {

        if (conn == null) return false;

        conn.setAutoCommit(false);

        try {
            int maSP;

            try (PreparedStatement psMa = conn.prepareStatement(sqlMa);
                 ResultSet rs = psMa.executeQuery()) {

                rs.next();
                maSP = rs.getInt(1);
            }

            try (PreparedStatement ps = conn.prepareStatement(sql)) {

                ps.setInt(1, maSP);
                ps.setString(2, tenSP);
                ps.setString(3, danhMuc);
                ps.setString(4, donViTinh);
                ps.setBigDecimal(5, giaBan);
                ps.setString(6, moTa);

                int ketQua = ps.executeUpdate();

                conn.commit();

                return ketQua > 0;
            }

        } catch (Exception e) {
            conn.rollback();
            throw e;
        }

    } catch (Exception e) {
        System.out.println("Loi them san pham!");
        e.printStackTrace();
    }

    return false;
}
    public boolean suaSanPham(
        int maSP,
        String tenSP,
        String danhMuc,
        String donViTinh,
        java.math.BigDecimal giaBan,
        String moTa) {

    String sql = """
        UPDATE SanPham
        SET TenSanPham = ?,
            DanhMuc = ?,
            DonViTinh = ?,
            GiaBan = ?,
            MoTa = ?
        WHERE MaSanPham = ?
        """;

    try (Connection conn = DatabaseConnection.getConnection()) {

        if (conn == null) return false;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, tenSP);
            ps.setString(2, danhMuc);
            ps.setString(3, donViTinh);
            ps.setBigDecimal(4, giaBan);
            ps.setString(5, moTa);
            ps.setInt(6, maSP);

            int ketQua = ps.executeUpdate();

            return ketQua > 0;
        }

    } catch (Exception e) {
        System.out.println("Loi sua san pham!");
        e.printStackTrace();
    }

    return false;
}
    public boolean xoaSanPham(int maSP) {

    String sql = """
        DELETE FROM SanPham
        WHERE MaSanPham = ?
        """;

    try (Connection conn = DatabaseConnection.getConnection()) {

        if (conn == null) return false;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, maSP);

            int ketQua = ps.executeUpdate();

            return ketQua > 0;
        }

    } catch (Exception e) {
        System.out.println("Loi xoa san pham!");
        e.printStackTrace();
    }

    return false;
}
    public List<Object[]> timKiemSanPham(String tuKhoa) {

    List<Object[]> danhSach = new ArrayList<>();

    String sql = """
        SELECT MaSanPham, TenSanPham, DonViTinh, GiaBan
        FROM SanPham
        WHERE TenSanPham LIKE ?
           OR DanhMuc LIKE ?
           OR DonViTinh LIKE ?
        ORDER BY MaSanPham
        """;

    try (Connection conn = DatabaseConnection.getConnection()) {

        if (conn == null) return danhSach;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            String keyword = "%" + tuKhoa + "%";

            ps.setString(1, keyword);
            ps.setString(2, keyword);
            ps.setString(3, keyword);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    int maSP = rs.getInt("MaSanPham");
                    String tenSP = rs.getString("TenSanPham");
                    String donViTinh = rs.getString("DonViTinh");
                    java.math.BigDecimal giaBan =
                            rs.getBigDecimal("GiaBan");

                    danhSach.add(new Object[]{
                        maSP, tenSP, donViTinh, giaBan
                    });
                }
            }
        }

    } catch (Exception e) {
        System.out.println("Loi tim kiem san pham!");
        e.printStackTrace();
    }

    return danhSach;
}
}
