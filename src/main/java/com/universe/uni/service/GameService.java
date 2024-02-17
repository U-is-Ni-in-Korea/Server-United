package com.universe.uni.service;

import static com.universe.uni.domain.GameResult.*;
import static com.universe.uni.exception.dto.ErrorType.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.universe.uni.domain.GameResult;
import com.universe.uni.domain.GameType;
import com.universe.uni.domain.entity.Couple;
import com.universe.uni.domain.entity.Game;
import com.universe.uni.domain.entity.MissionCategory;
import com.universe.uni.domain.entity.MissionContent;
import com.universe.uni.domain.entity.RoundGame;
import com.universe.uni.domain.entity.RoundMission;
import com.universe.uni.domain.entity.ShortGame;
import com.universe.uni.domain.entity.User;
import com.universe.uni.domain.entity.UserGameHistory;
import com.universe.uni.domain.entity.WishCoupon;
import com.universe.uni.dto.request.CreateShortGameRequestDto;
import com.universe.uni.dto.response.CreateShortGameResponseDto;
import com.universe.uni.dto.response.GameReportResponseDto;
import com.universe.uni.exception.BadRequestException;
import com.universe.uni.exception.NotFoundException;
import com.universe.uni.repository.CoupleRepository;
import com.universe.uni.repository.GameRepository;
import com.universe.uni.repository.RoundGameRepository;
import com.universe.uni.repository.RoundMissionRepository;
import com.universe.uni.repository.UserGameHistoryRepository;
import com.universe.uni.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GameService {

	private final GameRepository gameRepository;
	private final RoundGameRepository roundGameRepository;
	private final RoundMissionRepository roundMissionRepository;
	private final UserRepository userRepository;
	private final CoupleRepository coupleRepository;
	private final UserGameHistoryRepository userGameHistoryRepository;
	private final MissionService missionService;
	private final WishCouponService wishCouponService;
	private final UserUtil userUtil;

	@Transactional
	public CreateShortGameResponseDto createShortGame(CreateShortGameRequestDto createShortGameRequestDto) {

		final User user = userUtil.getCurrentUser();
		final Couple couple = user.getCouple();

		try {
			getLockedCoupleById(couple.getId());

			//한판승부 생성
			ShortGame shortGame = createShortGameBy(couple);
			//roundGame 생성
			RoundGame roundGame = createRoundGameBy(shortGame, createShortGameRequestDto.getMissionCategoryId());
			//커플 유저 둘 다 가져와서 roundMission 만들어주기
			setRoundMissionsToUsersWith(couple, roundGame);

			//소원권 생성
			createWishCouponWith(createShortGameRequestDto.getWishContent(), shortGame);

			RoundMission myRoundMission = getRoundMissionByRoundGameAndUser(roundGame, user);

			return CreateShortGameResponseDto.of(shortGame, roundGame.getId(), myRoundMission);
		} catch (OptimisticLockingFailureException exception) {
			throw new BadRequestException(ALREADY_GAME_CREATED);
		}
	}

	private void getLockedCoupleById(Long coupleId) {
		coupleRepository.findWithOptimisticForceIncrementById(coupleId)
			.orElseThrow(() -> new NotFoundException(NOT_FOUND_COUPLE));
	}

	private ShortGame createShortGameBy(Couple couple) {
		verifyIsThereOngoingGame(couple);
		ShortGame shortGame = ShortGame.builder()
			.couple(couple)
			.build();
		return gameRepository.save(shortGame);
	}

	private void verifyIsThereOngoingGame(Couple couple) {
		if (gameRepository.existsByCoupleAndEnable(couple, true)) {
			throw new BadRequestException(ALREADY_GAME_CREATED);
		}
	}

	private RoundGame createRoundGameBy(ShortGame shortGame, Long missionCategoryId) {
		//미션 카테고리 가져와서
		MissionCategory missionCategory = missionService.getMissionCategoryById(missionCategoryId);
		//roundGame 생성
		RoundGame roundGame = RoundGame.builder()
			.game(shortGame)
			.missionCategory(missionCategory)
			.build();
		return roundGameRepository.save(roundGame);
	}

	// TODO: 라운드 게임에 지정된 미션 카테고리를 통해 각 카테고리 별로 유저의 미션을 통일 혹은 다르게 주어야함.
	private void setRoundMissionsToUsersWith(Couple couple, RoundGame roundGame) {
		//커플 유저 둘 다 가져와서 roundMission 만들어주기
		List<User> users = userRepository.findByCouple(couple);
		if (roundGame.getMissionCategory().isMissionTypeSame()) {
			createSameRoundMission(roundGame, users);
			return;
		}
		createDifferenceRoundMission(roundGame, users);
	}

	private void createSameRoundMission(RoundGame roundGame, List<User> users) {
		MissionContent missionContent = missionService.getMissionContentByRandom(roundGame.getMissionCategory());
		List<RoundMission> roundMissions = users.stream()
			.map(user -> createRoundMission(roundGame, user, missionContent))
			.toList();
		roundMissionRepository.saveAll(roundMissions);
	}

	private RoundMission createRoundMission(RoundGame roundGame, User user, MissionContent missionContent) {
		return RoundMission.builder()
			.roundGame(roundGame)
			.user(user)
			.missionContent(missionContent)
			.build();
	}

	private void createDifferenceRoundMission(RoundGame roundGame, List<User> users) {
		List<RoundMission> roundMissions = users.stream()
			.map(user -> createRandomRoundMission(roundGame, user))
			.toList();
		roundMissionRepository.saveAll(roundMissions);
	}

	private RoundMission createRandomRoundMission(RoundGame roundGame, User user) {
		return RoundMission.builder()
			.roundGame(roundGame)
			.user(user)
			.missionContent(missionService.getMissionContentByRandom(roundGame.getMissionCategory()))
			.build();
	}

	private void createWishCouponWith(String wishCouponContent, ShortGame shortGame) {
		//소원권 생성
		String normalizedContent = WhitespaceNormalizer.normalizeWhitespace(wishCouponContent);
		WishCoupon wishCoupon = wishCouponService.issueWishCoupon(normalizedContent, shortGame);

		wishCouponService.saveWishCoupon(wishCoupon);
	}

	private RoundMission getRoundMissionByRoundGameAndUser(RoundGame roundGame, User user) {
		return roundMissionRepository.findByRoundGameAndUser(roundGame, user)
			.orElseThrow(() -> new NotFoundException(NOT_FOUND_ROUND_MISSION));
	}

	@Transactional
	public GameReportResponseDto updateGameResult(Long roundGameId, Boolean result) {
		User user = userUtil.getCurrentUser();
		RoundGame roundGame = getRoundGameById(roundGameId);
		RoundMission myRoundMission = getRoundMissionByRoundGameAndUser(roundGame, user);

		updateGameResultWithTimeRecord(myRoundMission, result);

		return GameReportResponseDto.of(myRoundMission);
	}

	@Transactional
	public GameReportResponseDto updateFinalGameResult(Long roundGameId) {
		User user = userUtil.getCurrentUser();
		RoundGame roundGame = getRoundGameById(roundGameId);
		RoundMission myRoundMission = getRoundMissionByRoundGameAndUser(roundGame, user);
		RoundMission partnerRoundMission = getPartnerRoundMission(roundGame, user);

		if (!isResultEntered(partnerRoundMission)) {
			throw new BadRequestException(PARTNER_RESULT_NOT_ENTERED);
		}

		User winner = decideFinalGameScore(myRoundMission, partnerRoundMission);

		if (!userGameHistoryRepository.existsByGame(roundGame.getGame())) {
			finishGame(roundGame);
			updateGameHistory(myRoundMission, partnerRoundMission);
			wishCouponService.giveWishCouponToWinner(roundGame.getGame(), winner);
		}

		return GameReportResponseDto.of(myRoundMission, partnerRoundMission);
	}

	private void finishGame(RoundGame roundGame) {
		roundGame.finishGame();
		roundGame.getGame().finishGame(getCurrentTime());
	}

	private void updateGameHistory(RoundMission myRoundMission, RoundMission partnerRoundMission) {
		LocalDateTime now = getCurrentTime();
		addGameToHistory(myRoundMission, now, GameType.SHORT);
		addGameToHistory(partnerRoundMission, now, GameType.SHORT);
	}

	private void addGameToHistory(RoundMission roundMission, LocalDateTime now, GameType gameType) {
		UserGameHistory userGameHistory = UserGameHistory.builder()
			.game(roundMission.getRoundGame().getGame())
			.user(roundMission.getUser())
			.gameResult(roundMission.getFinalResult())
			.now(now)
			.gameType(gameType)
			.build();

		userGameHistoryRepository.save(userGameHistory);
	}

	private User decideFinalGameScore(RoundMission myRoundMission, RoundMission partnerRoundMission) {
		GameResult myResult = myRoundMission.getResult();
		GameResult partnerResult = partnerRoundMission.getResult();

		/**
		 * 최종결과 업데이트
		 * 내가 이겼는데 상대도 이김 => 시간비교 후 빠른 사람이 WIN
		 * 내가 이겼는데 상대 짐 => 나: WIN, 상대: LOSE
		 * 내가 졌는데 상대 이김 => 나: LOSE, 상대: WIN
		 * 내가 졌는데 상대 짐 => 나: DRAW, 상대: DRAW
		 */

		User winner = null;

		if (myResult == WIN && partnerResult == WIN) {
			LocalDateTime myUpdatedAt = myRoundMission.getUpdatedAt();
			LocalDateTime partnerUpdatedAt = partnerRoundMission.getUpdatedAt();

			if (myUpdatedAt.isAfter(partnerUpdatedAt)) {
				myRoundMission.updateFinalResult(LOSE);
				partnerRoundMission.updateFinalResult(WIN);
				winner = partnerRoundMission.getUser();
			} else if (myUpdatedAt.isBefore(partnerUpdatedAt)) {
				myRoundMission.updateFinalResult(WIN);
				partnerRoundMission.updateFinalResult(LOSE);
				winner = myRoundMission.getUser();
			} else {
				myRoundMission.updateFinalResult(DRAW);
				partnerRoundMission.updateFinalResult(DRAW);
			}
		} else if (myResult == LOSE && partnerResult == LOSE) {
			myRoundMission.updateFinalResult(DRAW);
			partnerRoundMission.updateFinalResult(DRAW);
		} else {
			myRoundMission.updateFinalResult(myResult);
			partnerRoundMission.updateFinalResult(partnerResult);
			if (myResult == WIN) {
				winner = myRoundMission.getUser();
			} else {
				winner = partnerRoundMission.getUser();
			}
		}

		return winner;
	}

	private Boolean isResultEntered(RoundMission roundMission) {
		return roundMission.getResult() != GameResult.UNDECIDED;
	}

	private RoundMission getPartnerRoundMission(RoundGame roundGame, User user) {
		return roundMissionRepository.findByRoundGameAndUserIsNot(roundGame, user)
			.orElseThrow(() -> new NotFoundException(NOT_FOUND_ROUND_MISSION));
	}

	private void updateGameResultWithTimeRecord(RoundMission roundMission, Boolean result) {
		LocalDateTime now = getCurrentTime();

		if (result) {
			roundMission.updateResult(GameResult.WIN, now);
		} else {
			roundMission.updateResult(LOSE, now);
		}
	}

	private LocalDateTime getCurrentTime() {
		ZoneId seoulZoneId = ZoneId.of("Asia/Seoul");
		return LocalDateTime.now(seoulZoneId);
	}

	private RoundGame getRoundGameById(Long roundGameId) {
		return roundGameRepository.findById(roundGameId)
			.orElseThrow(() -> new NotFoundException(NOT_FOUND_ROUND_MISSION));
	}

	@Transactional(readOnly = true)
	public GameReportResponseDto getGameReportIfGameIsOngoing(Long roundGameId) {
		User user = userUtil.getCurrentUser();
		RoundGame roundGame = getRoundGameById(roundGameId);

		//verifyIsOngoingGame(roundGame);
		RoundMission myRoundMission = getRoundMissionByRoundGameAndUser(roundGame, user);

		return GameReportResponseDto.of(myRoundMission);
	}

	private void verifyIsOngoingGame(RoundGame roundGame) {
		if (!roundGame.getEnable()) {
			throw new BadRequestException(ALREADY_GAME_DONE);
		}
	}

	public void quitGame(Long roundGameId) {
		RoundGame roundGame = getRoundGameById(roundGameId);
		Game game = roundGame.getGame();

		gameRepository.delete(game);
	}
}
