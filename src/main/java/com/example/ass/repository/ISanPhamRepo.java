package com.example.ass.repository;

import com.example.ass.entity.GioHang;
import com.example.ass.entity.LoaiSP;
import com.example.ass.entity.SanPham;
import com.example.ass.entity.Users;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
@Transactional
public interface ISanPhamRepo extends JpaRepository<SanPham,Integer> {
    SanPham findById(String id);
    @Query("select p from SanPham p where p.loaiSP.id = ?1")
    List<SanPham> findAllByIdLoai(String id);

//    SanPham findByUsers(Users users);
//    @Query("update SanPham set p.active=0 where p.expDate>?1")
//    void test(Date date);

}
