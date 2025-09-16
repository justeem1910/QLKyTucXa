package org.example.service;

import org.example.model.HoaDonThuePhong;
import org.example.repository.HoaDonThuePhongRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HoaDonThuePhongService {
    private final HoaDonThuePhongRepository repo;
    public HoaDonThuePhongService(HoaDonThuePhongRepository repo){ this.repo = repo; }
    public List<HoaDonThuePhong> getAll(){ return repo.findAll(); }
    public HoaDonThuePhong getById(String maSinhVien, Integer maHopDong, Integer maHoaDon){ return repo.findById(maSinhVien, maHopDong, maHoaDon); }
    public int create(HoaDonThuePhong hd){ return repo.save(hd); }
    public int update(HoaDonThuePhong hd){ return repo.update(hd); }
    public int delete(String maSinhVien, Integer maHopDong, Integer maHoaDon){ return repo.delete(maSinhVien, maHopDong, maHoaDon); }
}
