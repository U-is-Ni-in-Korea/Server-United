package com.universe.uni.dto;

import java.time.LocalDateTime;

import com.universe.uni.domain.GameResult;
import com.universe.uni.domain.entity.RoundMission;
import com.universe.uni.dto.response.RoundMissionContentDto;

import lombok.Getter;

@Getter
public class RoundMissionDto {
	private final long id;
	private final RoundMissionContentDto missionContent;
	private final GameResult result;
	private final GameResult finalResult;
	private final LocalDateTime updatedAt;

	public RoundMissionDto(RoundMission roundMission) {
		this.id = roundMission.getId();
		this.missionContent = new RoundMissionContentDto(roundMission.getMissionContent());
		this.result = roundMission.getResult();
		this.finalResult = roundMission.getFinalResult();
		this.updatedAt = roundMission.getUpdatedAt();
	}
}
