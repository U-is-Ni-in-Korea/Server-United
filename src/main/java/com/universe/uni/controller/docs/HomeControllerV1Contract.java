package com.universe.uni.controller.docs;

import com.universe.uni.dto.response.HomeResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Tag(
        name = "Home",
        description = "홈 화면에서 필요한 데이터 관련 API 입니다."
)
public interface HomeControllerV1Contract {
    @Operation(
            summary = "홈 화면 조회",
            description = "사용자는 홈에 필요한 정보를 얻을 수 있다.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "성공",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = HomeResponseDto.class)
                            )
                    )
            }
    )
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    HomeResponseDto getHome();
}
