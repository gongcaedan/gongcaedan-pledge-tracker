package com.pledgetracker.repository;

import com.pledgetracker.entity.Pledge;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PledgeRepository extends JpaRepository<Pledge, Long> {
    // 후보자의 공약만 조회
    List<Pledge> findByCandidateId(Long candidateId);
}
