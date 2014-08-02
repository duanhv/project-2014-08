package com.spgo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

import com.spgo.dao.PersonDao;

public abstract class BaseService {

	@Autowired
	private PersonDao personDao;

	protected PersonDao getPersonDao() {
		return personDao;
	}
	
	public String encodeString(String str) {
		Md5PasswordEncoder encode = new Md5PasswordEncoder();
		return encode.encodePassword(str, null);
	}
}
