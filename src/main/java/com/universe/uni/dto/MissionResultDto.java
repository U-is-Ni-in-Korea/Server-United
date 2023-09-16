package com.universe.uni.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Schema(description = "미션 결과 DTO")
@JsonPropertyOrder({"content", "result", "time"})
@Builder
public record MissionResultDto(
        @Schema(description = "내 미션 내용")
        String content,
        @Schema(description = "미션의 결과")
        String result,
        @Schema(description = "결과를 입력한 시간")
        String time
) {
}
