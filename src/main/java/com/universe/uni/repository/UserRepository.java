package com.universe.uni.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.universe.uni.domain.entity.User;


public interface UserRepository extends JpaRepository<User, Long> {
}
