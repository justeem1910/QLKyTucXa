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
                    hdct.setMaHdct(rs.getInt("ma_hdct"));
                    hdct.setIdHd(rs.getInt("ma_hd"));
                    hdct.setIdDv(rs.getInt("ma_dv"));
                    hdct.setSoLuong(rs.getInt("so_luong"));
                    return hdct;
                });
    }

    public HoaDonChiTiet findById(Integer maHdct) {
        return jdbcTemplate.queryForObject("SELECT * FROM hoa_don_chi_tiet WHERE ma_hdct=?",
                new Object[]{maHdct},
                (rs, rowNum) -> {
                    HoaDonChiTiet hdct = new HoaDonChiTiet();
                    hdct.setMaHdct(rs.getInt("ma_hdct"));
                    hdct.setIdHd(rs.getInt("ma_hd"));
                    hdct.setIdDv(rs.getInt("ma_dv"));
                    hdct.setSoLuong(rs.getInt("so_luong"));
                    return hdct;
                });
    }

    public int save(HoaDonChiTiet hdct) {
        return jdbcTemplate.update(
                "INSERT INTO hoa_don_chi_tiet(ma_hd,ma_dv,so_luong) VALUES(?,?,?)",
                hdct.getIdHd(), hdct.getIdDv(), hdct.getSoLuong()
        );
    }

    public int update(HoaDonChiTiet hdct) {
        return jdbcTemplate.update(
                "UPDATE hoa_don_chi_tiet SET ma_hd=?, ma_dv=?, so_luong=? WHERE ma_hdct=?",
                hdct.getIdHd(), hdct.getIdDv(), hdct.getSoLuong(), hdct.getMaHdct()
        );
    }

    public int delete(Integer id) {
        return jdbcTemplate.update("DELETE FROM hoa_don_chi_tiet WHERE ma_hdct=?", id);
    }
}
