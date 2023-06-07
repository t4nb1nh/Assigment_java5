package com.example.ass.repository;

import com.example.ass.entity.GioHang;
import com.example.ass.entity.SanPham;
import com.example.ass.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUsersRepo extends JpaRepository<Users,Integer> {
    Users findByUsernameAndPassword(String username, String password);
    Users findById(String id);
}
