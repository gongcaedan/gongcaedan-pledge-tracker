package com.pledgetracker.dto;

import com.pledgetracker.entity.MilestoneStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "마일스톤 상태 변경 요청 DTO")
public class MilestoneStatusUpdateRequestDto {

    @Schema(description = "새로운 상태", example = "COMPLETED")
    private MilestoneStatus newStatus;

    @Schema(description = "변경 사유", example = "관련 법안 국회 통과")
    private String reason;

    @Schema(description = "업데이트한 사용자", example = "admin1")
    private String updatedBy;

    @Schema(description = "출처 링크", example = "https://news.example.com/article123")
    private String sourceUrl;
}

