package com.student;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {

    public static void main(String[] args) {

       
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        
        Session session = factory.openSession();

        try {

           
            session.beginTransaction();

            Student student = new Student(
                    1,
                    "Raguram",
                    "raguram@gmail.com",
                    "AI & Data Science"
            );


            session.persist(student);

            session.getTransaction().commit();

            System.out.println("=================================");
            System.out.println("Student inserted successfully!");
            System.out.println(student);
            System.out.println("=================================");

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            session.close();
        }

        // Close first SessionFactory
        factory.close();


      
        SessionFactory updateFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        Session updateSession = updateFactory.openSession();

        try {

            updateSession.beginTransaction();

            
            Student student = updateSession.get(Student.class, 1);

            if (student != null) {

                // Update student details
                student.setName("Raguram P");
                student.setEmail("raguram.p@gmail.com");
                student.setCourse("Artificial Intelligence");

                System.out.println("=================================");
                System.out.println("Student found!");
                System.out.println("Updated Student:");
                System.out.println(student);
                System.out.println("=================================");

            } else {

                System.out.println("Student not found!");

            }

            
            updateSession.getTransaction().commit();

            System.out.println("Student updated successfully!");

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            updateSession.close();
            updateFactory.close();
        }
    }
}