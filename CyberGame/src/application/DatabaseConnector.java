package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {

    private static final String URL = "jdbc:sqlite:/C:/Users/HP/OneDrive/Desktop/gamelogindata.db";

    public static Connection connect() {
        Connection conn = null;
        try {
        	Class.forName("org.sqlite.JDBC");
        	conn = DriverManager.getConnection(URL);
            System.out.println("Connection to SQLite has been established.");
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
}