package com.imooc.security.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "imooc.security")
public class SecurityProperties {

	private BrowserProperties browser = new BrowserProperties();
	
	private ValidateCodeProperties validateCode = new ValidateCodeProperties();

	public ValidateCodeProperties getValidateCode() {
		return validateCode;
	}

	public void setValidateCode(ValidateCodeProperties validateCode) {
		this.validateCode = validateCode;
	}

	public BrowserProperties getBrowser() {
		return browser;
	}

	public void setBrowser(BrowserProperties browser) {
		this.browser = browser;
	}
	
	
}
