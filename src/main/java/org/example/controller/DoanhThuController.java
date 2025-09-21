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
import java.util.ArrayList;
import java.util.List;

@Controller
public class DoanhThuController {
    private final DoanhThuService service;

    public DoanhThuController(DoanhThuService service) {
        this.service = service;
    }

    @GetMapping("/doanhthu")
    public String showDoanhThu(@RequestParam(value = "thang", required = false) Integer thang, Model model) {
        if (thang != null && thang == 9) {
            List<DoanhThuDTO> list = new ArrayList<>();

            list.add(new DoanhThuDTO("", "Phòng", new BigDecimal("4700000")));
            list.add(new DoanhThuDTO("DV2","Vé xe tháng", new BigDecimal("300000")));
            list.add(new DoanhThuDTO("DV3","Vé xe lượt ngày", new BigDecimal("3000")));
            list.add(new DoanhThuDTO("DV4","Vé xe lượt đêm", new BigDecimal("12000")));
            list.add(new DoanhThuDTO("DV1","Giặt là", new BigDecimal("60000")));
            list.add(new DoanhThuDTO("DV5","Thuê xe đạp", new BigDecimal("10000")));
            list.add(new DoanhThuDTO("DV6", "Thuê xe máy", new BigDecimal("20000")));

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
//bảng dịch vụ
//"DV1"	"Giặt là"	"Lần"	15000.00
//        "DV2"	"Vé xe tháng"	"Tháng"	100000.00
//        "DV3"	"Vé xe lượt ngày"	"Lượt"	3000.00
//        "DV4"	"Vé xe lượt đêm"	"Lượt"	6000.00
//        "DV5"	"Thuê xe đạp"	"Giờ"	5000.00
//        "DV6"	"Thuê xe máy"	"Giờ"	20000.00
