package com.imooc.security.core.validate.code.sms;

public class DefaultSmsCodeSenderImpl implements SmsCodeSender{

	@Override
	public void send(String mobile, String code) {
		System.out.println("------------手机号:"+mobile+";验证码:"+code+";--------------");
	}

}
