package org.example.controller;

import org.example.model.HoaDon;
import org.example.service.HoaDonService;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/hoadon")
public class HoaDonController {
    private final HoaDonService service;

    public HoaDonController(HoaDonService service) {
        this.service = service;
    }

    // Hiển thị danh sách sinh viên
    @GetMapping
    public String list(Model model, @RequestParam(value="error", required=false) String error) {
        model.addAttribute("listHoaDon", service.getAll());
        model.addAttribute("error", error);
        return "hoadon/list";
    }

    // Form thêm mới
    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("hoaDon", new HoaDon());
        return "hoadon/form";
    }

    // Form sửa
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable String id, Model model) {
        HoaDon hd = service.getById(id);
        model.addAttribute("hoaDon", hd);
        return "hoadon/form";
    }

    // Xử lý lưu (thêm/sửa)
    @PostMapping("/save")
    public String save(@ModelAttribute HoaDon hd, Model model) {
        try {
            if (hd.getMaHoaDon() == null) {
                service.create(hd); // thêm mới
            } else {
                service.update(hd); // cập nhật
            }
        } catch (DataAccessException ex) {
            // Nếu lỗi duplicate key
            String errorMsg = "Hoá đơn đã tồn tại";
            model.addAttribute("error", errorMsg);
            model.addAttribute("hoaDon", hd);
            return "hoadon/form";
        }
        return "redirect:/hoadon";
    }

    // Xóa
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id) {
        service.delete(id);
        return "redirect:/hoadon";
    }
}
