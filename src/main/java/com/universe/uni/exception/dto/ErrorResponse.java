package com.universe.uni.exception.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {

	@JsonProperty("code")
	private final String uniErrorCode;

	public static ErrorResponse businessErrorOf(ErrorType error) {
		return new ErrorResponse(error.getUniErrorCode());
	}

	public static ErrorResponse errorOf(int errorCode) {
		return new ErrorResponse("UE0" + errorCode);
	}
}
