package com.imooc.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.imooc.dto.User;

import io.swagger.annotations.ApiOperation;


@RestController
public class UserController {

	@RequestMapping(value = "/user",method = RequestMethod.GET)
	@ApiOperation(value = "用户查询")
	public List<User> quert(@RequestParam() String userName){
		List<User> users = new ArrayList<>();
		users.add(new User());
		users.add(new User());
		users.add(new User());
				
		return users;
	}
	
	
}
