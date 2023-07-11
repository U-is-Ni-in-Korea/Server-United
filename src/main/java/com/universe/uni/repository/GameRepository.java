package com.universe.uni.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.universe.uni.domain.entity.Game;

public interface GameRepository extends JpaRepository<Game, Long> {
}
