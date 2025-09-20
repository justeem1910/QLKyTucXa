package org.example.repository;

import org.example.model.DoanhThuDTO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public class DoanhThuRepository {
    private final JdbcTemplate jdbcTemplate;

    public DoanhThuRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<DoanhThuDTO> tinhDoanhThuTheoThang(Date thang) {
        String sql = "SELECT * FROM tinh_doanh_thu_dich_vu_thang(?)";
        return jdbcTemplate.query(sql, new Object[]{thang}, (rs, rowNum) -> {
            DoanhThuDTO dto = new DoanhThuDTO();
            dto.setMaDichVu(rs.getString("ma_dich_vu"));
            dto.setTenDichVu(rs.getString("ten_dich_vu"));
            dto.setTongDoanhThu(rs.getBigDecimal("tong_doanh_thu"));
            return dto;
        });
    }
}
