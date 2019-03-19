package com.imooc.code;

import org.springframework.web.context.request.ServletWebRequest;

import com.imooc.security.core.validate.code.ValidateCodeGenerator;
import com.imooc.security.core.validate.code.image.ImgCode;

//@Component("imageCodeGenerator")
public class DemoImageCodeGenerator implements ValidateCodeGenerator{

	@Override
	public ImgCode generator(ServletWebRequest request) {
		System.out.println("请求到demo的图形验证码生成器");
		return null;
	}

}
