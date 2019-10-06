package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class PrimaryKeyDemo {
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
            System.out.println("Creating 3 new student objects ... ");
            Student tempStudent = new Student("Adam", "Hana", "adam@luv2code.com");
            Student tempStudent1 = new Student("Linda", "Hana", "adam@luv2code.com");
            Student tempStudent2 = new Student("Daddy", "Hana", "adam@luv2code.com");
            // start a transaction
            System.out.println("Starting transaction ... ");
            session.beginTransaction();

            // save the student
            System.out.println("Saving Students ... ");
            session.save(tempStudent);
            session.save(tempStudent1);
            session.save(tempStudent2);

            // commit transaction
            System.out.println("Committing transaction ... ");
            session.getTransaction().commit();
            System.out.println("Done :)");
        } finally {
            session.close();
        }
    }
}
