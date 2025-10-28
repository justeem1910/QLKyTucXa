package org.example.model;

import java.time.LocalDateTime;

public class SuDungDichVu {
    private DichVu dichVu;
    private SinhVien sinhVien;
    private LocalDateTime ngaySuDung;

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

    public LocalDateTime getNgaySuDung() {
        return ngaySuDung;
    }

    public void setNgaySuDung(LocalDateTime ngaySuDung) {
        this.ngaySuDung = ngaySuDung;
    }
}
