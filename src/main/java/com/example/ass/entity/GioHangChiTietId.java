package com.example.ass.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Controller;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
public class GioHangChiTietId implements Serializable {
    @ManyToOne
    @JoinColumn(name = "idGioHang",referencedColumnName = "id")
    private GioHang gioHang;
    @ManyToOne
    @JoinColumn(name = "idSanPham",referencedColumnName = "id")
    private SanPham sanPham;

    public GioHangChiTietId(GioHang gioHang, SanPham sanPham) {
        this.gioHang = gioHang;
        this.sanPham = sanPham;
    }

    public GioHangChiTietId() {

    }
}
