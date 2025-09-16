package org.example.model;

import java.math.BigDecimal;

public class GiaPhong {
    private LoaiPhong loaiPhong;
    private Integer blockGia;
    private BigDecimal giaTien;
    // getters & setters


    public LoaiPhong getLoaiPhong() {
        return loaiPhong;
    }

    public void setLoaiPhong(LoaiPhong loaiPhong) {
        this.loaiPhong = loaiPhong;
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

