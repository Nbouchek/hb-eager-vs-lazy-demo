package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateDemo {
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
            // create the objects
            Instructor tempInstructor = new Instructor("Nacer", "Bouchekhima", "nacer@luv2code.com");
            InstructorDetail tempInstructorDetail = new InstructorDetail("http://www.ssanabel.com/youtube", "lv 2 code");

            // associate the objects
            tempInstructor.setInstructorDetail(tempInstructorDetail);

            // start a transaction
            System.out.println("Starting transaction ... ");
            session.beginTransaction();

            // save the instructor
            //
            // Note: this will also save the tempInstructorDetail
            // because of cascadeType.ALL
            System.out.println("Saving instructor: " + tempInstructor);
            session.save(tempInstructor);

            // commit transaction
            System.out.println("Committing transaction ... ");
            session.getTransaction().commit();
            System.out.println("Done :)");
        } finally {
            session.close();
        }
    }
}
