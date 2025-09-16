package org.example.repository;

import org.example.model.HopDongThue;
import org.hibernate.exception.DataException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public class HopDongThueRepository {
    private final JdbcTemplate jdbcTemplate;
    public HopDongThueRepository(JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate; }

    public List<HopDongThue> findAll() {
        return jdbcTemplate.query("SELECT * FROM hop_dong_thue",
                (rs, rowNum) -> {
                    HopDongThue hdt = new HopDongThue();
                    hdt.setIdSv(rs.getString("ma_sinh_vien"));
                    hdt.setIdPhong(rs.getInt("ma_phong"));
                    hdt.setNgayBatDau(rs.getDate("ngay_bat_dau"));
                    hdt.setNgayKetThuc(rs.getDate("ngay_ket_thuc"));
                    hdt.setNgayKetThuc(rs.getDate("ngay_thanh_ly"));
                    return hdt;
                });
    }

    public HopDongThue findById(String maSv, String maPhong, Date ngayBatDau) {
        return jdbcTemplate.queryForObject("SELECT * FROM hop_dong_thue WHERE ma_sinh_vien=? AND ma_phong=? AND ngay_bat_dau=?",
                new Object[]{maSv, maPhong, ngayBatDau},
                (rs, rowNum) -> {
                    HopDongThue hdt = new HopDongThue();
                    hdt.setIdSv(rs.getString("ma_sinh_vien"));
                    hdt.setIdPhong(rs.getInt("ma_phong"));
                    hdt.setNgayBatDau(rs.getDate("ngay_bat_dau"));
                    hdt.setNgayKetThuc(rs.getDate("ngay_ket_thuc"));
                    hdt.setNgayKetThuc(rs.getDate("ngay_thanh_ly"));
                    return hdt;
                });
    }

    public int save(HopDongThue hdt) {
        return jdbcTemplate.update(
                "INSERT INTO hop_dong_thue(ma_sinh_vien,ma_phong,ngay_bat_dau,ngay_ket_thuc,ngay_thanh_ly) VALUES(?,?,?,?,?)",
                hdt.getIdSv(), hdt.getIdPhong(), hdt.getNgayBatDau(), hdt.getNgayKetThuc(), hdt.getNgayThanhLy()
        );
    }

    public int update(HopDongThue hdt) {
        return jdbcTemplate.update(
                "UPDATE hop_dong_thue SET ngay_ket_thuc=?, ngay_thanh_ly=? WHERE ma_sinh_vien=? AND ma_phong=? AND ngay_bat_dau=?",
                hdt.getNgayKetThuc(), hdt.getNgayThanhLy(), hdt.getIdSv(), hdt.getIdPhong(), hdt.getNgayBatDau()
                );
    }

    public int delete(String maSv, String maPhong, Date ngayBatDau) {
        return jdbcTemplate.update("DELETE FROM hop_dong_thue WHERE ma_sinh_vien=? AND ma_phong=? AND ngay_bat_dau=?", maSv, maPhong, ngayBatDau);
    }
}
