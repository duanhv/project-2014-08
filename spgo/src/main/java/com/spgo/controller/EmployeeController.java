package com.spgo.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.spgo.business.EmployeeManager;
import com.spgo.common.Constants;
import com.spgo.common.EncryptionHelper;
import com.spgo.dao.EmployeeDao;
import com.spgo.facade.EmployeeConverter;
import com.spgo.form.EmployeeForm;
import com.spgo.form.validation.EmployeeFormValidator;
import com.spgo.model.bean.EmployeeModel;
import com.spgo.model.web.EmployeeInfo;
import com.spgo.security.EmployeeAuthenticationManager;
   
@Controller    
public class EmployeeController {  
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);
	@Autowired
	private EmployeeManager employeeManager;
	@Autowired
	private EmployeeFormValidator employeeValidation;
	@Autowired
	private EmployeeConverter employeeConverter;
	@Autowired  
    private MessageSource messageSource;
	@Autowired
	private EmployeeDao employeeDao;
	@Autowired
	private EmployeeAuthenticationManager employeeAuthenticationManager;

    // Guest (allow to add new)
    @RequestMapping(value = "/employee/save", method = RequestMethod.GET)  
	public String createEmployee(ModelMap model) { 
    	String message = messageSource.getMessage("welcome.employee", null, new Locale("en"));
    	System.out.println(message);    	
    	model.addAttribute("employeeForm",new EmployeeForm());
        return "createEmployee";  
    }
    
    @RequestMapping(value = "/employee/save", method = RequestMethod.POST)  
	public String createEmployee(@ModelAttribute EmployeeForm employeeForm, ModelMap model, BindingResult bindingResult ,HttpServletRequest request) {

    	if (bindingResult.hasErrors()) {
    		model.addAttribute("employeeForm",employeeForm);
			return "createEmployee";
		} else {
			String email = employeeForm.getEmail();
			EmployeeModel em = employeeDao.getEmployeeByLoginId(email);
			if(em != null ){  
				String message = messageSource.getMessage("create.employee", null, new Locale("en"));			
	    		model.addAttribute("userExist",message);
				return "createEmployee";
			}
			EmployeeModel employee = new EmployeeModel();
			employeeConverter.convertFormToModel(employeeForm, employee);			
	    	try {
		    	
		    	if(StringUtils.hasText(employee.getId())) {
		    		employeeManager.updateEmployee(employee);
		    	} else {
		    		employee.setActive(Constants.NO);
		    		employeeManager.addEmployee(employee);
		    	}
		    	
		    	// send email active
		    	String subject 		= messageSource.getMessage("active.subject", null, new Locale("en"));
		    	String content 		= messageSource.getMessage("active.content", null, new Locale("en"));
		    	
		    	employeeManager.sendActiveEmail(employee.getName(), employee.getEmail(), subject, content);

			} catch (Exception e) {
				e.printStackTrace();
			}
	    	return "redirect:/home"; 
		}
 
    }
        
    @RequestMapping(value = "/employee/delete", method = RequestMethod.GET)  
	public String deleteEmployee(@ModelAttribute EmployeeInfo employee, ModelMap model) {  
	    try {
	    	employeeManager.deleteEmployee(employee);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return "redirect:/employee";  
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

    // Login
    @RequestMapping(value = "/login", method = RequestMethod.GET)  
	public String getLogin(ModelMap model) {
        return "login";  
    }
  
    @RequestMapping(value = "/home", method = RequestMethod.GET)  
	public String getDefault(ModelMap model) {
        return "homePage";  
    }
    private void autoLogin(EmployeeModel employeeModel ,HttpServletRequest request){
    	UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(employeeModel.getEmail(), employeeModel.getPassword());
    	request.getSession();
    	token.setDetails(new WebAuthenticationDetails(request));
    	Authentication authenticatedUser = employeeAuthenticationManager.authenticate(token);  
  	    SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
    }
    @RequestMapping(value = "/employee/details", method = RequestMethod.GET)  
	public String employeeDetails(ModelMap model) {  
    	EmployeeModel em =  employeeDao.getCurrentUser();
    	EmployeeForm form = new EmployeeForm();
    	employeeConverter.convertModelToForm(em,form);
    	model.addAttribute("employee", form);
        return "employeeDetails"; 
    }
    
    @RequestMapping(value = "/employee/active", method = RequestMethod.GET)  
	public String activeEmployee(@RequestParam String id, HttpServletRequest request) {    	

    	try {
    		System.out.println("id Before = " + id);
    		String email = EncryptionHelper.decrypt(id);
    		System.out.println("id After  = " + email);

    		employeeManager.activeEmployeeByEmail(email);
    		
    		EmployeeModel employee = employeeDao.getEmployeeByLoginId(email);
	    	autoLogin(employee, request);	    	

    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	
        return "redirect:/home";  
    }
    
}
