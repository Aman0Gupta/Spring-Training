package com.cts.springbootdemo.mycoolapp.rest;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FunRestController {
	
	//inject properties for: coach.name and team.name
	@Value("${coach.name}")
	private String coachName;
	
	@Value("${team.name}")
	private String teamName;
	
	//expose new endpoint for "teamInfo"
	@GetMapping("/teaminfo")
	public String teamInfo() {
		return "Coach Name : " + coachName + ", Team Name : " + teamName; 
	}

	// expose "/" that return "Hello World"
	@GetMapping("/")
	public String sayHello() {
		return "Hello World! Time on server is " + LocalDateTime.now();
	}
	
	// expose a new endpoint for "workout"
	@GetMapping("/workout")
	public String getDailyWorkout() {
		return "Run a hard 5K.";
	}
	
	//expose a new endpoint for fortune
	@GetMapping("/fortune")
	public String getDailyFortune() {
		return "Go ahead today is your lucky day...";
	}

}
