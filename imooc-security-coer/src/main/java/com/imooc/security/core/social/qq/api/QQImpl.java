package com.imooc.security.core.social.qq.api;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

public class QQImpl extends AbstractOAuth2ApiBinding implements QQ {

	Logger logger = LoggerFactory.getLogger(getClass());
	
	private static final String URL_GET_OPENID = "https://graph.qq.com/oauth2.0/me?access_token=%s ";
	
	private static final String URL_GET_USERINFO = "https://graph.qq.com/user/get_user_info?oauth_consumer_key=%s&openid=%s";
	
	private String appId;
	
	private String openId;
	
	public QQImpl(String accessToken,String appId) {
		super(accessToken,TokenStrategy.ACCESS_TOKEN_PARAMETER);
		this.appId = appId;
		
		String url = String.format(URL_GET_OPENID, accessToken);
		String result = getRestTemplate().getForObject(url, String.class);
		logger.info("getOpenId: "+result);
		
		this.openId = StringUtils.substringBetween(result, "\"openId\":", "}");
		
	}
	
	@Override
	public QQUserInfo getUserInfo() {
		
		String url = String.format(URL_GET_USERINFO, appId,openId);
		QQUserInfo qQUserInfo = getRestTemplate().getForObject(url, QQUserInfo.class);
		
		logger.info(qQUserInfo.toString());
		return qQUserInfo;
	}

}
