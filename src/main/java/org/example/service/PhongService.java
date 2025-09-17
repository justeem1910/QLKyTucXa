package org.example.service;

import org.example.model.Phong;
import org.example.repository.PhongRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhongService {
    private final PhongRepository repo;
    public PhongService(PhongRepository repo){ this.repo = repo; }
    public List<Phong> getAll(){ return repo.findAll(); }
    public Phong getById(String id){ return repo.findById(id); }
    public int create(Phong p){ return repo.save(p); }
    public int update(Phong p){ return repo.update(p); }
    public int delete(String id){ return repo.delete(id); }
}
