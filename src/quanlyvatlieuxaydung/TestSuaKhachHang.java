/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quan.ly.vat.lieu.xay.dung;

import quan.ly.vat.lieu.xay.dung.dao.KhachHangDAO;
public class TestSuaKhachHang {
    public static void main(String[] args) {

        KhachHangDAO dao = new KhachHangDAO();

        boolean ketQua = dao.suaKhachHang(
            6,
            "Nguyen Van Test Da Sua",
            "0912345678",
            "Hai Phong"
        );

        if (ketQua) {
            System.out.println("Sua khach hang thanh cong!");
        } else {
            System.out.println("Sua khach hang that bai!");
        }
    }
}
