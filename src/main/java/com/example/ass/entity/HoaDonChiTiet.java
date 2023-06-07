package com.example.ass.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "HoaDonChiTiet")
public class HoaDonChiTiet {
    @EmbeddedId
    private HoaDonChiTietId id;
    private Long quantity;
    private Long price;
}
