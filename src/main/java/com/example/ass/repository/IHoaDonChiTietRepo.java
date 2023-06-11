package com.example.ass.repository;

import com.example.ass.entity.HoaDon;
import com.example.ass.entity.HoaDonChiTiet;
import com.example.ass.entity.HoaDonChiTietId;
import com.example.ass.entity.SanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IHoaDonChiTietRepo extends JpaRepository<HoaDonChiTiet, HoaDonChiTietId> {
    List<HoaDonChiTiet> findByIdHoaDon(HoaDon hoaDon);

    HoaDonChiTiet findByIdSanPhamAndIdHoaDon(SanPham sanPham, HoaDon hoaDon);
}
