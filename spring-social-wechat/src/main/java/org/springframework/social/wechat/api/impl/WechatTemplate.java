package org.springframework.social.wechat.api.impl;

import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.OAuth2Version;
import org.springframework.social.oauth2.TokenStrategy;
import org.springframework.social.support.ClientHttpRequestFactorySelector;
import org.springframework.social.wechat.api.MenuOperations;
import org.springframework.social.wechat.api.Wechat;
import org.springframework.social.wechat.api.handler.WechatErrorHandler;
import org.springframework.social.wechat.api.json.WechatModule;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

public class WechatTemplate extends AbstractOAuth2ApiBinding implements Wechat {

	private MenuOperations menuOperations = null;

	private ObjectMapper objectMapper;

	public WechatTemplate(String accessToken) {
		super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
		initialize();
	}

	public WechatTemplate() {
		initialize();
	}

	@Override
	protected void configureRestTemplate(RestTemplate restTemplate) {
		super.configureRestTemplate(restTemplate);
		restTemplate.setErrorHandler(new WechatErrorHandler());
	}

	@Override
	protected MappingJackson2HttpMessageConverter getJsonMessageConverter() {
		MappingJackson2HttpMessageConverter converter = super
				.getJsonMessageConverter();
		objectMapper = new ObjectMapper();
		objectMapper.registerModule(new WechatModule());
		converter.setObjectMapper(objectMapper);
		return converter;
	}

	private void initialize() {
		super.setRequestFactory(ClientHttpRequestFactorySelector
				.bufferRequests(getRestTemplate().getRequestFactory()));
		initSubApis();
	}

	private void initSubApis() {
		menuOperations = new MenuTemplate(objectMapper, getRestTemplate(),
				isAuthorized());
	}

	@Override
	protected OAuth2Version getOAuth2Version() {
		return OAuth2Version.BEARER_DRAFT_2;
	}

	@Override
	public void setRequestFactory(ClientHttpRequestFactory requestFactory) {
		super.setRequestFactory(ClientHttpRequestFactorySelector
				.bufferRequests(requestFactory));
	}

	@Override
	public MenuOperations menuOperations() {
		return menuOperations;
	}

}