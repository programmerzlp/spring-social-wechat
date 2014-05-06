package org.springframework.social.wechat.connect;

import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import org.springframework.social.wechat.api.Wechat;

public class WechatConnectionFactory extends OAuth2ConnectionFactory<Wechat> {

	public WechatConnectionFactory(String consumerKey, String consumerSecret) {
		super("wechat", new WechatServiceProvider(consumerKey, consumerSecret),
				new WechatAdapter());
	}

}