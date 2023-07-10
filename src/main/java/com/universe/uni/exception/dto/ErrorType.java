package com.universe.uni.exception.dto;

import java.util.Arrays;

import org.springframework.http.HttpStatus;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ErrorType {

	/**
	 * 400 BAD REQUEST
	 */
	VALIDATION_EXCEPTION(HttpStatus.BAD_REQUEST, "UE1001", "잘못된 요청입니다."),
	VALIDATION_REQUEST_MISSING_EXCEPTION(HttpStatus.BAD_REQUEST, "UE1002", "요청값이 입력되지 않았습니다."),

	/**
	 * 401 UNAUTHORIZED
	 */
	EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED, "UE2001", "만료된 토큰 입니다."),
	UNSUPPORTED_TOKEN(HttpStatus.UNAUTHORIZED, "UE2002", "잘못된 토큰 입니다"),

	/**
	 * 404 NOT FOUND
	 */
	METHOD_NOT_SUPPORTED(HttpStatus.NOT_FOUND, "UE5001", "존제하지 않는 EndPoint 입니다"),
	NOT_FOUND_USER_EXCEPTION(HttpStatus.NOT_FOUND, "UE5002", "존재하지 않는 유저입니다"),

	/**
	 * 406 Not Acceptable
	 */
	NOT_ACCEPTABLE(HttpStatus.NOT_ACCEPTABLE, "UE7001", "Accept 헤더에 유효하지 않거나 지원되지 않는 미디어 유형을 지정되었습니다."),

	/**
	 * 409 CONFLICT
	 */
	ALREADY_EXIST_USER_EXCEPTION(HttpStatus.CONFLICT, "UE10001", "이미 존재하는 유저입니다"),

	/**
	 * 415 Unsupported Media Type.
	 */
	UNSUPPORTED_METHOD_TYPE(HttpStatus.UNSUPPORTED_MEDIA_TYPE, "UE16001", "지원하지 않는 미디어 포멧입니다."),

	/**
	 * 500 INTERNAL SERVER ERROR
	 */
	INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "UE0500", "예상치 못한 서버 에러가 발생하였습니다."),

	/**
	 * 503 Service Unavailable
	 */
	SERVICE_UNAVAILABLE(HttpStatus.SERVICE_UNAVAILABLE, "UE0503", "서버가 작동중이지 않거나 과부하 상태입니다.");

	private final HttpStatus httpStatus;
	private final String uniErrorCode;
	private final String message;

	public int getHttpStatusCode() {
		return httpStatus.value();
	}

	public String getUniErrorCode() {
		return uniErrorCode;
	}

	public String getMessage() {
		return message;
	}

	private boolean hasErrorType(HttpStatus httpStatus) {
		return this.httpStatus.value() == httpStatus.value();
	}

	public static ErrorType findErrorTypeBy(HttpStatus httpStatus) {
		return Arrays.stream(values()).filter((errorType -> errorType.hasErrorType(httpStatus)))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("UnSupported Business HttpStatus Code :" + httpStatus));
	}
}
