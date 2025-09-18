package org.example.service;

import org.example.model.HoaDonChiTiet;
import org.example.repository.HoaDonChiTietRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HoaDonChiTietService {
    private final HoaDonChiTietRepository repo;
    public HoaDonChiTietService(HoaDonChiTietRepository repo){ this.repo = repo; }
}
