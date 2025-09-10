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
                    p.setId(rs.getInt("id"));
                    p.setTenPhong(rs.getString("ten_phong"));
                    p.setIdToa(rs.getInt("id_toa"));
                    p.setIdLoai(rs.getInt("id_loai"));
                    p.setSucChua(rs.getInt("suc_chua"));
                    p.setSoNguoiDaThue(rs.getInt("so_nguoi_da_thue"));
                    return p;
                });
    }

    public Phong findById(Integer id) {
        return jdbcTemplate.queryForObject("SELECT * FROM phong WHERE id=?",
                new Object[]{id},
                (rs, rowNum) -> {
                    Phong p = new Phong();
                    p.setId(rs.getInt("id"));
                    p.setTenPhong(rs.getString("ten_phong"));
                    p.setIdToa(rs.getInt("id_toa"));
                    p.setIdLoai(rs.getInt("id_loai"));
                    p.setSucChua(rs.getInt("suc_chua"));
                    p.setSoNguoiDaThue(rs.getInt("so_nguoi_da_thue"));
                    return p;
                });
    }

    public int save(Phong p) {
        return jdbcTemplate.update(
                "INSERT INTO phong(ten_phong,id_toa,id_loai,suc_chua,so_nguoi_da_thue) VALUES(?,?,?,?,?)",
                p.getTenPhong(), p.getIdToa(), p.getIdLoai(), p.getSucChua(), p.getSoNguoiDaThue()
        );
    }

    public int update(Phong p) {
        return jdbcTemplate.update(
                "UPDATE phong SET ten_phong=?, id_toa=?, id_loai=?, suc_chua=?, so_nguoi_da_thue=? WHERE id=?",
                p.getTenPhong(), p.getIdToa(), p.getIdLoai(), p.getSucChua(), p.getSoNguoiDaThue(), p.getId()
        );
    }

    public int delete(Integer id) {
        return jdbcTemplate.update("DELETE FROM phong WHERE id=?", id);
    }
}
