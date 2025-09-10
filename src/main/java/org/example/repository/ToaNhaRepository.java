package org.example.repository;

import org.example.model.ToaNha;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class ToaNhaRepository {
    private final JdbcTemplate jdbcTemplate;
    public ToaNhaRepository(JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate; }

    public List<ToaNha> findAll() {
        return jdbcTemplate.query("SELECT * FROM toa_nha",
                (rs, rowNum) -> {
                    ToaNha tn = new ToaNha();
                    tn.setId(rs.getInt("id"));
                    tn.setTenToa(rs.getString("ten_toa"));
                    return tn;
                });
    }

    public ToaNha findById(Integer id) {
        return jdbcTemplate.queryForObject("SELECT * FROM toa_nha WHERE id=?",
                new Object[]{id},
                (rs, rowNum) -> {
                    ToaNha tn = new ToaNha();
                    tn.setId(rs.getInt("id"));
                    tn.setTenToa(rs.getString("ten_toa"));
                    return tn;
                });
    }

    public int save(ToaNha tn) {
        return jdbcTemplate.update(
                "INSERT INTO toa_nha(ten_toa) VALUES(?)",
                tn.getTenToa()
        );
    }

    public int update(ToaNha tn) {
        return jdbcTemplate.update(
                "UPDATE toa_nha SET ten_toa=? WHERE id=?",
                tn.getTenToa(), tn.getId()
        );
    }

    public int delete(Integer id) {
        return jdbcTemplate.update("DELETE FROM toa_nha WHERE id=?", id);
    }
}
