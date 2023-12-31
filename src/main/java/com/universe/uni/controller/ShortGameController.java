package com.universe.uni.controller;

import javax.validation.Valid;

import com.universe.uni.controller.docs.ShortGameControllerContract;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.universe.uni.dto.request.CreateShortGameRequestDto;
import com.universe.uni.dto.request.EnterGameResultDto;
import com.universe.uni.dto.response.CreateShortGameResponseDto;
import com.universe.uni.dto.response.GameReportResponseDto;
import com.universe.uni.service.GameService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/game/short")
public class ShortGameController implements ShortGameControllerContract {

	private final GameService gameService;

	@PostMapping
	@ResponseStatus(HttpStatus.OK)
	@Override
	public CreateShortGameResponseDto createShortGame(
		@RequestBody @Valid final CreateShortGameRequestDto createShortGameRequestDto) {
		return gameService.createShortGame(createShortGameRequestDto);
	}

	@PatchMapping("/{roundGameId}")
	@ResponseStatus(HttpStatus.OK)
	@Override
	public GameReportResponseDto enterGameResult(
		@PathVariable final Long roundGameId,
		@RequestBody @Valid final EnterGameResultDto enterGameResultDto) {
		return gameService.updateGameResult(roundGameId, enterGameResultDto.getResult());
	}

	@GetMapping("/result/{roundGameId}")
	@ResponseStatus(HttpStatus.OK)
	@Override
	public GameReportResponseDto checkFinalGameResult(@PathVariable final Long roundGameId) {
		return gameService.updateFinalGameResult(roundGameId);
	}

	@GetMapping("/{roundGameId}")
	@ResponseStatus(HttpStatus.OK)
	@Override
	public GameReportResponseDto showGameReport(@PathVariable final Long roundGameId) {
		return gameService.getGameReportIfGameIsOngoing(roundGameId);
	}

	@DeleteMapping("/{roundGameId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@Override
	public void quitGame(@PathVariable final Long roundGameId) {
		gameService.quitGame(roundGameId);
	}
}
