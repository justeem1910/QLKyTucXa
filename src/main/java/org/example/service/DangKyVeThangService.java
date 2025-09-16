package org.example.service;

import org.example.model.DangKyVeThang;
import org.example.repository.DangKyVeThangRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DangKyVeThangService {
    private final DangKyVeThangRepository repo;
    public DangKyVeThangService(DangKyVeThangRepository repo){ this.repo = repo; }
    public List<DangKyVeThang> getAll(){ return repo.findAll(); }
    public DangKyVeThang getById(String maSv, String bienSoXe, Date ngayBatDau){ return repo.findById(maSv, bienSoXe, ngayBatDau); }
    public int create(DangKyVeThang vt){ return repo.save(vt); }
    public int update(DangKyVeThang vt){ return repo.update(vt); }
    public int delete(String maSv, String bienSoXe, Date ngayBatDau){ return repo.delete(maSv, bienSoXe, ngayBatDau); }
}
