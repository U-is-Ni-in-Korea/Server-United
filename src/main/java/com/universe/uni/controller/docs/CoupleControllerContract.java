package com.universe.uni.controller.docs;

import com.universe.uni.dto.request.CreateCoupleRequestDto;
import com.universe.uni.dto.request.JoinCoupleRequestDto;
import com.universe.uni.dto.response.CoupleConnectionResponseDto;
import com.universe.uni.dto.response.CoupleDto;
import com.universe.uni.exception.dto.ErrorResponse;
import com.universe.uni.exception.dto.ErrorType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Tag(name = "/api/couple", description = "커플 관련 api")
public interface CoupleControllerContract {

    @Operation(
            summary = "기념일로 커플 생성",
            description = "기념일을 통해 커플을 생성할 수 있다."
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "성공",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = CoupleDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "500-UE500",
                            description = "서버 내부 오류입니다.",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrorResponse.class)
                            )
                    )
            }
    )
    @PostMapping("")
    CoupleDto createCoupleBy(
            @Parameter(hidden = true)
            @AuthenticationPrincipal
            Long userId,
            @Parameter(description = "기념일을 담은 Request DTO")
            @RequestBody
            CreateCoupleRequestDto request
    );

    @Operation(
            summary = "초대 코드 입력하기",
            description = "초대 코드를 통해 이미 생성된 커플에 연결할 수 있다."
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "커플 연결 성공"
                    ),
                    @ApiResponse(
                            responseCode = "400-UE1004",
                            description = " 존재하지 않는 유저의 요청",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrorResponse.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400-UE1007",
                            description = "올바르지 않은 초대 코드입니다.",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrorResponse.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "500-UE500",
                            description = "서버 내부 에러입니다",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrorResponse.class)
                            )
                    )
            }
    )
    @PostMapping("join")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void joinCouple(
            @Parameter(hidden = true)
            @AuthenticationPrincipal
            Long userId,
            @Parameter(
                    required = true,
                    description = "초대 코드를 입력하기 위한 Request DTO"
            )
            @RequestBody
            JoinCoupleRequestDto body
    );

    @Operation(
            summary = "커플 연결 확인",
            description = "커플 연결을 확인 할 수 있다."
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "커플 연결 완료",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = CoupleConnectionResponseDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400-UE1004",
                            description = " 존재하지 않는 유저의 요청",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrorResponse.class)
                            )
                    )
            }
    )
    @GetMapping("join")
    @ResponseStatus(HttpStatus.OK)
    CoupleConnectionResponseDto checkConnection(
            @Parameter(hidden = true)
            @AuthenticationPrincipal Long userId
    );
}
