package orm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Connector {
    private static Connection connection;
    private static final String jdbc = "jdbc:mysql://localhost:3306/%s";

    public static void createConnection (String user, String password, String dbName) throws SQLException {
        Properties properties = new Properties();
        properties.setProperty("user", user);
        properties.setProperty("password", "1234");

        String formattedJDBC = String.format(jdbc, dbName);

        connection = DriverManager.getConnection(formattedJDBC, properties);
    }

    public static Connection getConnection(){
        return connection;
    }
}
