package org.example.model;


import java.math.BigDecimal;
import java.util.List;

public class HoaDonChiTiet {
    private SinhVien sinhVien;
    private String giaPhong;

    private List<DichVuChiTiet> dichVuChiTiets;

    public SinhVien getSinhVien() {
        return sinhVien;
    }

    public void setSinhVien(SinhVien sinhVien) {
        this.sinhVien = sinhVien;
    }

    public String getGiaPhong() {
        return giaPhong;
    }

    public void setGiaPhong(String giaPhong) {
        this.giaPhong = giaPhong;
    }

    public List<DichVuChiTiet> getDichVuChiTiets() {
        return dichVuChiTiets;
    }

    public void setDichVuChiTiets(List<DichVuChiTiet> dichVuChiTiets) {
        this.dichVuChiTiets = dichVuChiTiets;
    }
}

