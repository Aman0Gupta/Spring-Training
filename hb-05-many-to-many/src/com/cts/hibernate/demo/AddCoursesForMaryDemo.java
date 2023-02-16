package com.cts.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.cts.hibernate.demo.entity.Course;
import com.cts.hibernate.demo.entity.Instructor;
import com.cts.hibernate.demo.entity.InstructorDetail;
import com.cts.hibernate.demo.entity.Review;
import com.cts.hibernate.demo.entity.Student;


public class AddCoursesForMaryDemo {

	public static void main(String[] args) {

		//create session factory
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Instructor.class)
									.addAnnotatedClass(InstructorDetail.class)
									.addAnnotatedClass(Course.class)
									.addAnnotatedClass(Review.class)
									.addAnnotatedClass(Student.class)
									.buildSessionFactory();
		//create a session
		Session session = factory.getCurrentSession();
		
		try {
			
			//start the transaction
			session.beginTransaction();
			
			//get the student mary from database
			Student student = session.get(Student.class, (long)2);
			
			System.out.println("\nLoaded Student: " + student);
			System.out.println("\nCourses Student is enrolled to " + student.getCourses());
			
			//create courses
			Course course1 = new Course("Rubik's Cube - How to speed cube");
			Course course2 = new Course("Atari 2600 - Game Development");
			
			//add student to courses
			course1.addStudent(student);
			course2.addStudent(student);
			
			//save the courses
			System.out.println("\nSaving the courses...");
			session.save(course1);
			session.save(course2);
			
			//commit the transaction
			session.getTransaction().commit();
			
			System.out.println("Done!!");
			
		}
		finally {
			factory.close();
		}
		
	}

}
