package org.example.repository;

import org.example.model.DangKyVeThang;
import org.example.model.SinhVien;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class DangKyVeThangRepository {
    private final JdbcTemplate jdbcTemplate;
    public DangKyVeThangRepository(JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate; }

    public List<DangKyVeThang> findAll() {
        String sql = """
            SELECT dkvt.ma_sinh_vien, dkvt.bien_so_xe, dkvt.ngay_bat_dau, dkvt.ngay_ket_thuc,
                   sv.ma_sinh_vien, sv.ten, sv.gioi_tinh, sv.ngay_sinh, sv.so_dien_thoai, sv.email
            FROM dang_ky_ve_thang dkvt
            JOIN sinh_vien sv ON dkvt.ma_sinh_vien = sv.ma_sinh_vien
            """;

        return jdbcTemplate.query(sql,
                (rs, rowNum) -> {
                    DangKyVeThang vt = new DangKyVeThang();
                    vt.setBienSoXe(rs.getString("bien_so_xe"));
                    vt.setNgayBatDau(rs.getDate("ngay_bat_dau"));
                    vt.setNgayKetThuc(rs.getDate("ngay_ket_thuc"));

                    SinhVien sv = new SinhVien();
                    sv.setMaSv(rs.getString("ma_sinh_vien"));
                    sv.setTen(rs.getString("ten"));
                    sv.setGioiTinh(rs.getString("gioi_tinh"));
                    sv.setNgaySinh(rs.getDate("ngay_sinh"));
                    sv.setSoDienThoai(rs.getString("so_dien_thoai"));
                    sv.setEmail(rs.getString("email"));

                    vt.setSinhVien(sv);
                    return vt;
                });
    }

    public DangKyVeThang findById(String maSv, String bienSoXe, Date ngayBatDau) {
        String sql = """
            SELECT dkvt.ma_sinh_vien, dkvt.bien_so_xe, dkvt.ngay_bat_dau, dkvt.ngay_ket_thuc,
                   sv.ma_sinh_vien, sv.ten, sv.gioi_tinh, sv.ngay_sinh, sv.so_dien_thoai, sv.email
            FROM dang_ky_ve_thang dkvt
            JOIN sinh_vien sv ON dkvt.ma_sinh_vien = sv.ma_sinh_vien
            WHERE dkvt.ma_sinh_vien=? AND dkvt.bien_so_xe=? AND dkvt.ngay_bat_dau=?
            """;
        return jdbcTemplate.queryForObject(sql,
                new Object[]{maSv, bienSoXe, ngayBatDau},
                (rs, rowNum) -> {
                    DangKyVeThang vt = new DangKyVeThang();
                    vt.setBienSoXe(rs.getString("bien_so_xe"));
                    vt.setNgayBatDau(rs.getDate("ngay_bat_dau"));
                    vt.setNgayKetThuc(rs.getDate("ngay_ket_thuc"));

                    SinhVien sv = new SinhVien();
                    sv.setMaSv(rs.getString("ma_sinh_vien"));
                    sv.setTen(rs.getString("ten"));
                    sv.setGioiTinh(rs.getString("gioi_tinh"));
                    sv.setNgaySinh(rs.getDate("ngay_sinh"));
                    sv.setSoDienThoai(rs.getString("so_dien_thoai"));
                    sv.setEmail(rs.getString("email"));

                    vt.setSinhVien(sv);
                    return vt;
                });
    }

    public int save(DangKyVeThang vt) {
        return jdbcTemplate.update(
                "INSERT INTO dang_ky_ve_thang(ma_sinh_vien,bien_so_xe,ngay_bat_dau,ngay_ket_thuc) VALUES(?,?,?,?)",
                vt.getSinhVien().getMaSv(), vt.getBienSoXe(), vt.getNgayBatDau(), vt.getNgayKetThuc()
        );
    }

    public int update(DangKyVeThang vt) {
        return jdbcTemplate.update(
                "UPDATE dang_ky_ve_thang SET ngay_ket_thuc=? WHERE ma_sinh_vien=? AND bien_so_xe=? AND ngay_bat_dau=?",
                 vt.getNgayKetThuc(), vt.getSinhVien().getMaSv(), vt.getBienSoXe(), vt.getNgayBatDau()
        );
    }

    public int delete(String maSv, String bienSoXe, Date ngayBatDau) {
        return jdbcTemplate.update("DELETE FROM dang_ky_ve_thang WHERE ma_sinh_vien=? AND bien_so_xe=? AND ngay_bat_dau=?", maSv, bienSoXe, ngayBatDau);
    }
}

