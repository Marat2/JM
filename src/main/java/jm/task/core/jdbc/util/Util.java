package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

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
        if (sessionFactory==null){
            try{

                Properties properties = new Properties();

                properties.setProperty(Environment.DRIVER, "com.mysql.jdbc.Driver");
                properties.setProperty(Environment.USER, "jmuser");
                properties.setProperty(Environment.PASS, "QazQwe!23456");
                properties.setProperty(Environment.URL, "jdbc:mysql://localhost:3306/jm?useSSL=false&serverTimezone=UTC");

                Configuration cfg = new Configuration();
                cfg.setProperties(properties);
                cfg.addAnnotatedClass(User.class);
                sessionFactory = cfg.buildSessionFactory();

            }catch(Exception e){
                e.printStackTrace();

            }
        }
        return sessionFactory;
    }
    public static void shutdown() {
        if (registry != null) {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
