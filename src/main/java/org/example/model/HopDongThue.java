package org.example.model;

import java.sql.Date;

public class HopDongThue {
    private Integer maHdt;
    private String idSv;
    private Integer idPhong;
    private Date ngayBatDau;
    private Date ngayKetThuc;
    private Date ngayThanhLy;
    // getters & setters


    public Integer getMaHdt() {
        return maHdt;
    }

    public void setMaHdt(Integer maHdt) {
        this.maHdt = maHdt;
    }

    public String getIdSv() {
        return idSv;
    }

    public void setIdSv(String idSv) {
        this.idSv = idSv;
    }

    public Integer getIdPhong() {
        return idPhong;
    }

    public void setIdPhong(Integer idPhong) {
        this.idPhong = idPhong;
    }

    public Date getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(Date ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public Date getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(Date ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public Date getNgayThanhLy() {
        return ngayThanhLy;
    }

    public void setNgayThanhLy(Date ngayThanhLy) {
        this.ngayThanhLy = ngayThanhLy;
    }
}
