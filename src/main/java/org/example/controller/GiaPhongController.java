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

    @GetMapping
    public String list(Model model) {
        model.addAttribute("listGiaPhong", service.getAll());
        return "giaphong/list";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("giaPhong", new GiaPhong());
        return "giaphong/form";
    }

    @GetMapping("/edit")
    public String editForm(@RequestParam String maLoaiPhong, @RequestParam int blockGia, Model model) {
        GiaPhong gp = service.getById(maLoaiPhong, blockGia);
        model.addAttribute("giaPhong", gp);
        return "giaphong/form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute GiaPhong gp) {
        if (!service.getById(gp.getLoaiPhong().getMaLp(), gp.getBlockGia()).getLoaiPhong().getMaLp().isEmpty()) {
            service.update(gp);
        } else {
            service.create(gp);
        }
        return "redirect:/giaphong";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam String maLoaiPhong, @RequestParam int blockGia) {
        service.delete(maLoaiPhong, blockGia);
        return "redirect:/giaphong";
    }
}
