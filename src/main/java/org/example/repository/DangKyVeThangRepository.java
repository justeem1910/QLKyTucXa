package org.example.repository;

import org.example.model.DangKyVeThang;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class DangKyVeThangRepository {
    private final JdbcTemplate jdbcTemplate;
    public DangKyVeThangRepository(JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate; }

    public List<DangKyVeThang> findAll() {
        return jdbcTemplate.query("SELECT * FROM dang_ky_ve_thang",
                (rs, rowNum) -> {
                    DangKyVeThang vt = new DangKyVeThang();
                    vt.setMaVT(rs.getInt("ma_dkvt"));
                    vt.setBienSoXe(rs.getString("bien_so_xe"));
                    vt.setIdSv(rs.getString("ma_sv"));
                    vt.setNgayBatDau(rs.getDate("ngay_bat_dau"));
                    vt.setNgayKetThuc(rs.getDate("ngay_ket_thuc"));
                    return vt;
                });
    }

    public DangKyVeThang findById(Integer id) {
        return jdbcTemplate.queryForObject("SELECT * FROM dang_ky_ve_thang WHERE ma_dkvt=?",
                new Object[]{id},
                (rs, rowNum) -> {
                    DangKyVeThang vt = new DangKyVeThang();
                    vt.setMaVT(rs.getInt("ma_dkvt"));
                    vt.setBienSoXe(rs.getString("bien_so_xe"));
                    vt.setIdSv(rs.getString("ma_sv"));
                    vt.setNgayBatDau(rs.getDate("ngay_bat_dau"));
                    vt.setNgayKetThuc(rs.getDate("ngay_ket_thuc"));
                    return vt;
                });
    }

    public int save(DangKyVeThang vt) {
        return jdbcTemplate.update(
                "INSERT INTO dang_ky_ve_thang(ma_sv,bien_so_xe,ngay_bat_dau,ngay_ket_thuc) VALUES(?,?,?,?)",
                vt.getIdSv(), vt.getBienSoXe(), vt.getNgayBatDau(), vt.getNgayKetThuc()
        );
    }

    public int update(DangKyVeThang vt) {
        return jdbcTemplate.update(
                "UPDATE dang_ky_ve_thang SET ma_sv=?, bien_so_xe=?, ngay_bat_dau=?, ngay_ket_thuc=? WHERE ma_dkvt=?",
                vt.getIdSv(), vt.getBienSoXe(), vt.getNgayBatDau(), vt.getNgayKetThuc(), vt.getMaVT()
        );
    }

    public int delete(Integer id) {
        return jdbcTemplate.update("DELETE FROM dang_ky_ve_thang WHERE ma_dkvt=?", id);
    }
}

