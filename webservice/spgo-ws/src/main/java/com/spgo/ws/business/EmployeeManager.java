package com.spgo.ws.business;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.spgo.ws.domain.bean.EmployeeModel;
import com.spgo.ws.domain.info.EmployeeInfo;

@Service("employeeManager")
@Transactional(propagation = Propagation.REQUIRED)
public class EmployeeManager extends BaseManager {

	public EmployeeInfo getEmployeeByLoginId(String loginId) throws Exception {
		EmployeeInfo info = null;
		EmployeeModel bean = getEmployeeDao().getEmployeeByLoginId(loginId);
		if (bean != null) {
			info = new EmployeeInfo();
			BeanUtils.copyProperties(bean, info);
		}
		return info;
	}
	
	public void addEmployee(EmployeeModel employeeModel) throws Exception {
		
		if (employeeModel != null) {
			employeeModel.setPassword(encodeString(employeeModel.getPassword()));
			getEmployeeDao().addEmployee(employeeModel);
		}
	}
}
