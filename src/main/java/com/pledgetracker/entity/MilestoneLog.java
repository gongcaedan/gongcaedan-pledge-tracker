package com.pledgetracker.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * MilestoneLog(마일스톤 로그) 엔티티
 * 각 마일스톤의 상태가 변경될 때마다 기록되는 이력임
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MilestoneLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "milestone_id")
    private Milestone milestone;

    @Enumerated(EnumType.STRING)
    private MilestoneStatus prevStatus; // 변경 전 상태

    @Enumerated(EnumType.STRING)
    private MilestoneStatus newStatus; // 변경 후 상태

    @Column(columnDefinition = "TEXT")
    private String reason; // 변경 사유

    private String sourceUrl; // 출처 (기사 링크 등)

    private String updatedBy; // 관리자 or 시스템

    private LocalDateTime changedAt;

    @PrePersist
    protected void onCreate() {
        changedAt = LocalDateTime.now();
    }
}
