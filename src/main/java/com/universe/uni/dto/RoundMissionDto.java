package com.universe.uni.dto;

import java.time.LocalDateTime;

import com.universe.uni.domain.GameResult;
import com.universe.uni.domain.entity.MissionContent;
import com.universe.uni.domain.entity.RoundMission;

import lombok.Getter;

@Getter
public class RoundMissionDto {
	private long roundMissionId;
	private MissionContent missionContent;
	private GameResult result;
	private GameResult finalResult;
	private LocalDateTime updatedAt;

	public RoundMissionDto(RoundMission roundMission) {
		this.roundMissionId = roundMission.getId();
		this.missionContent = roundMission.getMissionContent();
		this.result = roundMission.getResult();
		this.finalResult = roundMission.getFinalResult();
		this.updatedAt = roundMission.getUpdatedAt();
	}
}
