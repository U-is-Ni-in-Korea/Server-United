package com.universe.uni.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.universe.uni.domain.entity.Couple;
import com.universe.uni.domain.entity.User;


public interface UserRepository extends JpaRepository<User, Long> {
	List<User> findByCouple(Couple couple);

}
