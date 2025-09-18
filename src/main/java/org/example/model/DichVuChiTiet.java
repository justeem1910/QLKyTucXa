package org.example.model;

import java.math.BigDecimal;

public class DichVuChiTiet {
    private HoaDonDichVu dichVu;
    private BigDecimal tongTien;

    public HoaDonDichVu getDichVu() {
        return dichVu;
    }

    public void setDichVu(HoaDonDichVu dichVu) {
        this.dichVu = dichVu;
    }

    public BigDecimal getTongTien() {
        return tongTien;
    }

    public void setTongTien(BigDecimal tongTien) {
        this.tongTien = tongTien;
    }
}
