package org.example.model;

import java.sql.Timestamp;

public class SuDungDichVu {
    private Integer id;
    private Integer idDv;
    private Integer idSv;
    private String bienSoXe;
    private Timestamp ngayBatDau;
    private Timestamp ngayKetThuc;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdDv() {
        return idDv;
    }

    public void setIdDv(Integer idDv) {
        this.idDv = idDv;
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

    public Timestamp getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(Timestamp ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public Timestamp getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(Timestamp ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }
    // getters & setters
}
