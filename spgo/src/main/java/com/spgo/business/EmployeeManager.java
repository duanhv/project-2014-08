package com.spgo.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.spgo.model.bean.Employee;
import com.spgo.model.web.EmployeeInfo;

@Service("employeeManager")
@Transactional(propagation = Propagation.REQUIRED)
public class EmployeeManager extends BaseManager {

	public EmployeeInfo getEmployeeByLoginId(String loginId) throws Exception {
		EmployeeInfo info = null;
		Employee bean = getEmployeeDao().getEmployeeByLoginId(loginId);
		if (bean != null) {
			info = new EmployeeInfo();
			BeanUtils.copyProperties(bean, info);
		}
		return info;
	}

	public void addEmployee(EmployeeInfo person) throws Exception {
		Employee bean = new Employee();
		BeanUtils.copyProperties(person, bean);
		// Encrypt Password
		bean.setPassword(encodeString(bean.getPassword()));
		getEmployeeDao().addEmployee(bean);
	}
	
	public List<EmployeeInfo> listEmployee() throws Exception {
		List<EmployeeInfo> listInfo  = null;
		EmployeeInfo info 		   = null;
		List<Employee> listBean      = getEmployeeDao().listEmployee();
		
		if (listBean != null) {
			listInfo = new ArrayList<EmployeeInfo>();
			for (Employee person : listBean) {
				info = new EmployeeInfo();
				BeanUtils.copyProperties(person, info);
				listInfo.add(info);
			}
		}
		return listInfo;
	}
	
	public void deleteEmployee(EmployeeInfo person) throws Exception {
		Employee bean = new Employee();
		BeanUtils.copyProperties(person, bean);
		getEmployeeDao().deleteEmployee(bean);
	}
	
	public void updateEmployee(EmployeeInfo person) throws Exception {
		Employee bean = new Employee();
		BeanUtils.copyProperties(person, bean);
		getEmployeeDao().updateEmployee(bean);		
	}
}
