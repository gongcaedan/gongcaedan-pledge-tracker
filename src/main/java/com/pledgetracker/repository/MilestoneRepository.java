package com.pledgetracker.repository;

import com.pledgetracker.entity.Milestone;
import com.pledgetracker.entity.Pledge;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MilestoneRepository extends JpaRepository<Milestone, Long> {
    // 특정 공약에 연결된 마일스톤 전체 조회
    List<Milestone> findByPledge(Pledge pledge);
    // pledgeI로 조회
    List<Milestone> findByPledgeIdOrderBySequenceAsc(Long pledgeId);
}
