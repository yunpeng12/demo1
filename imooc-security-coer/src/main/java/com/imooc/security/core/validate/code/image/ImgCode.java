package com.imooc.security.core.validate.code.image;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

import com.imooc.security.core.validate.code.ValidateCode;

public class ImgCode extends ValidateCode{
	private BufferedImage image;
	
	//几秒后过期构造
	public ImgCode(BufferedImage image, String code, int expireTime) {
		super(code, expireTime);
		this.image = image;
	}

	public ImgCode(BufferedImage image, String code, LocalDateTime expireTime) {
		super(code, expireTime);
		this.image = image;
	}
	
	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

}
