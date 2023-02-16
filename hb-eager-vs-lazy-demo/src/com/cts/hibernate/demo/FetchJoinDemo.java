package com.cts.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.cts.hibernate.demo.entity.Course;
import com.cts.hibernate.demo.entity.Instructor;
import com.cts.hibernate.demo.entity.InstructorDetail;

public class FetchJoinDemo {

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
			int theId = 1;
			
			Query<Instructor> query = session.createQuery("select i from Instructor i "
															+ "join fetch i.courses "
															+ "where i.id=:theInstructorId",
															Instructor.class);
			
			// set parameter on query
			query.setParameter("theInstructorId", theId);
			
			//execute query and get instructor
			Instructor instructor = query.getSingleResult();
			
			System.out.println("CTS Instructor: " + instructor);
			
			//commit the transaction
			session.getTransaction().commit();
			
			System.out.println("\nSession is closed now!!\n");
			
			System.out.println("Courses: " + instructor.getCourses());
			
			System.out.println("CTS Done!!");
			
		}
		finally {
			factory.close();
		}
		
	}

}
