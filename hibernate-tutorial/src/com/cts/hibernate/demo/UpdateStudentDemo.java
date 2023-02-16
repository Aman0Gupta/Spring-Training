package com.cts.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.cts.hibernate.demo.entity.Student;


public class UpdateStudentDemo {

	public static void main(String[] args) {
		
		long studentId = 2;

		//create session factory
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Student.class)
									.buildSessionFactory();
		//create a session
		Session session = factory.getCurrentSession();
		
		try {
			//start a transaction
			session.beginTransaction();
			
			Student updatedStudent = session.get(Student.class, studentId);
			
			updatedStudent.setFirstName("Naman");
			
			System.out.println("Updated Student: " + updatedStudent);
			
			session.getTransaction().commit();
			
			//NEW CODE
			
			session = factory.getCurrentSession();
			
			session.beginTransaction();
			
			//update email for all student
			
			System.out.println("Update email for all students...");
			
			session.createQuery("update Student set email='bar@gmail.com' where id=2")
					.executeUpdate();
			
			session.getTransaction().commit();
			
			System.out.println("Done!!");
			
		}
		finally {
			factory.close();
		}
		
	}

}
