package com.pledgetracker.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "공약 생성/수정 요청 DTO")
public class PledgeRequestDto {

    @Schema(description = "후보자 ID", example = "123")
    private Long candidateId;

    @Schema(description = "공약 제목", example = "AI 산업 투자 확대")
    private String title;

    @Schema(description = "공약 설명", example = "정부 차원의 AI 연구소 확대 및 예산 지원")
    private String description;
}


