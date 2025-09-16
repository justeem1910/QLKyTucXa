package org.example.service;

import org.example.model.HoaDonDichVu;
import org.example.repository.HoaDonDichVuRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HoaDonDichVuService {
    private final HoaDonDichVuRepository repo;
    public HoaDonDichVuService(HoaDonDichVuRepository repo){ this.repo = repo; }
    public List<HoaDonDichVu> getAll(){ return repo.findAll(); }
    public HoaDonDichVu getById(Integer maHoaDon, String maSinhVien, Integer maDichVu){ return repo.findById(maHoaDon, maSinhVien, maDichVu); }
    public int create(HoaDonDichVu hd){ return repo.save(hd); }
    public int update(HoaDonDichVu hd){ return repo.update(hd); }
    public int delete(Integer maHoaDon, String maSinhVien, Integer maDichVu){ return repo.delete(maHoaDon, maSinhVien, maDichVu); }
}
