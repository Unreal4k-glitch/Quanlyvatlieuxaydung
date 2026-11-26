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
public class KhachHangDAO {
    public List<Object[]> getDanhSachKhachHang() {

        List<Object[]> danhSach = new ArrayList<>();

        String sql = """
            SELECT MaKhachHang, TenKhachHang, SoDienThoai, DiaChi
            FROM KhachHang
            ORDER BY MaKhachHang
            """;
        
        try (Connection conn = DatabaseConnection.getConnection()) {

            if (conn == null) return danhSach;

            try (PreparedStatement ps = conn.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    danhSach.add(new Object[]{
                        rs.getInt("MaKhachHang"),
                        rs.getString("TenKhachHang"),
                        rs.getString("SoDienThoai"),
                        rs.getString("DiaChi")
                    });
                }
            }

        } catch (Exception e) {
            System.out.println("Loi lay danh sach khach hang!");
            e.printStackTrace();
        }

        return danhSach;
    }
    
public boolean themKhachHang(String tenKH, String sdt, String diaChi) {

    String sql = """
        INSERT INTO KhachHang
        (MaKhachHang, TenKhachHang, SoDienThoai, DiaChi)
        VALUES (?, ?, ?, ?)
        """;

    String sqlMa = """
        SELECT ISNULL(MAX(MaKhachHang), 0) + 1
        FROM KhachHang
        """;

    try (Connection conn = DatabaseConnection.getConnection()) {

        if (conn == null) return false;

        conn.setAutoCommit(false);

        try {
            int maKH;

            try (PreparedStatement psMa = conn.prepareStatement(sqlMa);
                 ResultSet rs = psMa.executeQuery()) {

                rs.next();
                maKH = rs.getInt(1);
            }

            try (PreparedStatement ps = conn.prepareStatement(sql)) {

                ps.setInt(1, maKH);
                ps.setString(2, tenKH);
                ps.setString(3, sdt);
                ps.setString(4, diaChi);

                int ketQua = ps.executeUpdate();

                conn.commit();
                return ketQua > 0;
            }

        } catch (Exception e) {
            conn.rollback();
            throw e;
        }

    } catch (Exception e) {
        System.out.println("Loi them khach hang!");
        e.printStackTrace();
    }

    return false;
}


    // 3. Sửa khách hàng
    public boolean suaKhachHang(int maKH, String tenKH,
                                String sdt, String diaChi) {

        String sql = """
            UPDATE KhachHang
            SET TenKhachHang = ?, SoDienThoai = ?, DiaChi = ?
            WHERE MaKhachHang = ?
            """;

        try (Connection conn = DatabaseConnection.getConnection()) {

            if (conn == null) return false;

            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, tenKH);
                ps.setString(2, sdt);
                ps.setString(3, diaChi);
                ps.setInt(4, maKH);

                return ps.executeUpdate() > 0;
            }

        } catch (Exception e) {
            System.out.println("Loi sua khach hang!");
            e.printStackTrace();
        }

        return false;
    }

    // 4. Xóa khách hàng
    public boolean xoaKhachHang(int maKH) {

        String sql = """
            DELETE FROM KhachHang
            WHERE MaKhachHang = ?
            """;

        try (Connection conn = DatabaseConnection.getConnection()) {

            if (conn == null) return false;

            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, maKH);

                return ps.executeUpdate() > 0;
            }

        } catch (Exception e) {
            System.out.println("Loi xoa khach hang!");
            e.printStackTrace();
        }

        return false;
    }

    // 5. Tìm kiếm khách hàng theo tên hoặc số điện thoại
    public List<Object[]> timKiemKhachHang(String tuKhoa) {

        List<Object[]> danhSach = new ArrayList<>();

        String sql = """
            SELECT MaKhachHang, TenKhachHang, SoDienThoai, DiaChi
            FROM KhachHang
            WHERE TenKhachHang LIKE ? OR SoDienThoai LIKE ?
            ORDER BY MaKhachHang
            """;

        try (Connection conn = DatabaseConnection.getConnection()) {

            if (conn == null) return danhSach;

            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, "%" + tuKhoa + "%");
                ps.setString(2, "%" + tuKhoa + "%");

                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        danhSach.add(new Object[]{
                            rs.getInt("MaKhachHang"),
                            rs.getString("TenKhachHang"),
                            rs.getString("SoDienThoai"),
                            rs.getString("DiaChi")
                        });
                    }
                }
            }

        } catch (Exception e) {
            System.out.println("Loi tim kiem khach hang!");
            e.printStackTrace();
        }

        return danhSach;
    }
}
