package org.example.model;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

public class LuotGuiXe {
    private SinhVien sinhVien;
    private String bienSoXe;
    private Timestamp thoiGianVao;
    private Timestamp thoiGianRa;

    public SinhVien getSinhVien() {
        return sinhVien;
    }

    public void setSinhVien(SinhVien sinhVien) {
        this.sinhVien = sinhVien;
    }

    public String getBienSoXe() {
        return bienSoXe;
    }

    public void setBienSoXe(String bienSoXe) {
        this.bienSoXe = bienSoXe;
    }

    public Timestamp getThoiGianVao() {
        return thoiGianVao;
    }

    public void setThoiGianVao(Timestamp thoiGianVao) {
        this.thoiGianVao = thoiGianVao;
    }

    public Timestamp getThoiGianRa() {
        return thoiGianRa;
    }

    public void setThoiGianRa(Timestamp thoiGianRa) {
        this.thoiGianRa = thoiGianRa;
    }
}

