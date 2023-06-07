package com.example.ass.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "GioHang")
public class GioHang implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "uniqueidentifier default NEWID()")
    private String id;
    @OneToOne
    @JoinColumn(name = "user_id")
    private Users users;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "idGioHang",referencedColumnName = "id")
    private List<GioHangChiTiet> gioHangChiTiet;
    private LocalDateTime ngayTao;
    private LocalDateTime ngayThanhToan;
}
