package org.example.repository;

import org.example.model.GiaPhong;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class GiaPhongRepository {
    private final JdbcTemplate jdbcTemplate;
    public GiaPhongRepository(JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate; }

    public List<GiaPhong> findAll() {
        return jdbcTemplate.query("SELECT * FROM gia_phong",
                (rs, rowNum) -> {
                    GiaPhong gp = new GiaPhong();
                    gp.setIdLoai(rs.getInt("ma_loai_phong"));
                    gp.setBlockGia(rs.getInt("block_gia"));
                    gp.setGiaTien(rs.getBigDecimal("gia_tien"));
                    return gp;
                });
    }

    public GiaPhong findById(String maDV, Integer blockGia) {
        return jdbcTemplate.queryForObject("SELECT * FROM gia_phong WHERE ma_loai_phong=? AND block_gia=?",
                new Object[]{maDV, blockGia},
                (rs, rowNum) -> {
                    GiaPhong gp = new GiaPhong();
                    gp.setIdLoai(rs.getInt("ma_loai_phong"));
                    gp.setBlockGia(rs.getInt("block_gia"));
                    gp.setGiaTien(rs.getBigDecimal("gia_tien"));
                    return gp;
                });
    }

    public int save(GiaPhong gp) {
        return jdbcTemplate.update(
                "INSERT INTO gia_phong(ma_loai_phong,block_gia,gia_tien) VALUES(?,?,?)",
                gp.getIdLoai(), gp.getBlockGia(), gp.getGiaTien()
        );
    }

    public int update(GiaPhong gp) {
        return jdbcTemplate.update(
                "UPDATE gia_phong SET gia_tien=? WHERE ma_loai_phong=? AND block_gia=?",
                gp.getGiaTien(), gp.getIdLoai(), gp.getBlockGia()
                );
    }

    public int delete(String maDV, Integer blockGia) {
        return jdbcTemplate.update("DELETE FROM gia_phong WHERE ma_loai_phong=? AND block_gia=?", maDV, blockGia);
    }
}
