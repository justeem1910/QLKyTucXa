package org.example.repository;

import org.example.model.HopDongThue;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HopDongThueRepository {
    private final JdbcTemplate jdbcTemplate;
    public HopDongThueRepository(JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate; }

    public List<HopDongThue> findAll() {
        return jdbcTemplate.query("SELECT * FROM hop_dong_thue",
                (rs, rowNum) -> {
                    HopDongThue hdt = new HopDongThue();
                    hdt.setId(rs.getInt("id"));
                    hdt.setIdSv(rs.getInt("id_sv"));
                    hdt.setIdPhong(rs.getInt("id_phong"));
                    hdt.setNgayBatDau(rs.getDate("ngay_bat_dau"));
                    hdt.setNgayKetThuc(rs.getDate("ngay_ket_thuc"));
                    hdt.setNgayKetThuc(rs.getDate("ngay_thanh_ly"));
                    return hdt;
                });
    }

    public HopDongThue findById(Integer id) {
        return jdbcTemplate.queryForObject("SELECT * FROM hop_dong_thue WHERE id=?",
                new Object[]{id},
                (rs, rowNum) -> {
                    HopDongThue hdt = new HopDongThue();
                    hdt.setId(rs.getInt("id"));
                    hdt.setIdSv(rs.getInt("id_sv"));
                    hdt.setIdPhong(rs.getInt("id_phong"));
                    hdt.setNgayBatDau(rs.getDate("ngay_bat_dau"));
                    hdt.setNgayKetThuc(rs.getDate("ngay_ket_thuc"));
                    hdt.setNgayKetThuc(rs.getDate("ngay_thanh_ly"));
                    return hdt;
                });
    }

    public int save(HopDongThue hdt) {
        return jdbcTemplate.update(
                "INSERT INTO hop_dong_thue(id_sv,id_phong,ngay_bat_dau,ngay_ket_thuc,ngay_thanh_ly) VALUES(?,?,?,?,?)",
                hdt.getIdSv(), hdt.getIdPhong(), hdt.getNgayBatDau(), hdt.getNgayKetThuc(), hdt.getNgayThanhLy()
        );
    }

    public int update(HopDongThue hdt) {
        return jdbcTemplate.update(
                "UPDATE hop_dong_thue SET id_sv=?, id_phong=?, ngay_bat_dau=?, ngay_ket_thuc=?, ngay_thanh_ly=? WHERE id=?",
                hdt.getIdSv(), hdt.getIdPhong(), hdt.getNgayBatDau(), hdt.getNgayKetThuc(), hdt.getNgayThanhLy(), hdt.getId()
        );
    }

    public int delete(Integer id) {
        return jdbcTemplate.update("DELETE FROM hop_dong_thue WHERE id=?", id);
    }
}
