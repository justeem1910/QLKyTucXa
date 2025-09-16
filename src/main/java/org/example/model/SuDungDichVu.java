package org.example.model;

import java.sql.Timestamp;

public class SuDungDichVu {
    private DichVu dichVu;
    private SinhVien sinhVien;
    private Timestamp ngaySuDung;

    public DichVu getDichVu() {
        return dichVu;
    }

    public void setDichVu(DichVu dichVu) {
        this.dichVu = dichVu;
    }

    public SinhVien getSinhVien() {
        return sinhVien;
    }

    public void setSinhVien(SinhVien sinhVien) {
        this.sinhVien = sinhVien;
    }

    public Timestamp getNgaySuDung() {
        return ngaySuDung;
    }

    public void setNgaySuDung(Timestamp ngaySuDung) {
        this.ngaySuDung = ngaySuDung;
    }
}
