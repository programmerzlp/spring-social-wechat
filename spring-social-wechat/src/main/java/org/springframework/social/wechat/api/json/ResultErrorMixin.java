package org.springframework.social.wechat.api.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class ResultErrorMixin {

	@JsonProperty("errcode")
	String code;

	@JsonProperty("errmsg")
	String msg;

}