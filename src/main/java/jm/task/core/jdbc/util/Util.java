package jm.task.core.jdbc.util;
import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/userDB";
    private static final String USERNAME = "root";
    public static final String PASSWORD = "2708rooT";

    private static Connection connection;

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException|SQLException ex) {
            ex.printStackTrace();
        }
        return connection;
    }

    public static SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration().addAnnotatedClass(User.class);
        return configuration.buildSessionFactory();
    }
}
