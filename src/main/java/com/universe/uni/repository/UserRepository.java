package com.universe.uni.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.universe.uni.domain.entity.Couple;
import com.universe.uni.domain.entity.User;
import com.universe.uni.domain.entity.WishCoupon;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findBySnsAuthCode(String snsAuthCode);

	List<User> findByCouple(Couple couple);

	List<WishCoupon> findAllById(Long userId);
}
