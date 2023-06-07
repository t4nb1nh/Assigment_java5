package com.example.ass.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class Users implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "uniqueidentifier default NEWID()")
    private String id;
    @Column(unique = true)
//    @NotNull
    private String ma;
//    @NotNull
    private String ten;
    @Temporal(TemporalType.DATE)
    private Date ngaySinh;
    private boolean gioiTinh;
//    @NotNull
    private String diaChi;
//    @NotNull
    private String sdt;
    @Column(unique = true)
//    @NotNull
    private String email;

    @Column(unique = true)
    @NotNull
    private String username;
    @NotNull
    private String password;
    @Column(name = "role",columnDefinition = "bit")
    private boolean role;
    @Temporal(TemporalType.DATE)
    private Date ngayNhap;
    @PrePersist
    public void prePersist() {
        this.ngayNhap = new Date();
    }
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private UserActivation activation;
    private boolean enabled;


}
