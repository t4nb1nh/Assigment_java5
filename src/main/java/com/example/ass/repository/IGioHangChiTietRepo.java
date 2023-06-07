package com.example.ass.repository;

import com.example.ass.entity.GioHang;
import com.example.ass.entity.GioHangChiTiet;
import com.example.ass.entity.GioHangChiTietId;
import com.example.ass.entity.SanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IGioHangChiTietRepo extends JpaRepository<GioHangChiTiet, GioHangChiTietId> {
    List<GioHangChiTiet> findByIdGioHang(GioHang gioHang);

    GioHangChiTiet findByIdSanPhamAndIdGioHang(SanPham sanPham, GioHang gioHang);
}
