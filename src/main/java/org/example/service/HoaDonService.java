package org.example.service;

import org.example.model.HoaDon;
import org.example.repository.HoaDonRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

@Service
public class HoaDonService {

    private final HoaDonRepository hoaDonRepository;

    public HoaDonService(HoaDonRepository hoaDonRepository) {
        this.hoaDonRepository = hoaDonRepository;
    }

    @Transactional
    public void taoHoaDonTheoThang(Date thang) {
        // Tạo hóa đơn (trigger tự sinh ma_hoa_don)
        String maHoaDon = hoaDonRepository.taoHoaDon(thang);

        // Nếu bạn có các function tính tiền phòng/dịch vụ, gọi ở đây
        // ví dụ: func_tinh_tien_phong(maHoaDon), func_tinh_tien_dich_vu(maHoaDon)
        // hiện tại tôi chỉ update tổng tiền
        hoaDonRepository.updateTongTien(maHoaDon);
    }

    public List<HoaDon> getHoaDonTheoThang(Date thang) {
        return hoaDonRepository.getHoaDonTheoThang(thang);
    }
}
