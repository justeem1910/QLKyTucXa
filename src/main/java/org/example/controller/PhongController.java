package org.example.controller;

import org.example.model.Phong;
import org.example.service.PhongService;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/phong")
public class PhongController {
    private final PhongService service;

    public PhongController(PhongService service) {
        this.service = service;
    }

    // Hiển thị danh sách sinh viên
    @GetMapping
    public String list(Model model, @RequestParam(value="error", required=false) String error) {
        model.addAttribute("listPhong", service.getAll());
        model.addAttribute("error", error);
        return "phong/list";
    }

    // Form thêm mới
    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("Phong", new Phong());
        return "phong/form";
    }

    // Form sửa
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable String id, Model model) {
        Phong p = service.getById(id);
        model.addAttribute("Phong", p);
        return "phong/form";
    }

    // Xử lý lưu (thêm/sửa)
    @PostMapping("/save")
    public String save(@ModelAttribute Phong p, Model model) {
        try {
            if (p.getMaPhong().isEmpty()) {
                service.create(p); // thêm mới
            } else {
                service.update(p); // cập nhật
            }
        } catch (DataAccessException ex) {
            // Nếu lỗi duplicate key
            String errorMsg = "Đã xảy ra lỗi!";
            model.addAttribute("error", errorMsg);
            model.addAttribute("Phong", p);
            return "phong/form";
        }
        return "redirect:/phong";
    }

    // Xóa
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id) {
        service.delete(id);
        return "redirect:/phong";
    }
}
