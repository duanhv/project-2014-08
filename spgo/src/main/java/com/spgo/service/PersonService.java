package com.spgo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.spgo.model.bean.Person;
import com.spgo.model.web.PersonInfo;

@Service("personService")
@Transactional(propagation = Propagation.REQUIRED)
public class PersonService extends BaseService {

	public PersonInfo getPersonByLoginId(String loginId) throws Exception {
		PersonInfo info = null;
		Person bean = getPersonDao().getPersonByLoginId(loginId);
		if (bean != null) {
			info = new PersonInfo();
			BeanUtils.copyProperties(bean, info);
		}
		return info;
	}

	public void addPerson(PersonInfo person) throws Exception {
		Person bean = new Person();
		BeanUtils.copyProperties(person, bean);
		// Encrypt Password
		bean.setPassword(encodeString(bean.getPassword()));
		getPersonDao().addPerson(bean);
	}
	
	public List<PersonInfo> listPerson() throws Exception {
		List<PersonInfo> listInfo  = null;
		PersonInfo info 		   = null;
		List<Person> listBean      = getPersonDao().listPerson();
		
		if (listBean != null) {
			listInfo = new ArrayList<PersonInfo>();
			for (Person person : listBean) {
				info = new PersonInfo();
				BeanUtils.copyProperties(person, info);
				listInfo.add(info);
			}
		}
		return listInfo;
	}
	
	public void deletePerson(PersonInfo person) throws Exception {
		Person bean = new Person();
		BeanUtils.copyProperties(person, bean);
		getPersonDao().deletePerson(bean);
	}
	
	public void updatePerson(PersonInfo person) throws Exception {
		Person bean = new Person();
		BeanUtils.copyProperties(person, bean);
		getPersonDao().updatePerson(bean);		
	}
}
