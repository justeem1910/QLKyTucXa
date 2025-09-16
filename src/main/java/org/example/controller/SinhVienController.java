package org.example.controller;

import org.example.model.SinhVien;
import org.example.service.SinhVienService;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/sinhvien")
public class SinhVienController {

    private final SinhVienService service;

    public SinhVienController(SinhVienService service) {
        this.service = service;
    }

    // Hiển thị danh sách sinh viên
    @GetMapping
    public String list(Model model, @RequestParam(value="error", required=false) String error) {
        model.addAttribute("listSV", service.getAll());
        model.addAttribute("error", error);
        return "sinhvien/list";
    }

    // Form thêm mới
    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("sinhVien", new SinhVien());
        return "sinhvien/form";
    }

    // Form sửa
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable String id, Model model) {
        SinhVien sv = service.getById(id);
        model.addAttribute("sinhVien", sv);
        return "sinhvien/form";
    }

    // Xử lý lưu (thêm/sửa)
    @PostMapping("/save")
    public String save(@ModelAttribute SinhVien sv, Model model) {
        try {
            if (sv.getMaSv() == null) {
                service.create(sv); // thêm mới
            } else {
                service.update(sv); // cập nhật
            }
        } catch (DataAccessException ex) {
            // Nếu lỗi duplicate key
            String errorMsg = "Sinh viên đã tồn tại (trùng mã SV, điện thoại hoặc email)";
            model.addAttribute("error", errorMsg);
            model.addAttribute("sinhVien", sv);
            return "sinhvien/form";
        }
        return "redirect:/sinhvien";
    }

    // Xóa
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id) {
        service.delete(id);
        return "redirect:/sinhvien";
    }
}
