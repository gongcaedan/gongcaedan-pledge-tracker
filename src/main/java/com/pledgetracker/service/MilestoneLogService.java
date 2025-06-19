package com.pledgetracker.service;

import com.pledgetracker.dto.MilestoneLogResponseDto;
import com.pledgetracker.entity.Milestone;
import com.pledgetracker.repository.MilestoneLogRepository;
import com.pledgetracker.repository.MilestoneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MilestoneLogService {
    private final MilestoneLogRepository milestoneLogRepository;
    private final MilestoneRepository milestoneRepository;

    // 특정 마일스톤의 상태 변 로그 전체 조회
    @Transactional(readOnly = true)
    public List<MilestoneLogResponseDto> getLogsByMilestoneId(Long milestoneId) {
        Milestone milestone = milestoneRepository.findById(milestoneId)
                .orElseThrow(() -> new RuntimeException("마일스톤을 찾을 수 없습니다."));

        return milestoneLogRepository.findByMilestoneOrderByChangedAtDesc(milestone).stream()
                .map(log -> MilestoneLogResponseDto.builder()
                        .id(log.getId())
                        .prevStatus(log.getPrevStatus().name())
                        .newStatus(log.getNewStatus().name())
                        .reason(log.getReason())
                        .sourceUrl(log.getSourceUrl())
                        .updatedBy(log.getUpdatedBy())
                        .changedAt(log.getChangedAt())
                        .build())
                .collect(Collectors.toList());
    }
}
