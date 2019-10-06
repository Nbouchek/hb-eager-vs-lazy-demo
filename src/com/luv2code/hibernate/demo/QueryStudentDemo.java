package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class QueryStudentDemo {
    public static void main(String[] args) {
        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();
        try {
            // start a transaction
            session.beginTransaction();

            // query students
            List<Student > theStudents = session.createQuery("from Student").getResultList();

            // display student
            displayStudent(theStudents);
            // query students
            System.out.println("query students: lastName='hana'");
            theStudents = session.createQuery("from Student s where s.lastName = 'hana'").getResultList();

            // display student who have last name of Hana
            displayStudent(theStudents);

            // query students: lastName='hana' OR firstName='adam'
            System.out.println("query students: lastName='hana' AND firstName='adam'");
            theStudents = session.createQuery("from Student s where s.lastName = 'hana' and s.firstName='adam'").getResultList();
            displayStudent(theStudents);

            // commit transaction
            session.getTransaction().commit();
            System.out.println("Done :)");
        } finally {
            session.close();
        }
    }

    private static void displayStudent(List<Student> theStudents) {
        for (Student tempStudent : theStudents) {
            System.out.println(tempStudent);
        }
    }
}
