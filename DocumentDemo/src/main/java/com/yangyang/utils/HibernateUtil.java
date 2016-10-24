package com.yangyang.utils;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateUtil {

    public static SessionFactory getSessionFactory2(){
        ServiceRegistry sevice = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        SessionFactory sessionFactory = new MetadataSources(sevice).buildMetadata().buildSessionFactory();
        return sessionFactory;
    }

    public static SessionFactory getSessionFactory(){
        SessionFactory sessionFactory = null;
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
        }
        catch (Exception e) {
            // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
            // so destroy it manually.
            e.printStackTrace();
            StandardServiceRegistryBuilder.destroy( registry );
        }
        return sessionFactory;
    }

    public static Session openSession(){
        return getSessionFactory().openSession();
    }
    public static void closeSession(Session session){
        if(session != null){
            session.close();
        }
    }

    private static String persistanceUnitName = "helloworld";
    private static EntityManagerFactory entityFactory = Persistence.createEntityManagerFactory(persistanceUnitName);

    public static EntityManager getEntityManager(){
        return entityFactory.createEntityManager();
    }

    public static void closeEntity(EntityManager manager){
        if(manager != null) manager.close();
    }

    public static void save(EntityManager manager,Object ...objs){
        for(Object obj : objs){
            manager.persist(obj);
        }
    }
}
