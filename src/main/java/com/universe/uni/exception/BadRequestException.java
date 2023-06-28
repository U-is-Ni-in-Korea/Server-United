package com.universe.uni.exception;

import com.universe.uni.common.dto.ErrorType;

public class BadRequestException extends ApiException {

	public BadRequestException(ErrorType error) {
		super(error, error.getMessage());
	}
}
