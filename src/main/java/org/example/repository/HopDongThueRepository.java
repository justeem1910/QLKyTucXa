package org.example.repository;

import org.example.model.HopDongThue;
import org.example.model.Phong;
import org.example.model.ToaNha;
import org.example.model.LoaiPhong;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HopDongThueRepository {
    private final JdbcTemplate jdbcTemplate;

    public HopDongThueRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Lấy tất cả hợp đồng kèm phòng, tòa nhà, loại phòng
    public List<HopDongThue> findAll() {
        String sql = """
            SELECT h.ma_hop_dong, h.ngay_bat_dau, h.ngay_ket_thuc, h.ngay_thanh_ly,
                   p.ma_phong, p.ten_phong, p.suc_chua,
                   t.ma_toa_nha, t.ten_toa,
                   l.ma_loai_phong, l.ten_loai
            FROM hop_dong_thue h
            JOIN phong p ON h.ma_phong = p.ma_phong
            JOIN toa_nha t ON p.ma_toa_nha = t.ma_toa_nha
            JOIN loai_phong l ON p.ma_loai_phong = l.ma_loai_phong
            """;

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            HopDongThue hdt = new HopDongThue();
            hdt.setMaHopDong(rs.getString("ma_hop_dong"));
            hdt.setNgayBatDau(rs.getDate("ngay_bat_dau"));
            hdt.setNgayKetThuc(rs.getDate("ngay_ket_thuc"));
            hdt.setNgayThanhLy(rs.getDate("ngay_thanh_ly"));

            // Tạo phòng
            Phong phong = new Phong();
            phong.setMaPhong(rs.getString("ma_phong"));
            phong.setTenPhong(rs.getString("ten_phong"));
            phong.setSucChua(rs.getInt("suc_chua"));

            // Tạo tòa nhà
            ToaNha toa = new ToaNha();
            toa.setMaToa(rs.getString("ma_toa_nha"));
            toa.setTenToa(rs.getString("ten_toa"));
            phong.setToaNha(toa);

            // Tạo loại phòng
            LoaiPhong loai = new LoaiPhong();
            loai.setMaLp(rs.getString("ma_loai_phong"));
            loai.setTenLoai(rs.getString("ten_loai"));
            phong.setLoaiPhong(loai);

            hdt.setPhong(phong);

            return hdt;
        });
    }

    // Tìm theo id
    public HopDongThue findById(String maHopDong) {
        String sql = """
            SELECT h.ma_hop_dong, h.ngay_bat_dau, h.ngay_ket_thuc, h.ngay_thanh_ly,
                   p.ma_phong, p.ten_phong, p.suc_chua,
                   t.ma_toa_nha, t.ten_toa,
                   l.ma_loai_phong, l.ten_loai
            FROM hop_dong_thue h
            JOIN phong p ON h.ma_phong = p.ma_phong
            JOIN toa_nha t ON p.ma_toa_nha = t.ma_toa_nha
            JOIN loai_phong l ON p.ma_loai_phong = l.ma_loai_phong
            WHERE h.ma_hop_dong = ?
            """;

        return jdbcTemplate.queryForObject(sql, new Object[]{maHopDong}, (rs, rowNum) -> {
            HopDongThue hdt = new HopDongThue();
            hdt.setMaHopDong(rs.getString("ma_hop_dong"));
            hdt.setNgayBatDau(rs.getDate("ngay_bat_dau"));
            hdt.setNgayKetThuc(rs.getDate("ngay_ket_thuc"));
            hdt.setNgayThanhLy(rs.getDate("ngay_thanh_ly"));

            Phong phong = new Phong();
            phong.setMaPhong(rs.getString("ma_phong"));
            phong.setTenPhong(rs.getString("ten_phong"));
            phong.setSucChua(rs.getInt("suc_chua"));

            ToaNha toa = new ToaNha();
            toa.setMaToa(rs.getString("ma_toa_nha"));
            toa.setTenToa(rs.getString("ten_toa"));
            phong.setToaNha(toa);

            LoaiPhong loai = new LoaiPhong();
            loai.setMaLp(rs.getString("ma_loai_phong"));
            loai.setTenLoai(rs.getString("ten_loai"));
            phong.setLoaiPhong(loai);

            hdt.setPhong(phong);

            return hdt;
        });
    }

    // Thêm mới
    public int save(HopDongThue hdt) {
        return jdbcTemplate.update(
                "INSERT INTO hop_dong_thue(ma_phong, ngay_bat_dau, ngay_ket_thuc, ngay_thanh_ly) VALUES (?, ?, ?, ?)",
                hdt.getPhong().getMaPhong(),
                hdt.getNgayBatDau(),
                hdt.getNgayKetThuc(),
                hdt.getNgayThanhLy()
        );
    }

    // Cập nhật
    public int update(HopDongThue hdt) {
        return jdbcTemplate.update(
                "UPDATE hop_dong_thue SET ma_phong=?, ngay_bat_dau=?, ngay_ket_thuc=?, ngay_thanh_ly=? WHERE ma_hop_dong=?",
                hdt.getPhong().getMaPhong(),
                hdt.getNgayBatDau(),
                hdt.getNgayKetThuc(),
                hdt.getNgayThanhLy(),
                hdt.getMaHopDong()
        );
    }

    // Xóa
    public int delete(String maHopDong) {
        return jdbcTemplate.update("DELETE FROM hop_dong_thue WHERE ma_hop_dong=?", maHopDong);
    }
}
