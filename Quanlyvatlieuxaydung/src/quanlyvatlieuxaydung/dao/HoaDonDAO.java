
package quanlyvatlieuxaydung.dao;

import quanlyvatlieuxaydung.DatabaseConnection;
import quanlyvatlieuxaydung.HoaDon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class HoaDonDAO {

    public List<HoaDon> getAll() {

        List<HoaDon> danhSach = new ArrayList<>();

        String sql = "SELECT * FROM HoaDon";

        try (Connection conn = DatabaseConnection.getConnection()) {

            if (conn == null) {
                System.out.println("Khong the ket noi database!");
                return danhSach;
            }

            try (PreparedStatement ps = conn.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    HoaDon hd = new HoaDon();

                    hd.setMaHoaDon(rs.getInt("MaHoaDon"));
                    hd.setMaKhachHang(rs.getInt("MaKhachHang"));
                    hd.setMaNhanVien(rs.getInt("MaNhanVien"));

                    Timestamp timestamp = rs.getTimestamp("NgayLap");

                    if (timestamp != null) {
                        hd.setNgayLap(timestamp.toLocalDateTime());
                    }

                    hd.setPhuongThucThanhToan(
                        rs.getString("PhuongThucThanhToan")
                    );

                    hd.setTongTien(rs.getBigDecimal("TongTien"));
                    hd.setGiamGia(rs.getBigDecimal("GiamGia"));
                    hd.setThanhToan(rs.getBigDecimal("ThanhToan"));

                    danhSach.add(hd);
                }
            }

        } catch (Exception e) {
            System.out.println("Loi khi lay danh sach hoa don:");
            e.printStackTrace();
        }

        return danhSach;
    }
}
