package org.example.repository;

import org.example.model.HoaDon;
import org.example.model.SinhVien;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HoaDonRepository {
    private final JdbcTemplate jdbcTemplate;
    public HoaDonRepository(JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate; }

    public List<HoaDon> findAll() {
        String sql = """
                SELECT hd.ma_hoa_don, hd.ngay_tao, hd.tong_tien,
                       sv.ma_sinh_vien, sv.ten
                FROM hoa_don hd
                LEFT JOIN hoa_don_thue_phong hdtp ON hd.ma_hoa_don = hdtp.ma_hoa_don
                LEFT JOIN sinh_vien sv ON hdtp.ma_sinh_vien = sv.ma_sinh_vien
                ORDER BY hd.ngay_tao DESC
                """;

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            HoaDon hd = new HoaDon();
            hd.setMaHoaDon(rs.getString("ma_hoa_don"));
            hd.setNgayTao(rs.getDate("ngay_tao"));
            hd.setTongTien(rs.getBigDecimal("tong_tien"));

            String maSv = rs.getString("ma_sinh_vien");
            if (maSv != null) {
                SinhVien sv = new SinhVien();
                sv.setMaSv(maSv);
                sv.setTen(rs.getString("ten"));
                hd.setSinhVien(sv);
            }
            return hd;
        });
    }

    public HoaDon findById(String maHd) {
        String sql = """
                SELECT hd.ma_hoa_don, hd.ngay_tao, hd.tong_tien,
                       sv.ma_sinh_vien, sv.ten
                FROM hoa_don hd
                LEFT JOIN hoa_don_thue_phong hdtp ON hd.ma_hoa_don = hdtp.ma_hoa_don
                LEFT JOIN sinh_vien sv ON hdtp.ma_sinh_vien = sv.ma_sinh_vien
                WHERE hd.ma_hoa_don = ?
                """;

        return jdbcTemplate.queryForObject(sql, new Object[]{maHd}, (rs, rowNum) -> {
            HoaDon hd = new HoaDon();
            hd.setMaHoaDon(rs.getString("ma_hoa_don"));
            hd.setNgayTao(rs.getDate("ngay_tao"));
            hd.setTongTien(rs.getBigDecimal("tong_tien"));

            String maSv = rs.getString("ma_sinh_vien");
            if (maSv != null) {
                SinhVien sv = new SinhVien();
                sv.setMaSv(maSv);
                sv.setTen(rs.getString("ten"));
                hd.setSinhVien(sv);
            }
            return hd;
        });
    }

    public int save(HoaDon hd) {
        return jdbcTemplate.update(
                "INSERT INTO hoa_don(ma_hoa_don, ngay_tao, tong_tien) VALUES(?,?,?)",
                hd.getMaHoaDon(), hd.getNgayTao(), hd.getTongTien()
        );
    }

    public int update(HoaDon hd) {
        return jdbcTemplate.update(
                "UPDATE hoa_don SET ngay_tao=?, tong_tien=? WHERE ma_hoa_don=?",
                hd.getNgayTao(), hd.getTongTien(), hd.getMaHoaDon()
        );
    }

    public int delete(String id) {
        return jdbcTemplate.update("DELETE FROM hoa_don WHERE ma_hoa_don=?", id);
    }
}
