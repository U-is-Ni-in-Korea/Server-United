package com.universe.uni.exception;

import com.universe.uni.exception.dto.ErrorType;

public class BadRequestException extends ApiException {

	public BadRequestException(ErrorType error) {
		super(error, error.getMessage());
	}
}
