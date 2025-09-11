package org.example.service;

import org.example.model.HoaDon;
import org.example.repository.HoaDonRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HoaDonService {
    private final HoaDonRepository repo;
    public HoaDonService(HoaDonRepository repo){ this.repo = repo; }
    public List<HoaDon> getAll(){ return repo.findAll(); }
    public HoaDon getById(Integer id){ return repo.findById(id); }
    public int create(HoaDon hd){ return repo.save(hd); }
    public int update(HoaDon hd){ return repo.update(hd); }
    public int delete(Integer id){ return repo.delete(id); }
}
