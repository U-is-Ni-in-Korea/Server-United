package com.universe.uni.controller.docs;

import com.universe.uni.dto.response.MissionCategoryResponseDto;
import com.universe.uni.exception.dto.ErrorResponse;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(
        name = "Mission",
        description = "사용자의 미션 관련 API 입니다."
)
public interface MissionControllerContract {
    @Operation(
            summary = "미션 카테고리 상세 조회",
            description = "사용자는 미션 카테고리 아이디를 통해 상세 정보를 조회할 수 있다.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "성공",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = MissionCategoryResponseDto.class)
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
                            description = "서버 내부 오류입니다.",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrorResponse.class)
                            )
                    )
            }
    )
    @GetMapping("/{missionCategoryId}")
    @ResponseStatus(HttpStatus.OK)
    MissionCategoryResponseDto getMissionCategory(
            @Parameter(required = true)
            @PathVariable Long missionCategoryId
    );

    @Operation(
            summary = "미션 카테고리 전체 조회",
            description = "사용자는 게임 생성시 미션의 모든 카테고리를 확인할 수 있다.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "성공",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(
                                            schema = @Schema(implementation = MissionCategoryResponseDto.class)
                                    )
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
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    List<MissionCategoryResponseDto> getMissionCategoryList();
}
