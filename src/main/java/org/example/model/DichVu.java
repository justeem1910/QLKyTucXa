package org.example.model;

import java.math.BigDecimal;

public class DichVu {
    private String maDichVu;
    private String tenDichVu;
    private String donVi;
    private BigDecimal giaCoDinh;
    // getters & setters

    public String getMaDichVu() {
        return maDichVu;
    }

    public void setMaDichVu(String maDichVu) {
        this.maDichVu = maDichVu;
    }

    public String getTenDichVu() {
        return tenDichVu;
    }

    public void setTenDichVu(String tenDichVu) {
        this.tenDichVu = tenDichVu;
    }

    public String getDonVi() {
        return donVi;
    }

    public void setDonVi(String donVi) {
        this.donVi = donVi;
    }

    public BigDecimal getGiaCoDinh() {
        return giaCoDinh;
    }

    public void setGiaCoDinh(BigDecimal giaCoDinh) {
        this.giaCoDinh = giaCoDinh;
    }
}
