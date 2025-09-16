package org.example.repository;

import org.example.model.LoaiPhong;
import org.example.model.Phong;
import org.example.model.ToaNha;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PhongRepository {
    private final JdbcTemplate jdbcTemplate;

    public PhongRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Lấy danh sách phòng + join loại phòng + join tòa nhà
    public List<Phong> findAll() {
        String sql = """
            SELECT p.ma_phong, p.ten_phong, p.suc_chua,
                   t.ma_toa_nha, t.ten_toa,
                   l.ma_loai_phong, l.ten_loai
            FROM phong p
            JOIN toa_nha t ON p.ma_toa_nha = t.ma_toa_nha
            JOIN loai_phong l ON p.ma_loai_phong = l.ma_loai_phong
            """;

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Phong p = new Phong();
            p.setMaPhong(rs.getInt("ma_phong"));
            p.setTenPhong(rs.getString("ten_phong"));
            p.setSucChua(rs.getInt("suc_chua"));

            ToaNha t = new ToaNha();
            t.setMaToa(rs.getInt("ma_toa_nha"));
            t.setTenToa(rs.getString("ten_toa"));
            p.setToaNha(t);

            LoaiPhong l = new LoaiPhong();
            l.setMaLp(rs.getInt("ma_loai_phong"));
            l.setTenLoai(rs.getString("ten_loai"));
            p.setLoaiPhong(l);

            return p;
        });
    }

    // Tìm theo id
    public Phong findById(Integer id) {
        String sql = """
            SELECT p.ma_phong, p.ten_phong, p.suc_chua,
                   t.ma_toa_nha, t.ten_toa,
                   l.ma_loai_phong, l.ten_loai
            FROM phong p
            JOIN toa_nha t ON p.ma_toa_nha = t.ma_toa_nha
            JOIN loai_phong l ON p.ma_loai_phong = l.ma_loai_phong
            WHERE p.ma_phong = ?
            """;

        return jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) -> {
            Phong p = new Phong();
            p.setMaPhong(rs.getInt("ma_phong"));
            p.setTenPhong(rs.getString("ten_phong"));
            p.setSucChua(rs.getInt("suc_chua"));

            ToaNha t = new ToaNha();
            t.setMaToa(rs.getInt("ma_toa_nha"));
            t.setTenToa(rs.getString("ten_toa"));
            p.setToaNha(t);

            LoaiPhong l = new LoaiPhong();
            l.setMaLp(rs.getInt("ma_loai_phong"));
            l.setTenLoai(rs.getString("ten_loai"));
            p.setLoaiPhong(l);

            return p;
        });
    }

    // Thêm mới
    public int save(Phong p) {
        return jdbcTemplate.update(
                "INSERT INTO phong (ten_phong, ma_toa_nha, ma_loai_phong, suc_chua) VALUES (?, ?, ?, ?)",
                p.getTenPhong(),
                p.getToaNha().getMaToa(),
                p.getLoaiPhong().getMaLp(),
                p.getSucChua()
        );
    }

    // Cập nhật
    public int update(Phong p) {
        return jdbcTemplate.update(
                "UPDATE phong SET ten_phong=?, ma_toa_nha=?, ma_loai_phong=?, suc_chua=? WHERE ma_phong=?",
                p.getTenPhong(),
                p.getToaNha().getMaToa(),
                p.getLoaiPhong().getMaLp(),
                p.getSucChua(),
                p.getMaPhong()
        );
    }

    // Xóa
    public int delete(Integer id) {
        return jdbcTemplate.update("DELETE FROM phong WHERE ma_phong=?", id);
    }
}
