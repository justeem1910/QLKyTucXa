package org.example.model;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

public class LuotGuiXe {
    private Integer id;
    private Integer idSv;
    private String bienSoXe;
    private Timestamp thoiGianVao;
    private Timestamp thoiGianRa;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdSv() {
        return idSv;
    }

    public void setIdSv(Integer idSv) {
        this.idSv = idSv;
    }

    public String getBienSoXe() {
        return bienSoXe;
    }

    public void setBienSoXe(String bienSoXe) {
        this.bienSoXe = bienSoXe;
    }

    public Timestamp getThoiGianVao() {
        return thoiGianVao;
    }

    public void setThoiGianVao(Timestamp thoiGianVao) {
        this.thoiGianVao = thoiGianVao;
    }

    public Timestamp getThoiGianRa() {
        return thoiGianRa;
    }

    public void setThoiGianRa(Timestamp thoiGianRa) {
        this.thoiGianRa = thoiGianRa;
    }
}

