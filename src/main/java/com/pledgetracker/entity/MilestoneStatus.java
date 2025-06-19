package com.pledgetracker.entity;

/**
 * 마일스톤 진행 상태를 나타내는 Enum 클래스
 * 각 상태는 마일스톤의 이행 단계를 표현
 */

public enum MilestoneStatus {
    NOT_STARTED,
    IN_PROGRESS,
    COMPLETED,
    FAILED, // 무산됨
}
