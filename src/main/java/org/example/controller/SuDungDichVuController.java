package org.example.controller;

import org.example.model.SuDungDichVu;
import org.example.service.SuDungDichVuService;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/sudungdichvu")
public class SuDungDichVuController {
    private final SuDungDichVuService service;

    public SuDungDichVuController(SuDungDichVuService service) {
        this.service = service;
    }

    // Hiển thị danh sách
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

    // Form sửa (composite key)
    @GetMapping("/edit")
    public String editForm(@RequestParam String maDichVu,
                           @RequestParam String maSinhVien,
                           @RequestParam LocalDateTime ngaySuDung,
                           Model model) {
        SuDungDichVu sddv = service.getById(maDichVu, maSinhVien, ngaySuDung);
        model.addAttribute("suDungDichVu", sddv);
        return "sudungdichvu/form";
    }

    // Xử lý lưu (thêm/sửa)
    @PostMapping("/save")
    public String save(@ModelAttribute SuDungDichVu sddv, Model model) {
        try {
            service.create(sddv); // service sẽ tự xử lý insert/update
        } catch (DataAccessException ex) {
            String errorMsg = "Dữ liệu sử dụng dịch vụ đã tồn tại!";
            model.addAttribute("error", errorMsg);
            model.addAttribute("suDungDichVu", sddv);
            return "sudungdichvu/form";
        }
        return "redirect:/sudungdichvu";
    }

    // Xóa (composite key)
    @GetMapping("/delete")
    public String delete(@RequestParam String maDichVu,
                         @RequestParam String maSinhVien,
                         @RequestParam LocalDateTime ngaySuDung) {
        service.delete(maDichVu, maSinhVien, ngaySuDung);
        return "redirect:/sudungdichvu";
    }
}
