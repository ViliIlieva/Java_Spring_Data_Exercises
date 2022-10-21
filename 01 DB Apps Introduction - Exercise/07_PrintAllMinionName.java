import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class _07_PrintAllMinionName {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "1234");

        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/minions_db", props);

        PreparedStatement statement = connection.prepareStatement(
                "SELECT `name` FROM minions;");
        ResultSet resultSet = statement.executeQuery();

        List<String> minionName = new ArrayList<>();
        while (resultSet.next()){
            minionName.add(resultSet.getString("name"));
        }
        int lastIndex = minionName.size()-1;
        for (int i = 0; i < minionName.size()/2; i++) {
            System.out.println(minionName.get(i));
            System.out.println(minionName.get(lastIndex));
            lastIndex--;
        }
    }
}
