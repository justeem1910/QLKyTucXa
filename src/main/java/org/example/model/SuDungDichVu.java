package org.example.model;

import java.sql.Timestamp;

public class SuDungDichVu {
    private Integer idDv;
    private String idSv;
    private Timestamp ngayBatDau;
    private Timestamp ngayKetThuc;

    public Integer getIdDv() {
        return idDv;
    }

    public void setIdDv(Integer idDv) {
        this.idDv = idDv;
    }

    public String getIdSv() {
        return idSv;
    }

    public void setIdSv(String idSv) {
        this.idSv = idSv;
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
