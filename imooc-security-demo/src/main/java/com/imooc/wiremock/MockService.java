package com.imooc.wiremock;


import java.util.ArrayList;
import java.util.List;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.imooc.dto.User;

public class MockService {

	public static void main(String[] args) {

		WireMock.configureFor(6003);
		WireMock.removeAllMappings();
		
		User user = new User();
		user.setUserName("小三");
		user.setPassword("123456");
		User user2 = new User();
		user2.setUserName("小四");
		user2.setPassword("654321");
		List<User> list = new ArrayList<>();
		list.add(user2);
		list.add(user);
		
		WireMock.stubFor(WireMock.get(WireMock.urlPathEqualTo("/user"))
				.willReturn(WireMock.aResponse().withBody(list.toString()).withStatus(200))
				);
	}

}
