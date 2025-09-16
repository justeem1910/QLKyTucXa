package org.example.model;

import java.math.BigDecimal;

public class GiaPhong {
    private Integer idLoai;
    private Integer blockGia;
    private BigDecimal giaTien;
    // getters & setters

    public Integer getIdLoai() {
        return idLoai;
    }

    public void setIdLoai(Integer idLoai) {
        this.idLoai = idLoai;
    }

    public Integer getBlockGia() {
        return blockGia;
    }

    public void setBlockGia(Integer blockGia) {
        this.blockGia = blockGia;
    }

    public BigDecimal getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(BigDecimal giaTien) {
        this.giaTien = giaTien;
    }
}

