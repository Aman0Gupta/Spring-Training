package com.cts.springboot.cruddemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.cts.springboot.cruddemo.entity.Employee;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO {
	
	//define field for entity manager
	private EntityManager entityManager;
	
	//setup constructor injection
	@Autowired
	public EmployeeDAOJpaImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<Employee> findAll() {
		
		//create a query
		Query query = entityManager.createQuery("from Employee");
		
		//execute query and get result list
		List<Employee> employees = query.getResultList();
		
		//return the list
		return employees;
		
	}

	@Override
	public Employee findById(int id) {
		
		//get the employee
		Employee employee = entityManager.find(Employee.class, id);
		
		//return the employee
		return employee;
		
	}

	@Override
	public void save(Employee employee) {
		
		//save or update the employee
		Employee dbEmployee  = entityManager.merge(employee);
		
		//update with id from db...so we can get generated id for save/insert
		employee.setId(dbEmployee.getId());
		
	}

	@Override
	public void deleteById(int id) {
		
		//delete object with primary key
		Query query = entityManager.createQuery("delete from Employee where id=:employeeId");
		
		query.setParameter("employeeId", id);
		
		query.executeUpdate();
		
	}

}
