package org.example.controller;

import org.example.model.HoaDon;
import org.example.service.HoaDonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@Controller
@RequestMapping("/hoadon")
public class HoaDonController {

    private final HoaDonService hoaDonService;

    public HoaDonController(HoaDonService hoaDonService) {
        this.hoaDonService = hoaDonService;
    }

    @GetMapping
    public String showForm(Model model) {
        model.addAttribute("hoaDons", List.of());
        model.addAttribute("selectedMonth", "");
        return "hoadon/list";
    }

    @PostMapping("/tao")
    public String taoHoaDon(@RequestParam("thang") String thangStr, Model model) {
        try {
            YearMonth ym = YearMonth.parse(thangStr);
            LocalDate firstDay = ym.atDay(1);
            hoaDonService.taoHoaDonTheoThang(Date.valueOf(firstDay));

            List<HoaDon> hoaDons = hoaDonService.getHoaDonTheoThang(Date.valueOf(firstDay));
            model.addAttribute("hoaDons", hoaDons);
            model.addAttribute("selectedMonth", thangStr);
        } catch (Exception e) {
            model.addAttribute("hoaDons", List.of());
            model.addAttribute("selectedMonth", thangStr);
            model.addAttribute("error", "Lỗi khi tạo hóa đơn: " + e.getMessage());
        }
        return "hoadon/list";
    }
}
