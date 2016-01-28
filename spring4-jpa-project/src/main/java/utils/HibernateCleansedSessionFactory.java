package utils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;

/**
 * 
 * Factory method return current Hibernate session.
 * 
 *
 */
public final class HibernateCleansedSessionFactory {

    private static final HibernateCleansedSessionFactory HIBERNATE_SESSION_FACTORY = new HibernateCleansedSessionFactory();
    
    @PersistenceContext(unitName = "PersistenceUnit")
    private EntityManager entityManager;


    /** 
     * Default constructor.
     */
    private HibernateCleansedSessionFactory() {
    }

    /**
     * Get instance of HibernateSessionFactory.
     * 
     * @return HibernateSessionFactory
     */
    public static HibernateCleansedSessionFactory instance() {
        return HIBERNATE_SESSION_FACTORY;
    }

    /**
     * Get cleansed data Session.
     * 
     * @return {@link Session}
     */
    public Session getSession() {
        return entityManager.unwrap(Session.class);
    }

}
