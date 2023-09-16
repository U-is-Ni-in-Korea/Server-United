package com.universe.uni.controller.docs;

import com.universe.uni.dto.request.CreateShortGameRequestDto;
import com.universe.uni.dto.request.EnterGameResultDto;
import com.universe.uni.dto.response.CreateShortGameResponseDto;
import com.universe.uni.dto.response.GameReportResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Tag(
        name = "Short Game",
        description = "한판 승부 관련 API"
)
public interface ShortGameControllerContract {

    @Operation(
            summary = "한판 승부 생성",
            description = "사용자는 커플 단위의 한판 승부를 생성할 수 있다.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "성공",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = CreateShortGameResponseDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400-UE1003",
                            description = "이미 생성된 승부가 있습니다."
                    ),
                    @ApiResponse(
                            responseCode = "500-UE500",
                            description = "서버 내부 오류"
                    )
            }
    )
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    CreateShortGameResponseDto createShortGame(
            @RequestBody @Valid CreateShortGameRequestDto createShortGameRequestDto
    );

    @Operation(
            summary = "한판 승부 기록",
            description = "사용자는 자신의 한판 승부 결과를 기록할 수 있다.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "성공",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = GameReportResponseDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404-UE5005",
                            description = "해당 라운드 미션이 존재하지 않습니다."
                    ),
                    @ApiResponse(
                            responseCode = "500-UE500",
                            description = "서버 내부 오류"
                    )
            }
    )
    @PatchMapping("/{roundGameId}")
    @ResponseStatus(HttpStatus.OK)
    GameReportResponseDto enterGameResult(
            @PathVariable Long roundGameId,
            @RequestBody @Valid EnterGameResultDto enterGameResultDto);
}
