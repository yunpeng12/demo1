package com.imooc.security.core.validate.code;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import com.imooc.security.core.properties.SecurityProperties;

@RestController
public class ValidateCodeController {
	
	public static final String SESSION_KEY = "SESSION_KEY_IMG_CODE";

	private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
	
	@Autowired
	private SecurityProperties securityProperties;
	
	@GetMapping("/code/img")
	public void createImgCode(HttpServletRequest request,HttpServletResponse response) throws IOException {
		
		ImgCode imgcode = createImgCode(request);
		sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_KEY, imgcode);
		ImageIO.write(imgcode.getImage(), "JPEG", response.getOutputStream());
	}

	private ImgCode createImgCode(HttpServletRequest request) {
		int width = ServletRequestUtils.getIntParameter(request, "width", securityProperties.getValidateCode().getImageCode().getWidth());
		int height = ServletRequestUtils.getIntParameter(request, "height", securityProperties.getValidateCode().getImageCode().getHeight());
		int length = securityProperties.getValidateCode().getImageCode().getLength();
		int expireIn = securityProperties.getValidateCode().getImageCode().getExpireIn();
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();
		Random random = new Random();
		
		g.setColor(getRandColor(200, 250));
		g.fillRect(0, 0, width, height);
		g.setFont(new Font("Times New Roman",Font.ITALIC,20));
		g.setColor(getRandColor(160, 200));
		for(int i = 0; i < 155; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int xl = random.nextInt(12);
			int yl = random.nextInt(12);
			g.drawLine(x, y, x + xl, y + yl);
		}
		String sRand = "";
		for(int i = 0; i < length; i++) {
			String rand = String.valueOf(random.nextInt(10));
			sRand += rand; 
			g.setColor(new Color(20 + random.nextInt(110),20 + random.nextInt(110),20 + random.nextInt(110)));
			g.drawString(rand, 13 * i + 6, 16);
		}
		g.dispose();
		return new ImgCode(image, sRand, expireIn);
	}
	
	private Color getRandColor(int fc,int bc) {
		Random random = new Random();
		if(fc > 255) {
			fc = 255;
		}
		if(bc >255) {
			bc = 255;
		}
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		
		return new Color(r,g,b);
	}
	
}
