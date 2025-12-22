package com.klu;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;

public class MainApp {

    public static void main(String[] args) {

        // Step 1: Load configuration & create SessionFactory
        SessionFactory factory = new Configuration()
                .configure()
                .buildSessionFactory();

        // Step 2: Open Session
        Session session = factory.openSession();

        // Step 3: Begin Transaction
        Transaction tx = session.beginTransaction();

        // Step 4: Create object
        Student s = new Student("Ravi");

        // Step 5: Save object
        session.save(s);

        // Step 6: Commit
        tx.commit();

        // Step 7: Close resources
        session.close();
        factory.close();

        System.out.println("Student saved successfully!");
    }
}
