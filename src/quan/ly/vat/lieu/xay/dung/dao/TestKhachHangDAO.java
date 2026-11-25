
package quan.ly.vat.lieu.xay.dung.dao;

import quan.ly.vat.lieu.xay.dung.BanHanggiaodien;
import javax.swing.SwingUtilities;

public class TestKhachHangDAO {
    public static void main(String[] args) {

        // Mo giao dien Swing
        SwingUtilities.invokeLater(() -> {
            new BanHanggiaodien().setVisible(true);
        });

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