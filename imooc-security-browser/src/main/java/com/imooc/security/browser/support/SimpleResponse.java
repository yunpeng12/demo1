package com.imooc.security.browser.support;

public class SimpleResponse {

	public SimpleResponse(Object content) {
		super();
		this.content = content;
	}

	private Object content;

	public Object getContent() {
		return content;
	}

	public void setContent(Object content) {
		this.content = content;
	}
	
}
