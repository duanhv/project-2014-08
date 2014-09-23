package com.spgo.facade;

import com.spgo.form.EmployeeForm;
import com.spgo.model.bean.EmployeeModel;

public class EmployeeConverter {
	public void convertFormToModel(EmployeeForm employeeForm ,EmployeeModel employeemodel ){

		employeemodel.setEmail(employeeForm.getEmail());
		employeemodel.setName(employeeForm.getName());
		employeemodel.setPassword(employeeForm.getPassword());
		employeemodel.setPhone(employeeForm.getPhone());
		employeemodel.setAge(Integer.valueOf(employeeForm.getAge()));	
	}

}
