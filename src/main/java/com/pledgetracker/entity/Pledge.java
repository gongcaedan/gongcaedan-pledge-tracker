package com.pledgetracker.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 공약(Pledge) 엔티티
 * 후보자가 내건 하나의 공약을 의미하며, 여러 개의 마일스톤(Milestone)을 가질 수 있음
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pledge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long candidateId;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // 하나의 공약은 여러 단계의 마일스톤을 가짐
    @OneToMany(mappedBy = "pledge", cascade = CascadeType.ALL)
    private List<Milestone> milestones = new ArrayList<>();

    // 자동 타임 스탬프
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}

