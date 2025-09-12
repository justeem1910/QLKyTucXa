package org.example.controller;

import org.example.model.SuDungDichVu;
import org.example.service.SuDungDichVuService;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/sudungdichvu")
public class SuDungDichVuController {
    private final SuDungDichVuService service;

    public SuDungDichVuController(SuDungDichVuService service) {
        this.service = service;
    }

    // Hiển thị danh sách sinh viên
    @GetMapping
    public String list(Model model, @RequestParam(value="error", required=false) String error) {
        model.addAttribute("listSuDungDichVu", service.getAll());
        model.addAttribute("error", error);
        return "sudungdichvu/list";
    }

    // Form thêm mới
    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("suDungDichVu", new SuDungDichVu());
        return "sudungdichvu/form";
    }

    // Form sửa
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Integer id, Model model) {
        SuDungDichVu sddv = service.getById(id);
        model.addAttribute("suDungDichVu", sddv);
        return "sudungdichvu/form";
    }

    // Xử lý lưu (thêm/sửa)
    @PostMapping("/save")
    public String save(@ModelAttribute SuDungDichVu sddv, Model model) {
        try {
            if (sddv.getMaSddv() == null) {
                service.create(sddv); // thêm mới
            } else {
                service.update(sddv); // cập nhật
            }
        } catch (DataAccessException ex) {
            // Nếu lỗi duplicate key
            String errorMsg = "Đã xảy ra lỗi!";
            model.addAttribute("error", errorMsg);
            model.addAttribute("suDungDichVu", sddv);
            return "sudungdichvu/form";
        }
        return "redirect:/sudungdichvu";
    }

    // Xóa
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        service.delete(id);
        return "redirect:/sudungdichvu";
    }
}
