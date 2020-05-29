package dao;

import model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import util.HibernateDBHelper;

import javax.persistence.Query;
import java.util.List;

public class UserHibernateDAO implements UserDAO {

    private static UserHibernateDAO instance;
    private final SessionFactory sessionFactory;

    private UserHibernateDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public static UserHibernateDAO getInstance() {
        if (instance == null) {
            instance = new UserHibernateDAO(HibernateDBHelper.getSessionFactory());
        }
        return instance;
    }

    @Override
    public List<User> getAllUsers() {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            List<User> users = session.createQuery("from User").list();
            transaction.commit();
            session.close();
            return users;
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User getUser(Long userId) {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            User user = session.get(User.class, userId);
            transaction.commit();
            session.close();
            return user;
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void addUser(User user) {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateUser(User user) {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUser(User user) {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            session.delete(user);
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }
}