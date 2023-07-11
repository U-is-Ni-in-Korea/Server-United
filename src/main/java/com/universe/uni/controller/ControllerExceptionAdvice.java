package com.universe.uni.controller;

import java.util.Objects;

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
		MethodArgumentNotValidException exception,
		HttpHeaders headers,
		HttpStatus status,
		WebRequest request
	) {
		ErrorResponse errorResponse = ErrorResponse.businessErrorOf(ErrorType.INVALID_REQUEST_METHOD);
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(
		MissingServletRequestParameterException exception,
		HttpHeaders headers,
		HttpStatus status,
		WebRequest request
	) {
		ErrorResponse errorResponse = ErrorResponse.businessErrorOf(ErrorType.INVALID_REQUEST_METHOD);
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleMissingPathVariable(
		MissingPathVariableException exception,
		HttpHeaders headers,
		HttpStatus status,
		WebRequest request
	) {
		ErrorResponse errorResponse = ErrorResponse.businessErrorOf(ErrorType.INVALID_REQUEST_METHOD);
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MissingRequestHeaderException.class)
	protected ResponseEntity<Object> handleMissingRequestHeaderException(
		MissingRequestHeaderException exception
	) {
		ErrorResponse errorResponse = ErrorResponse.businessErrorOf(ErrorType.INVALID_REQUEST_METHOD);
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(
		Exception ex,
		Object body,
		HttpHeaders headers,
		HttpStatus status,
		WebRequest request
	) {
		try {
			final ErrorType errorType = ErrorType.findErrorTypeBy(status);
			final ApiException businessError = new ApiException(errorType, ex.getMessage());
			final ErrorResponse errorResponse = ErrorResponse.businessErrorOf(errorType);
			return super.handleExceptionInternal(businessError, errorResponse, headers, status, request);
		} catch (IllegalArgumentException exception) {
			final ErrorResponse errorResponse = ErrorResponse.errorOf(status.value());
			return super.handleExceptionInternal(exception, errorResponse, headers, status, request);
		}
	}

	/**
	 * 500 Internal Server
	 */
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	protected ErrorResponse handleException(
		final Exception exception
	) {
		return ErrorResponse.businessErrorOf(ErrorType.INTERNAL_SERVER_ERROR);
	}

	/**
	 * Api custom error
	 */
	@ExceptionHandler(ApiException.class)
	protected ResponseEntity<ErrorResponse> handleCustomException(
		ApiException exception
	) {
		return ResponseEntity.status(exception.getHttpStatus())
			.body(ErrorResponse.businessErrorOf(exception.getError()));
	}
}
