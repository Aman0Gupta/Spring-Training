package com.cts.springboot.thymeleafdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cts.springboot.thymeleafdemo.entity.Employee;
import com.cts.springboot.thymeleafdemo.service.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
	
	private EmployeeService employeeService;
	
	@Autowired
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	//add mapping for "/list"
	@GetMapping("/list")
	public String employeeList(Model model) {
		
		List<Employee> employees = employeeService.findAll();
		
		model.addAttribute("employees", employees);
		
		return "employees/employee-list";
		
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model model) {
		//create model attribute to bind form data
		Employee employee = new Employee();
		model.addAttribute("employee", employee);
		return "employees/employee-form";
	}

	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("employeeId") int id, Model model) {
		//get the employee from the service
		Employee employee = employeeService.findById(id);
		
		//set employee as a model attritube to pre-populate the form
		model.addAttribute("employee", employee);
		
		//send to our form
		return "employees/employee-form";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("employeeId") int id) {
		//delete the employee
		employeeService.deleteById(id);
		//redirect to /employee/list
		return "redirect:/employees/list";
	}
	
	@PostMapping("/save")
	public String saveEmployee(@ModelAttribute("employee") Employee theEmployee) {
		//save the employe 
		employeeService.save(theEmployee);
		
		//use a redirect to prevent duplicate submission
		return "redirect:/employees/list";
	}
	

}
