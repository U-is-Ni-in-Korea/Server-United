package com.universe.uni.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.universe.uni.domain.entity.RoundMission;
import com.universe.uni.dto.RoundMissionDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Schema(description = "게임 결과 응답 DTO")
@Getter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GameReportResponseDto {
	private RoundMissionDto myRoundMission;
	private RoundMissionDto partnerRoundMission;

	public static GameReportResponseDto of(RoundMission myRoundMission, RoundMission partnerRoundMission) {
		return new GameReportResponseDto(new RoundMissionDto(myRoundMission), new RoundMissionDto(partnerRoundMission));
	}

	public static GameReportResponseDto of(RoundMission myRoundMission) {
		return new GameReportResponseDto(new RoundMissionDto(myRoundMission), null);
	}
}
