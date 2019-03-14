package com.imooc.security.core.validate.code;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

public class ValidateCodeFilter extends OncePerRequestFilter {
	
	//@Autowired()
	private AuthenticationFailureHandler authenticationFailureHandler;
	
	private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		if(StringUtils.equals("/authentication/form", request.getRequestURI())
				&& StringUtils.equalsIgnoreCase("post", request.getMethod())) {
			try {
				validate(new ServletWebRequest(request));
			} catch (ValidateCodeException e) {
				authenticationFailureHandler.onAuthenticationFailure(request, response, e);
				return;
			}
		}
		filterChain.doFilter(request, response);
	}

	private void validate(ServletWebRequest requet) throws ServletRequestBindingException {
		ImgCode imgCode = (ImgCode)sessionStrategy.getAttribute(requet, ValidateCodeController.SESSION_KEY);
		
		String requestCode = ServletRequestUtils.getStringParameter(requet.getRequest(), "imageCode");
		
		if(StringUtils.isBlank(requestCode)) {
			throw new ValidateCodeException("验证码不能为空");
		}
		if(imgCode == null) {
			throw new ValidateCodeException("验证码不存在");
		}
		if(imgCode.isExpried()) {
			sessionStrategy.removeAttribute(requet, ValidateCodeController.SESSION_KEY);
			throw new ValidateCodeException("验证码已过期");
		}
		if(!StringUtils.equals(requestCode, imgCode.getCode())) {
			throw new ValidateCodeException("验证码输入错误");
		}
		sessionStrategy.removeAttribute(requet, ValidateCodeController.SESSION_KEY);
	}

	public void setAuthenticationFailureHandler(AuthenticationFailureHandler authenticationFailureHandler) {
		this.authenticationFailureHandler = authenticationFailureHandler;
	}
	
	

}
