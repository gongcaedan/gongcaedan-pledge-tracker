package com.pledgetracker.repository;

import com.pledgetracker.entity.Milestone;
import com.pledgetracker.entity.MilestoneLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MilestoneLogRepository extends JpaRepository<MilestoneLog, Long> {
    // 마일스톤별 로그 이력 조회
    List<MilestoneLog> findByMilestoneOrderByChangedAtDesc(Milestone milestone);
}
