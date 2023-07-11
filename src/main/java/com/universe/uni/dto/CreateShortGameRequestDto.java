package com.universe.uni.dto;

import javax.validation.constraints.NotNull;

import lombok.Getter;

@Getter
public class CreateShortGameRequestDto {

	@NotNull
	private Long missionCategoryId;

	@NotNull
	private String wishContent;
}
