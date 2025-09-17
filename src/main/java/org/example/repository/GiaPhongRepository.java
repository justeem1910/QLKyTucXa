package org.example.repository;

import org.example.model.GiaPhong;
import org.example.model.LoaiPhong;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class GiaPhongRepository {
    private final JdbcTemplate jdbcTemplate;
    public GiaPhongRepository(JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate; }

    public List<GiaPhong> findAll() {
        String sql = """
            SELECT gp.ma_loai_phong, gp.block_gia, gp.gia_tien,
                   l.ma_loai_phong, l.ten_loai
            FROM gia_phong gp
            JOIN loai_phong l ON gp.ma_loai_phong = l.ma_loai_phong
            """;
        return jdbcTemplate.query(sql,
                (rs, rowNum) -> {
                    GiaPhong gp = new GiaPhong();
                    gp.setBlockGia(rs.getInt("block_gia"));
                    gp.setGiaTien(rs.getBigDecimal("gia_tien"));

                    LoaiPhong lp = new LoaiPhong();
                    lp.setMaLp(rs.getString("ma_loai_phong"));
                    lp.setTenLoai(rs.getString("ten_loai"));

                    gp.setLoaiPhong(lp);
                    return gp;
                });
    }

    public GiaPhong findById(String maLp, Integer blockGia) {
        String sql = """
            SELECT gp.ma_loai_phong, gp.block_gia, gp.gia_tien,
                   l.ma_loai_phong, l.ten_loai
            FROM gia_phong gp
            JOIN loai_phong l ON gp.ma_loai_phong = l.ma_loai_phong
            WHERE gp.ma_loai_phong=? AND gp.block_gia=?
            """;
        return jdbcTemplate.queryForObject(sql,
                new Object[]{maLp, blockGia},
                (rs, rowNum) -> {
                    GiaPhong gp = new GiaPhong();
                    gp.setBlockGia(rs.getInt("block_gia"));
                    gp.setGiaTien(rs.getBigDecimal("gia_tien"));

                    LoaiPhong lp = new LoaiPhong();
                    lp.setMaLp(rs.getString("ma_loai_phong"));
                    lp.setTenLoai(rs.getString("ten_loai"));

                    gp.setLoaiPhong(lp);
                    return gp;
                });
    }

    public int save(GiaPhong gp) {
        return jdbcTemplate.update(
                "INSERT INTO gia_phong(ma_loai_phong,block_gia,gia_tien) VALUES(?,?,?)",
                gp.getLoaiPhong().getMaLp(), gp.getBlockGia(), gp.getGiaTien()
        );
    }

    public int update(GiaPhong gp) {
        return jdbcTemplate.update(
                "UPDATE gia_phong SET gia_tien=? WHERE ma_loai_phong=? AND block_gia=?",
                gp.getGiaTien(), gp.getLoaiPhong().getMaLp(), gp.getBlockGia()
                );
    }

    public int delete(String maLp, Integer blockGia) {
        return jdbcTemplate.update("DELETE FROM gia_phong WHERE ma_loai_phong=? AND block_gia=?", maLp, blockGia);
    }
}
