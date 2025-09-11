package org.example.service;

import org.example.model.SuDungDichVu;
import org.example.repository.SuDungDichVuRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SuDungDichVuService {
    private final SuDungDichVuRepository repo;
    public SuDungDichVuService(SuDungDichVuRepository repo){ this.repo = repo; }
    public List<SuDungDichVu> getAll(){ return repo.findAll(); }
    public SuDungDichVu getById(Integer id){ return repo.findById(id); }
    public int create(SuDungDichVu sddv){ return repo.save(sddv); }
    public int update(SuDungDichVu sddv){ return repo.update(sddv); }
    public int delete(Integer id){ return repo.delete(id); }
}
