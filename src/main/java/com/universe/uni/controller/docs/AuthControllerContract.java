package com.universe.uni.controller.docs;

import com.universe.uni.dto.AuthTokenDto;
import com.universe.uni.dto.request.AuthRequestDto;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = AuthTokenDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "사용자 인증의 실패한 경우 입니다.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(example = "{ \"code\": \"UE500\"}")
                    )
            )
    })
    @PostMapping("kakao")
    AuthTokenDto authByKakao(
            @Parameter(
                    description = "Kakao 에서 받은 인증 코드",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)
            )
            @RequestBody AuthRequestDto request
    );

    @Operation(
            summary = "google 인증",
            description = "Google 인증을 통해 유저 인증 토큰을 생성합니다."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "성공",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = AuthTokenDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "사용자 인증의 실패한 경우 입니다.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(example = "{ \"code\": \"UE500\"}")
                    )
            )
    })
    @PostMapping("google")
    AuthTokenDto authByGoogle(
            @Parameter(
                    description = "Google 에서 받은 인증 코드",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)
            )
            @RequestBody AuthRequestDto request
    );
}
