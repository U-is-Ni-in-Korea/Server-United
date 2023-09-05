package com.universe.uni.dto.response;

import com.universe.uni.domain.entity.MissionContent;

import lombok.Getter;

@Getter
public class RoundMissionContentDto {
	private final Long id;
	private final MissionCategoryDto missionCategory;
	private final String content;
	private final String recommendTime;

	public RoundMissionContentDto(MissionContent missionContent) {
		this.id = missionContent.getId();
		this.missionCategory = new MissionCategoryDto(missionContent.getMissionCategory());
		this.content = missionContent.getContent();
		this.recommendTime = missionContent.getRecommendTime();
	}
}