package com.spgo.facade;

import com.spgo.form.EmployeeForm;
import com.spgo.form.EmployeeForm.Gender;
import com.spgo.model.bean.EmployeeModel;

public class EmployeeConverter {

	public void convertFormToModel(EmployeeForm employeeForm ,EmployeeModel employeemodel ) {

		employeemodel.setName(employeeForm.getName());
		employeemodel.setEmail(employeeForm.getEmail());
		employeemodel.setPassword(employeeForm.getPassword());
		employeemodel.setPhone(employeeForm.getPhone());
		employeemodel.setActive(employeeForm.getActive());
		employeemodel.setProfileImage(employeeForm.getProfileImage());
		if (employeeForm.getAge() != null) {
			employeemodel.setAge(Integer.valueOf(employeeForm.getAge()));
		}
		if (employeeForm.getGender() != null) {
			employeemodel.setGender(employeeForm.getGender().name());
		}
		employeemodel.setBirthDay(employeeForm.getBirthday());
	}
	
	public void convertModelToForm(EmployeeModel employeemodel ,EmployeeForm employeeForm ) {
		
		if (employeemodel != null) {

			employeeForm.setName(employeemodel.getName());
			employeeForm.setEmail(employeemodel.getEmail());
			employeeForm.setPassword(employeemodel.getPassword());
			employeeForm.setPhone(employeemodel.getPhone());
			employeeForm.setActive(employeemodel.getActive());
			employeeForm.setProfileImage(employeemodel.getProfileImage());
			if (employeemodel.getAge() > 0) {
				employeeForm.setAge(Integer.valueOf(employeemodel.getAge()));
			}
			if (employeemodel.getGender() != null) {
				employeeForm.setGender(Gender.MALE.equals(employeemodel.getGender())? Gender.MALE: Gender.FEMALE);
			}
			if(employeemodel.getBirthDay() != null){
				employeeForm.setBirthday(employeemodel.getBirthDay());
			}
		}
	}

}	
