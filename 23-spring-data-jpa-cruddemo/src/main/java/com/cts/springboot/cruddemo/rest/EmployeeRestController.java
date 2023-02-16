package com.cts.springboot.cruddemo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.springboot.cruddemo.entity.Employee;
import com.cts.springboot.cruddemo.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
	
	private EmployeeService employeeService;
	
	//inject employee Service
	@Autowired
	public EmployeeRestController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	//expose "/employees" and return the list of employees
	@GetMapping("/employees")
	public List<Employee> findAll(){
		return employeeService.findAll();
	}
	
	//expose "/employees/{employeeId}" and return employees
	@GetMapping("/employees/{employeeId}")
	public Employee findById(@PathVariable int employeeId){
		Employee employee = employeeService.findById(employeeId);
		if(employee == null) {
			throw new RuntimeException("Employee id not found: " + employeeId);
		}
		return employee;
	}
	
	//expose "/employees" and to add an employee
	@PostMapping("/employees")
	public Employee addEmployee(@RequestBody Employee employee ){
		
		//also just in case they pass an id in JSON...set it to 0
		employee.setId(0);
		employeeService.save(employee);
		return employee;
	}
	
	//expose "/employees and update an employee
	@PutMapping("/employees")
	public Employee updateEmployee(@RequestBody Employee employee ){
		
		employeeService.save(employee);
		return employee;
	}
	
	//expose "/employees/{employeeId}" and return the list of employees
	@DeleteMapping("/employees/{employeeId}")
	public String deleteEmployee(@PathVariable int employeeId ){
		
		Employee employee = employeeService.findById(employeeId);
		if(employee == null) {
			throw new RuntimeException("Employee with employee id " + employeeId + " not found!!");
		}
		employeeService.deleteById(employeeId);
		return "Employee deleted with employee id of " + employeeId;
	}

}
