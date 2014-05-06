package org.springframework.social.wechat.api.json;

import java.util.List;

import org.springframework.social.wechat.api.bean.Button;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class ButtonMixin {

	@JsonProperty("type")
	String type;

	@JsonProperty("name")
	String name;

	@JsonProperty("key")
	String key;

	@JsonProperty("sub_button")
	List<Button> subButton;

}