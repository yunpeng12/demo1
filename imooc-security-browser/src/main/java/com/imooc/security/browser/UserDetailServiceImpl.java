package com.imooc.security.browser;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.imooc.security.core.validate.code.ValidateCodeException;

@Component
public class UserDetailServiceImpl implements UserDetailsService {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		String encode = passwordEncoder.encode("123456");
		logger.info("加密串:"+ encode);
		if(StringUtils.isBlank(username)) {
			throw new ValidateCodeException("用户名不能为空");
		}
		
		return new User(username,encode ,true,true,true,true,AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
	}



}
