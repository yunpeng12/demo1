package com.imooc.security.core.validate.code;

import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

@RestController
public class ValidateCodeController {
	
	public static final String SESSION_KEY = "SESSION_KEY_IMG_CODE";

	private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
	
	@Autowired
	private ValidateCodeGenerator validateCodeGenerator;
	
	@GetMapping("/code/img")
	public void createImgCode(HttpServletRequest request,HttpServletResponse response) throws IOException {
		
		ImgCode imgcode = validateCodeGenerator.createImgCode(request);
		sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_KEY, imgcode);
		ImageIO.write(imgcode.getImage(), "JPEG", response.getOutputStream());
	}

	
}
