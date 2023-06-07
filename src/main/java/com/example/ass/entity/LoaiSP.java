package com.example.ass.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "LoaiSP")
public class LoaiSP implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "uniqueidentifier default NEWID()")
    private String id;
    @NotBlank(message = "Vui lòng nhập mã!")
    private String ma;

    @NotBlank(message = "Vui lòng nhập tên!")
    private String ten;

    @Temporal(TemporalType.DATE)
    private Date ngayTao;

    @PrePersist
    public void prePersist() {
        this.ngayTao = new Date();
    }

}
