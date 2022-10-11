package org.example;

import org.example.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().addAnnotatedClass(Person.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();

            Person person = session.get(Person.class, 2);
//            person.setName("New name");
//            session.delete(person);
            Person person2 = new Person("Some name", 18);
            session.save(person2);

            session.getTransaction().commit();

            System.out.println(person2.getId());

        } finally {
            sessionFactory.close();
        }
    }
}
