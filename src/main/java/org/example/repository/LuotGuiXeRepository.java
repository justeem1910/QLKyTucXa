package org.example.repository;

import org.example.model.LuotGuiXe;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LuotGuiXeRepository {
    private final JdbcTemplate jdbcTemplate;
    public LuotGuiXeRepository(JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate; }

    public List<LuotGuiXe> findAll() {
        return jdbcTemplate.query("SELECT * FROM luot_gui_xe",
                (rs, rowNum) -> {
                    LuotGuiXe lgx = new LuotGuiXe();
                    lgx.setMaLgx(rs.getInt("ma_lgx"));
                    lgx.setIdSv(rs.getString("ma_sv"));
                    lgx.setBienSoXe(rs.getString("bien_so_xe"));
                    lgx.setThoiGianVao(rs.getTimestamp("thoi_gian_vao"));
                    lgx.setThoiGianRa(rs.getTimestamp("thoi_gian_ra"));
                    return lgx;
                });
    }

    public LuotGuiXe findById(Integer id) {
        return jdbcTemplate.queryForObject("SELECT * FROM luot_gui_xe WHERE ma_lgx=?",
                new Object[]{id},
                (rs, rowNum) -> {
                    LuotGuiXe lgx = new LuotGuiXe();
                    lgx.setMaLgx(rs.getInt("ma_lgx"));
                    lgx.setIdSv(rs.getString("ma_sv"));
                    lgx.setBienSoXe(rs.getString("bien_so_xe"));
                    lgx.setThoiGianVao(rs.getTimestamp("thoi_gian_vao"));
                    lgx.setThoiGianRa(rs.getTimestamp("thoi_gian_ra"));
                    return lgx;
                });
    }

    public int save(LuotGuiXe lgx) {
        return jdbcTemplate.update(
                "INSERT INTO luot_gui_xe(ma_sv,bien_so_xe,thoi_gian_vao,thoi_gian_ra) VALUES(?,?,?,?)",
                lgx.getIdSv(), lgx.getBienSoXe(), lgx.getThoiGianVao(), lgx.getThoiGianRa()
        );
    }

    public int update(LuotGuiXe lgx) {
        return jdbcTemplate.update(
                "UPDATE luot_gui_xe SET ma_sv=?, bien_so_xe=?, thoi_gian_vao=?, thoi_gian_ra=? WHERE ma_lgx=?",
                lgx.getIdSv(), lgx.getBienSoXe(), lgx.getThoiGianVao(), lgx.getThoiGianRa(), lgx.getMaLgx()
        );
    }

    public int delete(Integer id) {
        return jdbcTemplate.update("DELETE FROM luot_gui_xe WHERE ma_lgx=?", id);
    }
}
