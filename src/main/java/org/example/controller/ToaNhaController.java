package org.example.controller;

import org.example.model.ToaNha;
import org.example.service.ToaNhaService;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/toanha")
public class ToaNhaController {
    private final ToaNhaService service;

    public ToaNhaController(ToaNhaService service) {
        this.service = service;
    }

    // Hiển thị danh sách sinh viên
    @GetMapping
    public String list(Model model, @RequestParam(value="error", required=false) String error) {
        model.addAttribute("listToaNha", service.getAll());
        model.addAttribute("error", error);
        return "toanha/list";
    }

    // Form thêm mới
    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("toaNha", new ToaNha());
        return "toanha/form";
    }

    // Form sửa
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Integer id, Model model) {
        ToaNha tn = service.getById(id);
        model.addAttribute("toaNha", tn);
        return "toanha/form";
    }

    // Xử lý lưu (thêm/sửa)
    @PostMapping("/save")
    public String save(@ModelAttribute ToaNha tn, Model model) {
        try {
            if (tn.getMaToa() == null) {
                service.create(tn); // thêm mới
            } else {
                service.update(tn); // cập nhật
            }
        } catch (DataAccessException ex) {
            // Nếu lỗi duplicate key
            String errorMsg = "Đã xảy ra lỗi!";
            model.addAttribute("error", errorMsg);
            model.addAttribute("toaNha", tn);
            return "toanha/form";
        }
        return "redirect:/toanha";
    }

    // Xóa
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        service.delete(id);
        return "redirect:/toanha";
    }
}
