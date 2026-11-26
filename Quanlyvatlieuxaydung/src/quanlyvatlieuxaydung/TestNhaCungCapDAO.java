/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlyvatlieuxaydung;

import quanlyvatlieuxaydung.dao.NhaCungCapDAO;
public class TestNhaCungCapDAO {
    public static void main(String[] args) {

        NhaCungCapDAO dao = new NhaCungCapDAO();

        System.out.println("===== KIEM THU DANH SACH NHA CUNG CAP =====");

        for (Object[] ncc : dao.getDanhSachNhaCungCap()) {
            System.out.println(
                "Ma NCC: " + ncc[0]
                + " | Ten NCC: " + ncc[1]
                + " | SDT: " + ncc[2]
                + " | Dia chi: " + ncc[3]
                + " | Email: " + ncc[4]
            );
        }
    }
}
