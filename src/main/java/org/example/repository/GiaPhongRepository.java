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
                    gp.setMaGp(rs.getInt("ma_gp"));
                    gp.setIdLoai(rs.getInt("ma_lp"));
                    gp.setNgayBatDau(rs.getInt("ngay_bat_dau"));
                    gp.setNgayKetThuc(rs.getInt("ngay_ket_thuc"));
                    gp.setGiaTien(rs.getBigDecimal("gia_tien"));
                    return gp;
                });
    }

    public GiaPhong findById(Integer id) {
        return jdbcTemplate.queryForObject("SELECT * FROM gia_phong WHERE ma_gp=?",
                new Object[]{id},
                (rs, rowNum) -> {
                    GiaPhong gp = new GiaPhong();
                    gp.setMaGp(rs.getInt("ma_gp"));
                    gp.setIdLoai(rs.getInt("ma_lp"));
                    gp.setNgayBatDau(rs.getInt("ngay_bat_dau"));
                    gp.setNgayKetThuc(rs.getInt("ngay_ket_thuc"));
                    gp.setGiaTien(rs.getBigDecimal("gia_tien"));
                    return gp;
                });
    }

    public int save(GiaPhong gp) {
        return jdbcTemplate.update(
                "INSERT INTO gia_phong(ma_lp,ngay_bat_dau,ngay_ket_thuc,gia_tien) VALUES(?,?,?,?)",
                gp.getIdLoai(), gp.getNgayBatDau(), gp.getNgayKetThuc(), gp.getGiaTien()
        );
    }

    public int update(GiaPhong gp) {
        return jdbcTemplate.update(
                "UPDATE gia_phong SET ma_lp=?, ngay_bat_dau=?, ngay_ket_thuc=?, gia_tien=? WHERE ma_gp=?",
                gp.getIdLoai(), gp.getNgayBatDau(), gp.getNgayKetThuc(), gp.getGiaTien(), gp.getMaGp()
        );
    }

    public int delete(Integer id) {
        return jdbcTemplate.update("DELETE FROM gia_phong WHERE ma_gp=?", id);
    }
}
