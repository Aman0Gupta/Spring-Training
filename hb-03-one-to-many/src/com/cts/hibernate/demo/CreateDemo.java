package com.cts.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.cts.hibernate.demo.entity.Instructor;
import com.cts.hibernate.demo.entity.InstructorDetail;


public class CreateDemo {

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
			//create the objects
			//Instructor instructor = new Instructor("Chad", "Darby", "darby@luv2code.com");
			//InstructorDetail instructorDetail = new InstructorDetail("http://www.luv2code.com/youtube", "luv 2 code");
			Instructor instructor = new Instructor("Madhu", "Patel", "madhu@gmail.com");
			InstructorDetail instructorDetail = new InstructorDetail("http://www.youtube.com/madhumusic", "Music");
			
			//associate the objects
			instructor.setInstructorDetail(instructorDetail);
			
			//start the transaction
			session.beginTransaction();
			
			//save the instructor
			//
			// Note:this will also save the details object
			// because of Cascade.ALL
			//
			System.out.println("SAVING INSTRUCTOR: " + instructor);
			session.save(instructor);
			
			//commit the transaction
			session.getTransaction().commit();
			
			System.out.println("Done!!");
			
		}
		finally {
			factory.close();
		}
		
	}

}
