package org.example.repository;

import org.example.model.SinhVien;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class SinhVienRepository {
    private final JdbcTemplate jdbcTemplate;
    public SinhVienRepository(JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate; }

    public List<SinhVien> findAll() {
        return jdbcTemplate.query("SELECT * FROM sinh_vien",
                (rs, rowNum) -> {
                    SinhVien sv = new SinhVien();
                    sv.setId(rs.getInt("id"));
                    sv.setMaSv(rs.getString("ma_sv"));
                    sv.setTen(rs.getString("ten"));
                    sv.setGioiTinh(rs.getString("gioi_tinh"));
                    sv.setNgaySinh(rs.getDate("ngay_sinh"));
                    sv.setSoDienThoai(rs.getString("so_dien_thoai"));
                    sv.setEmail(rs.getString("email"));
                    return sv;
                });
    }

    public SinhVien findById(Integer id) {
        return jdbcTemplate.queryForObject("SELECT * FROM sinh_vien WHERE id=?",
                new Object[]{id},
                (rs, rowNum) -> {
                    SinhVien sv = new SinhVien();
                    sv.setId(rs.getInt("id"));
                    sv.setMaSv(rs.getString("ma_sv"));
                    sv.setTen(rs.getString("ten"));
                    sv.setGioiTinh(rs.getString("gioi_tinh"));
                    sv.setNgaySinh(rs.getDate("ngay_sinh"));
                    sv.setSoDienThoai(rs.getString("so_dien_thoai"));
                    sv.setEmail(rs.getString("email"));
                    return sv;
                });
    }

    public int save(SinhVien sv) {
        return jdbcTemplate.update(
                "INSERT INTO sinh_vien(ma_sv,ten,gioi_tinh,ngay_sinh,so_dien_thoai,email) VALUES(?,?,?,?,?,?)",
                sv.getMaSv(), sv.getTen(), sv.getGioiTinh(), sv.getNgaySinh(), sv.getSoDienThoai(), sv.getEmail()
        );
    }

    public int update(SinhVien sv) {
        return jdbcTemplate.update(
                "UPDATE sinh_vien SET ma_sv=?, ten=?, gioi_tinh=?, ngay_sinh=?, so_dien_thoai=?, email=? WHERE id=?",
                sv.getMaSv(), sv.getTen(), sv.getGioiTinh(), sv.getNgaySinh(), sv.getSoDienThoai(), sv.getEmail(), sv.getId()
        );
    }

    public int delete(Integer id) {
        return jdbcTemplate.update("DELETE FROM sinh_vien WHERE id=?", id);
    }
}
