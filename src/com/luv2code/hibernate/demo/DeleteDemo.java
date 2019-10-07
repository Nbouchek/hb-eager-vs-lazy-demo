package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteDemo {
    public static void main(String[] args) {
        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();
        try {
            // start a transaction
            System.out.println("Starting transaction ... ");
            session.beginTransaction();

            // get instructor by primary key id
            int theId = 8;
            Instructor tempInstructor = session.get(Instructor.class, theId);
            System.out.println("Found instructor " + tempInstructor);

            // delete instructor
            if(tempInstructor != null) {
                System.out.println("Deleting instructor: " + tempInstructor);

                // Note: will also delete instructorDetail because of cascadeType.ALL
                session.delete(tempInstructor);
            }

            // commit transaction
            System.out.println("Committing transaction ... ");
            session.getTransaction().commit();
            System.out.println("Done :)");
        } finally {
            session.close();
        }
    }
}
