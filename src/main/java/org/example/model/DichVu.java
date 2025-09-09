package org.example.model;

import java.math.BigDecimal;

public class DichVu {
    private Integer id;
    private String tenDichVu;
    private BigDecimal giaCoDinh;
    // getters & setters


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
