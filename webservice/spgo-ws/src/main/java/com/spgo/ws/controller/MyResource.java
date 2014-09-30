package com.spgo.ws.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spgo.ws.dao.EmployeeDao;
import com.spgo.ws.domain.bean.EmployeeModel;
import com.spgo.ws.oauth.security.CustomUserAuthenticationProvider;


@Path("/MyResource")
public class MyResource {
	@Autowired
	private EmployeeDao employeeDao;
	@GET
	@Path("/createInfo")
	public String createInfo(){
		return "\n\n\t!!!Protected Resource(createInfo) Accessed !!!! Returning from Myresource createInfo\n";

	}
	
	@RequestMapping(value = "/getMyInfo", method = RequestMethod.GET,headers="Accept=application/json")
	public EmployeeModel getMyInfo(){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication != null && StringUtils.isNotBlank((String)authentication.getPrincipal())){
			String email = (String)authentication.getPrincipal();
			EmployeeModel employee = employeeDao.getEmployeeByLoginId(email);
			if(employee == null ){
				return null;
			} else {
				return employee;
			}			
		} else {
			return null;
		}
		
		
	}
	
	
	@GET
	@Path("/updateInfo")
	public String updateMyInfo(){
		return "\n\n\t Protected Resource(updateInfo) Accessed !!!! Returning from Myresource updateInfo\n";
		
	}

}
