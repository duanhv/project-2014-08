package com.spgo.facade;

import com.spgo.form.EmployeeForm;
import com.spgo.model.bean.EmployeeModel;

public class EmployeeConverter {
	public void convertFormToModel(EmployeeForm employeeForm ,EmployeeModel employeemodel ){

		employeemodel.setName(employeeForm.getName());
		employeemodel.setEmail(employeeForm.getEmail());
		employeemodel.setPassword(employeeForm.getPassword());
		employeemodel.setPhone(employeeForm.getPhone());
		if (employeeForm.getAge() != null) {
			employeemodel.setAge(Integer.valueOf(employeeForm.getAge()));
		}
		if (employeeForm.getGender() != null) {
			employeemodel.setGender(employeeForm.getGender().name());
		}
		employeemodel.setBirthDay(employeeForm.getBirthday());
	}
	
	public void convertModelToForm(EmployeeModel employeemodel ,EmployeeForm employeeForm ){
		employeeForm.setName(employeeForm.getName());
		employeeForm.setEmail(employeeForm.getEmail());
		employeeForm.setPassword(employeeForm.getPassword());
		employeeForm.setPhone(employeeForm.getPhone());
		if (employeemodel.getAge() > 0) {
			employeeForm.setAge(Integer.valueOf(employeemodel.getAge()));
		}
		if (employeemodel.getGender() != null) {
//			employeeForm.setGender(employeemodel.getGender());
		}
		if(employeemodel.getBirthDay() != null){
			employeeForm.setBirthday(employeemodel.getBirthDay());
		}
		
		
	}

}	
