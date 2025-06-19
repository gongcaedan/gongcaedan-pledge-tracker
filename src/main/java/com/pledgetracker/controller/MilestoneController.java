package com.pledgetracker.controller;

import com.pledgetracker.dto.MilestoneRequestDto;
import com.pledgetracker.dto.MilestoneResponseDto;
import com.pledgetracker.dto.MilestoneStatusUpdateRequestDto;
import com.pledgetracker.service.MilestoneService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pledge")
@RequiredArgsConstructor
@Tag(name = "Milestone API", description = "공약의 세부 단계(Milestone)를 관리하는 API")
public class MilestoneController {
    private final MilestoneService milestoneService;

    // 마일스톤 생성
    @Operation(summary = "마일스톤 생성", description = "특정 공약에 새로운 마일스톤을 추가합니다.")
    @PostMapping("/{pledgeId}/milestones")
    public ResponseEntity<Void> createMilestone(@PathVariable Long pledgeId,
                                                @RequestBody MilestoneRequestDto request) {
        milestoneService.createMilestone(pledgeId, request);
        return ResponseEntity.ok().build();
    }

    // 특정 공약의 마일스톤 전체 조회
    @Operation(summary = "마일스톤 전체 조회", description = "특정 공약의 마일스톤 전체를 조회합니다.")
    @GetMapping("/{pledgeId}/milestones")
    public ResponseEntity<List<MilestoneResponseDto>> getMilestones(@PathVariable Long pledgeId) {
        List<MilestoneResponseDto> result = milestoneService.getMilestonesByPledgeId(pledgeId);
        return ResponseEntity.ok(result);
    }

    // 특정 마일스톤 조회
    @Operation(summary = "특정 마일스톤 조회", description = "마일스톤 ID를 부여하여 특정 마일스톤을 조회합니다.")
    @GetMapping("/milestones/{milestoneId}")
    public ResponseEntity<MilestoneResponseDto> getMilestone(@PathVariable Long milestoneId) {
        return ResponseEntity.ok(milestoneService.getMilestone(milestoneId));
    }

    // 마일스톤 상태 변경
    @Operation(summary = "마일스톤 상태 변경", description = "마일스톤의 상태 변경을 합니다.")
    @PatchMapping("/milestones/{milestoneId}/status")
    public ResponseEntity<Void> updateMilestoneStatus(
            @PathVariable Long milestoneId,
            @RequestBody MilestoneStatusUpdateRequestDto request) {
        milestoneService.updateMilestoneStatus(
                milestoneId,
                request.getNewStatus(),
                request.getReason(),
                request.getUpdatedBy(),
                request.getSourceUrl()
        );
        return ResponseEntity.ok().build();
    }
}
