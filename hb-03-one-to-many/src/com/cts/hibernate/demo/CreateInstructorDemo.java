package com.cts.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.cts.hibernate.demo.entity.Course;
import com.cts.hibernate.demo.entity.Instructor;
import com.cts.hibernate.demo.entity.InstructorDetail;


public class CreateInstructorDemo {

	public static void main(String[] args) {

		//create session factory
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Instructor.class)
									.addAnnotatedClass(InstructorDetail.class)
									.addAnnotatedClass(Course.class)
									.buildSessionFactory();
		//create a session
		Session session = factory.getCurrentSession();
		
		try {
			//create the objects
			Instructor instructor = new Instructor("Susan", "Public", "susan@gmail.com");
			InstructorDetail instructorDetail = new InstructorDetail("http://www.youtube.com/susangame", "Gaming");
			
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
