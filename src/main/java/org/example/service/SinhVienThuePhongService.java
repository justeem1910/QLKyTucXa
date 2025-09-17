package org.example.service;

import org.example.model.SinhVienThuePhong;
import org.example.repository.SinhVienThuePhongRepository;

import java.util.List;

public class SinhVienThuePhongService {
    private final SinhVienThuePhongRepository repo;
    public SinhVienThuePhongService(SinhVienThuePhongRepository repo){ this.repo = repo; }
    public List<SinhVienThuePhong> getAll(){ return repo.findAll(); }
    public SinhVienThuePhong getById(String maSinhVien, String maHopDong){ return repo.findById(maSinhVien, maHopDong); }
    public int create(SinhVienThuePhong sv){ return repo.save(sv); }
    public int delete(String maSinhVien, String maHopDong){ return repo.delete(maSinhVien, maHopDong); }
}
