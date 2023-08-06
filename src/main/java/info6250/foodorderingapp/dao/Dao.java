/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package info6250.foodorderingapp.dao;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author manlingzhao
 */
public abstract class Dao {
    private static Configuration cfg = new Configuration();
    private static final SessionFactory sessionFactory = cfg.configure("hibernate.cfg.xml").buildSessionFactory();
    private static final ThreadLocal sessionThread = new ThreadLocal();

    private static final Logger log = Logger.getAnonymousLogger();
    
    protected Dao() {
    }

    public static Session getSession() {
        Session session = (Session) Dao.sessionThread.get();

        if (session == null) {
            session = sessionFactory.openSession();
            Dao.sessionThread.set(session);
        }
        return session;
    }

    protected void begin() {
        getSession().beginTransaction();
    }

    protected void commit() {
        getSession().getTransaction().commit();
    }

    protected void rollback() {
        try {
            getSession().getTransaction().rollback();
        } catch (HibernateException e) {
            log.log(Level.WARNING, "Cannot rollback", e);
        }
        try {
            getSession().close();
        } catch (HibernateException e) {
            log.log(Level.WARNING, "Cannot close", e);
        }
        Dao.sessionThread.set(null);
    }

    public static void close() {
        getSession().close();
        Dao.sessionThread.set(null);
    }
}
