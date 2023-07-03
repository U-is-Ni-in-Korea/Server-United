package com.universe.uni.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.universe.uni.exception.ApiException;
import com.universe.uni.exception.dto.ErrorResponse;
import com.universe.uni.exception.dto.ErrorType;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class ControllerExceptionAdvice extends ResponseEntityExceptionHandler {

	/**
	 * 400 BAD_REQUEST
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
		MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		ErrorResponse errorResponse = ErrorResponse.error(ErrorType.VALIDATION_REQUEST_MISSING_EXCEPTION);
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(
		MissingServletRequestParameterException exception,
		HttpHeaders headers, HttpStatus status, WebRequest request) {
		ErrorResponse errorResponse = ErrorResponse.error(ErrorType.VALIDATION_REQUEST_MISSING_EXCEPTION);
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException exception,
		HttpHeaders headers,
		HttpStatus status, WebRequest request) {
		ErrorResponse errorResponse = ErrorResponse.error(ErrorType.VALIDATION_REQUEST_MISSING_EXCEPTION);
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MissingRequestHeaderException.class)
	protected ResponseEntity<Object> handleMissingRequestHeaderException(
		MissingRequestHeaderException exception) {
		ErrorResponse errorResponse = ErrorResponse.error(ErrorType.VALIDATION_EXCEPTION);
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
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
			.body(ErrorResponse.error(exception.getError()));
	}
}
