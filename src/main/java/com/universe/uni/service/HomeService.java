package com.universe.uni.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.universe.uni.domain.GameResult;
import com.universe.uni.domain.entity.Couple;
import com.universe.uni.domain.entity.Game;
import com.universe.uni.domain.entity.RoundGame;
import com.universe.uni.domain.entity.ShortGame;
import com.universe.uni.domain.entity.User;
import com.universe.uni.domain.entity.UserGameHistory;
import com.universe.uni.dto.CoupleDto;
import com.universe.uni.dto.ShortGameDto;
import com.universe.uni.dto.response.HomeResponseDto;
import com.universe.uni.repository.GameRepository;
import com.universe.uni.repository.RoundGameRepository;
import com.universe.uni.repository.UserGameHistoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class HomeService {
	private final GameRepository gameRepository;
	private final UserGameHistoryRepository userGameHistoryRepository;
	private final RoundGameRepository roundGameRepository;
	private final UserUtil userUtil;

	public HomeResponseDto getHome() {
		User user = userUtil.getCurrentUser();
		Couple couple = user.getCouple();

		Game game = gameRepository.findByCoupleIdAndEnable(couple.getId(), true);
		RoundGame roundGame = game != null ? roundGameRepository.findByGameId(game.getId()) : null;

		List<UserGameHistory> gameHistoryList = userGameHistoryRepository.findByUserId(user.getId());

		int myScore = calculateScore(gameHistoryList, GameResult.WIN);
		int partnerScore = calculateScore(gameHistoryList, GameResult.LOSE);
		int drawCount = calculateScore(gameHistoryList, GameResult.DRAW);

		int dDay = calculateDays(couple);

		ShortGameDto shortGameDto = game instanceof ShortGame ? new ShortGameDto((ShortGame)game) : null;
		CoupleDto coupleDto = fromCoupleToCoupleDtoMapper(couple);

		return HomeResponseDto.builder()
			.userId(user.getId())
			.roundGameId(roundGame != null ? roundGame.getId() : null)
			.myScore(myScore)
			.partnerScore(partnerScore)
			.drawCount(drawCount)
			.dDay(dDay)
			.couple(coupleDto)
			.shortGame(shortGameDto)
			.build();
	}

	private int calculateScore(List<UserGameHistory> gameHistoryList, GameResult result) {
		return gameHistoryList != null
			? (int)gameHistoryList.stream().filter(history -> history.getResult() == result).count()
			: 0;
	}

	private int calculateDays(Couple couple) {
		LocalDate today = LocalDate.now();
		Period period = Period.between(couple.getStartDate(), today);
		return period.getDays() + 1;
	}

	private CoupleDto fromCoupleToCoupleDtoMapper(Couple couple) {
		return CoupleDto.builder()
			.id(couple.getId())
			.startDate(String.valueOf(couple.getStartDate()))
			.heartToken(couple.getHeartToken())
			.build();
	}
}
