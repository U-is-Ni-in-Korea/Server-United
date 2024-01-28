package com.universe.uni.controller;

import com.universe.uni.controller.docs.HomeControllerV1Contract;
import com.universe.uni.dto.response.HomeResponseDto;
import com.universe.uni.service.HomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/home")
public class HomeControllerV1 implements HomeControllerV1Contract {

	private final HomeService homeService;

	@GetMapping()
	@Override
	@ResponseStatus(HttpStatus.OK)
	public HomeResponseDto getHome() {
		return homeService.getHome();
	}
}
