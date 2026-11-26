/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlyvatlieuxaydung;

import quanlyvatlieuxaydung.dao.KhachHangDAO;
public class TestXoaKhachHang {
    public static void main(String[] args) {

        KhachHangDAO dao = new KhachHangDAO();

        boolean ketQua = dao.xoaKhachHang(6);

        if (ketQua) {
            System.out.println("Xoa khach hang thanh cong!");
        } else {
            System.out.println("Xoa khach hang that bai!");
        }
    }
}