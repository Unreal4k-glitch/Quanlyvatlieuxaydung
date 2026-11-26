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
public class NhaCungCapDAO {
    public List<Object[]> getDanhSachNhaCungCap() {

        List<Object[]> danhSach = new ArrayList<>();

        String sql = """
            SELECT MaNCC, TenNCC, SoDienThoai, DiaChi, Email
            FROM NhaCungCap
            ORDER BY MaNCC
            """;

        try (Connection conn = DatabaseConnection.getConnection()) {

            if (conn == null) return danhSach;

            try (PreparedStatement ps = conn.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    int maNCC = rs.getInt("MaNCC");
                    String tenNCC = rs.getString("TenNCC");
                    String sdt = rs.getString("SoDienThoai");
                    String diaChi = rs.getString("DiaChi");
                    String email = rs.getString("Email");

                    danhSach.add(new Object[]{
                        maNCC, tenNCC, sdt, diaChi, email
                    });
                }
            }

        } catch (Exception e) {
            System.out.println("Loi lay danh sach nha cung cap!");
            e.printStackTrace();
        }

        return danhSach;
    }
    public boolean themNhaCungCap(
        String tenNCC,
        String sdt,
        String diaChi,
        String email) {

    String sqlMa = """
        SELECT ISNULL(MAX(MaNCC), 0) + 1
        FROM NhaCungCap
        """;

    String sql = """
        INSERT INTO NhaCungCap
        (MaNCC, TenNCC, SoDienThoai, DiaChi, Email)
        VALUES (?, ?, ?, ?, ?)
        """;

    try (Connection conn = DatabaseConnection.getConnection()) {

        if (conn == null) return false;

        conn.setAutoCommit(false);

        try {
            int maNCC;

            try (PreparedStatement psMa = conn.prepareStatement(sqlMa);
                 ResultSet rs = psMa.executeQuery()) {

                rs.next();
                maNCC = rs.getInt(1);
            }

            try (PreparedStatement ps = conn.prepareStatement(sql)) {

                ps.setInt(1, maNCC);
                ps.setString(2, tenNCC);
                ps.setString(3, sdt);
                ps.setString(4, diaChi);
                ps.setString(5, email);

                int ketQua = ps.executeUpdate();

                conn.commit();

                return ketQua > 0;
            }

        } catch (Exception e) {
            conn.rollback();
            throw e;
        }

    } catch (Exception e) {
        System.out.println("Loi them nha cung cap!");
        e.printStackTrace();
    }

    return false;
}
    public boolean suaNhaCungCap(
        int maNCC,
        String tenNCC,
        String sdt,
        String diaChi,
        String email) {

    String sql = """
        UPDATE NhaCungCap
        SET TenNCC = ?,
            SoDienThoai = ?,
            DiaChi = ?,
            Email = ?
        WHERE MaNCC = ?
        """;

    try (Connection conn = DatabaseConnection.getConnection()) {

        if (conn == null) return false;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, tenNCC);
            ps.setString(2, sdt);
            ps.setString(3, diaChi);
            ps.setString(4, email);
            ps.setInt(5, maNCC);

            int ketQua = ps.executeUpdate();

            return ketQua > 0;
        }

    } catch (Exception e) {
        System.out.println("Loi sua nha cung cap!");
        e.printStackTrace();
    }

    return false;
}
    public boolean xoaNhaCungCap(int maNCC) {

    String sql = """
        DELETE FROM NhaCungCap
        WHERE MaNCC = ?
        """;

    try (Connection conn = DatabaseConnection.getConnection()) {

        if (conn == null) return false;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, maNCC);

            int ketQua = ps.executeUpdate();

            return ketQua > 0;
        }

    } catch (Exception e) {
        System.out.println("Loi xoa nha cung cap!");
        e.printStackTrace();
    }

    return false;
}
    public List<Object[]> timKiemNhaCungCap(String tuKhoa) {

    List<Object[]> danhSach = new ArrayList<>();

    String sql = """
        SELECT MaNCC, TenNCC, SoDienThoai, DiaChi, Email
        FROM NhaCungCap
        WHERE TenNCC LIKE ?
           OR SoDienThoai LIKE ?
           OR Email LIKE ?
        ORDER BY MaNCC
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

                    int maNCC = rs.getInt("MaNCC");
                    String tenNCC = rs.getString("TenNCC");
                    String sdt = rs.getString("SoDienThoai");
                    String diaChi = rs.getString("DiaChi");
                    String email = rs.getString("Email");

                    danhSach.add(new Object[]{
                        maNCC, tenNCC, sdt, diaChi, email
                    });
                }
            }
        }

    } catch (Exception e) {
        System.out.println("Loi tim kiem nha cung cap!");
        e.printStackTrace();
    }

    return danhSach;
}
}
