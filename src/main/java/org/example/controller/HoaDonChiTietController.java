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
        // Fake data (bạn thay bằng service lấy từ DB)
        SinhVien sv = new SinhVien();
        sv.setMaSv("SV01");
        sv.setTen("Nguyễn Văn A");

        HoaDon hoaDon = new HoaDon();
        hoaDon.setMaHoaDon("HD01");
        hoaDon.setNgayTao(java.sql.Date.valueOf("2025-09-30"));

        HoaDonThuePhong thuePhong = new HoaDonThuePhong();
        thuePhong.setSinhVien(sv);
        thuePhong.setHoaDon(hoaDon);
        thuePhong.setTienPhong(new BigDecimal("1500000"));

        // Dịch vụ chi tiết
        List<DichVuChiTiet> dichVuChiTiets = new ArrayList<>();

        // Vé xe tháng
        HoaDonDichVu dv1 = new HoaDonDichVu();
        dv1.setDichVu(new DichVu("DV01", "Vé xe tháng", "1 vé", new BigDecimal("100000")));
        dv1.setSoLuong(2);
        dv1.setPhiPhatSinh(new BigDecimal("5000"));

        DichVuChiTiet ct1 = new DichVuChiTiet();
        ct1.setDichVu(dv1);
        ct1.setTongTien(new BigDecimal("205000"));
        dichVuChiTiets.add(ct1);

        // Vé xe lượt
        HoaDonDichVu dv2 = new HoaDonDichVu();
        dv2.setDichVu(new DichVu("DV02", "Vé xe lượt", "1 vé", new BigDecimal("3000")));
        dv2.setSoLuong(10);
        dv2.setPhiPhatSinh(BigDecimal.ZERO);

        DichVuChiTiet ct2 = new DichVuChiTiet();
        ct2.setDichVu(dv2);
        ct2.setTongTien(new BigDecimal("30000"));
        dichVuChiTiets.add(ct2);

        // Gom thành hóa đơn chi tiết
        HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
        hoaDonChiTiet.setSinhVien(sv);
        hoaDonChiTiet.setHoaDonThuePhong(thuePhong);
        hoaDonChiTiet.setDichVuChiTiets(dichVuChiTiets);

        model.addAttribute("hoaDonChiTiet", hoaDonChiTiet);
        return "hoadon/chitiet";
    }
}
