package com.universe.uni.controller.docs;

import com.universe.uni.dto.response.GameHistoryResponseDto;
import com.universe.uni.exception.dto.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@Tag(
        name = "Game History",
        description = "사용자의 게임 기록 관련 API"
)
public interface HistoryControllerContract {

    @Operation(
            summary = "승부 히스토리 전체 조회",
            description = "사용자는 자신이 진행한 게임의 전체 히스토리를 조회 할 수 있다.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "성공",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = GameHistoryResponseDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404-UE5003",
                            description = "존재하지 않는 미션 카테고리입니다",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrorResponse.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "500-UE500",
                            description = "존재하지 않는 미션 카테고리입니다",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrorResponse.class)
                            )
                    )
            }
    )
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    List<GameHistoryResponseDto> getHome();
}
