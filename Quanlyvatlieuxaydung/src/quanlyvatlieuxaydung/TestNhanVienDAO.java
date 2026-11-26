/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlyvatlieuxaydung;

import quanlyvatlieuxaydung.dao.NhanVienDAO;
public class TestNhanVienDAO {
    public static void main(String[] args) {
        NhanVienDAO dao = new NhanVienDAO();

        System.out.println("===== KIEM TRA NHAN VIEN =====");

        for (Object[] nv : dao.getDanhSachNhanVien()) {
            System.out.println(
                "Ma NV: " + nv[0]
                + " | Ho ten: " + nv[1]
                + " | SDT: " + nv[2]
            );
        }
    }
}
