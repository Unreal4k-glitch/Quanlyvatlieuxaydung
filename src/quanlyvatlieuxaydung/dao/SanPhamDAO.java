
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quan.ly.vat.lieu.xay.dung.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import quan.ly.vat.lieu.xay.dung.DatabaseConnection;
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
}
