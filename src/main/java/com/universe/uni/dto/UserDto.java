package com.universe.uni.dto;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
	/**
	 * DTO for {@link com.universe.uni.domain.entity.Couple}
	 */
	public record CoupleDto(Long id, LocalDate startDate, String inviteCode, int heartToken) implements Serializable {
	}
}
