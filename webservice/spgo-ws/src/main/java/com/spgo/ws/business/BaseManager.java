package com.spgo.ws.business;

import com.spgo.ws.dao.EmployeeDao;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseManager {

	@Autowired
	private EmployeeDao employeeDao;

	protected EmployeeDao getEmployeeDao() {
		return employeeDao;
	}

}
