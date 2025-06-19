package com.pledgetracker.controller;

import com.pledgetracker.dto.PledgeRequestDto;
import com.pledgetracker.dto.PledgeResponseDto;
import com.pledgetracker.service.PledgeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pledges")
@Tag(name = "Pledge API", description = "공약(Pledge)을 관리하는 API")
public class PledgeController {

    private final PledgeService pledgeService;

    // 공약 등록
    @Operation(summary = "공약 등록", description = "공약을 등록합니다.")
    @PostMapping
    public ResponseEntity<Void> create(@RequestBody PledgeRequestDto dto) {
        pledgeService.createPledge(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // 모든 공약 조회
    @Operation(summary = "모든 공약 조회", description = "모든 공약을 조회합니다.")
    @GetMapping
    public ResponseEntity<List<PledgeResponseDto>> getAll() {
        return ResponseEntity.ok(pledgeService.getAllPledges());
    }

    // 특정 후보자의 공약 조회
    @Operation(summary = "특정 후보자의 공약 조회", description = "후보자 ID로 특정 후보자의 공약을 조회합니다.")
    @GetMapping("/candidate/{candidateId}")
    public ResponseEntity<List<PledgeResponseDto>> getByCandidate(@PathVariable Long candidateId) {
        return ResponseEntity.ok(pledgeService.getPledgesByCandidate(candidateId));
    }

    // 특정 공약 조회
    @Operation(summary = "특정 공약 조회", description = "공약 ID를 넣어 특정 공약을 조회합니다.")
    @GetMapping("/{id}")
    public ResponseEntity<PledgeResponseDto> get(@PathVariable Long id) {
        return ResponseEntity.ok(pledgeService.getPledge(id));
    }

    // 특정 공약 수정
    @Operation(summary = "특정 공약 수정", description = "공약 ID를 넣어 특정 공약을 수정합니다.")
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody PledgeRequestDto dto) {
        pledgeService.updatePledge(id, dto);
        return ResponseEntity.ok().build();
    }

    // 특정 공약 삭제
    @Operation(summary = "특정 공약 삭제", description = "공약 ID를 넣어 특정 공약을 삭제합니다.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        pledgeService.deletePledge(id);
        return ResponseEntity.noContent().build();
    }
}

