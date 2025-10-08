package org.example.controller;

import org.example.model.DangKyVeThang;
import org.example.model.SinhVien;
import org.example.service.DangKyVeThangService;
import org.springframework.dao.DataAccessException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;

@Controller
@RequestMapping("/dangkyvethang")
public class DangKyVeThangController {

    private final DangKyVeThangService service;

    public DangKyVeThangController(DangKyVeThangService service) {
        this.service = service;
    }

    // Hiển thị danh sách
    @GetMapping
    public String list(Model model, @RequestParam(value="error", required=false) String error) {
        model.addAttribute("listVeThang", service.getAll());
        model.addAttribute("error", error);
        return "dangkyvethang/list";
    }

    // Form thêm mới
    @GetMapping("/add")
    public String addForm(Model model) {
        DangKyVeThang veThang = new DangKyVeThang();
        veThang.setSinhVien(new SinhVien());
        model.addAttribute("dangKyVeThang", veThang);
        return "dangkyvethang/form";
    }

    // Form sửa (sử dụng khóa phức hợp)
    @GetMapping("/edit/{maSv}/{bienSoXe}/{ngayBatDau}")
    public String editForm(@PathVariable("maSv") String maSv,
                           @PathVariable("bienSoXe") String bienSoXe,
                           @PathVariable("ngayBatDau") Date ngayBatDau,
                           Model model) {
        DangKyVeThang vt = service.getById(maSv, bienSoXe, ngayBatDau);
        model.addAttribute("dangKyVeThang", vt);
        return "dangkyvethang/form";
    }

    // Xử lý lưu (thêm/sửa)
    @PostMapping("/save")
    public String save(@ModelAttribute DangKyVeThang vt, Model model) {
        try {
            if (!service.getById(vt.getSinhVien().getMaSv(), vt.getBienSoXe(), vt.getNgayBatDau()).getSinhVien().getMaSv().isEmpty()) {
                service.create(vt); // thêm mới
            } else {
                service.update(vt); // cập nhật
            }
        } catch (DataAccessException ex) {
            String errorMsg = "Vé tháng đã tồn tại";
            model.addAttribute("error", errorMsg);
            model.addAttribute("dangKyVeThang", vt);
            return "dangkyvethang/form";
        }
        return "redirect:/dangkyvethang";
    }

    // Xóa
    @GetMapping("/delete/{maSv}/{bienSoXe}/{ngayBatDau}")
    public String delete(@PathVariable("maSv") String maSv,
                         @PathVariable("bienSoXe") String bienSoXe,
                         @PathVariable("ngayBatDau") Date ngayBatDau) {
        service.delete(maSv, bienSoXe, ngayBatDau);
        return "redirect:/dangkyvethang";
    }
}
