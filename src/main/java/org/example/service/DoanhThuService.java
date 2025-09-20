package org.example.service;

import org.example.model.DoanhThuDTO;
import org.example.repository.DoanhThuRepository;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class DoanhThuService {
    private final DoanhThuRepository repo;

    public DoanhThuService(DoanhThuRepository repo) {
        this.repo = repo;
    }

    public List<DoanhThuDTO> getDoanhThuTheoThang(Date thang) {
        return repo.tinhDoanhThuTheoThang(thang);
    }
}
