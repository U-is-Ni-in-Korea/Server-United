package com.universe.uni.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.universe.uni.domain.GameType;
import com.universe.uni.domain.entity.WishCoupon;

public interface WishCouponRepository extends JpaRepository<WishCoupon, Long> {

	List<WishCoupon> findByGameTypeAndIsVisibleFalseAndIsUsedFalseAndUsedAtIsNull(GameType gameType);

	List<WishCoupon> findByUserId(Long userId);
}
