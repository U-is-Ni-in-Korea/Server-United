package com.universe.uni.exception;

import com.universe.uni.exception.dto.ErrorType;

public class UnauthorizedException extends ApiException {

	public UnauthorizedException(ErrorType error) {
		super(error, error.getMessage());
	}
}
