package org.example.model;

import java.math.BigDecimal;

public class DichVu {
    private Integer maDv;
    private String tenDichVu;
    private BigDecimal giaCoDinh;
    // getters & setters


    public Integer getMaDv() {
        return maDv;
    }

    public void setMaDv(Integer maDv) {
        this.maDv = maDv;
    }

    public String getTenDichVu() {
        return tenDichVu;
    }

    public void setTenDichVu(String tenDichVu) {
        this.tenDichVu = tenDichVu;
    }

    public BigDecimal getGiaCoDinh() {
        return giaCoDinh;
    }

    public void setGiaCoDinh(BigDecimal giaCoDinh) {
        this.giaCoDinh = giaCoDinh;
    }
}
