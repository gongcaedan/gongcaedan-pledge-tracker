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
@Schema(description = "마일스톤 응답 DTO")
public class MilestoneResponseDto {
    private Long id;
    private String title;
    private String description;
    private int sequence;
    private String status;
    private LocalDateTime updatedAt;
}


