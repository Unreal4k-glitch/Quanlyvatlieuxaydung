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
public class NhanVienDAO {
        public List<Object[]> getDanhSachNhanVien() {

        List<Object[]> danhSach = new ArrayList<>();

        String sql = """
            SELECT MaNhanVien, HoTen, SoDienThoai,
                   DiaChi, NgaySinh, ChucVu
            FROM NhanVien
            ORDER BY MaNhanVien
            """;

        try (Connection conn = DatabaseConnection.getConnection()) {

            if (conn == null) {
                return danhSach;
            }

            try (PreparedStatement ps = conn.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    int maNV = rs.getInt("MaNhanVien");
                    String hoTen = rs.getString("HoTen");
                    String sdt = rs.getString("SoDienThoai");
                    String diaChi = rs.getString("DiaChi");
                    java.sql.Date ngaySinh = rs.getDate("NgaySinh");
                    String chucVu = rs.getString("ChucVu");

                    danhSach.add(new Object[]{
                        maNV, hoTen, sdt, diaChi, ngaySinh, chucVu
                    });
                }
            }

        } catch (Exception e) {
            System.out.println("Loi lay danh sach nhan vien!");
            e.printStackTrace();
        }

        return danhSach;
    }
        public boolean themNhanVien(
        String hoTen,
        String sdt,
        String diaChi,
        java.sql.Date ngaySinh,
        String chucVu) {

    String sqlMa = """
        SELECT ISNULL(MAX(MaNhanVien), 0) + 1
        FROM NhanVien
        """;

    String sql = """
        INSERT INTO NhanVien
        (MaNhanVien, HoTen, SoDienThoai, DiaChi, NgaySinh, ChucVu)
        VALUES (?, ?, ?, ?, ?, ?)
        """;

    try (Connection conn = DatabaseConnection.getConnection()) {

        if (conn == null) {
            return false;
        }

        conn.setAutoCommit(false);

        try {
            int maNV;

            try (PreparedStatement psMa = conn.prepareStatement(sqlMa);
                 ResultSet rs = psMa.executeQuery()) {

                rs.next();
                maNV = rs.getInt(1);
            }

            try (PreparedStatement ps = conn.prepareStatement(sql)) {

                ps.setInt(1, maNV);
                ps.setString(2, hoTen);
                ps.setString(3, sdt);
                ps.setString(4, diaChi);
                ps.setDate(5, ngaySinh);
                ps.setString(6, chucVu);

                int ketQua = ps.executeUpdate();

                conn.commit();

                return ketQua > 0;
            }

        } catch (Exception e) {
            conn.rollback();
            throw e;
        }

    } catch (Exception e) {
        System.out.println("Loi them nhan vien!");
        e.printStackTrace();
    }

    return false;
}
        
        public boolean suaNhanVien(
        int maNV,
        String hoTen,
        String sdt,
        String diaChi,
        java.sql.Date ngaySinh,
        String chucVu) {

    String sql = """
        UPDATE NhanVien
        SET HoTen = ?,
            SoDienThoai = ?,
            DiaChi = ?,
            NgaySinh = ?,
            ChucVu = ?
        WHERE MaNhanVien = ?
        """;

    try (Connection conn = DatabaseConnection.getConnection()) {

        if (conn == null) return false;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, hoTen);
            ps.setString(2, sdt);
            ps.setString(3, diaChi);
            ps.setDate(4, ngaySinh);
            ps.setString(5, chucVu);
            ps.setInt(6, maNV);

            int ketQua = ps.executeUpdate();

            return ketQua > 0;
        }

    } catch (Exception e) {
        System.out.println("Loi sua nhan vien!");
        e.printStackTrace();
    }

    return false;
} 
        public boolean xoaNhanVien(int maNV) {

    String sql = """
        DELETE FROM NhanVien
        WHERE MaNhanVien = ?
        """;

    try (Connection conn = DatabaseConnection.getConnection()) {

        if (conn == null) return false;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, maNV);

            int ketQua = ps.executeUpdate();

            return ketQua > 0;
        }

    } catch (Exception e) {
        System.out.println("Loi xoa nhan vien!");
        e.printStackTrace();
    }

    return false;
}
        public List<Object[]> timKiemNhanVien(String tuKhoa) {

    List<Object[]> danhSach = new ArrayList<>();

    String sql = """
        SELECT MaNhanVien, HoTen, SoDienThoai,
               DiaChi, NgaySinh, ChucVu
        FROM NhanVien
        WHERE HoTen LIKE ?
           OR SoDienThoai LIKE ?
           OR ChucVu LIKE ?
        ORDER BY MaNhanVien
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

                    int maNV = rs.getInt("MaNhanVien");
                    String hoTen = rs.getString("HoTen");
                    String sdt = rs.getString("SoDienThoai");
                    String diaChi = rs.getString("DiaChi");
                    java.sql.Date ngaySinh = rs.getDate("NgaySinh");
                    String chucVu = rs.getString("ChucVu");

                    danhSach.add(new Object[]{
                        maNV, hoTen, sdt, diaChi, ngaySinh, chucVu
                    });
                }
            }
        }

    } catch (Exception e) {
        System.out.println("Loi tim kiem nhan vien!");
        e.printStackTrace();
    }

    return danhSach;
}
}
