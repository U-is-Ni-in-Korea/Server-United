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
	INVALID_REQUEST_METHOD(HttpStatus.BAD_REQUEST, "UE1001",
		"요청 방식이 잘못된 경우입니다. 요청 방식 자체가 잘못된 경우입니다."),
	VALIDATION_TOKEN_MISSING_EXCEPTION(HttpStatus.BAD_REQUEST, "UE1002",
		"요청 시 토큰이 누락되어 토큰 값이 없는 경우입니다."),
	ALREADY_GAME_CREATED(HttpStatus.BAD_REQUEST, "UE1003",
		"이미 생성된 승부가 있습니다."),
	USER_NOT_EXISTENT(HttpStatus.BAD_REQUEST, "UE1004", "존재하지 않는 유저의 요청"),
	ALREADY_GAME_DONE(HttpStatus.BAD_REQUEST, "UE1005", "이미 종료된 라운드입니다."),
	COUPLE_NOT_EXISTENT(HttpStatus.BAD_REQUEST, "UE1006", "존재하지 않는 커플 id 입니다"),
	INVALID_INVITE_CODE(HttpStatus.BAD_REQUEST, "UE1007", "올바르지 않은 초대 코드입니다."),
	TOKEN_VALUE_NOT_EXIST(HttpStatus.BAD_REQUEST, "UE1008", "토큰 값이 존재하지 않습니다."),
	PARTNER_RESULT_NOT_ENTERED(HttpStatus.BAD_REQUEST, "UE1009",
		"상대방이 아직 결과를 입력하지 않았습니다."),

	/**
	 * 401 Unauthorized
	 */
	EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED, "UE2001", "토큰이 만료된 경우입니다."),
	UNSUPPORTED_TOKEN(HttpStatus.UNAUTHORIZED, "UE2002",
		"서버에서 인증하지 않는 방식의 토큰 혹은 변조된 토큰을 사용한 경우입니다."),
	EMPTY_SECURITY_CONTEXT(HttpStatus.UNAUTHORIZED, "UE2003",
		"Security Context에 인증 정보가 없습니다."),


	/**
	 * 404 NOT FOUND
	 */
	INVALID_ENDPOINT_EXCEPTION(HttpStatus.NOT_FOUND, "UE5001",
		"잘못된 endpoint에 요청한 경우입니다."),
	NOT_FOUND_USER(HttpStatus.NOT_FOUND, "UE5002",
		"조회한 유저가 존재하지 않는 경우 입니다."),
	NOT_FOUND_MISSION_CATEGORY_EXCEPTION(HttpStatus.NOT_FOUND, "UE5003",
		"존재하지 않는 미션 카테고리입니다"),
	NOT_FOUND_MISSION_CONTENT(HttpStatus.NOT_FOUND, "UE5004",
		"해당 카테고리 미션이 존재하지 않습니다."),
	NOT_FOUND_ROUND_MISSION(HttpStatus.NOT_FOUND, "UE5005",
		"해당 라운드 미션이 존재하지 않습니다."),
	NOT_FOUND_WISH_COUPON(HttpStatus.NOT_FOUND, "UE5006", "소원권이 존재하지 않습니다."),
	NOT_FOUND_ROUND_GAME(HttpStatus.NOT_FOUND, "UE5007", "해당 라운드 게임이 존재하지 않습니다."),
	NOT_FOUND_COUPLE(HttpStatus.NOT_FOUND, "UE5008", "커플이 존재하지 않습니다."),

	/**
	 * 406 Not Acceptable
	 */
	UNSUPPORTED_MEDIA_TYPE_EXCEPTION(HttpStatus.NOT_ACCEPTABLE, "UE7001",
		"Accept 헤더에 유효하지 않거나 지원되지 않는 미디어 유형을 지정한 경우입니다."),

	/**
	 * 409 CONFLICT
	 */
	USER_ALREADY_EXISTS_EXCEPTION(HttpStatus.CONFLICT, "UE10001",
		"이미 존재하는 유저에 대한 생성에 대한 경우입니다."),
	COUPLE_ALREADY_CONNECTED(HttpStatus.CONFLICT, "UE10002",
		"이미 커플이 연결된 초대코드입니다."),

	/**
	 * 415 Unsupported Media Type
	 */
	UNSUPPORTED_MEDIA_FORMAT_EXCEPTION(HttpStatus.UNSUPPORTED_MEDIA_TYPE, "UE16001",
		"지원하지 않는 미디어 포멧으로 요청한 경우 입니다."),

	/**
	 * 500 INTERNAL SERVER ERROR
	 */
	INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "UE500",
		"서버 내부 오류입니다."),

	/**
	 * 503 Service Unavailable
	 */
	SERVICE_UNAVAILABLE_EXCEPTION(HttpStatus.SERVICE_UNAVAILABLE, "UE503",
		"서버가 작동중이지 않거나 과부하 상태입니다.");

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
