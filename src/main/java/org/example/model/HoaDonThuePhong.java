package org.example.model;

import java.math.BigDecimal;

public class HoaDonThuePhong {
    private SinhVien sinhVien;
    private HopDongThue hopDongThue;
    private HoaDon hoaDon;
    private BigDecimal tienPhong;

    public SinhVien getSinhVien() {
        return sinhVien;
    }

    public void setSinhVien(SinhVien sinhVien) {
        this.sinhVien = sinhVien;
    }

    public HopDongThue getHopDongThue() {
        return hopDongThue;
    }

    public void setHopDongThue(HopDongThue hopDongThue) {
        this.hopDongThue = hopDongThue;
    }

    public HoaDon getHoaDon() {
        return hoaDon;
    }

    public void setHoaDon(HoaDon hoaDon) {
        this.hoaDon = hoaDon;
    }

    public BigDecimal getTienPhong() {
        return tienPhong;
    }

    public void setTienPhong(BigDecimal tienPhong) {
        this.tienPhong = tienPhong;
    }
}
