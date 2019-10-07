package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class FetchJoinDemo {
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

            // option 2: Hibernate query with HQL
            // get instructor from db
            int theId = 1;

            Query<Instructor> query = session.createQuery("select i from Instructor i " +
                    "JOIN  fetch  i.courses " +
                    "where i.id=:theInstructorId", Instructor.class);
            // set parameter on query
            query.setParameter("theInstructorId", theId);

            // execute query and get instructor
            Instructor tempInstructor = query.getSingleResult();

            System.out.println("luv2code: Instructor: " + tempInstructor);

            System.out.println("\n\nluv2code: Courses: " + tempInstructor.getCourses() + "\n\n");

            // commit transaction
            System.out.println("Committing transaction ... ");
            session.getTransaction().commit();

            // close the session
            session.close();
            System.out.println("The session is now closed");

            // get get course for instructor
            System.out.println("\n\nluv2code: Courses: " + tempInstructor.getCourses() + "\n\n");

            System.out.println("luv2code: Done :)");
        } finally {
            // add clean up code
            session.close();
            factory.close();
        }
    }
}
