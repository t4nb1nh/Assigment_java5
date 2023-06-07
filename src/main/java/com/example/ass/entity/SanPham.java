package com.example.ass.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "SanPham")
public class SanPham implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "uniqueidentifier default NEWID()")
    private String id;
    @NotBlank
    private String ma;
    @NotBlank
    private String ten;
    @Positive
    private long giaNhap;
    @Positive
    private long giaBan;
    @Positive
    private long soLuong;
    private Boolean active;
    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ngayHetHan;
    @NotBlank
    private String moTa;
    @ManyToOne
    @JoinColumn(name = "idLoai", referencedColumnName = "id")
    private LoaiSP loaiSP;
    private String image;
    @Temporal(TemporalType.DATE)
    private Date ngayNhap;
    @PrePersist
    public void prePersist() {
        this.ngayNhap = new Date();
    }


}
