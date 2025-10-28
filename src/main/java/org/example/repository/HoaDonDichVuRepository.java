package org.example.repository;

import org.example.model.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HoaDonDichVuRepository {
    private final JdbcTemplate jdbcTemplate;

    public HoaDonDichVuRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Lấy tất cả hóa đơn dịch vụ kèm sinh viên, hóa đơn, dịch vụ
    public List<HoaDonDichVu> findAll() {
        String sql = """
            SELECT hddv.ma_hoa_don, hddv.ma_sinh_vien, hddv.ma_dich_vu, hddv.so_luong_dich_vu, hddv.phi_phat_sinh,
                   sv.ma_sinh_vien, sv.ten, sv.ngay_sinh, sv.gioi_tinh, sv.email, sv.so_dien_thoai,
                   hd.ma_hoa_don, hd.ngay_tao, hd.tong_tien,
                   dv.ma_dich_vu, dv.ten_dich_vu, dv.don_vi, dv.gia_co_dinh
            FROM hoa_don_dich_vu hddv
            JOIN sinh_vien sv ON hddv.ma_sinh_vien = sv.ma_sinh_vien
            JOIN hoa_don hd ON hddv.ma_hoa_don = hd.ma_hoa_don
            JOIN dich_vu dv ON hddv.ma_dich_vu = dv.ma_dich_vu
            """;

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            HoaDonDichVu hddv = new HoaDonDichVu();

            // Sinh viên
            SinhVien sv = new SinhVien();
            sv.setMaSv(rs.getString("ma_sinh_vien"));
            sv.setTen(rs.getString("ten"));
            sv.setNgaySinh(rs.getDate("ngay_sinh"));
            sv.setGioiTinh(rs.getString("gioi_tinh"));
            sv.setSoDienThoai(rs.getString("so_dien_thoai"));
            sv.setEmail(rs.getString("email"));

            // Hóa đơn
            HoaDon hd = new HoaDon();
            hd.setMaHoaDon(rs.getString("ma_hoa_don"));
            hd.setNgayTao(rs.getTimestamp("ngay_tao").toLocalDateTime());
            hd.setTongTien(rs.getBigDecimal("tong_tien"));

            // Dịch vụ
            DichVu dv = new DichVu();
            dv.setMaDichVu(rs.getString("ma_dich_vu"));
            dv.setTenDichVu(rs.getString("ten_dich_vu"));
            dv.setDonVi(rs.getString("don_vi"));
            dv.setGiaCoDinh(rs.getBigDecimal("gia_co_dinh"));

            // Mapping
            hddv.setSinhVien(sv);
            hddv.setHoaDon(hd);
            hddv.setDichVu(dv);
            hddv.setSoLuong(rs.getInt("so_luong_dich_vu"));
            hddv.setPhiPhatSinh(rs.getBigDecimal("phi_phat_sinh"));

            return hddv;
        });
    }

    // Tìm theo id
    public HoaDonDichVu findById(String maHoaDon, String maSinhVien, String maDichVu) {
        String sql = """
            SELECT hddv.ma_hoa_don, hddv.ma_sinh_vien, hddv.ma_dich_vu, hddv.so_luong_dich_vu, hddv.phi_phat_sinh,
                   sv.ma_sinh_vien, sv.ten, sv.ngay_sinh, sv.gioi_tinh, sv.email, sv.so_dien_thoai,
                   hd.ma_hoa_don, hd.ngay_tao, hd.tong_tien,
                   dv.ma_dich_vu, dv.ten_dich_vu, dv.don_vi, dv.gia_co_dinh
            FROM hoa_don_dich_vu hddv
            JOIN sinh_vien sv ON hddv.ma_sinh_vien = sv.ma_sinh_vien
            JOIN hoa_don hd ON hddv.ma_hoa_don = hd.ma_hoa_don
            JOIN dich_vu dv ON hddv.ma_dich_vu = dv.ma_dich_vu
            WHERE hddv.ma_hoa_don=? AND hddv.ma_sinh_vien=? AND hddv.ma_dich_vu=?
            """;

        return jdbcTemplate.queryForObject(sql, new Object[]{maHoaDon, maSinhVien, maDichVu}, (rs, rowNum) -> {
            HoaDonDichVu hddv = new HoaDonDichVu();

            SinhVien sv = new SinhVien();
            sv.setMaSv(rs.getString("ma_sinh_vien"));
            sv.setTen(rs.getString("ten"));
            sv.setNgaySinh(rs.getDate("ngay_sinh"));
            sv.setGioiTinh(rs.getString("gioi_tinh"));
            sv.setSoDienThoai(rs.getString("so_dien_thoai"));
            sv.setEmail(rs.getString("email"));

            HoaDon hd = new HoaDon();
            hd.setMaHoaDon(rs.getString("ma_hoa_don"));
            hd.setNgayTao(rs.getTimestamp("ngay_tao").toLocalDateTime());
            hd.setTongTien(rs.getBigDecimal("tong_tien"));

            DichVu dv = new DichVu();
            dv.setMaDichVu(rs.getString("ma_dich_vu"));
            dv.setTenDichVu(rs.getString("ten_dich_vu"));
            dv.setDonVi(rs.getString("don_vi"));
            dv.setGiaCoDinh(rs.getBigDecimal("gia_co_dinh"));

            hddv.setSinhVien(sv);
            hddv.setHoaDon(hd);
            hddv.setDichVu(dv);
            hddv.setSoLuong(rs.getInt("so_luong_dich_vu"));
            hddv.setPhiPhatSinh(rs.getBigDecimal("phi_phat_sinh"));

            return hddv;
        });
    }

    // Thêm mới
    public int save(HoaDonDichVu hddv) {
        String sql = "INSERT INTO hoa_don_dich_vu(ma_hoa_don, ma_sinh_vien, ma_dich_vu, so_luong_dich_vu, phi_phat_sinh) VALUES (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                hddv.getHoaDon().getMaHoaDon(),
                hddv.getSinhVien().getMaSv(),
                hddv.getDichVu().getMaDichVu(),
                hddv.getSoLuong(),
                hddv.getPhiPhatSinh());
    }

    // Cập nhật
    public int update(HoaDonDichVu hddv) {
        String sql = "UPDATE hoa_don_dich_vu SET so_luong_dich_vu=?, phi_phat_sinh=? WHERE ma_hoa_don=? AND ma_sinh_vien=? AND ma_dich_vu=?";
        return jdbcTemplate.update(sql,
                hddv.getSoLuong(),
                hddv.getPhiPhatSinh(),
                hddv.getHoaDon().getMaHoaDon(),
                hddv.getSinhVien().getMaSv(),
                hddv.getDichVu().getMaDichVu());
    }

    // Xóa
    public int delete(String maHoaDon, String maSinhVien, String maDichVu) {
        String sql = "DELETE FROM hoa_don_dich_vu WHERE ma_hoa_don=? AND ma_sinh_vien=? AND ma_dich_vu=?";
        return jdbcTemplate.update(sql, maHoaDon, maSinhVien, maDichVu);
    }
}
