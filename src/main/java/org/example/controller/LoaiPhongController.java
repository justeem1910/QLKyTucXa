package org.example.controller;

import org.example.model.LoaiPhong;
import org.example.service.LoaiPhongService;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/loaiphong")
public class LoaiPhongController {
    private final LoaiPhongService service;

    public LoaiPhongController(LoaiPhongService service) {
        this.service = service;
    }

    // Hiển thị danh sách sinh viên
    @GetMapping
    public String list(Model model, @RequestParam(value="error", required=false) String error) {
        model.addAttribute("listLoaiPhong", service.getAll());
        model.addAttribute("error", error);
        return "loaiphong/list";
    }

    // Form thêm mới
    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("loaiPhong", new LoaiPhong());
        return "loaiphong/form";
    }

    // Form sửa
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Integer id, Model model) {
        LoaiPhong lp = service.getById(id);
        model.addAttribute("loaiPhong", lp);
        return "loaiphong/form";
    }

    // Xử lý lưu (thêm/sửa)
    @PostMapping("/save")
    public String save(@ModelAttribute LoaiPhong lp, Model model) {
        try {
            if (lp.getMaLp() == null) {
                service.create(lp); // thêm mới
            } else {
                service.update(lp); // cập nhật
            }
        } catch (DataAccessException ex) {
            // Nếu lỗi duplicate key
            String errorMsg = "LoạiPhong đã tồn tại";
            model.addAttribute("error", errorMsg);
            model.addAttribute("loaiPhong", lp);
            return "loaiphong/form";
        }
        return "redirect:/loaiphong";
    }

    // Xóa
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        service.delete(id);
        return "redirect:/loaiphong";
    }
}
