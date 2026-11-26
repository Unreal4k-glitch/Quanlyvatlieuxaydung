/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quan.ly.vat.lieu.xay.dung.dao;
import quan.ly.vat.lieu.xay.dung.dao.KhachHangDAO;
public class TestKhachHangDAO {
    public static void main(String[] args) {

        KhachHangDAO dao = new KhachHangDAO();

        boolean ketQua = dao.themKhachHang(
            "Nguyen Van Test",
            "0987654321",
            "Ha Noi"
        );

        if (ketQua) {
            System.out.println("Them khach hang thanh cong!");
        } else {
            System.out.println("Them khach hang that bai!");
        }
    }
}
