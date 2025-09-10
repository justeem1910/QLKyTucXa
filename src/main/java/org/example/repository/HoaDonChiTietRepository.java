package org.example.repository;

import org.example.model.HoaDonChiTiet;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class HoaDonChiTietRepository {
    private final JdbcTemplate jdbcTemplate;
    public HoaDonChiTietRepository(JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate; }

    public List<HoaDonChiTiet> findAll() {
        return jdbcTemplate.query("SELECT * FROM hoa_don_chi_tiet",
                (rs, rowNum) -> {
                    HoaDonChiTiet hdct = new HoaDonChiTiet();
                    hdct.setId(rs.getInt("id"));
                    hdct.setIdHd(rs.getInt("id_hd"));
                    hdct.setIdDv(rs.getInt("id_dv"));
                    hdct.setSoLuong(rs.getInt("so_luong"));
                    return hdct;
                });
    }

    public HoaDonChiTiet findById(Integer id) {
        return jdbcTemplate.queryForObject("SELECT * FROM hoa_don_chi_tiet WHERE id=?",
                new Object[]{id},
                (rs, rowNum) -> {
                    HoaDonChiTiet hdct = new HoaDonChiTiet();
                    hdct.setId(rs.getInt("id"));
                    hdct.setIdHd(rs.getInt("id_hd"));
                    hdct.setIdDv(rs.getInt("id_dv"));
                    hdct.setSoLuong(rs.getInt("so_luong"));
                    return hdct;
                });
    }

    public int save(HoaDonChiTiet hdct) {
        return jdbcTemplate.update(
                "INSERT INTO hoa_don_chi_tiet(id_hd,id_dv,so_luong) VALUES(?,?,?)",
                hdct.getIdHd(), hdct.getIdDv(), hdct.getSoLuong()
        );
    }

    public int update(HoaDonChiTiet hdct) {
        return jdbcTemplate.update(
                "UPDATE hoa_don_chi_tiet SET id_hd=?, id_dv=?, so_luong=? WHERE id=?",
                hdct.getIdHd(), hdct.getIdDv(), hdct.getSoLuong(), hdct.getId()
        );
    }

    public int delete(Integer id) {
        return jdbcTemplate.update("DELETE FROM hoa_don_chi_tiet WHERE id=?", id);
    }
}
