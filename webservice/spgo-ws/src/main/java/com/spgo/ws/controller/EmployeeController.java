package com.spgo.ws.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spgo.ws.business.EmployeeManager;
import com.spgo.ws.domain.info.EmployeeInfo;

@Controller
@RequestMapping("/service/employee/")
public class EmployeeController {

	@Autowired
	private EmployeeManager employeeManager;
	
	@RequestMapping(value = "/getInfo", method = RequestMethod.GET ,headers="Accept=application/json")
	@ResponseBody
	public EmployeeInfo getEmployee(@RequestParam("id") String id) {

		
		EmployeeInfo info = null;
		try {
			info = employeeManager.getEmployeeByLoginId(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return info;
	}
	
}