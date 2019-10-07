package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCoursesDemo {
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

            // create some courses
            Course tempCourse1 = new Course("IGCSE - Maths");
            Course tempCourse2 = new Course("IGCSE - Physics");
            Course tempCourse3 = new Course("IGCSE - Biology");
            Course tempCourse4 = new Course("IGCSE - Computer Science");

            // add courses to instructor
            tempInstructor.addCourse(tempCourse1);
            tempInstructor.addCourse(tempCourse2);
            tempInstructor.addCourse(tempCourse3);
            tempInstructor.addCourse(tempCourse4);

            // save the courses
            session.save(tempCourse1);
            session.save(tempCourse2);
            session.save(tempCourse3);
            session.save(tempCourse4);

            // commit transaction
            System.out.println("Committing transaction ... ");
            session.getTransaction().commit();
            System.out.println("Done :)");
        } finally {
            // add clean up code
            session.close();
            factory.close();
        }
    }
}
