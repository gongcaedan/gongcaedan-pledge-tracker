package com.pledgetracker.service;

import com.pledgetracker.dto.PledgeRequestDto;
import com.pledgetracker.dto.PledgeResponseDto;
import com.pledgetracker.entity.Pledge;
import com.pledgetracker.repository.PledgeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PledgeService {
    private final PledgeRepository pledgeRepository;

    // 공약 등록
    @Transactional
    public void createPledge(PledgeRequestDto pledgeRequestDto) {
        Pledge pledge = Pledge.builder()
                .candidateId(pledgeRequestDto.getCandidateId())
                .title(pledgeRequestDto.getTitle())
                .description(pledgeRequestDto.getDescription())
                .build();
        pledgeRepository.save(pledge);
    }

    // 전체 공약 조회
    @Transactional(readOnly = true)
    public List<PledgeResponseDto> getAllPledges() {
        return pledgeRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    // 후보자로 공약 조회
    @Transactional(readOnly = true)
    public List<PledgeResponseDto> getPledgesByCandidate(Long candidateId) {
        return pledgeRepository.findByCandidateId(candidateId).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    // 특정 공약 조회
    @Transactional(readOnly = true)
    public PledgeResponseDto getPledge(Long id) {
        return pledgeRepository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new RuntimeException("공약을 찾을 수 없습니다."));
    }

    // 공약 수정
    @Transactional
    public void updatePledge(Long id, PledgeRequestDto dto) {
        Pledge pledge = pledgeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("공약을 찾을 수 없습니다."));
        pledge.setTitle(dto.getTitle());
        pledge.setDescription(dto.getDescription());
    }

    // 공약 삭제
    @Transactional
    public void deletePledge(Long id) {
        Pledge pledge = pledgeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("공약을 찾을 수 없습니다."));
        pledgeRepository.delete(pledge);
    }

    private PledgeResponseDto toDto(Pledge p) {
        return PledgeResponseDto.builder()
                .id(p.getId())
                .candidateId(p.getCandidateId())
                .title(p.getTitle())
                .description(p.getDescription())
                .createdAt(p.getCreatedAt())
                .updatedAt(p.getUpdatedAt())
                .build();
    }
}
