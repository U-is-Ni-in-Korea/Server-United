package com.universe.uni.repository;

import com.universe.uni.external.response.KakaoAuthResponse;

public interface KakaoRepository {
	KakaoAuthResponse fetchTokenBy(String code);
}
