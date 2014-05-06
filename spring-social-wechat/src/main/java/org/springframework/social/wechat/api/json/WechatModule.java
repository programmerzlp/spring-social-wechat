package org.springframework.social.wechat.api.json;

import java.awt.Menu;

import org.springframework.social.wechat.api.bean.Button;
import org.springframework.social.wechat.api.bean.Result;

import com.fasterxml.jackson.databind.module.SimpleModule;

public class WechatModule extends SimpleModule {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public WechatModule() {
		super("WechatModule");
	}

	@Override
	public void setupModule(SetupContext context) {
		context.setMixInAnnotations(Menu.class, MenuMixin.class);
		context.setMixInAnnotations(Button.class, ButtonMixin.class);
		context.setMixInAnnotations(Result.class, ResultErrorMixin.class);
	}

}