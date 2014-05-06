package org.springframework.social.wechat.connect;

import java.util.Collections;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.MediaType;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Template;
import org.springframework.social.support.ClientHttpRequestFactorySelector;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class WechatOAuth2Template extends OAuth2Template {

	private static final Log logger = LogFactory
			.getLog(WechatOAuth2Template.class.getName());

	public WechatOAuth2Template(String clientId, String clientSecret) {
		super(clientId, clientSecret, "",
				"https://api.weixin.qq.com/cgi-bin/token");
		setUseParametersForClientAuthentication(true);
	}

	@Override
	protected RestTemplate createRestTemplate() {
		RestTemplate restTemplate = new RestTemplate(
				ClientHttpRequestFactorySelector.getRequestFactory());
		FormHttpMessageConverter messageConverter = new FormHttpMessageConverter() {
			public boolean canRead(Class<?> clazz, MediaType mediaType) {
				return true;
			}
		};
		restTemplate.setMessageConverters(Collections
				.<HttpMessageConverter<?>> singletonList(messageConverter));
		return restTemplate;
	}

	@Override
	@SuppressWarnings("unchecked")
	protected AccessGrant postForAccessGrant(String accessTokenUrl,
			MultiValueMap<String, String> parameters) {

		// 针对微信的奇葩请求参数进行修改
		if ("client_credentials".equals(parameters.getFirst("grant_type"))) {// 如果是微信client_credential
			parameters.set("appid", parameters.getFirst("client_id"));
			parameters.set("secret", parameters.getFirst("client_secret"));
			parameters.set("grant_type",
					parameters.getFirst("client_credential"));
			parameters.remove("client_id");
			parameters.remove("client_secret");
		}

		MultiValueMap<String, String> response = getRestTemplate()
				.postForObject(accessTokenUrl, parameters, MultiValueMap.class);
		String expires = response.getFirst("expires_in");
		String accessToken = response.getFirst("access_token");
		if (logger.isDebugEnabled()) {
			logger.debug("access token value = " + accessToken);
		}
		return new AccessGrant(accessToken, null, null,
				expires != null ? Long.valueOf(expires) : null);
	}

}