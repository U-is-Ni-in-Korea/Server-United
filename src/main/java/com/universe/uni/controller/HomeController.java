package com.universe.uni.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.universe.uni.dto.response.HomeResponseDto;
import com.universe.uni.service.HomeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/home")
public class HomeController {

	private final HomeService homeService;

	@GetMapping()
	@ResponseStatus(HttpStatus.OK)
	public HomeResponseDto getHome() {
		return homeService.getHome();
	}
}
