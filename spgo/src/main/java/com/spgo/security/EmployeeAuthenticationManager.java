package com.spgo.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;

import com.spgo.dao.EmployeeDao;
import com.spgo.model.bean.EmployeeModel;

@SuppressWarnings("deprecation")
public class EmployeeAuthenticationManager  implements AuthenticationManager{
	
	@Autowired
	private EmployeeDao employeeDao;
	
	@Autowired
	private Md5PasswordEncoder passwordEncoder;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		EmployeeModel em = employeeDao.getEmployeeByLoginId((String) authentication.getName());
		if(em == null ){
			throw new BadCredentialsException("User does not exists!");
		} else if (! em.getPassword().equals(passwordEncoder.encodePassword( (String)authentication.getCredentials(),null))) {			
			throw new BadCredentialsException("Wrong password!");
		} else {
			final List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			authorities.add(new GrantedAuthorityImpl("ROLE_USER"));
			return new UsernamePasswordAuthenticationToken( authentication.getName(), authentication.getCredentials(),authorities);
		}		
	}

}
