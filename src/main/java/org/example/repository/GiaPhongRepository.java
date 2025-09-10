package org.example.repository;

import org.example.model.GiaPhong;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class GiaPhongRepository {
    private final JdbcTemplate jdbcTemplate;
    public GiaPhongRepository(JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate; }

    public List<GiaPhong> findAll() {
        return jdbcTemplate.query("SELECT * FROM gia_phong",
                (rs, rowNum) -> {
                    GiaPhong gp = new GiaPhong();
                    gp.setId(rs.getInt("id"));
                    gp.setIdLoai(rs.getInt("id_loai"));
                    gp.setNgayBatDau(rs.getInt("ngay_bat_dau"));
                    gp.setNgayKetThuc(rs.getInt("ngay_ket_thuc"));
                    gp.setGiaTien(rs.getBigDecimal("gia_tien"));
                    return gp;
                });
    }

    public GiaPhong findById(Integer id) {
        return jdbcTemplate.queryForObject("SELECT * FROM gia_phong WHERE id=?",
                new Object[]{id},
                (rs, rowNum) -> {
                    GiaPhong gp = new GiaPhong();
                    gp.setId(rs.getInt("id"));
                    gp.setIdLoai(rs.getInt("id_loai"));
                    gp.setNgayBatDau(rs.getInt("ngay_bat_dau"));
                    gp.setNgayKetThuc(rs.getInt("ngay_ket_thuc"));
                    gp.setGiaTien(rs.getBigDecimal("gia_tien"));
                    return gp;
                });
    }

    public int save(GiaPhong gp) {
        return jdbcTemplate.update(
                "INSERT INTO gia_phong(id_loai,ngay_bat_dau,ngay_ket_thuc,gia_tien) VALUES(?,?,?,?)",
                gp.getIdLoai(), gp.getNgayBatDau(), gp.getNgayKetThuc(), gp.getGiaTien()
        );
    }

    public int update(GiaPhong gp) {
        return jdbcTemplate.update(
                "UPDATE gia_phong SET id_loai=?, ngay_bat_dau=?, ngay_ket_thuc=?, gia_tien=? WHERE id=?",
                gp.getIdLoai(), gp.getNgayBatDau(), gp.getNgayKetThuc(), gp.getGiaTien(), gp.getId()
        );
    }

    public int delete(Integer id) {
        return jdbcTemplate.update("DELETE FROM gia_phong WHERE id=?", id);
    }
}
