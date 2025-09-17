package org.example.controller;

import org.example.model.HopDongThue;
import org.example.service.HopDongThueService;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/hopdongthue")
public class HopDongThueController {
    private final HopDongThueService service;

    public HopDongThueController(HopDongThueService service) {
        this.service = service;
    }

    // Hiển thị danh sách sinh viên
    @GetMapping
    public String list(Model model, @RequestParam(value="error", required=false) String error) {
        model.addAttribute("listHopDongThue", service.getAll());
        model.addAttribute("error", error);
        return "hopdongthue/list";
    }

    // Form thêm mới
    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("hopDongThue", new HopDongThue());
        return "hopdongthue/form";
    }

    // Form sửa
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable String id, Model model) {
        HopDongThue hdt = service.getById(id);
        model.addAttribute("hopDongThue", hdt);
        return "hopdongthue/form";
    }

    // Xử lý lưu (thêm/sửa)
    @PostMapping("/save")
    public String save(@ModelAttribute HopDongThue hdt, Model model) {
        try {
            if (hdt.getMaHopDong() == null) {
                service.create(hdt); // thêm mới
            } else {
                service.update(hdt); // cập nhật
            }
        } catch (DataAccessException ex) {
            // Nếu lỗi duplicate key
            String errorMsg = "Hợp đồng thuê đã tồn tại";
            model.addAttribute("error", errorMsg);
            model.addAttribute("hopDongThue", hdt);
            return "hopdongthue/form";
        }
        return "redirect:/hopdongthue";
    }

    // Xóa
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id) {
        service.delete(id);
        return "redirect:/hopdongthue";
    }
}
