package com.spgo.dao;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import com.spgo.model.bean.EmployeeModel;

@Repository("employeeDao")
public class EmployeeDao {
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	public static final String COLLECTION_NAME = "EmployeeModel";

	public EmployeeModel getEmployeeByLoginId(String email) {
		String tagName = "email";
		Query query = new Query();
		query.limit(1);
		query.addCriteria(Criteria.where(tagName).is(email));
		
		EmployeeModel employee = mongoTemplate.findOne(query, EmployeeModel.class, COLLECTION_NAME); 
		return employee;
	}
	public EmployeeModel getCurrentUser(){
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
    	if("anonymousUser".equals(userName)){
    		return null;
    	} else {
    		return getEmployeeByLoginId(userName);
    	}		
	}
	public void addEmployee(EmployeeModel employee) {
		employee.setUpdatedDate(new Date());
		if (!mongoTemplate.collectionExists(EmployeeModel.class)) {
			mongoTemplate.createCollection(EmployeeModel.class);
		}		
		employee.setId(UUID.randomUUID().toString());
		employee.setCreatedDate(new Date());
		mongoTemplate.insert(employee, COLLECTION_NAME);
	}

	public List<EmployeeModel> listEmployee() {
		return mongoTemplate.findAll(EmployeeModel.class, COLLECTION_NAME);
	}
	
	public void deleteEmployee(EmployeeModel employee) {
		mongoTemplate.remove(employee, COLLECTION_NAME);
	}
	
	public void updateEmployee(EmployeeModel employee) {
		mongoTemplate.insert(employee, COLLECTION_NAME);		
	}
}
