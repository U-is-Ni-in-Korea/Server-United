package com.universe.uni.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.universe.uni.domain.GameResult;
import com.universe.uni.domain.entity.MissionCategory;
import com.universe.uni.domain.entity.MissionContent;
import com.universe.uni.domain.entity.RoundMission;

import lombok.Getter;

@Getter
public class RoundMissionVO {
	private long roundMissionId;
	private MissionContent missionContent;
	private GameResult result;
	private GameResult finalResult;
	private LocalDateTime updatedAt;

	public RoundMissionVO(RoundMission roundMission){
		this.roundMissionId = roundMission.getId();
		this.missionContent = roundMission.getMissionContent();
		this.result = roundMission.getResult();
		this.finalResult = roundMission.getFinalResult();
		this.updatedAt = roundMission.getUpdatedAt();
	}
}
