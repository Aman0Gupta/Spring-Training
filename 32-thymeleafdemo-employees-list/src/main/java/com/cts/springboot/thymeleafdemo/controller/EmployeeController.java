package com.cts.springboot.thymeleafdemo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cts.springboot.thymeleafdemo.model.Employee;

import jakarta.annotation.PostConstruct;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
	
	//load employee data
	private List<Employee> employees;
	
	//create a mapping for "/hello"
	@PostConstruct
	public void loadData() {
		
		//create employees
		Employee emp1 = new Employee(1, "Leslie", "Andrews", "leslie@luv2code.com");
		Employee emp2 = new Employee(2, "Rick", "Hudson", "hudson@luv2code.com");
		Employee emp3 = new Employee(3, "Joey", "Johnson", "joey@luv2code.com");
		
		//create the list
		employees = new ArrayList<>();
		
		//add to the list
		employees.add(emp1);
		employees.add(emp2);
		employees.add(emp3);
		
	}
	
	//add mapping for "/list"
	@GetMapping("/list")
	public String employeeList(Model model) {
		
		model.addAttribute("employees", employees);
		
		return "employee-list";
		
	}

}
