package com.universe.uni.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {

	private final int status;
	private final String message;

	public static ErrorResponse error(ErrorType error) {
		return new ErrorResponse(error.getHttpStatusCode(), error.getMessage());
	}

	public static ErrorResponse error(ErrorType error, String message) {
		return new ErrorResponse(error.getHttpStatusCode(), message);
	}
}
