package com.spgo.ws.oauth.security;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;

import com.spgo.ws.dao.EmployeeDao;
import com.spgo.ws.domain.bean.EmployeeModel;

public class CustomUserAuthenticationProvider implements AuthenticationProvider{
	@Autowired
	private MongoTemplate mongoTemplate;
	@Autowired
	private EmployeeDao employeeDao;

	private Md5PasswordEncoder passwordEncoder = new Md5PasswordEncoder();
	
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String userName = (String) authentication.getPrincipal();
		if(StringUtils.isNotBlank((userName))){
			EmployeeModel employee = employeeDao.getEmployeeByLoginId(userName);
			if(employee != null ){
				if(employee.getPassword().equals(passwordEncoder.encodePassword((String)authentication.getCredentials(),null))){
					List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
					CustomUserPasswordAuthenticationToken auth=new CustomUserPasswordAuthenticationToken(authentication.getPrincipal(), authentication.getCredentials(),grantedAuthorities);						
					return auth;
				} else {
					throw new BadCredentialsException("Bad User Credentials.");
				}
			} else {
				throw new BadCredentialsException("Bad User Credentials.");
			}			
		}
		throw new BadCredentialsException("Bad User Credentials.");
	}
	
	public boolean supports(Class<? extends Object> authentication) {
		
		return true;
	}


}
