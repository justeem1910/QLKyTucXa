package org.example.service;

import org.example.model.SuDungDichVu;
import org.example.repository.SuDungDichVuRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class SuDungDichVuService {
    private final SuDungDichVuRepository repo;
    public SuDungDichVuService(SuDungDichVuRepository repo){ this.repo = repo; }
    public List<SuDungDichVu> getAll(){ return repo.findAll(); }
    public SuDungDichVu getById(Integer maDv, String maSv, Timestamp ngaySuDung){ return repo.findById(maDv, maSv, ngaySuDung); }
    public int create(SuDungDichVu sddv){ return repo.save(sddv); }
//    public int update(SuDungDichVu sddv){ return repo.update(sddv); }
    public int delete(Integer maDv, String maSv, Timestamp ngaySuDung){ return repo.delete(maDv, maSv, ngaySuDung); }
}
