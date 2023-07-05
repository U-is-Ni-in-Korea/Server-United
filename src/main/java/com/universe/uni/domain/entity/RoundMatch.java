package com.universe.uni.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "round_match")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class RoundMatch {

	@Id
	@Column(name = "round_match_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "match_id", nullable = false)
	private Long matchId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "mission_category_id", nullable = false)
	private MissionCategory missionCategory;

	@Column(name = "enable", nullable = false)
	private Boolean enable;
}
