package com.universe.uni.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.universe.uni.domain.entity.UserGameHistory;

public interface UserGameHistoryRepository extends JpaRepository<UserGameHistory, Long> {
}
