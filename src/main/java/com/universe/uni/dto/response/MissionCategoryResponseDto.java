package com.universe.uni.dto.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.universe.uni.dto.MissionContentDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.List;

@Schema(description = "미션 카테고리 상세 정보 응답 DTO")
@JsonPropertyOrder({"id", "title", "description", "rule", "tip", "image", "missionContentList"})
@Builder
public record MissionCategoryResponseDto(
        @Schema(description = "미션 카테고리 id")
        Long id,
        @Schema(description = "미션 카테고리 제목")
        String title,
        @Schema(description = "미션 카테고리 설명")
        String description,
        @Schema(description = "미션 룰")
        String rule,
        @Schema(description = "미션 팁")
        String tip,
        @Schema(description = "미션 이미지")
        String image,
        @Schema(description = "미션 컨텐츠 목록")
        List<MissionContentDto> missionContentList
) {
}
