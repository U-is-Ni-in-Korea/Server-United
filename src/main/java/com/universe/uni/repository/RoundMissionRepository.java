package com.universe.uni.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.universe.uni.domain.entity.RoundGame;
import com.universe.uni.domain.entity.RoundMission;
import com.universe.uni.domain.entity.User;

public interface RoundMissionRepository extends JpaRepository<RoundMission, Long> {
	Optional<RoundMission> findByRoundGameAndUser(RoundGame roundGame, User user);

	List<RoundMission> findByRoundGame(RoundGame roundGame);

	Optional<RoundMission> findByRoundGameAndUserIsNot(RoundGame roundGame, User user);
}
