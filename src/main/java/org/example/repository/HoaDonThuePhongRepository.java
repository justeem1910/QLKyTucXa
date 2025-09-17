package org.example.repository;

import org.example.model.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HoaDonThuePhongRepository {
    private final JdbcTemplate jdbcTemplate;

    public HoaDonThuePhongRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Lấy tất cả hóa đơn thuê phòng kèm sinh viên, hợp đồng, hóa đơn
    public List<HoaDonThuePhong> findAll() {
        String sql = """
            SELECT hdtp.ma_sinh_vien, hdtp.ma_hop_dong, hdtp.ma_hoa_don, hdtp.tien_phong,
                   sv.ma_sinh_vien, sv.ten_sinh_vien, sv.ngay_sinh, sv.gioi_tinh, sv.email, sv.so_dien_thoai,
                   hd.ma_hoa_don, hd.ngay_tao, hd.tong_tien,
                   h.ma_hop_dong, h.ngay_bat_dau, h.ngay_ket_thuc, h.ngay_thanh_ly,
                   p.ma_phong, p.ten_phong, p.suc_chua,
                   t.ma_toa_nha, t.ten_toa,
                   l.ma_loai_phong, l.ten_loai
            FROM hoa_don_thue_phong hdtp
            JOIN sinh_vien sv ON hdtp.ma_sinh_vien = sv.ma_sinh_vien
            JOIN hoa_don hd ON hdtp.ma_hoa_don = hd.ma_hoa_don
            JOIN hop_dong_thue h ON hdtp.ma_hop_dong = h.ma_hop_dong
            JOIN phong p ON h.ma_phong = p.ma_phong
            JOIN toa_nha t ON p.ma_toa_nha = t.ma_toa_nha
            JOIN loai_phong l ON p.ma_loai_phong = l.ma_loai_phong
            """;

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            HoaDonThuePhong hdtp = new HoaDonThuePhong();

            // Sinh viên
            SinhVien sv = new SinhVien();
            sv.setMaSv(rs.getString("ma_sinh_vien"));
            sv.setTen(rs.getString("ten"));
            sv.setNgaySinh(rs.getDate("ngay_sinh"));
            sv.setGioiTinh(rs.getString("gioi_tinh"));
            sv.setSoDienThoai(rs.getString("so_dien_thoai"));
            sv.setEmail(rs.getString("email"));

            // Hóa đơn
            HoaDon hd = new HoaDon();
            hd.setMaHoaDon(rs.getInt("ma_hoa_don"));
            hd.setNgayTao(rs.getDate("ngay_tao"));
            hd.setTongTien(rs.getBigDecimal("tong_tien"));

            // Hợp đồng thuê
            HopDongThue hopDong = new HopDongThue();
            hopDong.setMaHopDong(rs.getInt("ma_hop_dong"));
            hopDong.setNgayBatDau(rs.getDate("ngay_bat_dau"));
            hopDong.setNgayKetThuc(rs.getDate("ngay_ket_thuc"));
            hopDong.setNgayThanhLy(rs.getDate("ngay_thanh_ly"));

            // Phòng
            Phong phong = new Phong();
            phong.setMaPhong(rs.getInt("ma_phong"));
            phong.setTenPhong(rs.getString("ten_phong"));
            phong.setSucChua(rs.getInt("suc_chua"));

            // Tòa nhà
            ToaNha toa = new ToaNha();
            toa.setMaToa(rs.getInt("ma_toa_nha"));
            toa.setTenToa(rs.getString("ten_toa"));
            phong.setToaNha(toa);

            // Loại phòng
            LoaiPhong loai = new LoaiPhong();
            loai.setMaLp(rs.getInt("ma_loai_phong"));
            loai.setTenLoai(rs.getString("ten_loai"));
            phong.setLoaiPhong(loai);

            hopDong.setPhong(phong);

            hdtp.setSinhVien(sv);
            hdtp.setHoaDon(hd);
            hdtp.setHopDongThue(hopDong);
            hdtp.setTienPhong(rs.getBigDecimal("tien_phong"));

            return hdtp;
        });
    }

    // Tìm theo id
    public HoaDonThuePhong findById(String maSinhVien, Integer maHopDong, Integer maHoaDon) {
        String sql = """
            SELECT hdtp.ma_sinh_vien, hdtp.ma_hop_dong, hdtp.ma_hoa_don, hdtp.tien_phong,
                   sv.ma_sinh_vien, sv.ten, sv.ngay_sinh, sv.gioi_tinh, sv.email, sv.so_dien_thoai,
                   hd.ma_hoa_don, hd.ngay_tao, hd.tong_tien,
                   h.ma_hop_dong, h.ngay_bat_dau, h.ngay_ket_thuc, h.ngay_thanh_ly,
                   p.ma_phong, p.ten_phong, p.suc_chua,
                   t.ma_toa_nha, t.ten_toa,
                   l.ma_loai_phong, l.ten_loai
            FROM hoa_don_thue_phong hdtp
            JOIN sinh_vien sv ON hdtp.ma_sinh_vien = sv.ma_sinh_vien
            JOIN hoa_don hd ON hdtp.ma_hoa_don = hd.ma_hoa_don
            JOIN hop_dong_thue h ON hdtp.ma_hop_dong = h.ma_hop_dong
            JOIN phong p ON h.ma_phong = p.ma_phong
            JOIN toa_nha t ON p.ma_toa_nha = t.ma_toa_nha
            JOIN loai_phong l ON p.ma_loai_phong = l.ma_loai_phong
            WHERE hdtp.ma_sinh_vien=? AND hdtp.ma_hop_dong=? AND hdtp.ma_hoa_don=?
            """;

        return jdbcTemplate.queryForObject(sql, new Object[]{maSinhVien, maHopDong, maHoaDon}, (rs, rowNum) -> {
            HoaDonThuePhong hdtp = new HoaDonThuePhong();

            SinhVien sv = new SinhVien();
            sv.setMaSv(rs.getString("ma_sinh_vien"));
            sv.setTen(rs.getString("ten"));
            sv.setNgaySinh(rs.getDate("ngay_sinh"));
            sv.setGioiTinh(rs.getString("gioi_tinh"));
            sv.setSoDienThoai(rs.getString("so_dien_thoai"));
            sv.setEmail(rs.getString("email"));

            HoaDon hd = new HoaDon();
            hd.setMaHoaDon(rs.getInt("ma_hoa_don"));
            hd.setNgayTao(rs.getDate("ngay_tao"));
            hd.setTongTien(rs.getBigDecimal("tong_tien"));

            HopDongThue hopDong = new HopDongThue();
            hopDong.setMaHopDong(rs.getInt("ma_hop_dong"));
            hopDong.setNgayBatDau(rs.getDate("ngay_bat_dau"));
            hopDong.setNgayKetThuc(rs.getDate("ngay_ket_thuc"));
            hopDong.setNgayThanhLy(rs.getDate("ngay_thanh_ly"));

            Phong phong = new Phong();
            phong.setMaPhong(rs.getInt("ma_phong"));
            phong.setTenPhong(rs.getString("ten_phong"));
            phong.setSucChua(rs.getInt("suc_chua"));

            ToaNha toa = new ToaNha();
            toa.setMaToa(rs.getInt("ma_toa_nha"));
            toa.setTenToa(rs.getString("ten_toa"));
            phong.setToaNha(toa);

            LoaiPhong loai = new LoaiPhong();
            loai.setMaLp(rs.getInt("ma_loai_phong"));
            loai.setTenLoai(rs.getString("ten_loai"));
            phong.setLoaiPhong(loai);

            hopDong.setPhong(phong);

            hdtp.setSinhVien(sv);
            hdtp.setHoaDon(hd);
            hdtp.setHopDongThue(hopDong);
            hdtp.setTienPhong(rs.getBigDecimal("tien_phong"));

            return hdtp;
        });
    }

    // Thêm mới
    public int save(HoaDonThuePhong hdtp) {
        String sql = "INSERT INTO hoa_don_thue_phong(ma_sinh_vien, ma_hop_dong, ma_hoa_don, tien_phong) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                hdtp.getSinhVien().getMaSv(),
                hdtp.getHopDongThue().getMaHopDong(),
                hdtp.getHoaDon().getMaHoaDon(),
                hdtp.getTienPhong());
    }

    // Cập nhật
    public int update(HoaDonThuePhong hdtp) {
        String sql = "UPDATE hoa_don_thue_phong SET tien_phong=? WHERE ma_sinh_vien=? AND ma_hop_dong=? AND ma_hoa_don=?";
        return jdbcTemplate.update(sql,
                hdtp.getTienPhong(),
                hdtp.getSinhVien().getMaSv(),
                hdtp.getHopDongThue().getMaHopDong(),
                hdtp.getHoaDon().getMaHoaDon());
    }

    // Xóa
    public int delete(String maSinhVien, Integer maHopDong, Integer maHoaDon) {
        String sql = "DELETE FROM hoa_don_thue_phong WHERE ma_sinh_vien=? AND ma_hop_dong=? AND ma_hoa_don=?";
        return jdbcTemplate.update(sql, maSinhVien, maHopDong, maHoaDon);
    }
}
