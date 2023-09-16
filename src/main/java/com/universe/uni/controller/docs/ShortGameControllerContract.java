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

    @Operation(
            summary = "최종 승부 결과 확인",
            description = "사용자는 최종 결과 확인 하기를 통해 최종 결과를 알 수 있다.",
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
                            responseCode = "400-UE1009",
                            description = "상대방이 아직 결과를 입력하지 않았습니다."

                    ),
                    @ApiResponse(
                            responseCode = "404-UE5005",
                            description = "해당 라운드 미션이 존재하지 않습니다."
                    ),
                    @ApiResponse(
                            responseCode = "404-UE5006",
                            description = "소원권이 존재하지 않습니다.(게임 자체에서 소원권이 생성되지 않은 경우라 이 경우는 서버 내부 에러입니다.)"
                    ),
                    @ApiResponse(
                            responseCode = "500-UE500",
                            description = "서버 내부 오류"
                    )
            }
    )
    @PostMapping("/{roundGameId}")
    @ResponseStatus(HttpStatus.OK)
    GameReportResponseDto checkFinalGameResult(@PathVariable Long roundGameId);

    @Operation(
            summary = "한판 승부 조회",
            description = "사용자는 한판 승부 조회 뷰를 통해 한판 승부를 조회할 수 있다.",
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
    @GetMapping("/{roundGameId}")
    @ResponseStatus(HttpStatus.OK)
    GameReportResponseDto showGameReport(@PathVariable Long roundGameId);

    @Operation(
            summary = "승부 중단",
            description = "사용자가 승부 중단하기를 통해 승부를 중단할 수 있다.",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "승부 중단 성공(삭제)"
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
    @DeleteMapping("/{roundGameId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void quitGame(@PathVariable Long roundGameId);
}
