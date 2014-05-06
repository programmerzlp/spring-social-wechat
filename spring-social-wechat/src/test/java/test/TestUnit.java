package test;

import org.junit.Test;
import org.springframework.social.wechat.api.MenuOperations;
import org.springframework.social.wechat.api.bean.Result;
import org.springframework.social.wechat.api.impl.WechatTemplate;

public class TestUnit {

	@Test
	public void test() {
		WechatTemplate wechatTemplate = new WechatTemplate(
				"Al2RSCaxb3ZpyXm8lTWsPn5nSBoW2bQxLsm3IoH8YIBRIoV1oAsWbRTYrf70MK1Y");
		MenuOperations menuOperations = wechatTemplate.menuOperations();
		Result result = menuOperations.delete();
		System.out.println(result.getCode());
		System.out.println(result.getMsg());
	}

}