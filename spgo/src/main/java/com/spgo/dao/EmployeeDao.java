package com.spgo.dao;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.spgo.model.bean.EmployeeModel;

@Repository("employeeDao")
public class EmployeeDao {
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	public static final String COLLECTION_NAME = "employee";

	public EmployeeModel getEmployeeByLoginId(String loginId) {
		String tagName = "loginId";
		Query query = new Query();
		query.limit(1);
		query.addCriteria(Criteria.where(tagName).regex(loginId));

		return mongoTemplate.findOne(query, EmployeeModel.class);
	}
	
	public void addEmployee(EmployeeModel employee) {
		if (!mongoTemplate.collectionExists(EmployeeModel.class)) {
			mongoTemplate.createCollection(EmployeeModel.class);
		}		
		employee.setId(UUID.randomUUID().toString());
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
