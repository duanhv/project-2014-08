package com.spgo.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

import com.spgo.common.MailUtil;
import com.spgo.dao.EmployeeDao;

public abstract class BaseManager {

	@Autowired
	private EmployeeDao employeeDao;

	@Autowired
	private MailUtil mailUtil;
	
	protected EmployeeDao getEmployeeDao() {
		return employeeDao;
	}
	
	public String encodeString(String str) {
		//
		Md5PasswordEncoder encode = new Md5PasswordEncoder();
		return encode.encodePassword(str, null);
	}

	public MailUtil getMailUtil() {
		return mailUtil;
	}
}
