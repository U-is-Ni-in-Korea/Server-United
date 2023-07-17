package com.universe.uni.dto.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.universe.uni.dto.MissionResultDto;

import lombok.Builder;

@JsonPropertyOrder({"roundGameId", "date", "result", "title", "image", "winner", "myMission", "partnerMission"})
@Builder
public record GameHistoryResponseDto(
	int roundGameId,
	String date,
	String result,
	String title,
	String image,
	String winner,
	MissionResultDto myMission,
	MissionResultDto partnerMission
) {
}
