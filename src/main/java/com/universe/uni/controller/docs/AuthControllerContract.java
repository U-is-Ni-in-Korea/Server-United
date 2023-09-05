package com.universe.uni.controller.docs;

import com.universe.uni.dto.AuthTokenDto;
import com.universe.uni.dto.request.AuthRequestDto;
import com.universe.uni.exception.dto.ErrorResponse;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

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
                    description = "UE500: 사용자 인증에 실패한 경우 입니다.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponse.class)
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
                    description = "UE500: 사용자 인증에 실패한 경우 입니다.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponse.class)
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

    @Operation(
            summary = "Apple 인증",
            description = "Apple 인증을 통해 유저 인증 토큰을 생성합니다."
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
                    description = "UE500: 사용자 인증에 실패한 경우 입니다",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )

    })
    @PostMapping("apple")
    AuthTokenDto authByApple(
            @Parameter(
                    description = "Apple 에서 받은 identity Token",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = AuthRequestDto.class)
                    )
            )
            @RequestBody AuthRequestDto request
    );

    @Operation(
            summary = "카카오 인증 Redirect Link",
            description = "카카오 웹 페이지 로그인을 통해 로그인한 코드를 받을 경우 사용합니다. 일반적으로 사용되지 않는 api",
            hidden = true
    )
    @GetMapping("kakao/callback")
    AuthTokenDto redirectKakaoAuth(
            @RequestParam(name = "code") String authenticationCode
    );

    @Operation(
            summary = "구글 인증 Redirect Link",
            description = "구글 웹 페이지 로그인을 통해 로그인한 코드를 받을 경우 사용합니다. 일반적으로 사용되지 않는 api",
            hidden = true
    )
    @GetMapping("google/callback")
    AuthTokenDto redirectGoogleAuth(
            @RequestParam(name = "code") String authenticationCode
    );
}
