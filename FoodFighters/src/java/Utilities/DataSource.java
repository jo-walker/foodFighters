package Utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {

    private static Connection connection = null;

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/projectjava";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    // Static block to register the JDBC driver
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Register the MySQL driver
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL JDBC driver not found.", e);
        }
    }

    private DataSource() { 
    }

    /**
     * Gets the singleton connection instance.
     * @return the database connection.
     */
    public static Connection getConnection() {
        if (connection == null || isConnectionClosed()) {
            try {
                connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            } catch (SQLException ex) {
                ex.printStackTrace();
                throw new RuntimeException("Error connecting to the database", ex);
            }
        }
        return connection;
    }

    /**
     * Checks if the current connection is closed.
     * @return true if the connection is closed, false otherwise.
     */
    private static boolean isConnectionClosed() {
        try {
            return connection == null || connection.isClosed();
        } catch (SQLException e) {
            e.printStackTrace();
            return true;
        }
    }
}
