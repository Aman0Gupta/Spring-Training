package com.cts.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.cts.hibernate.demo.entity.Student;


public class QueryStudentDemo {

	public static void main(String[] args) {

		//create session factory
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Student.class)
									.buildSessionFactory();
		//create a session
		Session session = factory.getCurrentSession();
		
		try {
			//start the transaction
			session.beginTransaction();
			
			//query student
			List<Student> myStudents = session
									.createQuery("from Student")
									.getResultList();
			
			//display the students
			
			System.out.println("\nSelect * from Student");
			
			displayStudents(myStudents);
			
			//query student where lastName="Kumar"
			
			myStudents=session
					.createQuery("from Student where lastName='kumar'")
					.getResultList();
			
			System.out.println("\nSelect * from Student where last_name='Kumar'");
			
			displayStudents(myStudents);
			
			//query student where lastName='Kumar' or firstName='Anita'
			
			myStudents=session
					.createQuery("from Student s where s.lastName='kumar' or s.firstName='anita'")
					.getResultList();
			
			System.out.println("\nSelect * from Student s where s.last_name='Kumar' or s.first_name='Anita'");
			
			displayStudents(myStudents);
			
			//query student where email like '%@yahoo.com'
			
			myStudents=session
					.createQuery("from Student s where s.email like '%@yahoo.com'")
					.getResultList();
			
			System.out.println("\nSelect * from Student s where s.email like '%@yahoo.com'");
			
			displayStudents(myStudents);
			
			//commit the transaction
			session.getTransaction().commit();
			
			System.out.println("Done!!");
			
		}
		finally {
			factory.close();
		}
		
	}

	private static void displayStudents(List<Student> myStudents) {
		for (Student student : myStudents) {
			System.out.println(student);
		}
	}

}
