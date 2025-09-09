package org.example.service;

import org.example.model.DangKyVeThang;
import org.example.repository.DangKyVeThangRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DangKyVeThangService {
    private final DangKyVeThangRepository repo;
    public DangKyVeThangService(DangKyVeThangRepository repo){ this.repo = repo; }
    public List<DangKyVeThang> getAll(){ return repo.findAll(); }
    public DangKyVeThang getById(Integer id){ return repo.findById(id); }
    public int create(DangKyVeThang vt){ return repo.save(vt); }
    public int update(DangKyVeThang vt){ return repo.update(vt); }
    public int delete(Integer id){ return repo.delete(id); }
}
