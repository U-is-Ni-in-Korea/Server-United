package com.universe.uni.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.universe.uni.dto.response.CoupleDto;

/**
 * DTO for {@link com.universe.uni.domain.entity.User}
 */
public record UserDto(
	@NotNull Long id,
	@Size(max = 10)
	String nickname,
	String image,
	CoupleDto couple
) implements Serializable {
}
