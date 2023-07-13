package com.universe.uni.dto.response;

import com.universe.uni.domain.entity.MissionContent;

import lombok.Getter;

@Getter
public class RoundMissionContentDto {
	private Long id;
	private MissionCategoryDto missionCategory;
	private String content;
	private String image;

	public RoundMissionContentDto(MissionContent missionContent) {
		this.id = missionContent.getId();
		this.missionCategory = new MissionCategoryDto(missionContent.getMissionCategory());
		this.content = missionContent.getContent();
		this.image = missionContent.getImage();
	}
}