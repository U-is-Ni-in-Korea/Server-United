package com.universe.uni.exception.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "서버 에러 발생시 에러 바디 DTO")
public class ErrorResponse {

	@JsonProperty("code")
    @Schema(description = "자체 에러 코드, 별도 문서 혹은 각 api 에러 코드 확인")
	private final String uniErrorCode;

	public static ErrorResponse businessErrorOf(ErrorType error) {
		return new ErrorResponse(error.getUniErrorCode());
	}

	public static ErrorResponse errorOf(int errorCode) {
		return new ErrorResponse("UE0" + errorCode);
	}
}
