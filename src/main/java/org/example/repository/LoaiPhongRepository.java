package org.example.repository;

import org.example.model.LoaiPhong;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class LoaiPhongRepository {
    private final JdbcTemplate jdbcTemplate;
    public LoaiPhongRepository(JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate; }

    public List<LoaiPhong> findAll() {
        return jdbcTemplate.query("SELECT * FROM loai_phong",
                (rs, rowNum) -> {
                    LoaiPhong lp = new LoaiPhong();
                    lp.setMaLp(rs.getInt("ma_loai_phong"));
                    lp.setTenLoai(rs.getString("ten_loai"));
                    return lp;
                });
    }

    public LoaiPhong findById(Integer maLp) {
        return jdbcTemplate.queryForObject("SELECT * FROM loai_phong WHERE ma_loai_phong=?",
                new Object[]{maLp},
                (rs, rowNum) -> {
                    LoaiPhong lp = new LoaiPhong();
                    lp.setMaLp(rs.getInt("ma_loai_phong"));
                    lp.setTenLoai(rs.getString("ten_loai"));
                    return lp;
                });
    }

    public int save(LoaiPhong lp) {
        return jdbcTemplate.update(
                "INSERT INTO loai_phong(ten_loai) VALUES(?)",
                lp.getTenLoai()
        );
    }

    public int update(LoaiPhong lp) {
        return jdbcTemplate.update(
                "UPDATE loai_phong SET ten_loai=? WHERE ma_loai_phong=?",
                lp.getTenLoai(), lp.getMaLp()
        );
    }

    public int delete(Integer id) {
        return jdbcTemplate.update("DELETE FROM loai_phong WHERE ma_loai_phong=?", id);
    }
}
