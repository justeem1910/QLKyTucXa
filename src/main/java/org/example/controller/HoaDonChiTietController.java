package org.example.controller;

import org.example.model.HoaDonChiTiet;
import org.example.service.HoaDonChiTietService;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/hoadonchitiet")
public class HoaDonChiTietController {
    private final HoaDonChiTietService service;

    public HoaDonChiTietController(HoaDonChiTietService service) {
        this.service = service;
    }

    // Hiển thị danh sách sinh viên
    @GetMapping
    public String list(Model model, @RequestParam(value="error", required=false) String error) {
        model.addAttribute("listHoaDonChiTiet", service.getAll());
        model.addAttribute("error", error);
        return "hoadonchitiet/list";
    }

    // Form thêm mới
    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("hoaDonChiTiet", new HoaDonChiTiet());
        return "hoadonchitiet/form";
    }

    // Form sửa
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Integer id, Model model) {
        HoaDonChiTiet hdct = service.getById(id);
        model.addAttribute("hoaDonChiTiet", hdct);
        return "hoadonchitiet/form";
    }

    // Xử lý lưu (thêm/sửa)
    @PostMapping("/save")
    public String save(@ModelAttribute HoaDonChiTiet hdct, Model model) {
        try {
            if (hdct.getMaHdct() == null) {
                service.create(hdct); // thêm mới
            } else {
                service.update(hdct); // cập nhật
            }
        } catch (DataAccessException ex) {
            // Nếu lỗi duplicate key
            String errorMsg = "Hoá đơn chi tiết đã tồn tại";
            model.addAttribute("error", errorMsg);
            model.addAttribute("hoaDonChiTiet", hdct);
            return "hoadonchitiet/form";
        }
        return "redirect:/hoadonchitiet";
    }

    // Xóa
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        service.delete(id);
        return "redirect:/hoadonchitiet";
    }
}
