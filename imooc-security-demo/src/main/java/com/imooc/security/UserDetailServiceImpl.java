package com.imooc.security;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Component;

import com.imooc.security.core.validate.code.ValidateCodeException;

@Component
public class UserDetailServiceImpl implements UserDetailsService,SocialUserDetailsService {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.info("表单登陆用户名:"+username);
		return buildUser(username);
	}



	@Override
	public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
		logger.info("社交登陆用户id:"+userId);
		return buildUser(userId);
	}



	private SocialUserDetails buildUser(String userId) {
		String encode = passwordEncoder.encode("123456");
		logger.info("加密串:"+ encode);
		if(StringUtils.isBlank(userId)) {
			throw new ValidateCodeException("用户id不能为空");
		}
		
		return new SocialUser(userId,encode ,true,true,true,true,AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
	}



}
