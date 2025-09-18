package org.example.model;


import java.math.BigDecimal;
import java.util.List;

public class HoaDonChiTiet {
    private SinhVien sinhVien;
    private HoaDonThuePhong hoaDonThuePhong;

    private List<DichVuChiTiet> dichVuChiTiets;

    public SinhVien getSinhVien() {
        return sinhVien;
    }

    public void setSinhVien(SinhVien sinhVien) {
        this.sinhVien = sinhVien;
    }

    public HoaDonThuePhong getHoaDonThuePhong() {
        return hoaDonThuePhong;
    }

    public void setHoaDonThuePhong(HoaDonThuePhong hoaDonThuePhong) {
        this.hoaDonThuePhong = hoaDonThuePhong;
    }

    public List<DichVuChiTiet> getDichVuChiTiets() {
        return dichVuChiTiets;
    }

    public void setDichVuChiTiets(List<DichVuChiTiet> dichVuChiTiets) {
        this.dichVuChiTiets = dichVuChiTiets;
    }
}

