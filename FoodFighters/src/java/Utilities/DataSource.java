package Utilities;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Provides a single database connection instance for the application.
 */
public class DataSource {

    private static Connection connection = null;

    private DataSource() { }

    /**
     * Gets the singleton connection instance.
     * @return the database connection.
     */
   public static Connection getConnection() {
    if (connection == null || isConnectionClosed()) {
        String[] connectionInfo = openPropsFile();
        try {
<<<<<<< HEAD
            connection = DriverManager.getConnection(connectionInfo[0], connectionInfo[1], connectionInfo[2]);
=======
            if (connection == null) { // || connection.isClosed()) { // used if connection is closed in other DAO implementations
                //connection = DriverManager.getConnection(connectionInfo[0], connectionInfo[1], connectionInfo[2]);
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/foodFighters", "root", "N@s-402@Av-131811");
            } else {
                System.out.println("Cannot create new connection, using existing one");
            }
>>>>>>> origin/AndreaVisani
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    } else {
        System.out.println("Cannot create new connection, using existing one");
    }
    return connection;
}


    /**
     * Checks if the current connection is closed.
     * @return true if the connection is closed, false otherwise.
     */
    private static boolean isConnectionClosed() {
        try {
            return connection.isClosed();
        } catch (SQLException e) {
            e.printStackTrace();
            return true;
        }
    }

    /**
     * Loads database properties from the properties file.
     * @return an array containing JDBC URL, username, and password.
     */
    private static String[] openPropsFile() {
        Properties props = new Properties();
        String[] info = new String[3];
        
        // Update file path to be a valid relative or absolute path
        String filePath = "C:\\Users\\Owner\\Documents\\NetBeansProjects\\Lab2-maven\\data\\database.properties";
        try (InputStream inputStream = Files.newInputStream(Paths.get(filePath))) {
            props.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Construct JDBC URL from properties
        String db = props.getProperty("db");
        String name = props.getProperty("name");
        String host = props.getProperty("host");
        String port = props.getProperty("port");
        String user = props.getProperty("user");
        String pass = props.getProperty("pass");

        String jdbcUrl = String.format("jdbc:%s://%s:%s/%s", db, host, port, name);

        info[0] = jdbcUrl;
        info[1] = user;
        info[2] = pass;

        return info;
    }
}
