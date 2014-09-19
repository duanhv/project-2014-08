package com.spgo.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import com.spgo.business.EmployeeManager;
import com.spgo.model.web.EmployeeInfo;
   
@Controller    
public class EmployeeController {  
   
	@Autowired
	private EmployeeManager employeeManager;

    @RequestMapping(value = "/employee/save", method = RequestMethod.POST)  
	public View createEmployee(@ModelAttribute EmployeeInfo employee, ModelMap model) {
    	try {
	    	employee.setBirthDay(new Date());
	    	if(StringUtils.hasText(employee.getId())) {
	    		employeeManager.updateEmployee(employee);
	    	} else {
	    		employeeManager.addEmployee(employee);
	    	}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return new RedirectView("/spgo/employee");  
    }
        
    @RequestMapping(value = "/employee/delete", method = RequestMethod.GET)  
	public View deleteEmployee(@ModelAttribute EmployeeInfo employee, ModelMap model) {  
	    try {
	    	employeeManager.deleteEmployee(employee);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return new RedirectView("/spgo/employee");  
    }
    
    @RequestMapping(value = "/employee", method = RequestMethod.GET)  
	public String getEmployeeList(ModelMap model) {
    	try {
    		model.addAttribute("employeeList", employeeManager.listEmployee());
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
        return "listEmployee";  
    } 

    // Guest (allow to add new)
    @RequestMapping(value = "/guest", method = RequestMethod.GET)  
	public String getGuest(ModelMap model) {
        return "addEmployee";  
    }
    @RequestMapping(value = "/guest/save", method = RequestMethod.POST)  
	public View createGuest(@ModelAttribute EmployeeInfo employee, ModelMap model) {
    	try {
	    	employee.setBirthDay(new Date());
	    	if(StringUtils.hasText(employee.getId())) {
	    		employeeManager.updateEmployee(employee);
	    	} else {
	    		employeeManager.addEmployee(employee);
	    	}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return new RedirectView("/spgo/employee");  
    }

    // Login-Logout
    @RequestMapping(value = "/login", method = RequestMethod.GET)  
	public String getLogin(ModelMap model) {
        return "login";  
    }
    
    @RequestMapping(value = "/logout", method = RequestMethod.GET)  
	public String getLogout(ModelMap model) {
        return "logout";  
    }
}
