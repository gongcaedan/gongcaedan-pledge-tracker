package com.pledgetracker.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Milestone(마일스톤) 엔티티
 * 하나의 공약을 구성하는 개별 단계이며, 상태(진행 중, 완료 등)를 가짐
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Milestone {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pledge_id")
    private Pledge pledge;

    private int sequence; // 순서 (1, 2, 3, ...)

    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    private MilestoneStatus status;

    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "milestone", cascade = CascadeType.ALL)
    private List<MilestoneLog> logs = new ArrayList<>();

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}

