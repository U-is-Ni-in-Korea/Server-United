package com.universe.uni.exception.dto;

import org.springframework.http.HttpStatus;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ErrorType {

	/**
	 * 400 BAD REQUEST
	 */
	VALIDATION_EXCEPTION(HttpStatus.BAD_REQUEST, "", "잘못된 요청입니다."),
	VALIDATION_REQUEST_MISSING_EXCEPTION(HttpStatus.BAD_REQUEST, "", "요청값이 입력되지 않았습니다."),

	/**
	 * 404 NOT FOUND
	 */
	NOT_FOUND_USER_EXCEPTION(HttpStatus.NOT_FOUND, "", "존재하지 않는 유저입니다"),
	NOT_FOUND_MISSION_CATEGORY_EXCEPTION(HttpStatus.NOT_FOUND, "", "존재하지 않는 미션 카테고리입니다"),
	NOT_FOUND_MISSION_CONTENT(HttpStatus.NOT_FOUND, "", "해당 카테고리 미션이 존재하지 않습니다."),
	NOT_FOUND_ROUND_MISSION(HttpStatus.NOT_FOUND, "", "해당 라운드 미션이 존재하지 않습니다."),

	/**
	 * 409 CONFLICT
	 */
	ALREADY_EXIST_USER_EXCEPTION(HttpStatus.CONFLICT, "", "이미 존재하는 유저입니다"),

	/**
	 * 500 INTERNAL SERVER ERROR
	 */
	INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "", "예상치 못한 서버 에러가 발생하였습니다.");

	private final HttpStatus httpStatus;
	private String uniErrorCode;
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
}
