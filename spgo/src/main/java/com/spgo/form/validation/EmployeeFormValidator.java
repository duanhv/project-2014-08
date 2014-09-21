package com.spgo.form.validation;


import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.spgo.form.EmployeeForm;


public class EmployeeFormValidator implements Validator{

	public boolean supports(Class<?> paramClass) {
		return EmployeeForm.class.equals(paramClass);
	}


	public void validate(Object target, Errors errors) {
		  EmployeeForm employee = (EmployeeForm) target ;
 		  ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name","name.required","Employee Name is required");
		  ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone","NotEmpty.registration.lastName","Last name must not be Empty.");
		  ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email","email.registration.email","Email must not be Empty.");
		  ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password","password.registration.email","Password must not be Empty.");
	      String firstName = employee.getName();
	      if ((firstName.length()) > 20) {
		      errors.rejectValue("firstName","lengthOfFirstName.registration.firstName","First name must between 1 to 20 characters.");
		    }
	}
}
