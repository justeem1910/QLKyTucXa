package org.example.controller;

import org.example.model.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HoaDonChiTietController {

    @GetMapping("/hoadon/chitiet")
    public String viewHoaDonChiTiet(Model model) {
        SinhVien sv = new SinhVien();
        sv.setMaSv("SV1");
        sv.setTen("Nguyễn Văn A");

        HoaDon hoaDon = new HoaDon();
        hoaDon.setMaHoaDon("HDON1");
        hoaDon.setNgayTao(java.sql.Date.valueOf("2025-09-30"));

        HoaDonThuePhong thuePhong = new HoaDonThuePhong();
        thuePhong.setSinhVien(sv);
        thuePhong.setHoaDon(hoaDon);
        thuePhong.setTienPhong(new BigDecimal("1800000"));

        List<DichVuChiTiet> dichVuChiTiets = new ArrayList<>();

        // Vé tháng (2 vé, 200k) + phí phát sinh 3000
        HoaDonDichVu veThang = new HoaDonDichVu();
        DichVu dvVeThang = new DichVu("DV2", "Vé xe tháng", "Tháng", new BigDecimal("100000"));
        veThang.setDichVu(dvVeThang);
        veThang.setSoLuong(2);
        veThang.setPhiPhatSinh(new BigDecimal("3000")); // phí vượt
        DichVuChiTiet ctVeThang = new DichVuChiTiet();
        ctVeThang.setDichVu(veThang);
        ctVeThang.setTongTien(new BigDecimal("203000"));
        dichVuChiTiets.add(ctVeThang);

        // Xe không vé tháng: DV4
        HoaDonDichVu dem30x1 = new HoaDonDichVu();
        DichVu dvDem = new DichVu("DV4", "Vé xe lượt đêm", "Lượt", new BigDecimal("6000"));
        dem30x1.setDichVu(dvDem);
        dem30x1.setSoLuong(1);
        dem30x1.setPhiPhatSinh(BigDecimal.ZERO);
        DichVuChiTiet ctDem = new DichVuChiTiet();
        ctDem.setDichVu(dem30x1);
        ctDem.setTongTien(new BigDecimal("6000"));
        dichVuChiTiets.add(ctDem);

        // Giặt là
        HoaDonDichVu giatLa = new HoaDonDichVu();
        DichVu dvGiatLa = new DichVu("DV1", "Giặt là", "Lần", new BigDecimal("15000"));
        giatLa.setDichVu(dvGiatLa);
        giatLa.setSoLuong(3);
        giatLa.setPhiPhatSinh(BigDecimal.ZERO);
        DichVuChiTiet ctGiatLa = new DichVuChiTiet();
        ctGiatLa.setDichVu(giatLa);
        ctGiatLa.setTongTien(new BigDecimal("45000"));
        dichVuChiTiets.add(ctGiatLa);

        // Thuê xe đạp
        HoaDonDichVu xeDap = new HoaDonDichVu();
        DichVu dvXeDap = new DichVu("DV5", "Thuê xe đạp", "Giờ", new BigDecimal("5000"));
        xeDap.setDichVu(dvXeDap);
        xeDap.setSoLuong(1);
        xeDap.setPhiPhatSinh(BigDecimal.ZERO);
        DichVuChiTiet ctXeDap = new DichVuChiTiet();
        ctXeDap.setDichVu(xeDap);
        ctXeDap.setTongTien(new BigDecimal("5000"));
        dichVuChiTiets.add(ctXeDap);

        // Thuê xe máy
        HoaDonDichVu xeMay = new HoaDonDichVu();
        DichVu dvXeMay = new DichVu("DV6", "Thuê xe máy", "Giờ", new BigDecimal("20000"));
        xeMay.setDichVu(dvXeMay);
        xeMay.setSoLuong(1);
        xeMay.setPhiPhatSinh(BigDecimal.ZERO);
        DichVuChiTiet ctXeMay = new DichVuChiTiet();
        ctXeMay.setDichVu(xeMay);
        ctXeMay.setTongTien(new BigDecimal("20000"));
        dichVuChiTiets.add(ctXeMay);

        HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
        hoaDonChiTiet.setSinhVien(sv);
        hoaDonChiTiet.setHoaDonThuePhong(thuePhong);
        hoaDonChiTiet.setDichVuChiTiets(dichVuChiTiets);

        BigDecimal tong = new BigDecimal("2079000");
        hoaDon.setTongTien(tong);

        model.addAttribute("hoaDonChiTiet", hoaDonChiTiet);
        model.addAttribute("hoaDon", hoaDon);
        return "hoadon/chitiet";
    }
}
