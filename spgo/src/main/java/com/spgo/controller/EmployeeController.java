package com.spgo.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import com.spgo.business.EmployeeManager;
import com.spgo.facade.EmployeeConverter;
import com.spgo.form.EmployeeForm;
import com.spgo.form.validation.EmployeeFormValidator;
import com.spgo.model.bean.EmployeeModel;
import com.spgo.model.web.EmployeeInfo;
   
@Controller    
public class EmployeeController {  
	private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);
	@Autowired
	private EmployeeManager employeeManager;
	@Autowired
	private EmployeeFormValidator employeeValidation;
	@Autowired
	private EmployeeConverter employeeConverter;

    @RequestMapping(value = "/employee/save", method = RequestMethod.POST)  
	public String createEmployee(@ModelAttribute EmployeeForm employeeForm, ModelMap model, BindingResult bindingResult) {

    	if (bindingResult.hasErrors()) {
    		model.addAttribute("employeeForm",employeeForm);
			return "redirect:/guest";
		} else {	
			EmployeeModel employee = new EmployeeModel();
			employeeConverter.convertFormToModel(employeeForm, employee);			
	    	try {
		    	
		    	if(StringUtils.hasText(employee.getId())) {
		    		employeeManager.updateEmployee(employee);
		    	} else {
		    		employeeManager.addEmployee(employee);
		    	}
			} catch (Exception e) {
				e.printStackTrace();
			}
	    	return "redirect:/login"; 
		}
 
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
    @RequestMapping(value = "/createEmployee", method = RequestMethod.GET)  
	public String getGuest(ModelMap model) {
    	model.addAttribute("employeeForm",new EmployeeForm());
        return "addEmployee";  
    }
    @RequestMapping(value = "/createEmployee", method = RequestMethod.POST)  
	public String createGuest(@ModelAttribute EmployeeForm employeeForm, ModelMap model , BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			logger.info("Returning custSave.jsp page");
			return "addEmployee";
		}
		EmployeeModel employee = new EmployeeModel();
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
    	return "redirect:/spgo/employee";  
    }

    // Login
    @RequestMapping(value = "/login", method = RequestMethod.GET)  
	public String getLogin(ModelMap model) {
        return "login";  
    }

    @RequestMapping(value = "/default", method = RequestMethod.GET)  
	public String getDefault(ModelMap model) {
        return "default";  
    }
}
