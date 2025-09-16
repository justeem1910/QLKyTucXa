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

    // Hiển thị danh sách
    @GetMapping
    public String list(Model model, @RequestParam(value="error", required=false) String error) {
        model.addAttribute("listTN", service.getAll());
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
    public String editForm(@PathVariable int id, Model model) {
        ToaNha tn = service.getById(id);
        model.addAttribute("toaNha", tn);
        return "toanha/form";
    }

    // Lưu (thêm/sửa)
    @PostMapping("/save")
    public String save(@ModelAttribute ToaNha tn, Model model) {
        try {
            if (tn.getMaToa() == 0) {
                service.create(tn);
            } else {
                service.update(tn);
            }
        } catch (DataAccessException ex) {
            model.addAttribute("error", "Lỗi dữ liệu, có thể trùng tên tòa nhà");
            model.addAttribute("toaNha", tn);
            return "toanha/form";
        }
        return "redirect:/toanha";
    }

    // Xóa
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        service.delete(id);
        return "redirect:/toanha";
    }
}
