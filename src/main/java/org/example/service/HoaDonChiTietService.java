package org.example.service;

import org.example.model.HoaDonChiTiet;
import org.example.repository.HoaDonChiTietRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HoaDonChiTietService {
    private final HoaDonChiTietRepository repo;
    public HoaDonChiTietService(HoaDonChiTietRepository repo){ this.repo = repo; }
    public List<HoaDonChiTiet> getAll(){ return repo.findAll(); }
    public HoaDonChiTiet getById(Integer id){ return repo.findById(id); }
    public int create(HoaDonChiTiet hdct){ return repo.save(hdct); }
    public int update(HoaDonChiTiet hdct){ return repo.update(hdct); }
    public int delete(Integer id){ return repo.delete(id); }
}
