package org.example.service;

import org.example.model.DichVu;
import org.example.repository.DichVuRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DichVuService {
    private final DichVuRepository repo;
    public DichVuService(DichVuRepository repo){ this.repo = repo; }
    public List<DichVu> getAll(){ return repo.findAll(); }
    public DichVu getById(Integer id){ return repo.findById(id); }
    public int create(DichVu dv){ return repo.save(dv); }
    public int update(DichVu dv){ return repo.update(dv); }
    public int delete(Integer id){ return repo.delete(id); }
}
