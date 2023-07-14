package com.universe.uni.dto.request;

import javax.validation.constraints.NotNull;

import lombok.Getter;

@Getter
public class EnterGameResultDto {

	@NotNull
	private Boolean result;
}
