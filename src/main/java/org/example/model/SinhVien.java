package org.example.model;

import java.sql.Date;

public class SinhVien {
    private String ma_sv;
    private String ten;
    private String gioiTinh;
    private Date ngaySinh;
    private String soDienThoai;
    private String email;
    // getters & setters

    public String getMaSv() { return ma_sv; }
    public void setMaSv(String ma_sv) { this.ma_sv = ma_sv; }

    public String getTen() { return ten; }
    public void setTen(String ten) { this.ten = ten; }

    public Date getNgaySinh() { return ngaySinh; }
    public void setNgaySinh(Date ngaySinh) { this.ngaySinh = ngaySinh; }

    public String getGioiTinh() { return gioiTinh; }
    public void setGioiTinh(String gioiTinh) { this.gioiTinh = gioiTinh; }

    public String getSoDienThoai() { return soDienThoai; }
    public void setSoDienThoai(String soDienThoai) { this.soDienThoai = soDienThoai; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
