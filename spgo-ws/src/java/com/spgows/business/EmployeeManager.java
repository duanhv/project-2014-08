package com.spgows.business;

import com.spgows.domain.bean.Employee;
import com.spgows.domain.info.EmployeeInfo;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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

}
