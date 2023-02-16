package com.cts.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.cts.hibernate.demo.entity.Course;
import com.cts.hibernate.demo.entity.Instructor;
import com.cts.hibernate.demo.entity.InstructorDetail;


public class EagerLazyDemo {

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
			
			//start the transaction
			session.beginTransaction();
			
			//get the instructor from db
			int id = 1;
			Instructor instructor = session.get(Instructor.class, id);
			
			System.out.println("CTS Instructor: " + instructor);
			
			//get courses for the instructor
			System.out.println("CTS Courses: " + instructor.getCourses());
			
			//commit the transaction
			session.getTransaction().commit();
			
			System.out.println("CTS Done!!");
			
		}
		finally {
			factory.close();
		}
		
	}

}
