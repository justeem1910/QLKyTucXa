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
                    hd.setId(rs.getInt("id"));
                    hd.setIdSv(rs.getInt("id_sv"));
                    hd.setNgayTao(rs.getDate("ngay_tao"));
                    return hd;
                });
    }

    public HoaDon findById(Integer id) {
        return jdbcTemplate.queryForObject("SELECT * FROM hoa_don WHERE id=?",
                new Object[]{id},
                (rs, rowNum) -> {
                    HoaDon hd = new HoaDon();
                    hd.setId(rs.getInt("id"));
                    hd.setIdSv(rs.getInt("id_sv"));
                    hd.setNgayTao(rs.getDate("ngay_tao"));
                    return hd;
                });
    }

    public int save(HoaDon hd) {
        return jdbcTemplate.update(
                "INSERT INTO hoa_don(id_sv,ngay_tao) VALUES(?,?)",
                hd.getIdSv(), hd.getNgayTao()
        );
    }

    public int update(HoaDon hd) {
        return jdbcTemplate.update(
                "UPDATE hoa_don SET id_sv=?, ngay_tao=? WHERE id=?",
                hd.getIdSv(), hd.getNgayTao(), hd.getId()
        );
    }

    public int delete(Integer id) {
        return jdbcTemplate.update("DELETE FROM hoa_don WHERE id=?", id);
    }
}
