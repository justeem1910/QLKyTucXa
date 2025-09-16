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
                    dv.setTenDichVu(rs.getString("ten_dich_vu"));
                    dv.setDonVi(rs.getString("don_vi"));
                    dv.setGiaCoDinh(rs.getBigDecimal("gia_co_dinh"));
                    return dv;
                });
    }

    public DichVu findById(String tendv) {
        return jdbcTemplate.queryForObject("SELECT * FROM dich_vu WHERE ten_dich_vu=?",
                new Object[]{tendv},
                (rs, rowNum) -> {
                    DichVu dv = new DichVu();
                    dv.setTenDichVu(rs.getString("ten_dich_vu"));
                    dv.setDonVi(rs.getString("don_vi"));
                    dv.setGiaCoDinh(rs.getBigDecimal("gia_co_dinh"));
                    return dv;
                });
    }

    public int save(DichVu dv) {
        return jdbcTemplate.update(
                "INSERT INTO dich_vu(ten_dich_vu,don_vi,gia_co_dinh) VALUES(?,?,?)",
                dv.getTenDichVu(), dv.getDonVi(), dv.getGiaCoDinh()
        );
    }

    public int update(DichVu dv) {
        return jdbcTemplate.update(
                "UPDATE dich_vu SET don_vi=?, gia_co_dinh=? WHERE ten_dich_vu=?",
                dv.getDonVi(), dv.getGiaCoDinh(), dv.getTenDichVu()
        );
    }

    public int delete(String tenDv) {
        return jdbcTemplate.update("DELETE FROM dich_vu WHERE ten_dich_vu=?", tenDv);
    }
}
