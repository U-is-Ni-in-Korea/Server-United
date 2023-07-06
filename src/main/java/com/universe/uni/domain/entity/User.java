package com.universe.uni.domain.entity;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.universe.uni.domain.SnsType;
import com.universe.uni.domain.entity.convertor.SnsTypeAttributeConverter;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id", nullable = false)
	private Long id;

	@Column(name = "nickname", length = 10)
	private String nickname;

	@Column(name = "sns_type", nullable = false)
	@Convert(converter = SnsTypeAttributeConverter.class)
	private SnsType snsType;

	@Column(name = "sns_auth", nullable = false)
	private String snsAuthCode;

	@Column(name = "image")
	private String image;

	@Column(name = "fcm_token")
	private String fcmToken;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "couple_id")
	private Couple couple;

	@Builder
	public User(SnsType snsType, String snsAuthCode) {
		this.snsType = snsType;
		this.snsAuthCode = snsAuthCode;
	}

	public void updateNickname(String nickname) {
		if (nickname.length() > 10) {
			throw new IllegalArgumentException("nickname exceeds the maximum length of 10 characters");
		}
		this.nickname = nickname;
	}

	public void updateImage(String image) {
		this.image = image;
	}

	public void connectCouple(Couple couple) {
		this.couple = couple;
	}
}
