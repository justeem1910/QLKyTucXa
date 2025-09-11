package org.example.service;

import org.example.model.GiaPhong;
import org.example.repository.GiaPhongRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GiaPhongService {
    private final GiaPhongRepository repo;
    public GiaPhongService(GiaPhongRepository repo){ this.repo = repo; }
    public List<GiaPhong> getAll(){ return repo.findAll(); }
    public GiaPhong getById(Integer id){ return repo.findById(id); }
    public int create(GiaPhong gp){ return repo.save(gp); }
    public int update(GiaPhong gp){ return repo.update(gp); }
    public int delete(Integer id){ return repo.delete(id); }
}
