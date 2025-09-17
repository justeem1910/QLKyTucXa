package org.example.controller;

import org.example.model.HoaDonDichVu;
import org.example.service.HoaDonDichVuService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/hoadondichvu")
public class HoaDonDichVuController {
    private final HoaDonDichVuService service;

    public HoaDonDichVuController(HoaDonDichVuService service) {
        this.service = service;
    }

    // Danh sách
    @GetMapping
    public String list(Model model) {
        List<HoaDonDichVu> list = service.getAll();
        model.addAttribute("list", list);
        return "hoadondichvu/list";
    }

    // Form thêm mới
    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("hddv", new HoaDonDichVu());
        return "hoadondichvu/form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute HoaDonDichVu hddv) {
        service.create(hddv);
        return "redirect:/hoadondichvu";
    }

    // Form sửa
    @GetMapping("/edit/{maHoaDon}/{maSinhVien}/{maDichVu}")
    public String editForm(@PathVariable String maHoaDon,
                           @PathVariable String maSinhVien,
                           @PathVariable String maDichVu,
                           Model model) {
        HoaDonDichVu hddv = service.getById(maHoaDon, maSinhVien, maDichVu);
        model.addAttribute("hddv", hddv);
        return "hoadondichvu/form";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute HoaDonDichVu hddv) {
        service.update(hddv);
        return "redirect:/hoadondichvu";
    }

    // Xóa
    @GetMapping("/delete/{maHoaDon}/{maSinhVien}/{maDichVu}")
    public String delete(@PathVariable String maHoaDon,
                         @PathVariable String maSinhVien,
                         @PathVariable String maDichVu) {
        service.delete(maHoaDon, maSinhVien, maDichVu);
        return "redirect:/hoadondichvu";
    }
}
