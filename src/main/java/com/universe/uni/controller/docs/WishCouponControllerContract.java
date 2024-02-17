package com.universe.uni.controller.docs;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.universe.uni.dto.request.UpdateWishCouponRequestDto;
import com.universe.uni.dto.response.WishCouponResponseDto;
import com.universe.uni.exception.dto.ErrorResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(
	name = "Wish Coupon",
	description = "사용자의 소원권 관련 API 입니다."
)
public interface WishCouponControllerContract {

	@Operation(
		summary = "소원권 생성 및 수정",
		description = "사용자는 게임으로 얻은 백지 소원권을 생성하거나 소원권을 수정할 수 있다.",
		responses = {
			@ApiResponse(
				responseCode = "204",
				description = "소원권 생성 및 수정 성공"
			),
			@ApiResponse(
				responseCode = "404-UE1001",
				description = "요청 방식이 잘못된 경우입니다. 요청 방식 자체가 잘못된 경우입니다.",
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
	@PatchMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	void updateWishCoupon(@RequestBody UpdateWishCouponRequestDto requestDto);

	@Operation(
		summary = "소원권 사용",
		description = "사용자가 소원권을 사용하면 사용된 소원권으로 변경된다",
		responses = {
			@ApiResponse(
				responseCode = "204",
				description = "소원권 사용 성공"
			),
			@ApiResponse(
				responseCode = "404-UE5001",
				description = "잘못된 endpoint에 요청한 경우입니다.",
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
	@PatchMapping("/{wishCouponId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	void useWishCoupon(
		@Parameter(required = true)
		@PathVariable Long wishCouponId
	);

	@Operation(
		summary = "소원권 상세 조회",
		description = "사용자는 소원권의 상세 정보를 조회할 수 있다.",
		responses = {
			@ApiResponse(
				responseCode = "200",
				description = "성공",
				content = @Content(
					mediaType = MediaType.APPLICATION_JSON_VALUE,
					schema = @Schema(implementation = WishCouponResponseDto.class)
				)
			),
			@ApiResponse(
				responseCode = "404-UE5001",
				description = "잘못된 endpoint에 요청한 경우입니다.",
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
	@GetMapping("/{wishCouponId}")
	@ResponseStatus(HttpStatus.OK)
	WishCouponResponseDto getWishCoupon(
		@Parameter(required = true)
		@PathVariable Long wishCouponId
	);
}
