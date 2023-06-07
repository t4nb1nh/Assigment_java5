package com.example.ass.repository;

import com.example.ass.entity.GioHang;
import com.example.ass.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IGioHangRepo extends JpaRepository<GioHang,Integer> {
    GioHang findByUsers(Users users);
}
