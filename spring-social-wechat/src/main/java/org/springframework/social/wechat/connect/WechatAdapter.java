package org.springframework.social.wechat.connect;

import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.wechat.api.Wechat;

public class WechatAdapter implements ApiAdapter<Wechat> {

	@Override
	public boolean test(Wechat api) {
		return true;
	}

	@Override
	public void setConnectionValues(Wechat api, ConnectionValues values) {
		throw new UnsupportedOperationException();
	}

	@Override
	public UserProfile fetchUserProfile(Wechat api) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void updateStatus(Wechat api, String message) {
		throw new UnsupportedOperationException();
	}

}