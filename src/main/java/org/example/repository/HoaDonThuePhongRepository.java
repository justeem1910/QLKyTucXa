package org.example.repository;

import org.example.model.HoaDonThuePhong;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HoaDonThuePhongRepository {
    private final JdbcTemplate jdbcTemplate;

    public HoaDonThuePhongRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<HoaDonThuePhong> findAll() {
        return jdbcTemplate.query("SELECT * FROM hoa_don_thue_phong",
                (rs, rowNum) -> new HoaDonThuePhong(
                        rs.getString("ma_sinh_vien"),
                        rs.getInt("ma_hop_dong"),
                        rs.getInt("ma_hoa_don"),
                        rs.getBigDecimal("tien_phong")
                ));
    }

    public HoaDonThuePhong findById(String maSinhVien, Integer maHopDong, Integer maHoaDon) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM hoa_don_thue_phong WHERE ma_sinh_vien=? AND ma_hop_dong=? AND ma_hoa_don=?",
                new Object[]{maSinhVien, maHopDong, maHoaDon},
                (rs, rowNum) -> new HoaDonThuePhong(
                        rs.getString("ma_sinh_vien"),
                        rs.getInt("ma_hop_dong"),
                        rs.getInt("ma_hoa_don"),
                        rs.getBigDecimal("tien_phong")
                ));
    }

    public int save(HoaDonThuePhong hdtp) {
        return jdbcTemplate.update(
                "INSERT INTO hoa_don_thue_phong(ma_sinh_vien, ma_hop_dong, ma_hoa_don, tien_phong) VALUES(?, ?, ?, ?)",
                hdtp.getMaSinhVien(), hdtp.getMaHopDong(), hdtp.getMaHoaDon(), hdtp.getTienPhong()
        );
    }

    public int update(HoaDonThuePhong hdtp) {
        return jdbcTemplate.update(
                "UPDATE hoa_don_thue_phong SET tien_phong=? WHERE ma_sinh_vien=? AND ma_hop_dong=? AND ma_hoa_don=?",
                hdtp.getTienPhong(), hdtp.getMaSinhVien(), hdtp.getMaHopDong(), hdtp.getMaHoaDon()
        );
    }

    public int delete(String maSinhVien, Integer maHopDong, Integer maHoaDon) {
        return jdbcTemplate.update(
                "DELETE FROM hoa_don_thue_phong WHERE ma_sinh_vien=? AND ma_hop_dong=? AND ma_hoa_don=?",
                maSinhVien, maHopDong, maHoaDon
        );
    }
}
