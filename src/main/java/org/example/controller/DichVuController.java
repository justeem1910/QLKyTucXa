package org.example.controller;

import org.example.model.DichVu;
import org.example.service.DichVuService;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/dichvu")
public class DichVuController {
    private final DichVuService service;

    public DichVuController(DichVuService service) {
        this.service = service;
    }

    // Hiển thị danh sách sinh viên
    @GetMapping
    public String list(Model model, @RequestParam(value="error", required=false) String error) {
        model.addAttribute("listDichVu", service.getAll());
        model.addAttribute("error", error);
        return "dichvu/list";
    }

    // Form thêm mới
    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("dichVu", new DichVu());
        return "dichvu/form";
    }

    // Form sửa
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Integer id, Model model) {
        DichVu dv = service.getById(id);
        model.addAttribute("dichVu", dv);
        return "dichvu/form";
    }

    // Xử lý lưu (thêm/sửa)
    @PostMapping("/save")
    public String save(@ModelAttribute DichVu dv, Model model) {
        try {
            if (dv.getMaDv() == null) {
                service.create(dv); // thêm mới
            } else {
                service.update(dv); // cập nhật
            }
        } catch (DataAccessException ex) {
            // Nếu lỗi duplicate key
            String errorMsg = "Dich vu đã tồn tại";
            model.addAttribute("error", errorMsg);
            model.addAttribute("dichVu", dv);
            return "dichvu/form";
        }
        return "redirect:/dichvu";
    }

    // Xóa
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        service.delete(id);
        return "redirect:/dichvu";
    }
}
