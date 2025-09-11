package org.example.repository;

import org.example.model.DichVu;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class DichVuRepository {
    private final JdbcTemplate jdbcTemplate;
    public DichVuRepository(JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate; }

    public List<DichVu> findAll() {
        return jdbcTemplate.query("SELECT * FROM dich_vu",
                (rs, rowNum) -> {
                    DichVu dv = new DichVu();
                    dv.setMaDv(rs.getInt("ma_dv"));
                    dv.setTenDichVu(rs.getString("ten_dich_vu"));
                    dv.setGiaCoDinh(rs.getBigDecimal("gia_co_dinh"));
                    return dv;
                });
    }

    public DichVu findById(Integer maDv) {
        return jdbcTemplate.queryForObject("SELECT * FROM dich_vu WHERE ma_dv=?",
                new Object[]{maDv},
                (rs, rowNum) -> {
                    DichVu dv = new DichVu();
                    dv.setMaDv(rs.getInt("ma_dv"));
                    dv.setTenDichVu(rs.getString("ten_dich_vu"));
                    dv.setGiaCoDinh(rs.getBigDecimal("gia_co_dinh"));
                    return dv;
                });
    }

    public int save(DichVu dv) {
        return jdbcTemplate.update(
                "INSERT INTO dich_vu(ten_dich_vu,gia_co_dinh) VALUES(?,?)",
                dv.getTenDichVu(), dv.getGiaCoDinh()
        );
    }

    public int update(DichVu dv) {
        return jdbcTemplate.update(
                "UPDATE dich_vu SET ten_dich_vu=?, gia_co_dinh=? WHERE ma_dv=?",
                dv.getTenDichVu(), dv.getGiaCoDinh(), dv.getMaDv()
        );
    }

    public int delete(Integer id) {
        return jdbcTemplate.update("DELETE FROM dich_vu WHERE ma_dv=?", id);
    }
}
