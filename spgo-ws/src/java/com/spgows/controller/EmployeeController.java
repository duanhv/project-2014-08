package com.spgows.controller;

import com.spgows.business.EmployeeManager;
import com.spgows.domain.info.EmployeeInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/service/employee/")
public class EmployeeController {

	@Autowired
	private EmployeeManager employeeManager;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET,headers="Accept=application/json")
	public EmployeeInfo getEmployee(@PathVariable String id) {

		
		EmployeeInfo info = null;
		try {
			info = employeeManager.getEmployeeByLoginId(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return info;
	}
	
}