package com.cts.springboot.cruddemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cts.springboot.cruddemo.entity.Employee;

import jakarta.persistence.EntityManager;

@Repository
public class EmployeeDAOHibernateImpl2 implements EmployeeDAO {
	
	//define field for entity manager
	private EntityManager entityManager;
	
	//setup constructor injection
	@Autowired
	public EmployeeDAOHibernateImpl2(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<Employee> findAll() {
		
		//get the current hibernate session
		Session session = entityManager.unwrap(Session.class);
		
		//create a query
		Query<Employee> query = session.createQuery("from Employee", Employee.class);
		
		//execute query and get result list
		List<Employee> employees = query.getResultList();
		
		//return the list
		return employees;
		
	}

	@Override
	public Employee findById(int id) {
		
		//get the current hibernate session
		Session session = entityManager.unwrap(Session.class);
		
		//get the employee
		Employee employee = session.get(Employee.class, id);
		
		//return the employee
		return employee;
		
	}

	@Override
	public void save(Employee employee) {
		
		// get the current hibernate session
		Session session = entityManager.unwrap(Session.class);
		
		//save employee
		session.merge(employee);
		
	}

	@Override
	public void deleteById(int id) {
		
		// get the current hibernate session
		Session session = entityManager.unwrap(Session.class);
		
		//get employee with id
		Employee employee = session.get(Employee.class, id);
		
		//delete the employee
		session.remove(employee);
		
	}

}
