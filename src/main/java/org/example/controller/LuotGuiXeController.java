package org.example.controller;

import org.example.model.LuotGuiXe;
import org.example.service.LuotGuiXeService;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/luotguixe")
public class LuotGuiXeController {
    private final LuotGuiXeService service;

    public LuotGuiXeController(LuotGuiXeService service) {
        this.service = service;
    }

    // Hiển thị danh sách
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

    // Form sửa (truyền đủ 3 khóa chính)
    @GetMapping("/edit")
    public String editForm(@RequestParam String maSinhVien,
                           @RequestParam String bienSoXe,
                           @RequestParam String thoiGianVao,
                           Model model) {
        Timestamp ts = Timestamp.valueOf(thoiGianVao.replace("T", " ") + ":00");
        LuotGuiXe lgx = service.getById(maSinhVien, bienSoXe, ts);
        model.addAttribute("luotGuiXe", lgx);
        return "luotguixe/form";
    }

    // Xử lý lưu (thêm/sửa)
    @PostMapping("/save")
    public String save(@ModelAttribute LuotGuiXe lgx, Model model) {
        try {
            if (lgx.getThoiGianVao() == null) {
                lgx.setThoiGianVao(new Timestamp(System.currentTimeMillis()));
                service.create(lgx);
            } else {
                service.update(lgx);
            }
        } catch (DataAccessException ex) {
            String errorMsg = "Đã xảy ra lỗi khi lưu!";
            model.addAttribute("error", errorMsg);
            model.addAttribute("luotGuiXe", lgx);
            return "luotguixe/form";
        }
        return "redirect:/luotguixe";
    }

    // Xóa (truyền đủ 3 khóa chính)
    @GetMapping("/delete")
    public String delete(@RequestParam String maSinhVien,
                         @RequestParam String bienSoXe,
                         @RequestParam String thoiGianVao) {
        Timestamp ts = Timestamp.valueOf(thoiGianVao.replace("T", " ") + ":00");
        service.delete(maSinhVien, bienSoXe, ts);
        return "redirect:/luotguixe";
    }
}
