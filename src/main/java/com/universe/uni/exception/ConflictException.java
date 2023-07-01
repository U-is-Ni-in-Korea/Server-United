package com.universe.uni.exception;

import com.universe.uni.exception.dto.ErrorType;

public class ConflictException extends ApiException {

	public ConflictException(ErrorType error) {
		super(error, error.getMessage());
	}
}
