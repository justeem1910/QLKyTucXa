package org.example.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class HoaDon {
    private String maHoaDon;
    private LocalDate ngayTao;
    private BigDecimal tongTien;

    public HoaDon() {}

    public HoaDon(String maHoaDon, LocalDate ngayTao, BigDecimal tongTien) {
        this.maHoaDon = maHoaDon;
        this.ngayTao = ngayTao;
        this.tongTien = tongTien;
    }

    public String getMaHoaDon() { return maHoaDon; }
    public void setMaHoaDon(String maHoaDon) { this.maHoaDon = maHoaDon; }

    public LocalDate getNgayTao() { return ngayTao; }
    public void setNgayTao(LocalDate ngayTao) { this.ngayTao = ngayTao; }

    public BigDecimal getTongTien() { return tongTien; }
    public void setTongTien(BigDecimal tongTien) { this.tongTien = tongTien; }

    public void setNgayTao(LocalDateTime ngayTao) {
    }
}
