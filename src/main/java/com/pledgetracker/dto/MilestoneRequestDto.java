package com.pledgetracker.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "마일스톤 생성 요청 DTO")
public class MilestoneRequestDto {

    @Schema(description = "마일스톤 제목", example = "본회의 통과")
    private String title;

    @Schema(description = "마일스톤 설명", example = "국회 통과 및 법적 절차 완료")
    private String description;

    @Schema(description = "진행 순서", example = "1")
    private int sequence;
}

