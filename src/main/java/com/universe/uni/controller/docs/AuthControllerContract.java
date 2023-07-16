package com.universe.uni.controller.docs;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.universe.uni.dto.AuthTokenDto;
import com.universe.uni.exception.dto.ErrorResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "유저 인증", description = "유저 인증 및 로그인/회원가입 api")
public interface AuthControllerContract {

	@Operation(
			summary = "kakao 인증",
			description = "kakao 인증을 통해 유저 인증 토큰을 생성합니다."
	)
	@ApiResponses(value = {
			@ApiResponse(
					responseCode = "200",
					description = "성공",
					content = @Content(schema = @Schema(implementation = AuthTokenDto.class))
			),
			@ApiResponse(
					responseCode = "UE500",
					description = "사용자 인증의 실패한 경우 입니다.",
					content = @Content(schema = @Schema(implementation = ErrorResponse.class))
			)
	})
	@GetMapping("kakao")
	AuthTokenDto redirectKakaoAuth(
			@Parameter(
					description = "Kakao 에서 받은 인증 코드",
					content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)
			)
			@RequestParam(name = "code") String authenticationCode
	);

	@Operation(
			summary = "google 인증",
			description = "Google 인증을 통해 유저 인증 토큰을 생성합니다."
	)
	@ApiResponses(value = {
			@ApiResponse(
					responseCode = "200",
					description = "성공",
					content = @Content(schema = @Schema(implementation = AuthTokenDto.class))
			),
			@ApiResponse(
					responseCode = "UE500",
					description = "사용자 인증의 실패한 경우 입니다.",
					content = @Content(schema = @Schema(implementation = ErrorResponse.class))
			)
	})
	@GetMapping("google")
	AuthTokenDto redirectGoogleAuth(
			@Parameter(
					description = "Google 에서 받은 인증 코드",
					content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)
			)
			@RequestParam(name = "code") String authenticationCode
	);
}
