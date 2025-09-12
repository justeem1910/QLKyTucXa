package org.example.controller;

import org.example.model.LuotGuiXe;
import org.example.service.LuotGuiXeService;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/luotguixe")
public class LuotGuiXeController {
    private final LuotGuiXeService service;

    public LuotGuiXeController(LuotGuiXeService service) {
        this.service = service;
    }

    // Hiển thị danh sách sinh viên
    @GetMapping
    public String list(Model model, @RequestParam(value="error", required=false) String error) {
        model.addAttribute("listLuotGuiXe", service.getAll());
        model.addAttribute("error", error);
        return "luotguixe/list";
    }

    // Form thêm mới
    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("luotGuiXe", new LuotGuiXe());
        return "luotguixe/form";
    }

    // Form sửa
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Integer id, Model model) {
        LuotGuiXe lgx = service.getById(id);
        model.addAttribute("luotGuiXe", lgx);
        return "luotguixe/form";
    }

    // Xử lý lưu (thêm/sửa)
    @PostMapping("/save")
    public String save(@ModelAttribute LuotGuiXe lgx, Model model) {
        try {
            if (lgx.getMaLgx() == null) {
                service.create(lgx); // thêm mới
            } else {
                service.update(lgx); // cập nhật
            }
        } catch (DataAccessException ex) {
            // Nếu lỗi duplicate key
            String errorMsg = "Đã xảy ra lỗi!";
            model.addAttribute("error", errorMsg);
            model.addAttribute("luotGuiXe", lgx);
            return "luotguixe/form";
        }
        return "redirect:/luotguixe";
    }

    // Xóa
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        service.delete(id);
        return "redirect:/luotguixe";
    }
}
