package com.universe.uni.domain.entity;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.universe.uni.domain.SnsType;
import com.universe.uni.domain.entity.convertor.SnsTypeAttributeConverter;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id", nullable = false)
	private Long id;

	@Column(name = "nickname")
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

	@Column(name = "couple_id")
	private Long coupleId;
}
