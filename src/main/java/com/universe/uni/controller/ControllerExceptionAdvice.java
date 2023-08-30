package com.universe.uni.controller;

import com.universe.uni.exception.ApiException;
import com.universe.uni.exception.dto.ErrorResponse;
import com.universe.uni.exception.dto.ErrorType;
import com.universe.uni.external.SentryExceptionSender;

import feign.FeignException;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

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
        sendSentryEvent(exception, request);
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
        sendSentryEvent(exception, request);
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
        sendSentryEvent(exception, request);
        ErrorResponse errorResponse = ErrorResponse.businessErrorOf(ErrorType.INVALID_REQUEST_METHOD);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingRequestHeaderException.class)
    protected ResponseEntity<Object> handleMissingRequestHeaderException(
            MissingRequestHeaderException exception,
            WebRequest request
    ) {
        sendSentryEvent(exception, request);
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
        sendSentryEvent(ex, request);
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
    protected ErrorResponse handleNonCatchingException(
            final Exception exception,
            WebRequest request
    ) {
        sendSentryEvent(exception, request);
        return ErrorResponse.businessErrorOf(ErrorType.INTERNAL_SERVER_ERROR);
    }

    /**
     * Api custom error
     */
    @ExceptionHandler(ApiException.class)
    protected ResponseEntity<ErrorResponse> handleCustomException(
            ApiException exception,
            WebRequest request
    ) {
        sendSentryEvent(exception, request);
        return ResponseEntity.status(exception.getHttpStatus())
                .body(ErrorResponse.businessErrorOf(exception.getError()));
    }

    @ExceptionHandler(FeignException.class)
    protected ResponseEntity<Object> handleFeginException(
            FeignException exception,
            WebRequest request
    ) {
        sendSentryEvent(exception, request);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ErrorResponse.businessErrorOf(ErrorType.INTERNAL_SERVER_ERROR));
    }

    @ExceptionHandler(ObjectOptimisticLockingFailureException.class)
    public ResponseEntity<ErrorResponse> handleOptimisticLock(ObjectOptimisticLockingFailureException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(ErrorResponse.businessErrorOf(ErrorType.ALREADY_GAME_CREATED));
    }

    private void sendSentryEvent(
            Exception exception,
            WebRequest request
    ) {
        SentryExceptionSender.captureException(exception, request);
    }
}
