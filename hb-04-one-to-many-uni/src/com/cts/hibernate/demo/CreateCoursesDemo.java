package com.cts.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.cts.hibernate.demo.entity.Course;
import com.cts.hibernate.demo.entity.Instructor;
import com.cts.hibernate.demo.entity.InstructorDetail;
import com.cts.hibernate.demo.entity.Review;


public class CreateCoursesDemo {

	public static void main(String[] args) {

		//create session factory
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Instructor.class)
									.addAnnotatedClass(InstructorDetail.class)
									.addAnnotatedClass(Course.class)
									.addAnnotatedClass(Review.class)
									.buildSessionFactory();
		//create a session
		Session session = factory.getCurrentSession();
		
		try {
			
			//start the transaction
			session.beginTransaction();
			
			//get the instructor from db
			int id = 1;
			Instructor instructor = session.get(Instructor.class, id);
			
			//create some courses
			Course course = session.get(Course.class, 1);
			
			//add courses to instructor
			instructor.add(course);
			
			//save the courses
			session.save(course);
			
			//commit the transaction
			session.getTransaction().commit();
			
			System.out.println("Done!!");
			
		}
		finally {
			factory.close();
		}
		
	}

}
