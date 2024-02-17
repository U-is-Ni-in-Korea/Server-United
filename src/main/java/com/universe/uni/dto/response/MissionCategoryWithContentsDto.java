package com.universe.uni.dto.response;

import java.util.List;

import com.universe.uni.domain.entity.MissionCategory;
import com.universe.uni.dto.MissionContentDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Schema(description = "미션 카테고리 정보 DTO")
@Getter
public class MissionCategoryWithContentsDto extends MissionCategoryDto {

	private final List<MissionContentDto> missionContentsDto;

	public MissionCategoryWithContentsDto(
		MissionCategory missionCategory,
		List<MissionContentDto> missionContentsDto
	) {
		super(missionCategory);
		this.missionContentsDto = missionContentsDto;
	}
}
