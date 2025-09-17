package org.example.model;

public class Phong {
    private String maPhong;
    private String tenPhong;
    private ToaNha toaNha;
    private LoaiPhong loaiPhong;
    private Integer sucChua;

    public String getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(String maPhong) {
        this.maPhong = maPhong;
    }

    public String getTenPhong() {
        return tenPhong;
    }

    public void setTenPhong(String tenPhong) {
        this.tenPhong = tenPhong;
    }

    public ToaNha getToaNha() {
        return toaNha;
    }

    public void setToaNha(ToaNha toaNha) {
        this.toaNha = toaNha;
    }

    public LoaiPhong getLoaiPhong() {
        return loaiPhong;
    }

    public void setLoaiPhong(LoaiPhong loaiPhong) {
        this.loaiPhong = loaiPhong;
    }

    public Integer getSucChua() {
        return sucChua;
    }

    public void setSucChua(Integer sucChua) {
        this.sucChua = sucChua;
    }

}
