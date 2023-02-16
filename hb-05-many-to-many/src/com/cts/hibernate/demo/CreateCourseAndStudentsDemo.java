package com.cts.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.cts.hibernate.demo.entity.Course;
import com.cts.hibernate.demo.entity.Instructor;
import com.cts.hibernate.demo.entity.InstructorDetail;
import com.cts.hibernate.demo.entity.Review;
import com.cts.hibernate.demo.entity.Student;


public class CreateCourseAndStudentsDemo {

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
			
			//create a course
			Course course = new Course("Pacman: How to score one million points.");
			
			//Save the course
			System.out.println("\nSaving the course...");
			session.save(course);
			System.out.println("\nSaved course: " + course);
			
			//create the student
			Student student1 = new Student("Aman", "Gupta", "aman@gmail.com");
			Student student2 = new Student("Mary", "Public", "mary@gmail.com");
			
			//add students to the course
			course.addStudent(student1);
			course.addStudent(student2);
			
			//save student
			System.out.println("\nSaving students...");
			session.save(student1);
			session.save(student2);
			System.out.println("Saved Students: " + course.getStudents());
			
			//commit the transaction
			session.getTransaction().commit();
			
			System.out.println("Done!!");
			
		}
		finally {
			factory.close();
		}
		
	}

}
