import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws SQLException {
        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "");

        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/diablo", props);

        PreparedStatement statement = connection.prepareStatement
                ("SELECT user_name, first_name, last_name, count(users_games.id) as game_count  FROM users" +
                        " JOIN users_games on users.id = users_games.user_id" +
                        " WHERE user_name = ?");

        Scanner scanner = new Scanner(System.in);
        String username = scanner.nextLine();

        statement.setString(1, username);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()){
            Integer dbCountGame = resultSet.getInt("game_count");
            String dbUsername = resultSet.getString("user_name");
            String dbFirstName = resultSet.getString("first_name");
            String dbLastName = resultSet.getString("last_name");
            resultSet.getString("user_name");
            resultSet.getString("user_name");
            System.out.printf("User: %s\n" +
                    "%s %s has played %d games\n", dbUsername, dbFirstName, dbLastName, dbCountGame);
        } else {
            System.out.println("No such user exists");
        }
    }
}
