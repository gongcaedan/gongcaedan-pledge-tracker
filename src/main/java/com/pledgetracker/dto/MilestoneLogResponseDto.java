package com.pledgetracker.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "마일스톤 변경 로그 응답 DTO")
public class MilestoneLogResponseDto {

    private Long id;

    @Schema(description = "이전 상태", example = "IN_PROGRESS")
    private String prevStatus;

    @Schema(description = "새로운 상태", example = "COMPLETED")
    private String newStatus;

    @Schema(description = "변경 이유", example = "예산 집행 완료")
    private String reason;

    @Schema(description = "출처 링크", example = "https://news.example.com/article456")
    private String sourceUrl;

    @Schema(description = "수정한 사용자", example = "admin2")
    private String updatedBy;

    private LocalDateTime changedAt;
}


