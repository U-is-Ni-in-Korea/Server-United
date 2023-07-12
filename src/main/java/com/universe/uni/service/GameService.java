package com.universe.uni.service;

import static com.universe.uni.domain.entity.QGame.*;
import static com.universe.uni.exception.dto.ErrorType.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.universe.uni.domain.entity.Couple;
import com.universe.uni.domain.entity.Game;
import com.universe.uni.domain.entity.MissionCategory;
import com.universe.uni.domain.entity.RoundGame;
import com.universe.uni.domain.entity.RoundMission;
import com.universe.uni.domain.entity.ShortGame;
import com.universe.uni.domain.entity.User;
import com.universe.uni.domain.entity.WishCoupon;
import com.universe.uni.dto.CreateShortGameRequestDto;
import com.universe.uni.dto.CreateShortGameResponseDto;
import com.universe.uni.exception.BadRequestException;
import com.universe.uni.exception.NotFoundException;
import com.universe.uni.repository.GameRepository;
import com.universe.uni.repository.RoundGameRepository;
import com.universe.uni.repository.RoundMissionRepository;
import com.universe.uni.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GameService {

	private final GameRepository gameRepository;
	private final RoundGameRepository roundGameRepository;
	private final RoundMissionRepository roundMissionRepository;
	private final UserRepository userRepository;
	private final MissionService missionService;
	private final WishCouponService wishCouponService;

	@Transactional
	public CreateShortGameResponseDto createShortGame(CreateShortGameRequestDto createShortGameRequestDto) {

		User user = getUser();
		Couple couple = user.getCouple();

		verifyOngoingGame(couple);

		//한판승부 생성
		ShortGame shortGame = ShortGame.builder()
			.couple(couple)
			.build();

		//미션 카테고리 가져와서
		MissionCategory missionCategory= missionService
			.getMissionCategoryById(createShortGameRequestDto.getMissionCategoryId());

		//roundGame 생성
		RoundGame roundGame = RoundGame.builder()
			.game(shortGame)
			.missionCategory(missionCategory)
			.build();

		//커플 유저 둘 다 가져와서 roundMission 만들어주기
		List<User> userList = userRepository.findByCouple(getUser().getCouple());
		List<RoundMission> roundMissionList = userList.stream()
			.map(u -> createRoundMission(roundGame, u))
			.collect(Collectors.toList());

		//소원권 생성
		WishCoupon wishCoupon = wishCouponService.issueWishCoupon(createShortGameRequestDto.getWishContent(),
			shortGame);

		gameRepository.save(shortGame);
		roundGameRepository.save(roundGame);
		roundMissionRepository.saveAll(roundMissionList);
		wishCouponService.saveWishCoupon(wishCoupon);


		RoundMission myRoundMission = getRoundMissionByRoundGameAndUser(roundGame, user);

		return CreateShortGameResponseDto.of(shortGame, myRoundMission);
	}

	private RoundMission createRoundMission(RoundGame roundGame, User user) {
		return RoundMission.builder()
			.roundGame(roundGame)
			.user(user)
			.missionContent(missionService.getMissionContentByRandom(roundGame.getMissionCategory()))
			.build();
	}

	private RoundMission getRoundMissionByRoundGameAndUser(RoundGame roundGame, User user) {
		return roundMissionRepository.findByRoundGameAndUser(roundGame, user)
			.orElseThrow(() -> new NotFoundException(NOT_FOUND_ROUND_MISSION));
	}

	//임시 : 유저 가져오는 함수
	private User getUser() {
		Optional<User> byId = userRepository.findById(1L);
		return byId.get();
	}

	private void verifyOngoingGame(Couple couple) {
		if(gameRepository.existsByCoupleAndEnable(couple, true)) {
			throw new BadRequestException(ALREADY_GAME_CREATED);
		}
	}
}
