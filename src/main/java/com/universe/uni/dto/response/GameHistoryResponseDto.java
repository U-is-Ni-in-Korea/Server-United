package com.universe.uni.dto.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.universe.uni.dto.MissionResultDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Schema(description = "게임 기록 응답 DTO")
@JsonPropertyOrder({"roundGameId", "date", "result", "title", "image", "winner", "myMission", "partnerMission"})
@Builder
public record GameHistoryResponseDto(
	@Schema(description = "게임에 라운드 id")
	int roundGameId,
	@Schema(description = "게임 종료 날짜")
	String date,
	@Schema(description = "게임 결과")
	String result,
	@Schema(description = "게임 타이틀")
	String title,
	@Schema(description = "게임 이미지")
	String winner,
	@Schema(description = "승자 이름, 비기는 경우 빈 문자열")
	String image,
	@Schema(description = "내가 진행한 미션 정보")
	MissionResultDto myMission,
	@Schema(description = "상대가 진행한 미션 정보")
	MissionResultDto partnerMission
) {

}
