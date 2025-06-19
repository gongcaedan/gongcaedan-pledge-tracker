package com.pledgetracker.service;

import com.pledgetracker.dto.MilestoneRequestDto;
import com.pledgetracker.dto.MilestoneResponseDto;
import com.pledgetracker.entity.Milestone;
import com.pledgetracker.entity.MilestoneLog;
import com.pledgetracker.entity.MilestoneStatus;
import com.pledgetracker.entity.Pledge;
import com.pledgetracker.repository.MilestoneLogRepository;
import com.pledgetracker.repository.MilestoneRepository;
import com.pledgetracker.repository.PledgeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MilestoneService {
    private final MilestoneRepository milestoneRepository;
    private final PledgeRepository pledgeRepository;
    private final MilestoneLogRepository milestoneLogRepository;

    // 마일스톤 생성
    public void createMilestone(Long pledgeId, MilestoneRequestDto milestoneRequestDto) {
        Pledge pledge = pledgeRepository.findById(pledgeId).orElseThrow(() -> new RuntimeException(("해당 공약이 존재하지 않습니다.")));

        Milestone milestone = Milestone.builder()
                .pledge(pledge)
                .title(milestoneRequestDto.getTitle())
                .description(milestoneRequestDto.getDescription())
                .sequence(milestoneRequestDto.getSequence())
                .status(MilestoneStatus.NOT_STARTED)
                .updatedAt(LocalDateTime.now())
                .build();

        milestoneRepository.save(milestone);
    }

    // 마일스톤 상세 조회
    @Transactional(readOnly=true)
    public MilestoneResponseDto getMilestone(Long id) {
        Milestone m = milestoneRepository.findById(id).orElseThrow(() -> new RuntimeException(("마일스톤을 찾을 수 없습니다.")));

        return MilestoneResponseDto.builder()
                .id(m.getId())
                .title(m.getTitle())
                .description(m.getDescription())
                .sequence(m.getSequence())
                .status(m.getStatus().name())
                .updatedAt(m.getUpdatedAt())
                .build();
    }

    // 공약에 포함된 마일스톤 전체 조회
    @Transactional(readOnly = true)
    public List<MilestoneResponseDto> getMilestonesByPledgeId(Long pledgeId) {
        return milestoneRepository.findByPledgeIdOrderBySequenceAsc(pledgeId).stream()
                .map(m -> MilestoneResponseDto.builder()
                        .id(m.getId())
                        .title(m.getTitle())
                        .description(m.getDescription())
                        .sequence(m.getSequence())
                        .status(m.getStatus().name())
                        .updatedAt(m.getUpdatedAt())
                        .build())
                .collect(Collectors.toList());
    }

    // 마일스톤 상태 변 및 로그 저장
    @Transactional
    public void updateMilestoneStatus(Long milestoneId, MilestoneStatus newStatus, String reason, String updatedBy, String sourceUrl) {
        Milestone m = milestoneRepository.findById(milestoneId).orElseThrow(() -> new RuntimeException(("마일스톤을 찾을 수 없습니다.")));

        MilestoneStatus prevStatus = m.getStatus();

        // 상태 변경
        if(prevStatus != newStatus) {
            m.setStatus(newStatus);
            m.setUpdatedAt(LocalDateTime.now());
        }

        // 로그 저장
        MilestoneLog milestoneLog = MilestoneLog.builder()
                .milestone(m)
                .prevStatus(prevStatus)
                .newStatus(newStatus)
                .reason(reason)
                .updatedBy(updatedBy)
                .sourceUrl(sourceUrl)
                .changedAt(LocalDateTime.now())
                .build();

        milestoneLogRepository.save(milestoneLog);
    }
}
