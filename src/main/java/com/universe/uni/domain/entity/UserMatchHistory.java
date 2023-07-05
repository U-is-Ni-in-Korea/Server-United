package com.universe.uni.domain.entity;

import static javax.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.universe.uni.domain.MatchResult;
import com.universe.uni.domain.MatchType;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_match_history")
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class UserMatchHistory {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "user_match_history_id")
	private Long id;

	@Column(name = "user_id", nullable = false)
	private Long userId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "match_id", nullable = false)
	private Match match;

	@Column(name = "result")
	private MatchResult result;

	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

	@Column(name = "match_type")
	private MatchType matchType;

}
