package com.universe.uni.repository;

import org.springframework.stereotype.Repository;

import com.universe.uni.config.AuthConfig;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class RemoteKakaoRepository implements KakaoRepository {
	private final AuthConfig authConfig;

}
