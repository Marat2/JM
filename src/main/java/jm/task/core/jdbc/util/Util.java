package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class Util {
    private static StandardServiceRegistry registry;
    public static SessionFactory sessionFactory;

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        String hostName = "localhost";

        String dbName = "jm";
        String userName = "jmuser";
        String password = "QazQwe!23456";
        return getMysqlConnect(hostName, dbName, userName, password);
    }
    public static  Connection getMysqlConnect(String hostName, String dbName,String userName, String password)
            throws SQLException {
        String connectionURL = "jdbc:mysql://localhost:3306/jm?useSSL=false&serverTimezone=UTC";
        return DriverManager.getConnection(connectionURL, userName,password);
    }

    public static SessionFactory getHibernateConnect(){
        Map<String,String> settings = new HashMap<>();
        settings.put(Environment.DRIVER, "com.mysql.jdbc.Driver");
        settings.put(Environment.USER, "jmuser");
        settings.put(Environment.PASS, "QazQwe!23456");
        settings.put(Environment.URL, "jdbc:mysql://localhost:3306/jm?useSSL=false&serverTimezone=UTC");
        if (sessionFactory==null) {
            StandardServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(settings).build();
            MetadataSources metadataSources = new MetadataSources(registry);
            metadataSources.addAnnotatedClass(User.class);
            Metadata metadata = metadataSources.buildMetadata();
            sessionFactory = metadata.buildSessionFactory();
        }
        return sessionFactory;
    }
}
