package com.universe.uni.controller;

import com.universe.uni.controller.docs.AuthControllerContract;
import com.universe.uni.dto.AuthTokenDto;
import com.universe.uni.dto.request.AuthRequestDto;
import com.universe.uni.service.AuthServiceContract;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("auth")
public class AuthController implements AuthControllerContract {

    private final AuthServiceContract authService;

    @PostMapping("kakao")
    @Override
    public AuthTokenDto authByKakao(@RequestBody AuthRequestDto request) {
        return authService.authWithKakao(request.code());
    }

    @PostMapping("google")
    @Override
    public AuthTokenDto authByGoogle(@RequestBody AuthRequestDto request) {
        return authService.authWithGoogle(request.code());
    }

    @PostMapping("apple")
    @Override
    public AuthTokenDto authByApple(@RequestBody AuthRequestDto request) {
        return authService.authWithAppleUser(request.code());
    }

    @GetMapping("kakao/callback")
    @Override
    public AuthTokenDto redirectKakaoAuth(
            @RequestParam(name = "code") String authenticationCode
    ) {
        return authService.authWithKakaoCode(authenticationCode);
    }

    @GetMapping("google/callback")
    @Override
    public AuthTokenDto redirectGoogleAuth(
            @RequestParam(name = "code") String authenticationCode
    ) {
        return authService.authWithGoogleCode(authenticationCode);
    }
}
