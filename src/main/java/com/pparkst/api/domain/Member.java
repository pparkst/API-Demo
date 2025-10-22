package com.pparkst.api.domain;

import java.time.LocalDateTime;

import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.AccessLevel;

@Entity
@Getter
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tbl_member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long no;

    @Column(name = "account", nullable = false, length = 15)
    private String account;

    @Column(name = "password", nullable = false, length = 20)
    private String password;

    @Column(name = "name", nullable = false, length = 15)
    private String name;

    @Column(name = "createdAt", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updatedAt")
    private LocalDateTime updatedAt;

    @Column(name = "isWithdrawal")
    @ColumnDefault("false")
    private boolean isWithdrawal;

    @Column(name = "withdrawalAt")
    private LocalDateTime withdrawalAt;

    public void softCreate() {
        this.createdAt = LocalDateTime.now();
    }

    public void softUpdate(String password, String name) {
        this.password = password;
        this.name = name;
    }

    public void softDelete() {
        this.isWithdrawal = true;
        this.withdrawalAt = LocalDateTime.now();
    }
    
}
