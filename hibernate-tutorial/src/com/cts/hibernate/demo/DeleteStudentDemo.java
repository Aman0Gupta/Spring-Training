package com.cts.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.cts.hibernate.demo.entity.Student;


public class DeleteStudentDemo {

	public static void main(String[] args) {
		
		long studentId = 3;

		//create session factory
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Student.class)
									.buildSessionFactory();
		//create a session
		Session session = factory.getCurrentSession();
		
		try {
			//start the transaction
			/*session.beginTransaction();
			
			Student deleteStudent = session.get(Student.class, studentId);
			
			session.delete(deleteStudent);
			
			//commit the transaction
			session.getTransaction().commit();*/
			
			session = factory.getCurrentSession();
			
			session.beginTransaction();
			
			session.createQuery("delete Student where id =4 ")
					.executeUpdate();
			
			session.getTransaction().commit();
			
			System.out.println("Done!!");
			
		}
		finally {
			factory.close();
		}
		
	}

}
