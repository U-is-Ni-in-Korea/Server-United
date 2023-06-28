package com.universe.uni.common;

import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.universe.uni.common.dto.ErrorResponse;
import com.universe.uni.common.dto.ErrorType;
import com.universe.uni.exception.ApiException;

@RestControllerAdvice
public class ControllerExceptionAdvice {

	/**
	 * 400 BAD_REQUEST
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	protected ErrorResponse handleMethodArgumentNotValidException(final MethodArgumentNotValidException exception) {
		FieldError fieldError = Objects.requireNonNull(exception.getBindingResult().getFieldError());
		String errorMessage = String.format("%s: %s", fieldError.getField(), fieldError.getDefaultMessage());
		return ErrorResponse.error(ErrorType.VALIDATION_REQUEST_MISSING_EXCEPTION,
			String.format("%s", fieldError.getDefaultMessage(), fieldError.getField()));
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MissingServletRequestParameterException.class)
	protected ErrorResponse handleMissingServletRequestParameterException(
		MissingServletRequestParameterException exception) {
		String errorMessage = String.format("'%s' 파라미터가 누락되었습니다.", exception.getParameterName());
		return ErrorResponse.error(ErrorType.VALIDATION_EXCEPTION, errorMessage);
	}

	/**
	 * 500 Internal Server
	 */
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	protected ErrorResponse handleException(final Exception exception) {
		return ErrorResponse.error(ErrorType.INTERNAL_SERVER_ERROR);
	}

	/**
	 * Api custom error
	 */
	@ExceptionHandler(ApiException.class)
	protected ResponseEntity handleCustomException(ApiException exception) {
		return ResponseEntity.status(exception.getHttpStatus())
			.body(ErrorResponse.error(exception.getError(), exception.getMessage()));
	}
}
