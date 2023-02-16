package com.cts.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.cts.hibernate.demo.entity.Instructor;
import com.cts.hibernate.demo.entity.InstructorDetail;


public class DeleteDemo {

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
			int id = 2; 
			
			//start the transaction
			session.beginTransaction();
			
			//get instructor by primary key/id
			Instructor instructor = session.get(Instructor.class, id);
			
			System.out.println("FOUND INSTRUCTOR: " + instructor);
			//delete the instructor
			if(instructor!=null) {
				System.out.println("DELETING: " + instructor);
				
				//NOTE: will also delete associated "details" object
				//because of Casecade.ALL
				//
				session.delete(instructor);
			}
			
			//commit the transaction
			session.getTransaction().commit();
			
			System.out.println("Done!!");
			
		}
		finally {
			factory.close();
		}
		
	}

}
