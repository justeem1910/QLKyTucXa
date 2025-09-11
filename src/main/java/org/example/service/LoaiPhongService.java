package org.example.service;

import org.example.model.LoaiPhong;
import org.example.repository.LoaiPhongRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoaiPhongService {
    private final LoaiPhongRepository repo;
    public LoaiPhongService(LoaiPhongRepository repo){ this.repo = repo; }
    public List<LoaiPhong> getAll(){ return repo.findAll(); }
    public LoaiPhong getById(Integer id){ return repo.findById(id); }
    public int create(LoaiPhong lp){ return repo.save(lp); }
    public int update(LoaiPhong lp){ return repo.update(lp); }
    public int delete(Integer id){ return repo.delete(id); }
}
