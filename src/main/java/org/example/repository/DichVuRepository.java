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
                    dv.setId(rs.getInt("id"));
                    dv.setTenDichVu(rs.getString("ten_dich_vu"));
                    dv.setGiaCoDinh(rs.getBigDecimal("gia_co_dinh"));
                    return dv;
                });
    }

    public DichVu findById(Integer id) {
        return jdbcTemplate.queryForObject("SELECT * FROM dich_vu WHERE id=?",
                new Object[]{id},
                (rs, rowNum) -> {
                    DichVu dv = new DichVu();
                    dv.setId(rs.getInt("id"));
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
                "UPDATE dich_vu SET ten_dich_vu=?, gia_co_dinh=? WHERE id=?",
                dv.getTenDichVu(), dv.getGiaCoDinh(), dv.getId()
        );
    }

    public int delete(Integer id) {
        return jdbcTemplate.update("DELETE FROM dich_vu WHERE id=?", id);
    }
}
