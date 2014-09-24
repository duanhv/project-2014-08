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

}
