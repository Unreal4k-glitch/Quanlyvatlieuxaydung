/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package quanlyvatlieuxaydung;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import quanlyvatlieuxaydung.dao.HoaDonDAO;
import quanlyvatlieuxaydung.HoaDon;
import quanlyvatlieuxaydung.dao.ChiTietHoaDonDAO;
import quanlyvatlieuxaydung.ChiTietHoaDon;
import java.util.ArrayList;
import java.util.List;
import quanlyvatlieuxaydung.dao.BanHangDAO;
import quanlyvatlieuxaydung.dao.KhachHangDAO;
import quanlyvatlieuxaydung.dao.NhanVienDAO;
import quanlyvatlieuxaydung.dao.SanPhamDAO;
public class QuanLyVatLieuXayDung {

    public static void main(String[] args) {

    Connection conn = DatabaseConnection.getConnection();

        if (conn != null) {
            System.out.println("Da ket noi duoc database!");
            KhachHangDAO khDAO = new KhachHangDAO();

            List<Object[]> dsKH = khDAO.getDanhSachKhachHang();

            System.out.println("DANH SACH KHACH HANG:");

            for (Object[] kh : dsKH) {
                System.out.println(
                    "Ma khach hang: " + kh[0]
                    + " - Ten khach hang: " + kh[1]
                );
            }
            NhanVienDAO nvDAO = new NhanVienDAO();

            List<Object[]> dsNV = nvDAO.getDanhSachNhanVien();

            System.out.println("DANH SACH NHAN VIEN:");

            for (Object[] nv : dsNV) {
                System.out.println(
                    "Ma nhan vien: " + nv[0]
                    + " - Ho ten: " + nv[1]
                );
            }
            SanPhamDAO spDAO = new SanPhamDAO();

            List<Object[]> dsSP = spDAO.getDanhSachSanPham();

            System.out.println("DANH SACH SAN PHAM:");

            for (Object[] sp : dsSP) {
                System.out.println(
                    "Ma SP: " + sp[0]
                    + " - Ten: " + sp[1]
                    + " - Don vi: " + sp[2]
                    + " - Gia ban: " + sp[3]
                );
            }
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
