package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteInstructorDetailDemo {
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

            // get the instructorDetail object
            int theId = 2;
            InstructorDetail tempInstructorDetail = session.get(InstructorDetail.class, theId);
            // print the instructor detail
            System.out.println("tempInstructorDetail: " + tempInstructorDetail);

            // print the associated instructor object
            System.out.println("Associated instructor " + tempInstructorDetail.getInstructor());

            // now lets delete the instructor detail
            System.out.println("Deleting the instructor details: " + tempInstructorDetail);
            session.delete(tempInstructorDetail);

            // commit transaction
            System.out.println("Committing transaction ... ");
            session.getTransaction().commit();
            System.out.println("Done :)");
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            // handle connection leak issue
            session.close();
            factory.close();
        }
    }
}
