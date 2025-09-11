package org.example.repository;

import org.example.model.SuDungDichVu;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SuDungDichVuRepository {
    private final JdbcTemplate jdbcTemplate;
    public SuDungDichVuRepository(JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate; }

    public List<SuDungDichVu> findAll() {
        return jdbcTemplate.query("SELECT * FROM su_dung_dich_vu",
                (rs, rowNum) -> {
                    SuDungDichVu sddv = new SuDungDichVu();
                    sddv.setMaSddv(rs.getInt("ma_sddv"));
                    sddv.setIdDv(rs.getInt("ma_dv"));
                    sddv.setIdSv(rs.getString("ma_sv"));
                    sddv.setNgayBatDau(rs.getTimestamp("ngay_bat_dau"));
                    sddv.setNgayKetThuc(rs.getTimestamp("ngay_ket_thuc"));
                    return sddv;
                });
    }

    public SuDungDichVu findById(Integer maSddv) {
        return jdbcTemplate.queryForObject("SELECT * FROM su_dung_dich_vu WHERE ma_sddv=?",
                new Object[]{maSddv},
                (rs, rowNum) -> {
                    SuDungDichVu sddv = new SuDungDichVu();
                    sddv.setMaSddv(rs.getInt("ma_sddv"));
                    sddv.setIdDv(rs.getInt("ma_dv"));
                    sddv.setIdSv(rs.getString("ma_sv"));
                    sddv.setNgayBatDau(rs.getTimestamp("ngay_bat_dau"));
                    sddv.setNgayKetThuc(rs.getTimestamp("ngay_ket_thuc"));
                    return sddv;
                });
    }

    public int save(SuDungDichVu sddv) {
        return jdbcTemplate.update(
                "INSERT INTO su_dung_dich_vu(ma_dv,ma_sv,ngay_bat_dau,ngay_ket_thuc) VALUES(?,?,?,?)",
                sddv.getIdDv(), sddv.getIdSv(), sddv.getNgayBatDau(), sddv.getNgayKetThuc()
        );
    }

    public int update(SuDungDichVu sddv) {
        return jdbcTemplate.update(
                "UPDATE su_dung_dich_vu SET ma_dv=?, ma_sv=?, ngay_bat_dau=?, ngay_ket_thuc=?? WHERE ma_sddv=?",
                sddv.getIdDv(), sddv.getIdSv(), sddv.getNgayBatDau(), sddv.getNgayKetThuc(), sddv.getMaSddv()
        );
    }

    public int delete(Integer id) {
        return jdbcTemplate.update("DELETE FROM su_dung_dich_vu WHERE ma_sddv=?", id);
    }
}
