package com.universe.uni.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.universe.uni.domain.SnsType;
import com.universe.uni.domain.entity.User;
import com.universe.uni.dto.AuthTokenDto;
import com.universe.uni.external.response.KakaoAuthResponse;
import com.universe.uni.external.response.KakaoUserResponse;
import com.universe.uni.repository.KakaoRepository;
import com.universe.uni.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService implements AuthServiceContract {

	private final JwtManager jwtManager;
	private final KakaoRepository kakaoRepository;
	private final UserRepository userRepository;

	@Override
	@Transactional
	public AuthTokenDto authWithKakao(String code) {
		final KakaoAuthResponse kakaoToken = kakaoRepository.fetchTokenBy(code);
		final KakaoUserResponse kakaoUser = kakaoRepository.getUser(kakaoToken.accessToken());
		final User user = userRepository.findBySnsAuthCode(kakaoUser.id().toString())
			.orElseGet(() -> userRepository.save(registerKakaoUser(kakaoUser)));
		return beIssuedAuthToken(user.getId());
	}

	private User registerKakaoUser(KakaoUserResponse kakaoUser) {
		return User.builder()
			.snsType(SnsType.KAKAO)
			.snsAuthCode(kakaoUser.id().toString())
			.build();
	}

	private AuthTokenDto beIssuedAuthToken(long userId) {
		return jwtManager.issueToken(userId);
	}
}
