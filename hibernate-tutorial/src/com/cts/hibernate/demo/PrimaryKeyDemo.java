package com.cts.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.cts.hibernate.demo.entity.Student;

public class PrimaryKeyDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		// create a session
		Session session = factory.getCurrentSession();

		try {
			// create 3 student object
			System.out.println("Creating 3 Student Object");
			Student student1 = new Student("Gaurav", "Gupta", "gaurav@gmail.com");
			Student student2 = new Student("Anita", "Gupta", "anita@gmail.com");
			Student student3 = new Student("Raj", "Gupta", "raj@gmail.com");

			// start the transaction
			session.beginTransaction();

			// save the student object
			System.out.println("Saving the students...");
			session.save(student1);
			session.save(student2);
			session.save(student3);

			// commit the transaction
			session.getTransaction().commit();

			System.out.println("Done!!");

		} finally {
			factory.close();
		}

	}

}
