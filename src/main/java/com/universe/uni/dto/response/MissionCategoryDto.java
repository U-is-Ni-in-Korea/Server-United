package com.universe.uni.dto.response;

import com.universe.uni.domain.MissionType;
import com.universe.uni.domain.entity.MissionCategory;

import lombok.Getter;

@Getter
public class MissionCategoryDto {
	private final Long id;
	private final String title;
	private final String description;
	private final String tip;
	private final String image;
	private final int level;
	private final int expectedTime;
	private final MissionType missionType;

	public MissionCategoryDto(MissionCategory missionCategory) {
		this.id = missionCategory.getId();
		this.title = missionCategory.getTitle();
		this.description = missionCategory.getDescription();
		this.tip = missionCategory.getTip();
		this.image = missionCategory.getImage();
		this.level = missionCategory.getLevel();
		this.expectedTime = missionCategory.getExpectedTime();
		this.missionType = missionCategory.getMissionType();
	}
}
