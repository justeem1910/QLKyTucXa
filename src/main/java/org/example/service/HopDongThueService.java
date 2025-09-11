package org.example.service;

import org.example.model.HopDongThue;
import org.example.repository.HopDongThueRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HopDongThueService {
    private final HopDongThueRepository repo;
    public HopDongThueService(HopDongThueRepository repo){ this.repo = repo; }
    public List<HopDongThue> getAll(){ return repo.findAll(); }
    public HopDongThue getById(Integer id){ return repo.findById(id); }
    public int create(HopDongThue hdt){ return repo.save(hdt); }
    public int update(HopDongThue hdt){ return repo.update(hdt); }
    public int delete(Integer id){ return repo.delete(id); }
}
