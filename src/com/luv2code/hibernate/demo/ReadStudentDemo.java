package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ReadStudentDemo {
    public static void main(String[] args) {
        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();
        try {
            // create a student object
            System.out.println("Creating new student object ... ");
            Student tempStudent = new Student("Dummy", "Student", "dummy@luv2code.com");

            // start a transaction
            System.out.println("Starting transaction ... ");
            session.beginTransaction();

            // save the student
            System.out.println("Saving Student ... ");
            System.out.println(tempStudent);

            session.save(tempStudent);

            // commit transaction
            System.out.println("Committing transaction ... ");
            session.getTransaction().commit();

            // find out the student's primary key
            System.out.println("Saved student Generated if: " + tempStudent.getId());

            // now get a new session
            session = factory.getCurrentSession();
            session.beginTransaction();

            // retrieve student based on the id: primary key
            System.out.println("\nGetting student with id: " + tempStudent.getId());
            Student mySudent = session.get(Student.class, tempStudent.getId());
            System.out.println("Get complete: " + mySudent);

            // commit the transaction
            session.getTransaction().commit();
            System.out.println("Done :)");
        } finally {
            session.close();
        }
    }
}
