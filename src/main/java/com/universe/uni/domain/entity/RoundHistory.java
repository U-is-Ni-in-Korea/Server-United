package com.universe.uni.domain.entity;

import static javax.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.universe.uni.domain.MatchResult;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "round_history")
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PROTECTED)
public class RoundHistory {

	@Id
	@Column(name = "round_history_id")
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "round_match_id", nullable = false)
	private RoundMatch roundMatch;

	@Column(name = "user_id", nullable = false)
	private Long userId;

	@Column(name = "result")
	private MatchResult result;

	@OneToMany(mappedBy = "mission_content_id")
	private List<RoundMission> roundMissionList;

	@Column(name = "user_match_history_id", nullable = false)
	private Long userMatchHistoryId;
}
