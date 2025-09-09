package org.example.model;

public class Phong {
    private Integer id;
    private String tenPhong;
    private Integer idToa;
    private Integer idLoai;
    private Integer sucChua;
    private Integer soNguoiDaThue;
    // getters & setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTenPhong() {
        return tenPhong;
    }

    public void setTenPhong(String tenPhong) {
        this.tenPhong = tenPhong;
    }

    public Integer getIdToa() {
        return idToa;
    }

    public void setIdToa(Integer idToa) {
        this.idToa = idToa;
    }

    public Integer getIdLoai() {
        return idLoai;
    }

    public void setIdLoai(Integer idLoai) {
        this.idLoai = idLoai;
    }

    public Integer getSucChua() {
        return sucChua;
    }

    public void setSucChua(Integer sucChua) {
        this.sucChua = sucChua;
    }

    public Integer getSoNguoiDaThue() {
        return soNguoiDaThue;
    }

    public void setSoNguoiDaThue(Integer soNguoiDaThue) {
        this.soNguoiDaThue = soNguoiDaThue;
    }
}
