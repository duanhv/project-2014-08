package com.spgows.business;

import com.spgows.dao.EmployeeDao;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseManager {

	@Autowired
	private EmployeeDao employeeDao;

	protected EmployeeDao getEmployeeDao() {
		return employeeDao;
	}

}
