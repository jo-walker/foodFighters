package Client;

import java.sql.Connection;
import java.sql.SQLException;
import Utilities.DataSource;

public class Main {

    public static void main(String[] args) {
        Connection connection = null;
        try {
            connection = DataSource.getConnection();
            
            if (connection != null && !connection.isClosed()) {
                System.out.println("Connection to the database was successful.");
            } else {
                System.out.println("Connection to the database failed.");
            }
        } catch (SQLException e) {
            System.out.println("An error occurred while trying to connect to the database.");
            e.printStackTrace();
        } finally {
            // Clean up resources
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("An error occurred while closing the connection.");
                    e.printStackTrace();
                }
            }
        }
    }
}
