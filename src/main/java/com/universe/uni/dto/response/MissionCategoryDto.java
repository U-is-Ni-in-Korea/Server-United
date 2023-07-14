package com.universe.uni.dto.response;

import com.universe.uni.domain.MissionType;
import com.universe.uni.domain.entity.MissionCategory;

import lombok.Getter;

@Getter
public class MissionCategoryDto {
	private Long id;
	private String title;
	private String description;
	private String tip;
	private String image;
	private int level;
	private int expectedTime;
	private MissionType missionType;

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
