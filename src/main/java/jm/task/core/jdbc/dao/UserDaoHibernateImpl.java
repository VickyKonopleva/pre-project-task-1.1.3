package jm.task.core.jdbc.dao;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        Session session = Util.getSession();
        try {
            session.beginTransaction();
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS USER("
                    + "id bigint PRIMARY KEY NOT NULL AUTO_INCREMENT,"
                    + " name VARCHAR(20),"
                    + " lastname VARCHAR(20), "
                    + "age SMALLINT)").executeUpdate();
            session.getTransaction().commit();
        } finally {
            session.close();
        }
    }

    @Override
    public void dropUsersTable() {
        Session session = Util.getSession();
        try {
            session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS USER").executeUpdate();
            session.getTransaction().commit();
        } finally {
            session.close();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = Util.getSession();
        try {
            session.beginTransaction();
            User user = new User(name, lastName, age);
            session.persist(user);
            session.getTransaction().commit();
        } finally {
            session.close();
        }
    }

    @Override
    public void removeUserById(long id) {
        Session session = Util.getSession();
        try {
            session.beginTransaction();
            User user = session.get(User.class, id);
            session.remove(user);
            session.getTransaction().commit();
        } finally {
            session.close();
        }
    }

    @Override
    public List<User> getAllUsers() {
        Session session = Util.getSession();
        List<User> users = null;
        try {
            session.beginTransaction();
            users = session.createQuery("From User").getResultList();
            session.getTransaction().commit();
        } finally {
            session.close();
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        Session session = Util.getSession();
        try {
            session.beginTransaction();
            session.createSQLQuery("TRUNCATE TABLE USER").executeUpdate();
            session.getTransaction().commit();
        } finally {
            session.close();
        }
    }
}
