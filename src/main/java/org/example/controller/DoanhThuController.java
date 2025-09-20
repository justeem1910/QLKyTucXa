package org.example.controller;

import org.example.model.DoanhThuDTO;
import org.example.service.DoanhThuService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Controller
public class DoanhThuController {
    private final DoanhThuService service;

    public DoanhThuController(DoanhThuService service) {
        this.service = service;
    }

    @GetMapping("/doanhthu")
    public String showDoanhThu(@RequestParam(value = "thang", required = false) Integer thang, Model model) {
        if (thang != null) {
            // Lấy ngày bất kỳ trong tháng (ví dụ ngày 1)
            LocalDate date = LocalDate.of(LocalDate.now().getYear(), thang, 1);
            List<DoanhThuDTO> list = service.getDoanhThuTheoThang(Date.valueOf(date));

            BigDecimal tongDoanhThu = list.stream()
                    .map(DoanhThuDTO::getTongDoanhThu)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            model.addAttribute("doanhThuList", list);
            model.addAttribute("tongDoanhThu", tongDoanhThu);
            model.addAttribute("thang", thang);
        }
        return "doanhthu/list";
    }
}
