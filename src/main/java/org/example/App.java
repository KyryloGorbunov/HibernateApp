package org.example;

import org.example.model.*;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration()
                .addAnnotatedClass(Person.class)
                .addAnnotatedClass(Item.class)
                .addAnnotatedClass(Passport.class)
                .addAnnotatedClass(Actor.class)
                .addAnnotatedClass(Movie.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();

        try (sessionFactory) {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            Person person = session.get(Person.class, 1);
            System.out.println("We got a person");
//            System.out.println(person.getItems());
//            Hibernate.initialize(person.getItems());

            /*Item item = session.get(Item.class, 1);
            System.out.println("We got an item");*/

            session.getTransaction().commit();

            System.out.println("Session close");

            session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            System.out.println("Inside of second transaction");

            person = (Person) session.merge(person);

            Hibernate.initialize(person.getItems());

            session.getTransaction().commit();

            System.out.println("Out of second transaction");

            System.out.println(person.getItems());

        }
    }
}
