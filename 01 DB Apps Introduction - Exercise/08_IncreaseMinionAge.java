import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class _08_IncreaseMinionAge {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "1234");

        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/minions_db", props);

        String [] input = scanner.nextLine().split(" ");

        PreparedStatement updateMinion = connection.prepareStatement(
                "UPDATE minions set age = age + 1, `name` = lower(`name`) WHERE id = ?");

        for (int i = 0; i < input.length; i++) {
            int minionId = Integer.parseInt(input[i]);

            updateMinion.setInt(1, minionId);
            updateMinion.executeUpdate();
        }

        PreparedStatement allMinion = connection.prepareStatement(
                "select name, age from minions order by id");
        ResultSet resultSet = allMinion.executeQuery();

        for (int i = 0; resultSet.next(); i++) {
            String minionName = resultSet.getString("name");
            int minionAge = resultSet.getInt("age");
            System.out.printf("%s %d\n", minionName, minionAge);
        }
    }
}
