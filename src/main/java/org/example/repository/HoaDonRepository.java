package org.example.repository;

import org.example.model.HoaDon;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public class HoaDonRepository {

    private final JdbcTemplate jdbcTemplate;

    public HoaDonRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<HoaDon> rowMapper = (rs, rowNum) -> {
        HoaDon hd = new HoaDon();
        hd.setMaHoaDon(rs.getString("ma_hoa_don"));
        hd.setNgayTao(rs.getDate("ngay_tao").toLocalDate());
        hd.setTongTien(rs.getBigDecimal("tong_tien"));
        return hd;
    };

    // Tạo hóa đơn mới, trigger tự sinh ma_hoa_don
    public String taoHoaDon(Date ngayTao) {
        String sql = "INSERT INTO hoa_don(ngay_tao, tong_tien) VALUES (?, 0) RETURNING ma_hoa_don";
        return jdbcTemplate.queryForObject(sql, new Object[]{ngayTao}, String.class);
    }

    // Lấy danh sách hóa đơn theo tháng
    public List<HoaDon> getHoaDonTheoThang(Date firstDayOfMonth) {
        String sql = "SELECT * FROM hoa_don " +
                "WHERE EXTRACT(YEAR FROM ngay_tao) = EXTRACT(YEAR FROM ?) " +
                "AND EXTRACT(MONTH FROM ngay_tao) = EXTRACT(MONTH FROM ?)";
        return jdbcTemplate.query(sql, new Object[]{firstDayOfMonth, firstDayOfMonth}, rowMapper);
    }

    // Cập nhật tổng tiền (nếu có function PostgreSQL)
    public void updateTongTien(String maHoaDon) {
        String sql = "SELECT update_invoice_total(?)";
        jdbcTemplate.queryForObject(sql, new Object[]{maHoaDon}, Void.class);
    }
}
