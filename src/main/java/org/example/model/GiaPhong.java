package org.example.model;

import java.math.BigDecimal;

public class GiaPhong {
    private Integer id;
    private Integer idLoai;
    private Integer ngayBatDau;
    private Integer ngayKetThuc;
    private BigDecimal giaTien;
    // getters & setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdLoai() {
        return idLoai;
    }

    public void setIdLoai(Integer idLoai) {
        this.idLoai = idLoai;
    }

    public Integer getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(Integer ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public Integer getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(Integer ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public BigDecimal getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(BigDecimal giaTien) {
        this.giaTien = giaTien;
    }
}

