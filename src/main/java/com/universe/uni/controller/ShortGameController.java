package com.universe.uni.controller;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.universe.uni.dto.CreateShortGameRequestDto;
import com.universe.uni.dto.CreateShortGameResponseDto;
import com.universe.uni.service.GameService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ShortGameController {

	private final GameService gameService;

	@PostMapping("/game/short")
	public CreateShortGameResponseDto createShortGame(@RequestBody @Valid final CreateShortGameRequestDto createShortGameRequestDto) {
		return gameService.createShortGame(createShortGameRequestDto);
	}

}
