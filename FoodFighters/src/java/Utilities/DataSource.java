/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
 *
 * @author Andrea Visani 041104651 visa0004@algonquinlive.com
 */
public class DataSource {

    private static Connection connection = null;

    private DataSource() { }

    /* Only use one connection for this application, prevent memory leaks. */
    public static Connection getConnection() {
        String[] connectionInfo = openPropsFile();

        try {
            if (connection == null) { // || connection.isClosed()) { // used if connection is closed in other DAO implementations
                //connection = DriverManager.getConnection(connectionInfo[0], connectionInfo[1], connectionInfo[2]);
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/foodFighters", "root", "N@s-402@Av-131811");
            } else {
                System.out.println("Cannot create new connection, using existing one");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return connection;
    }

    private static String[] openPropsFile() {
        Properties props = new Properties();
        

        /** 
         * =================================================================================================================
         * ============  WE NEED TO FIX THIS ISSUE, NOT WORKING IN MY MACHINE (ANDREA) IF USING RELATIVE PATH ==============
         * =================================================================================================================
         */
        try (InputStream inputStream = Files.newInputStream(Paths.get("C:\\Users\\visan\\OneDrive\\Documents\\NetBeansProjects\\fFighters\\FoodFighters\\src\\java\\Database\\database.properties"))) {
            props.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String connectionString = props.getProperty("jdbc.url");
        String username = props.getProperty("jdbc.username");
        String password = props.getProperty("jdbc.password");

        String[] info = new String[3];
        info[0] = connectionString;
        info[1] = username;
        info[2] = password;

        return info;
    }
}
