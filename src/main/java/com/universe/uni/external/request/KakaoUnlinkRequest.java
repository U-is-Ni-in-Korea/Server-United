package com.universe.uni.external.request;

import feign.form.FormProperty;

public record KakaoUnlinkRequest(
	@FormProperty("target_id_type")
	String targetIdType,
	@FormProperty("target_id")
	Long targetId
) {

}
