package org.example.service;

import org.example.model.SinhVienThuePhong;
import org.example.repository.SinhVienThuePhongRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

@Service
public class SinhVienThuePhongService {
    private final SinhVienThuePhongRepository repo;
    public SinhVienThuePhongService(SinhVienThuePhongRepository repo){ this.repo = repo; }
    public List<SinhVienThuePhong> getAll(){ return repo.findAll(); }
    public SinhVienThuePhong getById(String maSinhVien, String maHopDong){ return repo.findById(maSinhVien, maHopDong); }
    public int create(SinhVienThuePhong sv){ return repo.save(sv); }
    public int delete(String maSinhVien, String maHopDong){ return repo.delete(maSinhVien, maHopDong); }

    @Transactional
    public void createAuto(String maSv, String maPhong, Date ngayBatDau, Date ngayKetThuc) {
        repo.createWithAutoContract(maSv, maPhong, ngayBatDau, ngayKetThuc);
    }
}
