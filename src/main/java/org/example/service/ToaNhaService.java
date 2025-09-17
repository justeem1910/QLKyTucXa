package org.example.service;

import org.example.model.ToaNha;
import org.example.repository.ToaNhaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToaNhaService {
    private final ToaNhaRepository repo;
    public ToaNhaService(ToaNhaRepository repo){ this.repo = repo; }
    public List<ToaNha> getAll(){ return repo.findAll(); }
    public ToaNha getById(String id){ return repo.findById(id); }
    public int create(ToaNha tn){ return repo.save(tn); }
    public int update(ToaNha tn){ return repo.update(tn); }
    public int delete(String id){ return repo.delete(id); }
}
