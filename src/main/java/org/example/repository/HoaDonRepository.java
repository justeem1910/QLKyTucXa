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
                    hd.setMaHd(rs.getInt("ma_hd"));
                    hd.setIdSv(rs.getString("ma_sv"));
                    hd.setNgayTao(rs.getDate("ngay_tao"));
                    return hd;
                });
    }

    public HoaDon findById(Integer maHd) {
        return jdbcTemplate.queryForObject("SELECT * FROM hoa_don WHERE ma_hd=?",
                new Object[]{maHd},
                (rs, rowNum) -> {
                    HoaDon hd = new HoaDon();
                    hd.setMaHd(rs.getInt("ma_hd"));
                    hd.setIdSv(rs.getString("ma_sv"));
                    hd.setNgayTao(rs.getDate("ngay_tao"));
                    return hd;
                });
    }

    public int save(HoaDon hd) {
        return jdbcTemplate.update(
                "INSERT INTO hoa_don(ma_sv,ngay_tao) VALUES(?,?)",
                hd.getIdSv(), hd.getNgayTao()
        );
    }

    public int update(HoaDon hd) {
        return jdbcTemplate.update(
                "UPDATE hoa_don SET ma_sv=?, ngay_tao=? WHERE ma_hd=?",
                hd.getIdSv(), hd.getNgayTao(), hd.getMaHd()
        );
    }

    public int delete(Integer id) {
        return jdbcTemplate.update("DELETE FROM hoa_don WHERE ma_hd=?", id);
    }
}
