package com.imooc.code;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.imooc.security.core.validate.code.ImgCode;
import com.imooc.security.core.validate.code.ValidateCodeGenerator;

//@Component("imageCodeGenerator")
public class DemoImageCodeGenerator implements ValidateCodeGenerator{

	@Override
	public ImgCode createImgCode(HttpServletRequest request) {
		System.out.println("请求到demo的图形验证码生成器");
		return null;
	}

}
