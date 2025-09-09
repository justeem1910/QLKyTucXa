package org.example.service;

import org.example.model.SinhVien;
import org.example.repository.SinhVienRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SinhVienService {
    private final SinhVienRepository repo;
    public SinhVienService(SinhVienRepository repo){ this.repo = repo; }
    public List<SinhVien> getAll(){ return repo.findAll(); }
    public SinhVien getById(Integer id){ return repo.findById(id); }
    public int create(SinhVien sv){ return repo.save(sv); }
    public int update(SinhVien sv){ return repo.update(sv); }
    public int delete(Integer id){ return repo.delete(id); }
}
