package org.example.model;

import java.math.BigDecimal;

public class HoaDonDichVu {
    private HoaDon hoaDon;
    private SinhVien sinhVien;
    private DichVu dichVu;
    private Integer soLuong;
    private BigDecimal phiPhatSinh;

    public HoaDon getHoaDon() {
        return hoaDon;
    }

    public void setHoaDon(HoaDon hoaDon) {
        this.hoaDon = hoaDon;
    }

    public SinhVien getSinhVien() {
        return sinhVien;
    }

    public void setSinhVien(SinhVien sinhVien) {
        this.sinhVien = sinhVien;
    }

    public DichVu getDichVu() {
        return dichVu;
    }

    public void setDichVu(DichVu dichVu) {
        this.dichVu = dichVu;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    public BigDecimal getPhiPhatSinh() {
        return phiPhatSinh;
    }

    public void setPhiPhatSinh(BigDecimal phiPhatSinh) {
        this.phiPhatSinh = phiPhatSinh;
    }
}
