package com.universe.uni.exception;

import com.universe.uni.common.dto.ErrorType;

public class NotFoundException extends ApiException {

	public NotFoundException(ErrorType error) {
		super(error, error.getMessage());
	}
}
