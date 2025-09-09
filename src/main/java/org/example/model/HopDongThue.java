package org.example.model;

import java.sql.Date;

public class HopDongThue {
    private Integer id;
    private Integer idSv;
    private Integer idPhong;
    private Date ngayBatDau;
    private Date ngayKetThuc;
    private Date ngayThanhLy;
    // getters & setters


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdSv() {
        return idSv;
    }

    public void setIdSv(Integer idSv) {
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
