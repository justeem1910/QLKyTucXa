package org.example.repository;

import org.example.model.HoaDon;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HoaDonRepository {
    private final JdbcTemplate jdbcTemplate;
    public HoaDonRepository(JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate; }

    public List<HoaDon> findAll() {
        return jdbcTemplate.query("SELECT * FROM hoa_don",
                (rs, rowNum) -> {
                    HoaDon hd = new HoaDon();
                    hd.setMaHoaDon(rs.getInt("ma_hoa_don"));
                    hd.setNgayTao(rs.getDate("ngay_tao"));
                    hd.setTongTien(rs.getBigDecimal("tong_tien"));
                    return hd;
                });
    }

    public HoaDon findById(Integer maHd) {
        return jdbcTemplate.queryForObject("SELECT * FROM hoa_don WHERE ma_hoa_don=?",
                new Object[]{maHd},
                (rs, rowNum) -> {
                    HoaDon hd = new HoaDon();
                    hd.setMaHoaDon(rs.getInt("ma_hoa_don"));
                    hd.setNgayTao(rs.getDate("ngay_tao"));
                    hd.setTongTien(rs.getBigDecimal("tong_tien"));
                    return hd;
                });
    }

    public int save(HoaDon hd) {
        return jdbcTemplate.update(
                "INSERT INTO hoa_don(ma_hoa_don,ngay_tao) VALUES(?,?)",
                hd.getMaHoaDon(), hd.getNgayTao()
        );
    }

    public int update(HoaDon hd) {
        return jdbcTemplate.update(
                "UPDATE hoa_don SET ngay_tao=? WHERE ma_hoa_don=?",
                hd.getNgayTao(), hd.getMaHoaDon()
        );
    }

    public int delete(Integer id) {
        return jdbcTemplate.update("DELETE FROM hoa_don WHERE ma_hoa_don=?", id);
    }
}
