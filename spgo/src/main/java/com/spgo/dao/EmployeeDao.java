package com.spgo.dao;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.spgo.model.bean.Employee;

@Repository("employeeDao")
public class EmployeeDao {
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	public static final String COLLECTION_NAME = "employee";

	public Employee getEmployeeByLoginId(String loginId) {
		String tagName = "loginId";
		Query query = new Query();
		query.limit(1);
		query.addCriteria(Criteria.where(tagName).regex(loginId));

		return mongoTemplate.findOne(query, Employee.class);
	}
	
	public void addEmployee(Employee employee) {
		if (!mongoTemplate.collectionExists(Employee.class)) {
			mongoTemplate.createCollection(Employee.class);
		}		
		employee.setId(UUID.randomUUID().toString());
		mongoTemplate.insert(employee, COLLECTION_NAME);
	}

	public List<Employee> listEmployee() {
		return mongoTemplate.findAll(Employee.class, COLLECTION_NAME);
	}
	
	public void deleteEmployee(Employee employee) {
		mongoTemplate.remove(employee, COLLECTION_NAME);
	}
	
	public void updateEmployee(Employee employee) {
		mongoTemplate.insert(employee, COLLECTION_NAME);		
	}
}
