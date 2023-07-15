package com.universe.uni.dto.response;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * DTO for {@link com.universe.uni.domain.entity.Couple}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record CoupleDto(
	Long id,
	LocalDate startDate,
	String inviteCode,
	int heartToken
) implements Serializable {
}