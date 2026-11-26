/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlyvatlieuxaydung;
import quanlyvatlieuxaydung.dao.HoaDonDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import quanlyvatlieuxaydung.dao.ChiTietHoaDonDAO;
public class DanhSachHoaDon extends JFrame{
    private JTable tblHoaDon;
    private DefaultTableModel model;

    private final HoaDonDAO hoaDonDAO = new HoaDonDAO();

    public DanhSachHoaDon() {
        setTitle("DANH SACH HOA DON");
        setSize(1100, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        khoiTaoGiaoDien();
        taiDanhSachHoaDon();
    }

    private void khoiTaoGiaoDien() {
        setLayout(new BorderLayout(10, 10));

        JLabel lblTieuDe = new JLabel(
                "DANH SACH HOA DON",
                SwingConstants.CENTER
        );

        lblTieuDe.setFont(
                new Font("Arial", Font.BOLD, 24)
        );

        add(lblTieuDe, BorderLayout.NORTH);

        model = new DefaultTableModel(
                new String[]{
                    "Ma hoa don",
                    "Ma khach hang",
                    "Ma nhan vien",
                    "Ngay lap",
                    "Phuong thuc thanh toan",
                    "Tong tien",
                    "Giam gia",
                    "Thanh toan"
                }, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tblHoaDon = new JTable(model);
        tblHoaDon.setRowHeight(28);

        JScrollPane scrollPane = new JScrollPane(tblHoaDon);

        add(scrollPane, BorderLayout.CENTER);

        JButton btnLamMoi = new JButton("Lam moi");
        JButton btnXemChiTiet = new JButton("Xem chi tiet");

        JPanel pnlButton = new JPanel();
        pnlButton.add(btnLamMoi);
        pnlButton.add(btnXemChiTiet);

        add(pnlButton, BorderLayout.SOUTH);

        btnLamMoi.addActionListener(e -> taiDanhSachHoaDon());

        btnXemChiTiet.addActionListener(e -> xemChiTietHoaDon());
    }

    private void taiDanhSachHoaDon() {
        model.setRowCount(0);

        List<HoaDon> danhSach = hoaDonDAO.getAll();

        for (HoaDon hd : danhSach) {
            model.addRow(new Object[]{
                hd.getMaHoaDon(),
                hd.getMaKhachHang(),
                hd.getMaNhanVien(),
                hd.getNgayLap(),
                hd.getPhuongThucThanhToan(),
                hd.getTongTien(),
                hd.getGiamGia(),
                hd.getThanhToan()
            });
        }
    }
    private void xemChiTietHoaDon() {

    int selectedRow = tblHoaDon.getSelectedRow();

    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(
                this,
                "Hay chon hoa don can xem!"
        );
        return;
    }

    int maHoaDon = Integer.parseInt(
            model.getValueAt(selectedRow, 0).toString()
    );

    ChiTietHoaDonDAO ctDAO = new ChiTietHoaDonDAO();

    List<ChiTietHoaDon> danhSachCT = ctDAO.getAll();

    StringBuilder noiDung = new StringBuilder();

    noiDung.append("CHI TIET HOA DON: ")
            .append(maHoaDon)
            .append("\n\n");

    noiDung.append(
            String.format(
                    "%-10s %-10s %-15s %-15s\n",
                    "Ma SP", "So luong", "Don gia", "Thanh tien"
            )
    );

    for (ChiTietHoaDon ct : danhSachCT) {

        if (ct.getMaHoaDon() == maHoaDon) {

            noiDung.append(
                    String.format(
                            "%-10d %-10d %-15s %-15s\n",
                            ct.getMaSanPham(),
                            ct.getSoLuong(),
                            ct.getDonGiaBan(),
                            ct.getThanhTien()
                    )
            );
        }
    }

    JTextArea txtChiTiet = new JTextArea(
        noiDung.toString()
);

txtChiTiet.setFont(
        new Font("Monospaced", Font.PLAIN, 14)
);

txtChiTiet.setEditable(false);

JScrollPane scrollPane = new JScrollPane(txtChiTiet);
scrollPane.setPreferredSize(new Dimension(500, 200));

JOptionPane.showMessageDialog(
        this,
        scrollPane,
        "Chi tiet hoa don",
        JOptionPane.INFORMATION_MESSAGE
);
}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new DanhSachHoaDon().setVisible(true);
        });
    }
}
