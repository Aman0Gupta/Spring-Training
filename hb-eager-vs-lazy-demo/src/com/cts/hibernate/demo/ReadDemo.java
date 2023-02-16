package com.cts.hibernate.demo;

import java.util.concurrent.ExecutionException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.cts.hibernate.demo.entity.Instructor;
import com.cts.hibernate.demo.entity.InstructorDetail;


public class ReadDemo {

	public static void main(String[] args) {

		//create session factory
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Instructor.class)
									.addAnnotatedClass(InstructorDetail.class)
									.buildSessionFactory();
		//create a session
		Session session = factory.getCurrentSession();
		
		try {
			int id = 19; 
			
			//start the transaction
			session.beginTransaction();
			
			//get instructor detail by primary key/id
			InstructorDetail instructorDetail = session.get(InstructorDetail.class, id);
			
			System.out.println("FOUND INSTRUCTOR DETAIL: " + instructorDetail);
			
			System.out.println("FOUND ASSOCIATED INSTRUCTOR: " + instructorDetail.getInstructor());
			
			//commit the transaction
			session.getTransaction().commit();
			
			System.out.println("Done!!");
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			//handle connection leak issue
			session.close();
			factory.close();
		}
		
	}

}
