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
                    sv.setMaSv(rs.getString("ma_sinh_vien"));
                    sv.setTen(rs.getString("ten"));
                    sv.setGioiTinh(rs.getString("gioi_tinh"));
                    sv.setNgaySinh(rs.getDate("ngay_sinh"));
                    sv.setSoDienThoai(rs.getString("so_dien_thoai"));
                    sv.setEmail(rs.getString("email"));
                    return sv;
                });
    }

    public SinhVien findById(String maSv) {
        return jdbcTemplate.queryForObject("SELECT * FROM sinh_vien WHERE ma_sinh_vien=?",
                new Object[]{maSv},
                (rs, rowNum) -> {
                    SinhVien sv = new SinhVien();
                    sv.setMaSv(rs.getString("ma_sinh_vien"));
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
                "INSERT INTO sinh_vien(ma_sinh_vien,ten,gioi_tinh,ngay_sinh,so_dien_thoai,email) VALUES(?,?,?,?,?,?)",
                sv.getMaSv(), sv.getTen(), sv.getGioiTinh(), sv.getNgaySinh(), sv.getSoDienThoai(), sv.getEmail()
        );
    }

    public int update(SinhVien sv) {
        return jdbcTemplate.update(
                "UPDATE sinh_vien SET ten=?, gioi_tinh=?, ngay_sinh=?, so_dien_thoai=?, email=? WHERE ma_sinh_vien=?",
                sv.getTen(), sv.getGioiTinh(), sv.getNgaySinh(), sv.getSoDienThoai(), sv.getEmail(),  sv.getMaSv()
        );
    }

    public int delete(String ma_sv) {
        return jdbcTemplate.update("DELETE FROM sinh_vien WHERE ma_sinh_vien=?", ma_sv);
    }
}
