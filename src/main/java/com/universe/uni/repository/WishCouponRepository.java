package com.universe.uni.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.universe.uni.domain.GameType;
import com.universe.uni.domain.entity.Game;
import com.universe.uni.domain.entity.User;
import com.universe.uni.domain.entity.WishCoupon;

public interface WishCouponRepository extends JpaRepository<WishCoupon, Long> {

	List<WishCoupon> findByUserAndGameTypeAndIsVisibleFalseAndIsUsedFalseAndUsedAtIsNull(User user, GameType gameType);

	Optional<WishCoupon> findByGame(Game game);

	List<WishCoupon> findByUserId(Long userId);
}
