package org.example.repository;

import org.example.model.HoaDon;
import org.example.model.SinhVienThuePhong;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SinhVienThuePhongRepository {
    private final JdbcTemplate jdbcTemplate;

    public SinhVienThuePhongRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<SinhVienThuePhong> findAll() {
        return jdbcTemplate.query("SELECT * FROM sinh_vien_thue_phong",
                (rs, rowNum) -> {
                    SinhVienThuePhong svtp = new SinhVienThuePhong();
                    svtp.setMaSv(rs.getString("ma_sinh_vien"));
                    svtp.setMaHopDong(rs.getInt("ma_hop_dong"));
                    return svtp;
                });
    }

    public SinhVienThuePhong findById(String maSinhVien, Integer maHopDong) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM sinh_vien_thue_phong WHERE ma_sinh_vien=? AND ma_hop_dong=?",
                new Object[]{maSinhVien, maHopDong},
                (rs, rowNum) -> {
                    SinhVienThuePhong svtp = new SinhVienThuePhong();
                    svtp.setMaSv(rs.getString("ma_sinh_vien"));
                    svtp.setMaHopDong(rs.getInt("ma_hop_dong"));
                    return svtp;
                });
    }

    public int save(SinhVienThuePhong svtp) {
        return jdbcTemplate.update(
                "INSERT INTO sinh_vien_thue_phong(ma_sinh_vien, ma_hop_dong) VALUES(?, ?)",
                svtp.getMaSv(), svtp.getMaHopDong()
        );
    }

    public int delete(String maSinhVien, Integer maHopDong) {
        return jdbcTemplate.update(
                "DELETE FROM sinh_vien_thue_phong WHERE ma_sinh_vien=? AND ma_hop_dong=?",
                maSinhVien, maHopDong
        );
    }
}
