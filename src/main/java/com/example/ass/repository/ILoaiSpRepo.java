package com.example.ass.repository;

import com.example.ass.entity.GioHang;
import com.example.ass.entity.LoaiSP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ILoaiSpRepo extends JpaRepository<LoaiSP,Integer> {
    LoaiSP findById(String id);
}
