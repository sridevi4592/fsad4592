package com.klu;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class MainApp {

    public static void main(String[] args) {

        SessionFactory factory =
            new Configuration().configure().buildSessionFactory();

        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        // ---------------------------------------------------
        // INSERT SAMPLE DATA (run once)
        // ---------------------------------------------------
        session.save(new StudentEntity(1, "Ravi", 85));
        session.save(new StudentEntity(2, "Sita", 92));
        session.save(new StudentEntity(3, "John", 70));
        session.save(new StudentEntity(4, "Anita", 60));

        tx.commit();

        // ===================================================
        // 1️ SELECT QUERY
        // ===================================================
        Query<StudentEntity> q1 =
            session.createQuery("from StudentEntity", StudentEntity.class);

        List<StudentEntity> students = q1.list();
        System.out.println("---- All Students ----");
        for (StudentEntity s : students) {
            System.out.println(s.getName() + " : " + s.getMarks());
        }

        // ===================================================
        // 2️ SELECT with POSITONAL PARAMETER
        // ===================================================
        Query<StudentEntity> q2 =
            session.createQuery(
                "from StudentEntity where marks > ?1", StudentEntity.class);
        q2.setParameter(1, 75);

        System.out.println("---- Marks > 75 ----");
        q2.list().forEach(
            s -> System.out.println(s.getName())
        );

        // ===================================================
        // 3️ SELECT with NAMED PARAMETER
        // ===================================================
        Query<StudentEntity> q3 =
            session.createQuery(
                "from StudentEntity where name = :studentName",
                StudentEntity.class);

        q3.setParameter("studentName", "Ravi");

        System.out.println("---- Named Parameter ----");
        q3.list().forEach(
            s -> System.out.println(s.getName())
        );

        // ===================================================
        // 4️ UPDATE QUERY
        // ===================================================
        Transaction tx2 = session.beginTransaction();

        Query updateQuery =
            session.createQuery(
                "update StudentEntity set marks = :m where id = :id");

        updateQuery.setParameter("m", 95);
        updateQuery.setParameter("id", 1);

        int rowsUpdated = updateQuery.executeUpdate();
        System.out.println("Rows Updated: " + rowsUpdated);

        tx2.commit();

        // ===================================================
        // 5️ DELETE QUERY
        // ===================================================
        Transaction tx3 = session.beginTransaction();

        Query deleteQuery =
            session.createQuery("delete from StudentEntity where marks < :min");

        deleteQuery.setParameter("min", 65);

        int rowsDeleted = deleteQuery.executeUpdate();
        System.out.println("Rows Deleted: " + rowsDeleted);

        tx3.commit();

        // ===================================================
        // 6️ ORDER BY (Sorting)
        // ===================================================
        Query<StudentEntity> q4 =
            session.createQuery(
                "from StudentEntity order by marks desc",
                StudentEntity.class);

        System.out.println("---- Sorted by Marks (DESC) ----");
        q4.list().forEach(
            s -> System.out.println(s.getName() + " " + s.getMarks())
        );

        // ===================================================
        // 7️ AGGREGATE FUNCTIONS
        // ===================================================

        // COUNT
        Query<Long> countQuery =
            session.createQuery(
                "select count(*) from StudentEntity", Long.class);
        System.out.println("Total Students: " + countQuery.uniqueResult());

        // AVG
        Query<Double> avgQuery =
            session.createQuery(
                "select avg(marks) from StudentEntity", Double.class);
        System.out.println("Average Marks: " + avgQuery.uniqueResult());

        // SUM
        Query<Long> sumQuery =
            session.createQuery(
                "select sum(marks) from StudentEntity", Long.class);
        System.out.println("Total Marks: " + sumQuery.uniqueResult());

        session.close();
        factory.close();
    }
}
