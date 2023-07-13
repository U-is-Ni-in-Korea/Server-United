package com.universe.uni.service;

import static com.universe.uni.exception.dto.ErrorType.NOT_FOUND_USER;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.universe.uni.domain.GameResult;
import com.universe.uni.domain.entity.Game;
import com.universe.uni.domain.entity.RoundGame;
import com.universe.uni.domain.entity.ShortGame;
import com.universe.uni.domain.entity.User;
import com.universe.uni.domain.entity.UserGameHistory;
import com.universe.uni.dto.CoupleDto;
import com.universe.uni.dto.ShortGameDto;
import com.universe.uni.dto.response.HomeResponseDto;
import com.universe.uni.exception.NotFoundException;
import com.universe.uni.repository.GameRepository;
import com.universe.uni.repository.RoundGameRepository;
import com.universe.uni.repository.UserGameHistoryRepository;
import com.universe.uni.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class HomeService {
	private final UserRepository userRepository;
	private final GameRepository gameRepository;
	private final UserGameHistoryRepository userGameHistoryRepository;
	private final RoundGameRepository roundGameRepository;

	public HomeResponseDto getHome() {
		/**
		 * jwt userId
		 * game에서 coupleId 조회해서 GameResult.WIN, GameResult.LOSE, GameResult.DRAW 각각 개수 변수에 저장
		 * userId로 shortGame 가져옴
		 * shortGame은 nullable합니다.
		 */

		/** TODO 영주 : 추후 1L 내 userId로 바꾸기*/
		Long userId = 1L;

		User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException(NOT_FOUND_USER));

		Game game = gameRepository.findByCoupleId(user.getCouple().getId());

		List<UserGameHistory> gameHistoryList = userGameHistoryRepository.findByUserId(userId);

		RoundGame roundGame = roundGameRepository.findByGameId(game.getId());

		int myScore = (int)gameHistoryList.stream().filter(history -> history.getResult() == GameResult.WIN).count();

		int partnerScore = (int)gameHistoryList.stream()
			.filter(history -> history.getResult() == GameResult.LOSE)
			.count();

		int drawCount = (int)gameHistoryList.stream().filter(history -> history.getResult() == GameResult.DRAW).count();

		Optional<ShortGameDto> shortGameDto = Optional.empty();
		if (game instanceof ShortGame) {
			shortGameDto = Optional.of(new ShortGameDto((ShortGame)game));
		}

		CoupleDto coupleDto = CoupleDto.builder()
			.id(user.getCouple().getId())
			.startDate(String.valueOf(user.getCouple().getStartDate()))
			.heartToken(user.getCouple().getHeartToken())
			.build();

		return HomeResponseDto.builder()
			.userId(userId)
			.roundGameId(roundGame.getId())
			.myScore(myScore)
			.partnerScore(partnerScore)
			.drawCount(drawCount)
			.couple(coupleDto)
			.shortGame(shortGameDto.orElse(null))
			.build();
	}
}
