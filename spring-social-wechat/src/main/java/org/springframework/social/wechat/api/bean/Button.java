package org.springframework.social.wechat.api.bean;

import java.util.ArrayList;
import java.util.List;

public class Button {

	private String type = null;

	private String name = null;

	private String key = null;

	private List<Button> subButton = new ArrayList<Button>();

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public List<Button> getSubButton() {
		return subButton;
	}

	public void setSubButton(List<Button> subButton) {
		this.subButton = subButton;
	}

}