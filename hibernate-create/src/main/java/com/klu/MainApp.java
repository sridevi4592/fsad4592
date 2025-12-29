package com.klu;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;

public class MainApp {

	public static void main(String[] args) {
		//Load configuration & create sessionfactory
		SessionFactory factory=new Configuration()
				.configure().buildSessionFactory();
		// Open Session
		Session session=factory.openSession();
		// begin transaction
		Transaction tx=session.beginTransaction();
		//create object
		Student s=new Student("Sridevi");
		// save object
		session.save(s);
		//commit
		tx.commit();
		//close the resources
		session.close();
		factory.close();
		System.out.println("Data have been inserted successfully");
	}
}
