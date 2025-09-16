package org.example.repository;

import org.example.model.HoaDonDichVu;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HoaDonDichVuRepository {
    private final JdbcTemplate jdbcTemplate;

    public HoaDonDichVuRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<HoaDonDichVu> findAll() {
        return jdbcTemplate.query("SELECT * FROM hoa_don_dich_vu",
                (rs, rowNum) -> new HoaDonDichVu(
                        rs.getInt("ma_hoa_don"),
                        rs.getString("ma_sinh_vien"),
                        rs.getInt("ma_dich_vu"),
                        rs.getInt("so_luong_dich_vu"),
                        rs.getBigDecimal("phi_phat_sinh")
                ));
    }

    public HoaDonDichVu findById(Integer maHoaDon, String maSinhVien, Integer maDichVu) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM hoa_don_dich_vu WHERE ma_hoa_don=? AND ma_sinh_vien=? AND ma_dich_vu=?",
                new Object[]{maHoaDon, maSinhVien, maDichVu},
                (rs, rowNum) -> new HoaDonDichVu(
                        rs.getInt("ma_hoa_don"),
                        rs.getString("ma_sinh_vien"),
                        rs.getInt("ma_dich_vu"),
                        rs.getInt("so_luong_dich_vu"),
                        rs.getBigDecimal("phi_phat_sinh")
                ));
    }

    public int save(HoaDonDichVu hddv) {
        return jdbcTemplate.update(
                "INSERT INTO hoa_don_dich_vu(ma_hoa_don, ma_sinh_vien, ma_dich_vu, so_luong_dich_vu, phi_phat_sinh) VALUES(?, ?, ?, ?, ?)",
                hddv.getMaHoaDon(), hddv.getMaSinhVien(), hddv.getMaDichVu(), hddv.getSoLuong(), hddv.getPhiPhatSinh()
        );
    }

    public int update(HoaDonDichVu hddv) {
        return jdbcTemplate.update(
                "UPDATE hoa_don_dich_vu SET so_luong_dich_vu=?, phi_phat_sinh=? WHERE ma_hoa_don=? AND ma_sinh_vien=? AND ma_dich_vu=?",
                hddv.getSoLuong() , hddv.getPhiPhatSinh(), hddv.getMaHoaDon(), hddv.getMaSinhVien(), hddv.getMaDichVu()
        );
    }

    public int delete(Integer maHoaDon, String maSinhVien, Integer maDichVu) {
        return jdbcTemplate.update(
                "DELETE FROM hoa_don_dich_vu WHERE ma_hoa_don=? AND ma_sinh_vien=? AND ma_dich_vu=?",
                maHoaDon, maSinhVien, maDichVu
        );
    }
}
