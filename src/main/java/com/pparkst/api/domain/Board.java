package com.pparkst.api.domain;

import java.time.LocalDateTime;

import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AccessLevel;

@Entity
@Getter
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PROTECTED) // Public으로 설정이 필요한경우 MapStructor 다시 확인 필요, 정상적으로 생성자에 접근하지못함
@Table(name = "tbl_board")
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false, length = 100)
    private String title;

    @Column(name = "content", nullable = false, length = 5000)
    private String content;

    @Column(name = "member_No", nullable = false)
    private Long member_No;

    @Column(name = "isDeleted", nullable = false)
    @ColumnDefault("false")
    private Boolean isDeleted;

    @Column(name = "createdAt", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updatedAt")
    private LocalDateTime updatedAt;

    @Column(name = "deletedAt")
    private LocalDateTime deletedAt;

    public void softCreate() {
        this.createdAt = LocalDateTime.now();
    }

    public void softUpdate(String title, String content) {
        this.title = title;
        this.content = content;
        this.updatedAt = LocalDateTime.now();
    }

    public void softDelete() {
        this.isDeleted = true;
        this.deletedAt = LocalDateTime.now();
    }

}
