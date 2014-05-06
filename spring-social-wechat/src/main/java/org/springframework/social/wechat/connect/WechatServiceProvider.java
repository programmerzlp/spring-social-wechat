package org.springframework.social.wechat.connect;

import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import org.springframework.social.wechat.api.Wechat;
import org.springframework.social.wechat.api.impl.WechatTemplate;

public final class WechatServiceProvider extends
		AbstractOAuth2ServiceProvider<Wechat> {

	public WechatServiceProvider(String consumerKey, String consumerSecret) {
		super(new WechatOAuth2Template(consumerKey, consumerSecret));
	}

	@Override
	public Wechat getApi(String accessToken) {
		return new WechatTemplate(accessToken);
	}

}