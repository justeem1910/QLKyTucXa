package org.example.repository;

import org.example.model.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public class SuDungDichVuRepository {
    private final JdbcTemplate jdbcTemplate;

    public SuDungDichVuRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<SuDungDichVu> findAll() {
        String sql = """
            SELECT sddv.ma_dich_vu, sddv.ma_sinh_vien, sddv.ngay_su_dung,
                   dv.ma_dich_vu, dv.ten_dich_vu, dv.don_gia, dv.gia_co_dinh,
                   sv.ma_sinh_vien, sv.ten, sv.gioi_tinh, sv.ngay_sinh, sv.email, sv.so_dien_thoai
            FROM su_dung_dich_vu sddv
            JOIN dich_vu dv ON sddv.ma_dich_vu = dv.ma_dich_vu
            JOIN sinh_vien sv ON sddv.ma_sinh_vien = sv.ma_sinh_vien
            """;
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            SuDungDichVu sddv = new SuDungDichVu();

            DichVu dv = new DichVu();
            dv.setMaDichVu(rs.getInt("ma_dich_vu"));
            dv.setTenDichVu(rs.getString("ten_dich_vu"));
            dv.setDonVi(rs.getString("don_gia"));
            dv.setGiaCoDinh(rs.getBigDecimal("gia_co_dinh"));

            SinhVien sv = new SinhVien();
            sv.setMaSv(rs.getString("ma_sinh_vien"));
            sv.setTen(rs.getString("ten"));
            sv.setGioiTinh(rs.getString("gioi_tinh"));
            sv.setNgaySinh(rs.getDate("ngay_sinh"));
            sv.setEmail(rs.getString("email"));
            sv.setSoDienThoai(rs.getString("so_dien_thoai"));

            sddv.setDichVu(dv);
            sddv.setSinhVien(sv);
            sddv.setNgaySuDung(rs.getTimestamp("ngay_su_dung"));

            return sddv;
        });
    }

    public SuDungDichVu findById(Integer maDv, String maSv, Timestamp ngaySuDung) {
        String sql = """
            SELECT sddv.ma_dich_vu, sddv.ma_sinh_vien, sddv.ngay_su_dung,
                   dv.ma_dich_vu, dv.ten_dich_vu, dv.don_gia, dv.gia_co_dinh,
                   sv.ma_sinh_vien, sv.ten, sv.gioi_tinh, sv.ngay_sinh, sv.email, sv.so_dien_thoai
            FROM su_dung_dich_vu sddv
            JOIN dich_vu dv ON sddv.ma_dich_vu = dv.ma_dich_vu
            JOIN sinh_vien sv ON sddv.ma_sinh_vien = sv.ma_sinh_vien
            WHERE sddv.ma_dich_vu=? AND sddv.ma_sinh_vien=? AND sddv.ngay_su_dung=?
            """;
        return jdbcTemplate.queryForObject(sql, new Object[]{maDv, maSv, ngaySuDung}, (rs, rowNum) -> {
            SuDungDichVu sddv = new SuDungDichVu();

            DichVu dv = new DichVu();
            dv.setMaDichVu(rs.getInt("ma_dich_vu"));
            dv.setTenDichVu(rs.getString("ten_dich_vu"));
            dv.setDonVi(rs.getString("don_gia"));
            dv.setGiaCoDinh(rs.getBigDecimal("gia_co_dinh"));

            SinhVien sv = new SinhVien();
            sv.setMaSv(rs.getString("ma_sinh_vien"));
            sv.setTen(rs.getString("ten"));
            sv.setGioiTinh(rs.getString("gioi_tinh"));
            sv.setNgaySinh(rs.getDate("ngay_sinh"));
            sv.setEmail(rs.getString("email"));
            sv.setSoDienThoai(rs.getString("so_dien_thoai"));

            sddv.setDichVu(dv);
            sddv.setSinhVien(sv);
            sddv.setNgaySuDung(rs.getTimestamp("ngay_su_dung"));

            return sddv;
        });
    }

    public int save(SuDungDichVu sddv) {
        return jdbcTemplate.update(
                "INSERT INTO su_dung_dich_vu(ma_dich_vu, ma_sinh_vien, ngay_su_dung) VALUES(?,?,?)",
                sddv.getDichVu().getMaDichVu(),
                sddv.getSinhVien().getMaSv(),
                sddv.getNgaySuDung()
        );
    }

    public int delete(Integer maDv, String maSv, Timestamp ngaySuDung) {
        return jdbcTemplate.update(
                "DELETE FROM su_dung_dich_vu WHERE ma_dich_vu=? AND ma_sinh_vien=? AND ngay_su_dung=?",
                maDv, maSv, ngaySuDung
        );
    }
}
