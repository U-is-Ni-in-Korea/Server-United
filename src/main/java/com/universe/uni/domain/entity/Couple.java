package com.universe.uni.domain.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.ColumnDefault;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "couple")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Couple {

	public static final int MAX_HEART_LIMIT = 5;

	@Id
	@Column(name = "couple_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Version
	private long version;

	@Column(name = "start_date", nullable = false)
	private LocalDate startDate;

	@Column(name = "invite_code", nullable = false, unique = true)
	private String inviteCode;

	@Column(name = "heart_token", nullable = false)
	@ColumnDefault("5")
	private int heartToken;

	@Builder
	public Couple(LocalDate startDate, String inviteCode) {
		this.startDate = startDate;
		this.inviteCode = inviteCode;
	}

	public void updateStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public boolean hasHeartToken() {
		return heartToken > 0;
	}

	public boolean isMaxHeartToken() {
		return this.heartToken >= 5;
	}

	public void increaseHeartTokenBy(int amount) {
		this.heartToken += amount;
	}

	public void changeHeartTokenMaximum() {
		if (!isMaxHeartToken()) {
			this.heartToken = MAX_HEART_LIMIT;
		}
	}

	public void decreaseHeartToken() throws IllegalStateException {
		if (!this.hasHeartToken()) {
			throw new IllegalStateException("Unable to decrease the heart token");
		}
		this.heartToken -= 1;
	}
}
