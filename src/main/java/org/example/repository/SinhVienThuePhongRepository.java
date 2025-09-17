package org.example.repository;

import org.example.model.*;
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
        String sql = """
            SELECT svtp.ma_sinh_vien, svtp.ma_hop_dong,
                   sv.ma_sinh_vien, sv.ten, sv.gioi_tinh, sv.ngay_sinh, sv.email, sv.so_dien_thoai,
                   hdt.ma_hop_dong, hdt.ngay_bat_dau, hdt.ngay_ket_thuc, hdt.ngay_thanh_ly,
                   p.ma_phong, p.ten_phong, p.suc_chua,
                   t.ma_toa_nha, t.ten_toa,
                   l.ma_loai_phong, l.ten_loai
            FROM sinh_vien_thue_phong svtp
            JOIN sinh_vien sv ON svtp.ma_sinh_vien = sv.ma_sinh_vien
            JOIN hop_dong_thue hdt ON svtp.ma_hop_dong = hdt.ma_hop_dong
            JOIN phong p ON hdt.ma_phong = p.ma_phong
            JOIN toa_nha t ON p.ma_toa_nha = t.ma_toa_nha
            JOIN loai_phong l ON p.ma_loai_phong = l.ma_loai_phong
            """;
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            SinhVienThuePhong svtp = new SinhVienThuePhong();

            SinhVien sv = new SinhVien();
            sv.setMaSv(rs.getString("ma_sinh_vien"));
            sv.setTen(rs.getString("ten"));
            sv.setGioiTinh(rs.getString("gioi_tinh"));
            sv.setNgaySinh(rs.getDate("ngay_sinh"));
            sv.setSoDienThoai(rs.getString("so_dien_thoai"));
            sv.setEmail(rs.getString("email"));

            HopDongThue hdt = new HopDongThue();
            hdt.setMaHopDong(rs.getInt("ma_hop_dong"));
            hdt.setNgayBatDau(rs.getDate("ngay_bat_dau"));
            hdt.setNgayKetThuc(rs.getDate("ngay_ket_thuc"));
            hdt.setNgayThanhLy(rs.getDate("ngay_thanh_ly"));

            Phong phong = new Phong();
            phong.setMaPhong(rs.getInt("ma_phong"));
            phong.setTenPhong(rs.getString("ten_phong"));
            phong.setSucChua(rs.getInt("suc_chua"));

            // Tạo tòa nhà
            ToaNha toa = new ToaNha();
            toa.setMaToa(rs.getInt("ma_toa_nha"));
            toa.setTenToa(rs.getString("ten_toa"));
            phong.setToaNha(toa);

            // Tạo loại phòng
            LoaiPhong loai = new LoaiPhong();
            loai.setMaLp(rs.getInt("ma_loai_phong"));
            loai.setTenLoai(rs.getString("ten_loai"));
            phong.setLoaiPhong(loai);

            hdt.setPhong(phong);

            svtp.setSinhVien(sv);
            svtp.setHopDongThue(hdt);

            return svtp;
        });
    }

    public SinhVienThuePhong findById(String maSv, Integer maHopDong) {
        String sql = """
            SELECT svtp.ma_sinh_vien, svtp.ma_hop_dong,
                   sv.ma_sinh_vien, sv.ten, sv.gioi_tinh, sv.ngay_sinh, sv.email, sv.so_dien_thoai,
                   hdt.ma_hop_dong, hdt.ngay_bat_dau, hdt.ngay_ket_thuc, hdt.ngay_thanh_ly,
                   p.ma_phong, p.ten_phong, p.suc_chua,
                   t.ma_toa_nha, t.ten_toa,
                   l.ma_loai_phong, l.ten_loai
            FROM sinh_vien_thue_phong svtp
            JOIN sinh_vien sv ON svtp.ma_sinh_vien = sv.ma_sinh_vien
            JOIN hop_dong_thue hdt ON svtp.ma_hop_dong = hdt.ma_hop_dong
            JOIN phong p ON hdt.ma_phong = p.ma_phong
            JOIN toa_nha t ON p.ma_toa_nha = t.ma_toa_nha
            JOIN loai_phong l ON p.ma_loai_phong = l.ma_loai_phong
            WHERE svtp.ma_sinh_vien=? AND svtp.ma_hop_dong=?
            """;
        return jdbcTemplate.queryForObject(sql, new Object[]{maSv, maHopDong}, (rs, rowNum) -> {
            SinhVienThuePhong svtp = new SinhVienThuePhong();

            SinhVien sv = new SinhVien();
            sv.setMaSv(rs.getString("ma_sinh_vien"));
            sv.setTen(rs.getString("ten"));
            sv.setGioiTinh(rs.getString("gioi_tinh"));
            sv.setNgaySinh(rs.getDate("ngay_sinh"));
            sv.setSoDienThoai(rs.getString("so_dien_thoai"));
            sv.setEmail(rs.getString("email"));

            HopDongThue hdt = new HopDongThue();
            hdt.setMaHopDong(rs.getInt("ma_hop_dong"));
            hdt.setNgayBatDau(rs.getDate("ngay_bat_dau"));
            hdt.setNgayKetThuc(rs.getDate("ngay_ket_thuc"));
            hdt.setNgayThanhLy(rs.getDate("ngay_thanh_ly"));

            Phong phong = new Phong();
            phong.setMaPhong(rs.getInt("ma_phong"));
            phong.setTenPhong(rs.getString("ten_phong"));
            phong.setSucChua(rs.getInt("suc_chua"));

            // Tạo tòa nhà
            ToaNha toa = new ToaNha();
            toa.setMaToa(rs.getInt("ma_toa_nha"));
            toa.setTenToa(rs.getString("ten_toa"));
            phong.setToaNha(toa);

            // Tạo loại phòng
            LoaiPhong loai = new LoaiPhong();
            loai.setMaLp(rs.getInt("ma_loai_phong"));
            loai.setTenLoai(rs.getString("ten_loai"));
            phong.setLoaiPhong(loai);

            hdt.setPhong(phong);

            svtp.setSinhVien(sv);
            svtp.setHopDongThue(hdt);

            return svtp;
        });
    }

    public int save(SinhVienThuePhong svtp) {
        return jdbcTemplate.update(
                "INSERT INTO sinh_vien_thue_phong(ma_sinh_vien, ma_hop_dong) VALUES(?, ?)",
                svtp.getSinhVien().getMaSv(),
                svtp.getHopDongThue().getMaHopDong()
        );
    }

    public int delete(String maSinhVien, Integer maHopDong) {
        return jdbcTemplate.update(
                "DELETE FROM sinh_vien_thue_phong WHERE ma_sinh_vien=? AND ma_hop_dong=?",
                maSinhVien, maHopDong
        );
    }
}
