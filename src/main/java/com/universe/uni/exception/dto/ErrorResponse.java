package com.universe.uni.exception.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {

	private final String message;

	public static ErrorResponse error(ErrorType error) {
		return new ErrorResponse(error.getMessage());
	}

	public static ErrorResponse error(ErrorType error, String message) {
		return new ErrorResponse(message);
	}
}
