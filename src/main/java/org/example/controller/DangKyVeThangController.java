package org.example.controller;

import org.example.model.DangKyVeThang;
import org.example.service.DangKyVeThangService;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/dangkyvethang")
public class DangKyVeThangController {

    private final DangKyVeThangService service;

    public DangKyVeThangController(DangKyVeThangService service) {
        this.service = service;
    }

    // Hiển thị danh sách sinh viên
    @GetMapping
    public String list(Model model, @RequestParam(value="error", required=false) String error) {
        model.addAttribute("listDangKyVeThang", service.getAll());
        model.addAttribute("error", error);
        return "dangkyvethang/list";
    }

    // Form thêm mới
    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("dangKyVeThang", new DangKyVeThang());
        return "dangkyvethang/form";
    }

    // Form sửa
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Integer id, Model model) {
        DangKyVeThang vt = service.getById(id);
        model.addAttribute("dangKyVeThang", vt);
        return "dangkyvethang/form";
    }

    // Xử lý lưu (thêm/sửa)
    @PostMapping("/save")
    public String save(@ModelAttribute DangKyVeThang vt, Model model) {
        try {
            if (vt.getId() == null) {
                service.create(vt); // thêm mới
            } else {
                service.update(vt); // cập nhật
            }
        } catch (DataAccessException ex) {
            // Nếu lỗi duplicate key
            String errorMsg = "Vé tháng đã tồn tại";
            model.addAttribute("error", errorMsg);
            model.addAttribute("dangKyVeThang", vt);
            return "dangkyvethang/form";
        }
        return "redirect:/dangkyvethang";
    }

    // Xóa
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        service.delete(id);
        return "redirect:/dangkyvethang";
    }
}
