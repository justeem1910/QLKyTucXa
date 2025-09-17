package org.example.controller;

import org.example.model.HoaDonThuePhong;
import org.example.model.HoaDon;
import org.example.model.HopDongThue;
import org.example.model.SinhVien;
import org.example.service.HoaDonThuePhongService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/hoadonthuephong")
public class HoaDonThuePhongController {
    private final HoaDonThuePhongService service;

    public HoaDonThuePhongController(HoaDonThuePhongService service) {
        this.service = service;
    }

    // Hiển thị danh sách
    @GetMapping
    public String list(Model model) {
        List<HoaDonThuePhong> list = service.getAll();
        model.addAttribute("list", list);
        return "hoadonthuephong/list";
    }

    // Hiển thị form thêm
    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("hoadonthuephong", new HoaDonThuePhong());
        return "hoadonthuephong/form";
    }

    // Lưu mới
    @PostMapping("/add")
    public String save(@RequestParam String maSinhVien,
                       @RequestParam String maHopDong,
                       @RequestParam String maHoaDon,
                       @RequestParam BigDecimal tienPhong) {
        HoaDonThuePhong hdtp = new HoaDonThuePhong();

        SinhVien sv = new SinhVien();
        sv.setMaSv(maSinhVien);
        hdtp.setSinhVien(sv);

        HopDongThue hopDong = new HopDongThue();
        hopDong.setMaHopDong(maHopDong);
        hdtp.setHopDongThue(hopDong);

        HoaDon hd = new HoaDon();
        hd.setMaHoaDon(maHoaDon);
        hdtp.setHoaDon(hd);

        hdtp.setTienPhong(tienPhong);

        service.create(hdtp);
        return "redirect:/hoadonthuephong";
    }

    // Hiển thị form sửa
    @GetMapping("/edit")
    public String editForm(@RequestParam String maSinhVien,
                           @RequestParam String maHopDong,
                           @RequestParam String maHoaDon,
                           Model model) {
        HoaDonThuePhong hdtp = service.getById(maSinhVien, maHopDong, maHoaDon);
        model.addAttribute("hoadonthuephong", hdtp);
        return "hoadonthuephong/form";
    }

    // Cập nhật
    @PostMapping("/edit")
    public String update(@RequestParam String maSinhVien,
                         @RequestParam String maHopDong,
                         @RequestParam String maHoaDon,
                         @RequestParam BigDecimal tienPhong) {
        HoaDonThuePhong hdtp = new HoaDonThuePhong();

        SinhVien sv = new SinhVien();
        sv.setMaSv(maSinhVien);
        hdtp.setSinhVien(sv);

        HopDongThue hopDong = new HopDongThue();
        hopDong.setMaHopDong(maHopDong);
        hdtp.setHopDongThue(hopDong);

        HoaDon hd = new HoaDon();
        hd.setMaHoaDon(maHoaDon);
        hdtp.setHoaDon(hd);

        hdtp.setTienPhong(tienPhong);

        service.update(hdtp);
        return "redirect:/hoadonthuephong";
    }

    // Xóa
    @GetMapping("/delete")
    public String delete(@RequestParam String maSinhVien,
                         @RequestParam String maHopDong,
                         @RequestParam String maHoaDon) {
        service.delete(maSinhVien, maHopDong, maHoaDon);
        return "redirect:/hoadonthuephong";
    }
}
