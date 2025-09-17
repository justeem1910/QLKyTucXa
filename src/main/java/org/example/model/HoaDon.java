package org.example.model;

import java.math.BigDecimal;
import java.sql.Date;

public class HoaDon {
    private String maHoaDon;
//    private String idSv;
    private Date ngayTao;
    private BigDecimal tongTien;

//    public String getIdSv() {
//        return idSv;
//    }
//
//    public void setIdSv(String idSv) {
//        this.idSv = idSv;
//    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public BigDecimal getTongTien() {
        return tongTien;
    }

    public void setTongTien(BigDecimal tongTien) {
        this.tongTien = tongTien;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }
    // getters & setters
}

