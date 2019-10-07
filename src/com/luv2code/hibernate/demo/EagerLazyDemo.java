package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class EagerLazyDemo {
    public static void main(String[] args) {
        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();
        try {

            // start a transaction
            System.out.println("Starting transaction ... ");
            session.beginTransaction();

            // get instructor from db
            int theId = 1;
            Instructor tempInstructor = session.get(Instructor.class, theId);
            System.out.println("luv2code: Instructor: "+ tempInstructor);

            System.out.println("luv2code: Courses: " + tempInstructor.getCourses());

            // commit transaction
            System.out.println("Committing transaction ... ");
            session.getTransaction().commit();

            // close the session
            session.close();
            System.out.println("The session is now closed");
            // option 1: call getter while session is open
            // get get course for instructor
            System.out.println("luv2code: Courses: " + tempInstructor.getCourses());

            System.out.println("luv2code: Done :)");
        } finally {
            // add clean up code
            session.close();
            factory.close();
        }
    }
}
