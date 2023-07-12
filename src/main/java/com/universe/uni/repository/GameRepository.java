package com.universe.uni.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.universe.uni.domain.entity.Couple;
import com.universe.uni.domain.entity.Game;

public interface GameRepository extends JpaRepository<Game, Long> {

	boolean existsByCoupleAndEnable(Couple couple, boolean enable);
}
