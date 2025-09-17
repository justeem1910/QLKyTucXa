package org.example.controller;

import org.example.model.SinhVienThuePhong;
import org.example.model.SinhVien;
import org.example.model.HopDongThue;
import org.example.service.SinhVienThuePhongService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/sinhvienthuephong")
public class SinhVienThuePhongController {
    private final SinhVienThuePhongService service;

    public SinhVienThuePhongController(SinhVienThuePhongService service) {
        this.service = service;
    }

    // Hiển thị danh sách
    @GetMapping
    public String list(Model model) {
        List<SinhVienThuePhong> list = service.getAll();
        model.addAttribute("list", list);
        return "sinhvienthuephong/list";
    }

    // Form thêm mới
    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("svtp", new SinhVienThuePhong());
        return "sinhvienthuephong/form";
    }

    // Lưu khi thêm
    @PostMapping("/save")
    public String save(@ModelAttribute SinhVienThuePhong svtp) {
        service.create(svtp);
        return "redirect:/sinhvienthuephong";
    }

    // Xóa
    @GetMapping("/delete/{maSv}/{maHd}")
    public String delete(@PathVariable("maSv") String maSv,
                         @PathVariable("maHd") String maHd) {
        service.delete(maSv, maHd);
        return "redirect:/sinhvienthuephong";
    }
}
