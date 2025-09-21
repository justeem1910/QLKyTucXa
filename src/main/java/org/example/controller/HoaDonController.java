package org.example.controller;

import org.example.model.HoaDon;
import org.example.service.HoaDonService;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/hoadon")
public class HoaDonController {
    private final HoaDonService service;

    public HoaDonController(HoaDonService service) {
        this.service = service;
    }

    @GetMapping
    public String list(Model model, @RequestParam(value="error", required=false) String error) {
        model.addAttribute("listHoaDon", service.getAll());
        model.addAttribute("error", error);
        return "hoadon/list";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("hoaDon", new HoaDon());
        return "hoadon/form";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable String id, Model model) {
        HoaDon hd = service.getById(id);
        model.addAttribute("hoaDon", hd);
        return "hoadon/form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute HoaDon hd, Model model) {
        try {
            if (hd.getMaHoaDon() == null || hd.getMaHoaDon().isEmpty()) {
                service.create(hd);
            } else {
                service.update(hd);
            }
        } catch (DataAccessException ex) {
            model.addAttribute("error", "Hoá đơn đã tồn tại");
            model.addAttribute("hoaDon", hd);
            return "hoadon/form";
        }
        return "redirect:/hoadon";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id) {
        service.delete(id);
        return "redirect:/hoadon";
    }
}
