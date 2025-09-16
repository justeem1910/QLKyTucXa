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

    @GetMapping
    public String list(Model model) {
        model.addAttribute("listLoaiPhong", service.getAll());
        return "loaiphong/list";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("loaiPhong", new LoaiPhong());
        return "loaiphong/form";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable int id, Model model) {
        LoaiPhong lp = service.getById(id);
        model.addAttribute("loaiPhong", lp);
        return "loaiphong/form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute LoaiPhong lp) {
        if (lp.getMaLp() == 0) {
            service.create(lp);
        } else {
            service.update(lp);
        }
        return "redirect:/loaiphong";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        service.delete(id);
        return "redirect:/loaiphong";
    }
}
