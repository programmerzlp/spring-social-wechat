package org.springframework.social.wechat.api.bean;

import java.util.ArrayList;
import java.util.List;

public class Menu {

	private List<Button> button = new ArrayList<Button>();

	private Menu menu = null;

	public List<Button> getButton() {
		return button;
	}

	public void setButton(List<Button> button) {
		this.button = button;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

}