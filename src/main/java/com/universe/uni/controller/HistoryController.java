package com.universe.uni.controller;

import java.util.List;

import com.universe.uni.controller.docs.HistoryControllerContract;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.universe.uni.dto.response.GameHistoryResponseDto;
import com.universe.uni.service.HistoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/history")
public class HistoryController implements HistoryControllerContract {
	private final HistoryService historyService;

	@GetMapping()
	@ResponseStatus(HttpStatus.OK)
	@Override
	public List<GameHistoryResponseDto> getHome() {
		return historyService.getGameHistory();
	}
}
