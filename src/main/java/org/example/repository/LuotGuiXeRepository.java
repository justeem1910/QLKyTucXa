package org.example.repository;

import org.example.model.LuotGuiXe;
import org.example.model.SinhVien;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public class LuotGuiXeRepository {
    private final JdbcTemplate jdbcTemplate;
    public LuotGuiXeRepository(JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate; }

    public List<LuotGuiXe> findAll() {
        String sql = """
            SELECT lgx.ma_sinh_vien, lgx.bien_so_xe, lgx.thoi_gian_vao, lgx.thoi_gian_ra,
                   sv.ma_sinh_vien, sv.ten, sv.gioi_tinh, sv.ngay_sinh, sv.so_dien_thoai, sv.email
            FROM luot_gui_xe lgx
            JOIN sinh_vien sv ON lgx.ma_sinh_vien = sv.ma_sinh_vien
            """;
        return jdbcTemplate.query(sql,
                (rs, rowNum) -> {
                    LuotGuiXe lgx = new LuotGuiXe();
                    lgx.setBienSoXe(rs.getString("bien_so_xe"));
                    lgx.setThoiGianVao(rs.getTimestamp("thoi_gian_vao").toLocalDateTime());
                    Timestamp tsRa = rs.getTimestamp("thoi_gian_ra");
                    if (tsRa != null) {
                        lgx.setThoiGianRa(tsRa.toLocalDateTime());
                    } else {
                        lgx.setThoiGianRa(null);
                    }

                    SinhVien sv = new SinhVien();
                    sv.setMaSv(rs.getString("ma_sinh_vien"));
                    sv.setTen(rs.getString("ten"));
                    sv.setGioiTinh(rs.getString("gioi_tinh"));
                    sv.setNgaySinh(rs.getDate("ngay_sinh"));
                    sv.setSoDienThoai(rs.getString("so_dien_thoai"));
                    sv.setEmail(rs.getString("email"));

                    lgx.setSinhVien(sv);
                    return lgx;
                });
    }

    public LuotGuiXe findById(String maSv, String bienSoXe) {
        String  sql = """
        SELECT lgx.ma_sinh_vien, lgx.bien_so_xe, lgx.thoi_gian_vao, lgx.thoi_gian_ra,
               sv.ma_sinh_vien, sv.ten, sv.gioi_tinh, sv.ngay_sinh, sv.so_dien_thoai, sv.email
        FROM luot_gui_xe lgx
        JOIN sinh_vien sv ON lgx.ma_sinh_vien = sv.ma_sinh_vien
        WHERE lgx.ma_sinh_vien=? AND lgx.bien_so_xe=? AND lgx.thoi_gian_ra IS NULL
        ORDER BY lgx.thoi_gian_vao DESC
        LIMIT 1
        """;
        return jdbcTemplate.queryForObject(sql,
                new Object[]{maSv, bienSoXe},
                (rs, rowNum) -> {
                    LuotGuiXe lgx = new LuotGuiXe();
                    lgx.setBienSoXe(rs.getString("bien_so_xe"));
                    lgx.setThoiGianVao(rs.getTimestamp("thoi_gian_vao").toLocalDateTime());
                    Timestamp tsRa = rs.getTimestamp("thoi_gian_ra");
                    if (tsRa != null) {
                        lgx.setThoiGianRa(tsRa.toLocalDateTime());
                    } else {
                        lgx.setThoiGianRa(null);
                    }

                    SinhVien sv = new SinhVien();
                    sv.setMaSv(rs.getString("ma_sinh_vien"));
                    sv.setTen(rs.getString("ten"));
                    sv.setGioiTinh(rs.getString("gioi_tinh"));
                    sv.setNgaySinh(rs.getDate("ngay_sinh"));
                    sv.setSoDienThoai(rs.getString("so_dien_thoai"));
                    sv.setEmail(rs.getString("email"));

                    lgx.setSinhVien(sv);
                    return lgx;
                });
    }

    public int save(LuotGuiXe lgx) {
        return jdbcTemplate.update(
                "INSERT INTO luot_gui_xe(ma_sinh_vien,bien_so_xe,thoi_gian_vao) VALUES(?,?,?)",
                lgx.getSinhVien().getMaSv(), lgx.getBienSoXe(), Timestamp.valueOf(lgx.getThoiGianVao())
        );
    }

    public int update(LuotGuiXe lgx) {
        return jdbcTemplate.update(
                "UPDATE luot_gui_xe SET thoi_gian_ra=? WHERE ma_sinh_vien=? AND bien_so_xe=? AND thoi_gian_vao=?",
                Timestamp.valueOf(lgx.getThoiGianRa()), lgx.getSinhVien().getMaSv(), lgx.getBienSoXe(), Timestamp.valueOf(lgx.getThoiGianVao())
        );
    }

    public int delete(String maSv, String bienSoXe, LocalDateTime thoiGianVao) {
        return jdbcTemplate.update("DELETE FROM luot_gui_xe WHERE ma_sinh_vien=? AND bien_so_xe=? AND thoi_gian_vao=?", maSv, bienSoXe, thoiGianVao);
    }
}
