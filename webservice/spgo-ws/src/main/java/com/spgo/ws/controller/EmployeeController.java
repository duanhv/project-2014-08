package com.spgo.ws.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.spgo.ws.business.EmployeeManager;
import com.spgo.ws.dao.EmployeeDao;
import com.spgo.ws.domain.bean.EmployeeModel;
import com.spgo.ws.domain.info.EmployeeInfo;

@Controller
@RequestMapping("/service/employee/")
public class EmployeeController {

	@Autowired
	private EmployeeManager employeeManager;
	@Autowired
	private EmployeeDao employeeDao;
	
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
	@RequestMapping(value = "/addEmployee", method = RequestMethod.POST ,headers="Accept=application/json")
	 @ResponseStatus( HttpStatus.CREATED )
	public void addEmployeeFromJson(@RequestBody final EmployeeModel employeeModel){		
		try{
			employeeDao.addEmployee(employeeModel);
		} catch(Exception ex){
			System.out.println("Can not save new employee");
		}
		
	}
	
}