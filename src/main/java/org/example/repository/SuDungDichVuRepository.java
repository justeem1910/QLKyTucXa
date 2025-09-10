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
                    sddv.setId(rs.getInt("id"));
                    sddv.setIdDv(rs.getInt("id_dv"));
                    sddv.setIdSv(rs.getInt("id_sv"));
                    sddv.setNgayBatDau(rs.getTimestamp("ngay_bat_dau"));
                    sddv.setNgayKetThuc(rs.getTimestamp("ngay_ket_thuc"));
                    return sddv;
                });
    }

    public SuDungDichVu findById(Integer id) {
        return jdbcTemplate.queryForObject("SELECT * FROM su_dung_dich_vu WHERE id=?",
                new Object[]{id},
                (rs, rowNum) -> {
                    SuDungDichVu sddv = new SuDungDichVu();
                    sddv.setId(rs.getInt("id"));
                    sddv.setIdDv(rs.getInt("id_dv"));
                    sddv.setIdSv(rs.getInt("id_sv"));
                    sddv.setNgayBatDau(rs.getTimestamp("ngay_bat_dau"));
                    sddv.setNgayKetThuc(rs.getTimestamp("ngay_ket_thuc"));
                    return sddv;
                });
    }

    public int save(SuDungDichVu sddv) {
        return jdbcTemplate.update(
                "INSERT INTO su_dung_dich_vu(id_dv,id_sv,ngay_bat_dau,ngay_ket_thuc) VALUES(?,?,?,?)",
                sddv.getIdDv(), sddv.getIdSv(), sddv.getNgayBatDau(), sddv.getNgayKetThuc()
        );
    }

    public int update(SuDungDichVu sddv) {
        return jdbcTemplate.update(
                "UPDATE su_dung_dich_vu SET id_dv=?, id_sv=?, ngay_bat_dau=?, ngay_ket_thuc=?? WHERE id=?",
                sddv.getIdDv(), sddv.getIdSv(), sddv.getNgayBatDau(), sddv.getNgayKetThuc(), sddv.getId()
        );
    }

    public int delete(Integer id) {
        return jdbcTemplate.update("DELETE FROM su_dung_dich_vu WHERE id=?", id);
    }
}
