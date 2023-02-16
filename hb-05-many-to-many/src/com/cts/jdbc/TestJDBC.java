package com.cts.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestJDBC {

	public static void main(String[] args) {

		String jdbcUrl = "jdbc:mysql://localhost:3306/hb_05_many_to_many?useSSL=false";
		String username="hbstudent";
		String password="hbstudent";
		
		try {
			System.out.println("Connecting to database " + jdbcUrl);
			Connection myCon = DriverManager.getConnection(jdbcUrl, username, password);
			System.out.println("Connection successfull!!");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
 