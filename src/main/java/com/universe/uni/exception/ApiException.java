package com.universe.uni.exception;

import com.universe.uni.exception.dto.ErrorType;

public class ApiException extends RuntimeException {
	private final ErrorType error;

	public ApiException(ErrorType error, String message) {
		super(message);
		this.error = error;
	}

	public int getHttpStatus() {
		return this.error.getHttpStatusCode();
	}

	public ErrorType getError() {
		return this.error;
	}
}
