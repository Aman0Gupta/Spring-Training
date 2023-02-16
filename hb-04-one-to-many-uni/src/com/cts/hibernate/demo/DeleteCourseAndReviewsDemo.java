package com.cts.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.cts.hibernate.demo.entity.Course;
import com.cts.hibernate.demo.entity.Instructor;
import com.cts.hibernate.demo.entity.InstructorDetail;
import com.cts.hibernate.demo.entity.Review;


public class DeleteCourseAndReviewsDemo {

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
			
			//get a course
			int courseId = 1;
			Course deleteCourse = session.get(Course.class, courseId);
			System.out.println("Deleting Course: " + deleteCourse);
			
			//delete the course
			if(deleteCourse!=null) {
				session.delete(deleteCourse);
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
