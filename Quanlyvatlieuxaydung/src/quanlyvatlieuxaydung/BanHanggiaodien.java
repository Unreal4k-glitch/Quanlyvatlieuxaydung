
package quanlyvatlieuxaydung;

import quanlyvatlieuxaydung.dao.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BanHanggiaodien extends JFrame {

    private JComboBox<ComboItem> cboKhachHang;
    private JComboBox<ComboItem> cboNhanVien;
    private JComboBox<SanPhamItem> cboSanPham;

    private JTextField txtSoLuong;
    private JTextField txtGiamGia;

    private JLabel lblTongTien;
    private JLabel lblThanhToan;

    private JComboBox<String> cboPhuongThuc;

    private JTable tblChiTiet;
    private DefaultTableModel model;

    private final KhachHangDAO khDAO = new KhachHangDAO();
    private final NhanVienDAO nvDAO = new NhanVienDAO();
    private final SanPhamDAO spDAO = new SanPhamDAO();
    private final BanHangDAO banHangDAO = new BanHangDAO();

    public BanHanggiaodien() {
        setTitle("QUAN LY BAN HANG - LAP HOA DON");
        setSize(1000, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        khoiTaoGiaoDien();
        taiDuLieu();
    }

    
    private void khoiTaoGiaoDien() {
        setLayout(new BorderLayout(10, 10));

        // 1. Tieu de
        JLabel lblTieuDe = new JLabel(
                "QUAN LY BAN HANG - LAP HOA DON",
                SwingConstants.CENTER
        );
        lblTieuDe.setFont(new Font("Arial", Font.BOLD, 24));
        lblTieuDe.setBorder(BorderFactory.createEmptyBorder(15, 5, 15, 5));

        add(lblTieuDe, BorderLayout.NORTH);

        // 2. Thong tin hoa don
        JPanel pnlThongTin = new JPanel(new GridLayout(2, 4, 10, 10));
        pnlThongTin.setBorder(
                BorderFactory.createTitledBorder("Thong tin hoa don")
        );

        cboKhachHang = new JComboBox<>();
        cboNhanVien = new JComboBox<>();

        cboPhuongThuc = new JComboBox<>(
                new String[]{"Tien mat", "Chuyen khoan"}
        );

        pnlThongTin.add(new JLabel("Khach hang:"));
        pnlThongTin.add(cboKhachHang);
        pnlThongTin.add(new JLabel("Nhan vien:"));
        pnlThongTin.add(cboNhanVien);

        pnlThongTin.add(new JLabel("Phuong thuc thanh toan:"));
        pnlThongTin.add(cboPhuongThuc);
        pnlThongTin.add(new JLabel("Ngay lap:"));
        pnlThongTin.add(new JLabel(LocalDateTime.now().toString()));

        // 3. Khu vuc them san pham
        JPanel pnlSanPham = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        pnlSanPham.setBorder(
                BorderFactory.createTitledBorder("Them san pham")
        );

        cboSanPham = new JComboBox<>();
        cboSanPham.setPreferredSize(new Dimension(350, 28));

        txtSoLuong = new JTextField("1", 8);

        JButton btnThem = new JButton("Them san pham");

        pnlSanPham.add(new JLabel("San pham:"));
        pnlSanPham.add(cboSanPham);
        pnlSanPham.add(new JLabel("So luong:"));
        pnlSanPham.add(txtSoLuong);
        pnlSanPham.add(btnThem);

        // 4. Bang chi tiet hoa don
        model = new DefaultTableModel(
                new String[]{
                    "Ma SP", "Ten san pham", "Don vi",
                    "Don gia", "So luong", "Thanh tien"
                }, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tblChiTiet = new JTable(model);
        tblChiTiet.setRowHeight(25);

        JScrollPane scrollPane = new JScrollPane(tblChiTiet);
        scrollPane.setBorder(
                BorderFactory.createTitledBorder("Chi tiet hoa don")
        );

        // 5. Gom thong tin va khu vuc them san pham
        JPanel pnlPhiaTren = new JPanel(new BorderLayout(5, 5));
        pnlPhiaTren.add(pnlThongTin, BorderLayout.NORTH);
        pnlPhiaTren.add(pnlSanPham, BorderLayout.SOUTH);

        // 6. Khu vuc trung tam
        JPanel pnlGiua = new JPanel(new BorderLayout(5, 5));
        pnlGiua.add(pnlPhiaTren, BorderLayout.NORTH);
        pnlGiua.add(scrollPane, BorderLayout.CENTER);

        add(pnlGiua, BorderLayout.CENTER);

        // 7. Thong tin thanh toan
        JPanel pnlThanhToan = new JPanel(new GridLayout(2, 4, 10, 10));
        pnlThanhToan.setBorder(
                BorderFactory.createTitledBorder("Thong tin thanh toan")
        );

        txtGiamGia = new JTextField("0");
        lblTongTien = new JLabel("0 VND");
        lblThanhToan = new JLabel("0 VND");

        JButton btnLuu = new JButton("Luu hoa don");
        JButton btnXoa = new JButton("Xoa san pham");

        pnlThanhToan.add(new JLabel("Tong tien:"));
        pnlThanhToan.add(lblTongTien);
        pnlThanhToan.add(new JLabel("Giam gia:"));
        pnlThanhToan.add(txtGiamGia);

        pnlThanhToan.add(new JLabel("Thanh toan:"));
        pnlThanhToan.add(lblThanhToan);
        pnlThanhToan.add(btnLuu);
        pnlThanhToan.add(btnXoa);

        add(pnlThanhToan, BorderLayout.SOUTH);

        // 8. Su kien cac nut
        btnThem.addActionListener(e -> themSanPham());
        btnXoa.addActionListener(e -> xoaSanPham());
        btnLuu.addActionListener(e -> luuHoaDon());

        txtGiamGia.addActionListener(e -> tinhTien());
    }


    private void taiDuLieu() {
        for (Object[] kh : khDAO.getDanhSachKhachHang()) {
            cboKhachHang.addItem(
                    new ComboItem(
                            (int) kh[0],
                            kh[1].toString()
                    )
            );
        }

        for (Object[] nv : nvDAO.getDanhSachNhanVien()) {
            cboNhanVien.addItem(
                    new ComboItem(
                            (int) nv[0],
                            nv[1].toString()
                    )
            );
        }

        for (Object[] sp : spDAO.getDanhSachSanPham()) {
            cboSanPham.addItem(
                    new SanPhamItem(
                            (int) sp[0],
                            sp[1].toString(),
                            sp[2].toString(),
                            new BigDecimal(sp[3].toString())
                    )
            );
        }
    }

    private void themSanPham() {
        try {
            SanPhamItem sp =
                    (SanPhamItem) cboSanPham.getSelectedItem();

            if (sp == null) {
                JOptionPane.showMessageDialog(this, "Chua chon san pham!");
                return;
            }

            int soLuong = Integer.parseInt(txtSoLuong.getText().trim());

            if (soLuong <= 0) {
                JOptionPane.showMessageDialog(
                        this, "So luong phai lon hon 0!"
                );
                return;
            }

            BigDecimal thanhTien = sp.giaBan.multiply(
                    BigDecimal.valueOf(soLuong)
            );

            model.addRow(new Object[]{
                sp.maSP,
                sp.tenSP,
                sp.donViTinh,
                sp.giaBan,
                soLuong,
                thanhTien
            });

            tinhTien();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(
                    this, "So luong khong hop le!"
            );
        }
    }

    private void xoaSanPham() {
        int selectedRow = tblChiTiet.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(
                    this, "Hay chon san pham can xoa!"
            );
            return;
        }

        model.removeRow(selectedRow);
        tinhTien();
    }

    private BigDecimal tinhTongTien() {
        BigDecimal tongTien = BigDecimal.ZERO;

        for (int i = 0; i < model.getRowCount(); i++) {
            BigDecimal thanhTien =
                    new BigDecimal(model.getValueAt(i, 5).toString());

            tongTien = tongTien.add(thanhTien);
        }

        return tongTien;
    }

    private BigDecimal layGiamGia() {
        return new BigDecimal(txtGiamGia.getText().trim());
    }

    private void tinhTien() {
        try {
            BigDecimal tongTien = tinhTongTien();
            BigDecimal giamGia = layGiamGia();

            if (giamGia.compareTo(BigDecimal.ZERO) < 0
                    || giamGia.compareTo(tongTien) > 0) {
                throw new NumberFormatException();
            }

            BigDecimal thanhToan = tongTien.subtract(giamGia);

            lblTongTien.setText(tongTien.toPlainString() + " VND");
            lblThanhToan.setText(thanhToan.toPlainString() + " VND");

        } catch (NumberFormatException e) {
            lblTongTien.setText("Khong hop le");
            lblThanhToan.setText("Khong hop le");
        }
    }

    private void luuHoaDon() {
        try {
            if (model.getRowCount() == 0) {
                JOptionPane.showMessageDialog(
                        this, "Hoa don chua co san pham!"
                );
                return;
            }

            ComboItem kh =
                    (ComboItem) cboKhachHang.getSelectedItem();

            ComboItem nv =
                    (ComboItem) cboNhanVien.getSelectedItem();

            if (kh == null || nv == null) {
                JOptionPane.showMessageDialog(
                        this, "Hay chon khach hang va nhan vien!"
                );
                return;
            }

            BigDecimal tongTien = tinhTongTien();
            BigDecimal giamGia = layGiamGia();

            if (giamGia.compareTo(BigDecimal.ZERO) < 0
                    || giamGia.compareTo(tongTien) > 0) {
                JOptionPane.showMessageDialog(
                        this, "Giam gia khong hop le!"
                );
                return;
            }

            BigDecimal thanhToan = tongTien.subtract(giamGia);

            HoaDon hd = new HoaDon();
            hd.setMaKhachHang(kh.ma);
            hd.setMaNhanVien(nv.ma);
            hd.setNgayLap(LocalDateTime.now());
            hd.setPhuongThucThanhToan(
                    cboPhuongThuc.getSelectedItem().toString()
            );
            hd.setTongTien(tongTien);
            hd.setGiamGia(giamGia);
            hd.setThanhToan(thanhToan);

            List<ChiTietHoaDon> danhSachCT = new ArrayList<>();

            for (int i = 0; i < model.getRowCount(); i++) {
                ChiTietHoaDon ct = new ChiTietHoaDon();

                ct.setMaSanPham(
                        Integer.parseInt(
                                model.getValueAt(i, 0).toString()
                        )
                );

                ct.setSoLuong(
                        Integer.parseInt(
                                model.getValueAt(i, 4).toString()
                        )
                );

                ct.setDonGiaBan(
                        new BigDecimal(
                                model.getValueAt(i, 3).toString()
                        )
                );

                ct.setThanhTien(
                        new BigDecimal(
                                model.getValueAt(i, 5).toString()
                        )
                );

                danhSachCT.add(ct);
            }

            boolean thanhCong =
                    banHangDAO.themHoaDonVaChiTiet(hd, danhSachCT);

            if (thanhCong) {
                JOptionPane.showMessageDialog(
                        this,
                        "Lap hoa don thanh cong!\nMa hoa don: "
                                + hd.getMaHoaDon()
                );

                model.setRowCount(0);
                txtGiamGia.setText("0");
                tinhTien();
            } else {
                JOptionPane.showMessageDialog(
                        this, "Lap hoa don that bai!"
                );
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(
                    this, "Gia tri giam gia khong hop le!"
            );
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    this,
                    "Loi lap hoa don: " + e.getMessage()
            );
            e.printStackTrace();
        }
    }

    private static class ComboItem {
        int ma;
        String ten;

        ComboItem(int ma, String ten) {
            this.ma = ma;
            this.ten = ten;
        }

        @Override
        public String toString() {
            return ma + " - " + ten;
        }
    }

    private static class SanPhamItem {
        int maSP;
        String tenSP;
        String donViTinh;
        BigDecimal giaBan;

        SanPhamItem(
                int maSP,
                String tenSP,
                String donViTinh,
                BigDecimal giaBan
        ) {
            this.maSP = maSP;
            this.tenSP = tenSP;
            this.donViTinh = donViTinh;
            this.giaBan = giaBan;
        }

        @Override
        public String toString() {
            return maSP + " - " + tenSP
                    + " (" + giaBan.toPlainString() + " VND)";
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new BanHanggiaodien().setVisible(true);
        });
    }
}
