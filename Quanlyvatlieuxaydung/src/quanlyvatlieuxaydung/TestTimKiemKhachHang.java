/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlyvatlieuxaydung;

import java.util.List;
import quanlyvatlieuxaydung.dao.KhachHangDAO;

public class TestTimKiemKhachHang {
    public static void main(String[] args) {

        KhachHangDAO dao = new KhachHangDAO();

        List<Object[]> danhSach =
                dao.timKiemKhachHang("Nguyen Van Test");

        if (danhSach.isEmpty()) {
            System.out.println("Khong tim thay khach hang!");
        } else {
            System.out.println("Ket qua tim kiem:");

            for (Object[] kh : danhSach) {
                System.out.println(
                    "Ma KH: " + kh[0]
                    + " | Ten: " + kh[1]
                    + " | SDT: " + kh[2]
                    + " | Dia chi: " + kh[3]
                );
            }
        }
    }
}
