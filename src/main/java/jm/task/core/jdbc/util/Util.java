package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        String hostName = "localhost";

        String dbName = "jm";
        String userName = "jmuser";
        String password = "QazQwe!23456";
        return getMysqlConnect(hostName, dbName, userName, password);
    }
    public static  Connection getMysqlConnect(String hostName, String dbName,String userName, String password)
            throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String connectionURL = "jdbc:mysql://localhost:3306/jm?useSSL=false&serverTimezone=UTC";
        return DriverManager.getConnection(connectionURL, userName,password);
    }
}
