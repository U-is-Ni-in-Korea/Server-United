package com.universe.uni.dto.response;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DTO for {@link com.universe.uni.domain.entity.Couple}
 */
@Schema(description = "커플 연결 결과 DTO")
@JsonIgnoreProperties(ignoreUnknown = true)
public record CoupleDto(
	@Schema(description = "커플 아이디")
	Long id,
	@Schema(description = "커플 디데이")
	LocalDate startDate,
	@Schema(description = "커플 초대 코드")
	String inviteCode,
	@Schema(description = "커플의 하트 코인 잔여량")
	int heartToken
) implements Serializable {

}