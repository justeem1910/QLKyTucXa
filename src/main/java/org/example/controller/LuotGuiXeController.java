package org.example.controller;

import org.example.model.LuotGuiXe;
import org.example.model.SinhVien;
import org.example.service.LuotGuiXeService;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
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
        SinhVien sv = new SinhVien();
        LuotGuiXe lgx = new LuotGuiXe();
        lgx.setSinhVien(sv);
        model.addAttribute("luotGuiXe", lgx);
        return "luotguixe/form";
    }

    // Form sửa (truyền đủ 3 khóa chính)
    @GetMapping("/edit")
    public String editForm(@RequestParam String maSinhVien,
                           @RequestParam String bienSoXe,
                           @RequestParam LocalDateTime thoiGianVao,
                           Model model) {
//        Timestamp ts = Timestamp.valueOf(thoiGianVao);
        LuotGuiXe lgx = service.getById(maSinhVien, bienSoXe);
        model.addAttribute("luotGuiXe", lgx);
        return "luotguixe/form";
    }

    // Xử lý lưu (thêm/sửa)
    @PostMapping("/save")
    public String save(@ModelAttribute LuotGuiXe luotGuiXe, Model model) {
        try {
            System.out.println("aaaaaaaaaaaaa "  + luotGuiXe.getSinhVien().getMaSv());
            LuotGuiXe luotGuiXe1 = service.getById(luotGuiXe.getSinhVien().getMaSv(), luotGuiXe.getBienSoXe());
            if (luotGuiXe1 == null) {
                service.create(luotGuiXe);
            } else if (luotGuiXe1.getThoiGianRa() == null) {
                luotGuiXe1.setThoiGianRa(luotGuiXe.getThoiGianVao());
                service.update(luotGuiXe1);
            }
        } catch (DataAccessException ex) {
            service.create(luotGuiXe);
        }
        return "redirect:/luotguixe";
    }

//    @PostMapping("/save")
//    public String save(
//            @RequestParam String maSinhVien,
//            @RequestParam  String bienSoXe,
//            @RequestParam String thoiGianVao,
//            Model model) {
//        try {
//            System.out.println("aaaaaaaaaaaaa "  + thoiGianVao);
//            // Convert datetime-local string -> Timestamp
//            Timestamp timestampVao = Timestamp.valueOf(thoiGianVao);
//
//            // Kiểm tra xem lượt này đã tồn tại chưa
//            LuotGuiXe existing = null;
//            try {
//                existing = service.getById(maSinhVien, bienSoXe, timestampVao);
//            } catch (Exception ignored) {}
//
//            if (existing == null) {
//                // Chưa có → thêm mới
//                LuotGuiXe lgx = new LuotGuiXe();
//                lgx.setBienSoXe(bienSoXe);
//                lgx.setThoiGianVao(timestampVao);
//
//                var sv = new SinhVien();
//                sv.setMaSv(maSinhVien);
//                lgx.setSinhVien(sv);
//
//                service.create(lgx);
//            } else if (existing.getThoiGianRa() == null) {
//                // Đã có → cập nhật thời gian ra
//                existing.setThoiGianRa(Timestamp.valueOf(LocalDateTime.now()));
//                service.update(existing);
//            }
//
//        } catch (DataAccessException ex) {
//            model.addAttribute("error", "Đã xảy ra lỗi khi lưu!");
//            return "luotguixe/form";
//        }
//
//        return "redirect:/luotguixe";
//    }


    // Xóa (truyền đủ 3 khóa chính)
    @GetMapping("/delete")
    public String delete(@RequestParam String maSinhVien,
                         @RequestParam String bienSoXe,
                         @RequestParam LocalDateTime thoiGianVao) {
//        Timestamp ts = Timestamp.valueOf(thoiGianVao.replace("T", " ") + ":00");
        service.delete(maSinhVien, bienSoXe, thoiGianVao);
        return "redirect:/luotguixe";
    }
}
