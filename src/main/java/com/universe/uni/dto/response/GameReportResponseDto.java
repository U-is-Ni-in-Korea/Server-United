package com.universe.uni.dto.response;

import com.universe.uni.domain.entity.RoundMission;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
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
