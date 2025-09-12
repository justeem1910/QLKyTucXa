package org.example.controller;

import org.example.model.GiaPhong;
import org.example.service.GiaPhongService;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/giaphong")
public class GiaPhongController {
    private final GiaPhongService service;

    public GiaPhongController(GiaPhongService service) {
        this.service = service;
    }

    // Hiển thị danh sách sinh viên
    @GetMapping
    public String list(Model model, @RequestParam(value="error", required=false) String error) {
        model.addAttribute("listGiaPhong", service.getAll());
        model.addAttribute("error", error);
        return "giaphong/list";
    }

    // Form thêm mới
    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("giaPhong", new GiaPhong());
        return "giaphong/form";
    }

    // Form sửa
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Integer id, Model model) {
        GiaPhong gp = service.getById(id);
        model.addAttribute("giaPhong", gp);
        return "giaphong/form";
    }

    // Xử lý lưu (thêm/sửa)
    @PostMapping("/save")
    public String save(@ModelAttribute GiaPhong gp, Model model) {
        try {
            if (gp.getMaGp() == null) {
                service.create(gp); // thêm mới
            } else {
                service.update(gp); // cập nhật
            }
        } catch (DataAccessException ex) {
            // Nếu lỗi duplicate key
            String errorMsg = "Giá phòng đã tồn tại";
            model.addAttribute("error", errorMsg);
            model.addAttribute("giaPhong", gp);
            return "giaphong/form";
        }
        return "redirect:/giaphong";
    }

    // Xóa
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        service.delete(id);
        return "redirect:/giaphong";
    }
}
