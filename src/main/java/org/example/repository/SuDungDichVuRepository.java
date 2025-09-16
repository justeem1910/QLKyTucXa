package org.example.repository;

import org.example.model.SuDungDichVu;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public class SuDungDichVuRepository {
    private final JdbcTemplate jdbcTemplate;
    public SuDungDichVuRepository(JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate; }

    public List<SuDungDichVu> findAll() {
        String sql = """
            SELECT p.ma_phong, p.ten_phong, p.suc_chua,
                   t.ma_toa_nha, t.ten_toa,
                   l.ma_loai_phong, l.ten_loai
            FROM phong p
            JOIN toa_nha t ON p.ma_toa_nha = t.ma_toa_nha
            JOIN loai_phong l ON p.ma_loai_phong = l.ma_loai_phong
            """;
        return jdbcTemplate.query("SELECT * FROM su_dung_dich_vu",
                (rs, rowNum) -> {
                    SuDungDichVu sddv = new SuDungDichVu(); 
                    sddv.setIdDv(rs.getInt("ma_dich_vu"));
                    sddv.setIdSv(rs.getString("ma_sinh_vien"));
                    sddv.setNgaySuDung(rs.getTimestamp("ngay_su_dung"));
                    return sddv;
                });
    }

    public SuDungDichVu findById(Integer maDv, String maSv, Timestamp ngaySuDung) {
        return jdbcTemplate.queryForObject("SELECT * FROM su_dung_dich_vu WHERE ma_dich_vu=? AND ma_sinh_vien=? AND ngay_su_dung=?",
                new Object[]{maDv, maSv, ngaySuDung},
                (rs, rowNum) -> {
                    SuDungDichVu sddv = new SuDungDichVu();
                    sddv.setIdDv(rs.getInt("ma_dich_vu"));
                    sddv.setIdSv(rs.getString("ma_sinh_vien"));
                    sddv.setNgaySuDung(rs.getTimestamp("ngay_su_dung"));
                    return sddv;
                });
    }

    public int save(SuDungDichVu sddv) {
        return jdbcTemplate.update(
                "INSERT INTO su_dung_dich_vu(ma_dich_vu,ma_sinh_vien,ngay_su_dung) VALUES(?,?,?)",
                sddv.getIdDv(), sddv.getIdSv(), sddv.getNgaySuDung()
        );
    }

//    public int update(SuDungDichVu sddv) {
//        return jdbcTemplate.update(
//                "UPDATE su_dung_dich_vu SET ma_dv=?, ma_sv=?, ngay_bat_dau=?, ngay_ket_thuc=?? WHERE ma_sddv=?",
//                sddv.getIdDv(), sddv.getIdSv(), sddv.getNgayBatDau(), sddv.getNgayKetThuc(), sddv.getMaSddv()
//        );
//    }

    public int delete(Integer maDv, String maSv, Timestamp ngaySuDung) {
        return jdbcTemplate.update("DELETE FROM su_dung_dich_vu WHERE ma_dich_vu=? AND ma_sinh_vien=? AND ngay_su_dung=?", maDv, maSv, ngaySuDung);
    }
}
