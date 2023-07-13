package com.universe.uni.dto.response;

import java.time.LocalDateTime;

import com.universe.uni.domain.GameResult;
import com.universe.uni.domain.entity.RoundMission;

import lombok.Getter;

@Getter
public class RoundMissionDto {
	private long id;
	private MissionContentDto missionContent;
	private GameResult result;
	private GameResult finalResult;
	private LocalDateTime updatedAt;

	public RoundMissionDto(RoundMission roundMission) {
		this.id = roundMission.getId();
		this.missionContent = new MissionContentDto(roundMission.getMissionContent());
		this.result = roundMission.getResult();
		this.finalResult = roundMission.getFinalResult();
		this.updatedAt = roundMission.getUpdatedAt();
	}
}
