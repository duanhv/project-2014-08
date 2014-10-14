package com.spgo.business;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.spgo.common.EncryptionHelper;
import com.spgo.model.bean.EmployeeModel;
import com.spgo.model.web.EmployeeInfo;

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
	
	public List<EmployeeInfo> listEmployee() throws Exception {
		List<EmployeeInfo> listInfo  = null;
		EmployeeInfo info 		   = null;
		List<EmployeeModel> listBean      = getEmployeeDao().listEmployee();
		
		if (listBean != null) {
			listInfo = new ArrayList<EmployeeInfo>();
			for (EmployeeModel person : listBean) {
				info = new EmployeeInfo();
				BeanUtils.copyProperties(person, info);
				listInfo.add(info);
			}
		}
		return listInfo;
	}
	
	public void deleteEmployee(EmployeeInfo person) throws Exception {
		EmployeeModel bean = new EmployeeModel();
		BeanUtils.copyProperties(person, bean);
		getEmployeeDao().deleteEmployee(bean);
	}
	
	public void updateEmployee(EmployeeModel employee) throws Exception {		
		getEmployeeDao().updateEmployee(employee);		
	}	
	
	public void sendActiveEmail(String name, String email, String subject, String content) {
		try {
			String from = "support@gmail.com";
			String to   = email;
			
			if (StringUtils.isNotBlank(content)) {
				
				String encEmail = EncryptionHelper.encodeURL(EncryptionHelper.encrypt(email));
				
				content = content.replace("#name#", name);
				content = content.replace("#email#", encEmail);
				
				getMailUtil().sendMail(from, to, subject, content);				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void activeEmployeeByEmail(String email) {
		getEmployeeDao().activeEmployeeByEmail(email);
	}
	
	public void changePassword(String email, String password) throws Exception {
		getEmployeeDao().changePassword(email, encodeString(password));
	}
}
