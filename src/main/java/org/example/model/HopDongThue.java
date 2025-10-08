package org.example.model;

import java.sql.Date;

public class HopDongThue {
//    private String idSv;
    private String maHopDong;
    private Phong phong;
    private Date ngayBatDau;
    private Date ngayKetThuc;
    private Date ngayThanhLy;
    // getters & setters

    public String getMaHopDong() {
        return maHopDong;
    }

    public void setMaHopDong(String maHopDong) {
        this.maHopDong = maHopDong;
    }

    public Phong getPhong() {
        return phong;
    }

    public void setPhong(Phong phong) {
        this.phong = phong;
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
