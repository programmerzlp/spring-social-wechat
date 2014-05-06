package org.springframework.social.wechat.api.handler;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatus.Series;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.social.UncategorizedApiException;
import org.springframework.web.client.DefaultResponseErrorHandler;

public class WechatErrorHandler extends DefaultResponseErrorHandler {

	private final static String WECHAT = "wechat";

	@Override
	public void handleError(ClientHttpResponse response) throws IOException {
		HttpStatus statusCode = response.getStatusCode();
		if (statusCode.series() == Series.SERVER_ERROR) {
			System.out.println("服务器错误" + Series.SERVER_ERROR);
			// TODO
		} else if (statusCode.series() == Series.CLIENT_ERROR) {
			System.out.println("客户端错误" + Series.SERVER_ERROR);
			// TODO
		}
		try {
			super.handleError(response);
		} catch (Exception e) {
			throw new UncategorizedApiException(WECHAT, "错误的调用微信接口", e);
		}
	}

}