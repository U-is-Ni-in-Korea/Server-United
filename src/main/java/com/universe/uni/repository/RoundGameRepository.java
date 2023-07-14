package com.universe.uni.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.universe.uni.domain.entity.RoundGame;

public interface RoundGameRepository extends JpaRepository<RoundGame, Long> {
	RoundGame findByGameId(Long gameId);
}
