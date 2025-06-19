package com.pledgetracker.controller;

import com.pledgetracker.dto.MilestoneLogResponseDto;
import com.pledgetracker.service.MilestoneLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/milestones")
@Tag(name = "MilestoneLog API", description = "마일스톤의 변경 로그를 조회하는 API")
public class MilestoneLogController {

    private final MilestoneLogService milestoneLogService;

    // 마일스톤 상태 변경 로그 조회
    @Operation(summary = "특정 마일스톤 상태 변경 로그 조회", description = "특정 마일스톤의 상태 변경 로그를 조회합니다.")
    @GetMapping("/{milestoneId}/logs")
    public ResponseEntity<List<MilestoneLogResponseDto>> getLogs(@PathVariable Long milestoneId) {
        return ResponseEntity.ok(milestoneLogService.getLogsByMilestoneId(milestoneId));
    }
}
