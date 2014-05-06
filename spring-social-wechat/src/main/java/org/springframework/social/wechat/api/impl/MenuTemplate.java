package org.springframework.social.wechat.api.impl;

import java.io.StringWriter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.social.wechat.api.MenuOperations;
import org.springframework.social.wechat.api.bean.Menu;
import org.springframework.social.wechat.api.bean.Result;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

public class MenuTemplate extends AbstractWechatOperations implements
		MenuOperations {

	private static final Log logger = LogFactory.getLog(MenuTemplate.class
			.getName());

	public MenuTemplate(ObjectMapper objectMapper, RestTemplate restTemplate,
			boolean isAuthorized) {
		super(objectMapper, restTemplate, isAuthorized);
	}

	@Override
	public Result create(Menu menu) {
		requireAuthorization();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		StringWriter jsonStr = new StringWriter();
		try {
			objectMapper.writeValue(jsonStr, menu);
		} catch (Exception e) {
			logger.error("微信自定义菜单请求Json生成失败", e);
		}
		HttpEntity<String> httpEntity = new HttpEntity<String>(
				jsonStr.toString(), headers);
		return restTemplate.postForObject(buildUri("menu/create"), httpEntity,
				Result.class);
	}

	@Override
	public Result delete() {
		requireAuthorization();
		return restTemplate.postForObject(buildUri("menu/delete"), null,
				Result.class);
	}

	@Override
	public Menu get() {
		requireAuthorization();
		return restTemplate.postForObject(buildUri("menu/get"), null,
				Menu.class);
	}

}