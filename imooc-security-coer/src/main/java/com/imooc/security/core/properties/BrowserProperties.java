package com.imooc.security.core.properties;

public class BrowserProperties {
	
	private String loginPage = "/imooc-signIn.html";
	
	private LoginType loginType = LoginType.JSON;
	
	private int remenberMeSeconds = 60;

	public String getLoginPage() {
		return loginPage;
	}

	public void setLoginPage(String loginPage) {
		this.loginPage = loginPage;
	}

	public LoginType getLoginType() {
		return loginType;
	}

	public void setLoginType(LoginType loginType) {
		this.loginType = loginType;
	}

	public int getRemenberMeSeconds() {
		return remenberMeSeconds;
	}

	public void setRemenberMeSeconds(int remenberMeSeconds) {
		this.remenberMeSeconds = remenberMeSeconds;
	}
	

}
