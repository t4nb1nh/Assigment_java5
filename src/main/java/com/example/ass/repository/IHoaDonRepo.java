package com.example.ass.repository;

import com.example.ass.entity.GioHang;
import com.example.ass.entity.HoaDon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IHoaDonRepo extends JpaRepository<HoaDon,Integer> {
}
