package com.universe.uni.dto.response;

import com.universe.uni.domain.MissionTool;
import com.universe.uni.domain.MissionType;
import com.universe.uni.domain.entity.MissionCategory;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Schema(description = "미션 카테고리 정보 DTO")
@Getter
public class MissionCategoryDto {
	private final Long id;
	private final String title;
	private final String description;
	private final String rule;
	private final String tip;
	private final String example;
	private final String image;
	private final int level;
	private final int expectedTime;
	@Schema(description = "미션 타입 [SAME, DIFFERENCE] enum 값")
	private final MissionType missionType;
	@Schema(description = "미션 도구[MEMO,TIMER,NONE] enum 값")
	private final MissionTool missionTool;

	public MissionCategoryDto(MissionCategory missionCategory) {
		this.id = missionCategory.getId();
		this.title = missionCategory.getTitle();
		this.description = missionCategory.getDescription();
		this.rule = missionCategory.getRule();
		this.tip = missionCategory.getTip();
		this.example = missionCategory.getExample();
		this.image = missionCategory.getImage();
		this.level = missionCategory.getLevel();
		this.expectedTime = missionCategory.getExpectedTime();
		this.missionType = missionCategory.getMissionType();
		this.missionTool = missionCategory.getMissionTool();
	}
}
