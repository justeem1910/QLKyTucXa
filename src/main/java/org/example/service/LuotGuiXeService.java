package org.example.service;

import org.example.model.LuotGuiXe;
import org.example.repository.LuotGuiXeRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class LuotGuiXeService {
    private final LuotGuiXeRepository repo;
    public LuotGuiXeService(LuotGuiXeRepository repo){ this.repo = repo; }
    public List<LuotGuiXe> getAll(){ return repo.findAll(); }
    public LuotGuiXe getById(String maSv, String bienSoXe){ return repo.findById(maSv, bienSoXe); }
    public int create(LuotGuiXe lgx){ return repo.save(lgx); }
    public int update(LuotGuiXe lgx){ return repo.update(lgx); }
    public int delete(String maSv, String bienSoXe, LocalDateTime thoiGianVao){ return repo.delete(maSv, bienSoXe, thoiGianVao); }
}
