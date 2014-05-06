package org.springframework.social.wechat.api.impl;

import java.net.URI;

import org.springframework.social.MissingAuthorizationException;
import org.springframework.social.support.URIBuilder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class AbstractWechatOperations {

	private final static String API_URL_BASE = "https://api.weixin.qq.com/cgi-bin/";
	private static final LinkedMultiValueMap<String, String> EMPTY_PARAMETERS = new LinkedMultiValueMap<String, String>();

	private final boolean isAuthorized;
	protected final RestTemplate restTemplate;
	protected final ObjectMapper objectMapper;

	public AbstractWechatOperations(ObjectMapper objectMapper,
			RestTemplate restTemplate, boolean isAuthorized) {
		this.isAuthorized = isAuthorized;
		this.objectMapper = objectMapper;
		this.restTemplate = restTemplate;
	}

	protected void requireAuthorization() {
		if (!isAuthorized) {
			throw new MissingAuthorizationException("wechat");
		}
	}

	protected URI buildUri(String path, String parameterName,
			Object parameterValue) {
		MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();
		parameters.set(parameterName, parameterValue.toString());
		return buildUri(path, parameters);
	}

	protected URI buildUri(String path, MultiValueMap<String, String> parameters) {
		return URIBuilder.fromUri(API_URL_BASE + path).queryParams(parameters)
				.build();
	}

	protected URI buildUri(String path) {
		return buildUri(path, EMPTY_PARAMETERS);
	}

	protected URIBuilder uriBuilder(String path) {
		return URIBuilder.fromUri(API_URL_BASE + path);
	}

}