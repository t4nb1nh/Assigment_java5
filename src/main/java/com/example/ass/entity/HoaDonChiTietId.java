package com.example.ass.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
public class HoaDonChiTietId implements Serializable {
    @ManyToOne
    @JoinColumn(name = "idHoaDon",referencedColumnName = "id")
    private HoaDon hoaDon;
    @ManyToOne
    @JoinColumn(name = "idSanPham",referencedColumnName = "id")
    private SanPham sanPham;

    public HoaDonChiTietId(HoaDon hoaDon, SanPham sanPham) {
        this.hoaDon = hoaDon;
        this.sanPham = sanPham;
    }
    public HoaDonChiTietId() {
    }
}
