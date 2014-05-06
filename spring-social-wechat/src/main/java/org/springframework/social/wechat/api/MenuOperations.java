package org.springframework.social.wechat.api;

import org.springframework.social.wechat.api.bean.Menu;
import org.springframework.social.wechat.api.bean.Result;

public interface MenuOperations {

	Result create(Menu menu);

	Result delete();

	Menu get();

}