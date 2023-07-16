package com.universe.uni.service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.universe.uni.domain.entity.Couple;
import com.universe.uni.domain.entity.MissionCategory;
import com.universe.uni.domain.entity.RoundGame;
import com.universe.uni.domain.entity.RoundMission;
import com.universe.uni.domain.entity.User;
import com.universe.uni.domain.entity.UserGameHistory;
import com.universe.uni.dto.MissionResultDto;
import com.universe.uni.dto.response.GameHistoryResponseDto;
import com.universe.uni.exception.NotFoundException;
import com.universe.uni.exception.dto.ErrorType;
import com.universe.uni.repository.CoupleRepository;
import com.universe.uni.repository.MissionCategoryRepository;
import com.universe.uni.repository.MissionContentRepository;
import com.universe.uni.repository.RoundGameRepository;
import com.universe.uni.repository.RoundMissionRepository;
import com.universe.uni.repository.UserGameHistoryRepository;
import com.universe.uni.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class HistoryService {

	private final UserGameHistoryRepository userGameHistoryRepository;
	private final RoundGameRepository roundGameRepository;
	private final MissionCategoryRepository missionCategoryRepository;
	private final MissionContentRepository missionContentRepository;
	private final RoundMissionRepository roundMissionRepository;
	private final CoupleRepository coupleRepository;
	private final UserRepository userRepository;

	public List<GameHistoryResponseDto> getGameHistory() {
		Long userId = 1L; // TODO: replace with actual user id

		// User 가져옴
		User user = userRepository.findById(userId)
			.orElseThrow(() -> new NotFoundException(ErrorType.NOT_FOUND_USER));

		// User로 UserGameHistory 가져옴
		List<UserGameHistory> userGameHistoryList = userGameHistoryRepository.findByUser(user);

		return userGameHistoryList.stream()
			.map(userGameHistory -> {
				// UserGameHistory의 Game의 id로 RoundGame 조회
				RoundGame roundGame = roundGameRepository.findById(userGameHistory.getGame().getId())
					.orElseThrow(() -> new NotFoundException(ErrorType.NOT_FOUND_ROUND_GAME));
				// RoundGame의 MissionCategory의 id로 MissionCategory 조회
				MissionCategory missionCategory = missionCategoryRepository.findById(
						roundGame.getMissionCategory().getId())
					.orElseThrow(() -> new NotFoundException(ErrorType.NOT_FOUND_MISSION_CATEGORY_EXCEPTION));

				// RoundGame으로 RoundMission 리스트 조회
				List<RoundMission> roundMissionList = roundMissionRepository.findByRoundGame(roundGame);

				return GameHistoryResponseDto.builder()
					.roundGameId(roundGame.getId().intValue())
					.date(userGameHistory.getUpdatedAt().format(DateTimeFormatter.ISO_DATE))
					.result(userGameHistory.getResult().name())
					.title(missionCategory.getTitle())
					.image(missionCategory.getImage())
					.myMission(createMissionResultDto(roundMissionList, userId))
					.partnerMission(createMissionResultDto(roundMissionList, getPartnerId(userId)))
					.build();
			}).collect(Collectors.toList());
	}

	private Long getPartnerId(Long userId) {
		User user = userRepository.findById(userId)
			.orElseThrow(() -> new NotFoundException(ErrorType.NOT_FOUND_USER));
		Couple couple = user.getCouple();

		List<User> userListInCouple = userRepository.findByCouple(couple);
		return userListInCouple.stream()
			.filter(u -> !u.getId().equals(userId))
			.findFirst()
			.map(User::getId)
			.orElseThrow(() -> new NotFoundException(ErrorType.NOT_FOUND_COUPLE));
	}

	private MissionResultDto createMissionResultDto(List<RoundMission> roundMissions, Long userId) {
		RoundMission roundMission = roundMissions.stream()
			.filter(rm -> rm.getUser().getId().equals(userId))
			.findFirst()
			.orElseThrow(() -> new NotFoundException(ErrorType.NOT_FOUND_ROUND_MISSION));
		return MissionResultDto.builder()
			.result(roundMission.getResult().name())
			.time(roundMission.getUpdatedAt().format(DateTimeFormatter.ISO_LOCAL_TIME))
			.build();
	}
}