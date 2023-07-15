package com.universe.uni.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.universe.uni.domain.entity.Couple;

public interface CoupleRepository extends JpaRepository<Couple, Long> {
	Optional<Couple> findByInviteCode(String inviteCode);
}
