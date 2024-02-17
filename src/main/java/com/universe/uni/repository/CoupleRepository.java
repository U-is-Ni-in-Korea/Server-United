package com.universe.uni.repository;

import java.util.Optional;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import com.universe.uni.domain.entity.Couple;

public interface CoupleRepository extends JpaRepository<Couple, Long> {

	Optional<Couple> findByInviteCode(String inviteCode);

	@Lock(LockModeType.OPTIMISTIC_FORCE_INCREMENT)
	Optional<Couple> findWithOptimisticForceIncrementById(Long coupleId);
}
