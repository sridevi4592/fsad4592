package com.klu;

import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.Scanner;


public class MainApp {


public static void main(String[] args) {


Scanner sc = new Scanner(System.in);
int choice;


do {
System.out.println("====== STUDENT CRUD MENU ======");
System.out.println("1. Add Student");
System.out.println("2. View Student");
System.out.println("3. Update Student");
System.out.println("4. Delete Student");
System.out.println("5. Exit");
System.out.print("Enter your choice: ");


choice = sc.nextInt();


switch (choice) {


case 1: // CREATE
Session session1 = HibernateUtil.getSessionFactory().openSession();
Transaction tx1 = session1.beginTransaction();


Student s = new Student();
System.out.print("Enter ID: ");
s.setId(sc.nextInt());
sc.nextLine();
System.out.print("Enter Name: ");
s.setName(sc.nextLine());
System.out.print("Enter Marks: ");
s.setMarks(sc.nextInt());


session1.save(s); // Persistent
tx1.commit();
session1.close();


System.out.println("Student Added Successfully");
break;


case 2: // READ
Session session2 = HibernateUtil.getSessionFactory().openSession();
System.out.print("Enter Student ID: ");
int id = sc.nextInt();


Student st = session2.get(Student.class, id);
if (st != null) {
System.out.println("ID: " + st.getId());
System.out.println("Name: " + st.getName());
System.out.println("Marks: " + st.getMarks());
} else {
System.out.println("Student Not Found");
}
session2.close();
case 3:
Session session3 = HibernateUtil.getSessionFactory().openSession();
Transaction tx3 = session3.beginTransaction();


System.out.print("Enter Student ID to Update: ");
int uid = sc.nextInt();


Student ust = session3.get(Student.class, uid); // Persistent
if (ust != null) {
sc.nextLine();
System.out.print("Enter New Name: ");
ust.setName(sc.nextLine());
System.out.print("Enter New Marks: ");
ust.setMarks(sc.nextInt());


// No update() needed
tx3.commit();
System.out.println("Student Updated Successfully");
} else {
System.out.println("Student Not Found");
tx3.rollback();
}
session3.close();
break;


case 4: // DELETE
Session session4 = HibernateUtil.getSessionFactory().openSession();
Transaction tx4 = session4.beginTransaction();


System.out.print("Enter Student ID to Delete: ");
int did = sc.nextInt();


Student dst = session4.get(Student.class, did); // Persistent
if (dst != null) {
session4.delete(dst);
tx4.commit();
System.out.println("Student Deleted Successfully");
} else {
System.out.println("Student Not Found");
tx4.rollback();
}
session4.close();
break;


case 5:
System.out.println("Exiting Application...");
HibernateUtil.getSessionFactory().close();
break;


default:
System.out.println("Invalid Choice");
}
} while (choice != 5);


sc.close();
}
}