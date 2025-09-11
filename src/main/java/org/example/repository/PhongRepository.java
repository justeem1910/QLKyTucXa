package org.example.repository;

import org.example.model.Phong;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PhongRepository {
    private final JdbcTemplate jdbcTemplate;
    public PhongRepository(JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate; }

    public List<Phong> findAll() {
        return jdbcTemplate.query("SELECT * FROM phong",
                (rs, rowNum) -> {
                    Phong p = new Phong();
                    p.setMaPhongg(rs.getInt("ma_phong"));
                    p.setTenPhong(rs.getString("ten_phong"));
                    p.setIdToa(rs.getInt("ma_toa"));
                    p.setIdLoai(rs.getInt("ma_lp"));
                    p.setSucChua(rs.getInt("suc_chua"));
                    p.setSoNguoiDaThue(rs.getInt("so_nguoi_da_thue"));
                    return p;
                });
    }

    public Phong findById(Integer id) {
        return jdbcTemplate.queryForObject("SELECT * FROM phong WHERE ma_phong=?",
                new Object[]{id},
                (rs, rowNum) -> {
                    Phong p = new Phong();
                    p.setMaPhongg(rs.getInt("ma_phong"));
                    p.setTenPhong(rs.getString("ten_phong"));
                    p.setIdToa(rs.getInt("ma_toa"));
                    p.setIdLoai(rs.getInt("ma_lp"));
                    p.setSucChua(rs.getInt("suc_chua"));
                    p.setSoNguoiDaThue(rs.getInt("so_nguoi_da_thue"));
                    return p;
                });
    }

    public int save(Phong p) {
        return jdbcTemplate.update(
                "INSERT INTO phong(ten_phong,ma_toa,ma_lp,suc_chua,so_nguoi_da_thue) VALUES(?,?,?,?,?)",
                p.getTenPhong(), p.getIdToa(), p.getIdLoai(), p.getSucChua(), p.getSoNguoiDaThue()
        );
    }

    public int update(Phong p) {
        return jdbcTemplate.update(
                "UPDATE phong SET ten_phong=?, ma_toa=?, ma_lp=?, suc_chua=?, so_nguoi_da_thue=? WHERE ma_phong=?",
                p.getTenPhong(), p.getIdToa(), p.getIdLoai(), p.getSucChua(), p.getSoNguoiDaThue(), p.getMaPhongg()
        );
    }

    public int delete(Integer id) {
        return jdbcTemplate.update("DELETE FROM phong WHERE ma_lp=?", id);
    }
}
