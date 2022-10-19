import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class _03_GetMinionNameByVillainName {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "1234");

        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/minions_db", props);

        int villains_id = Integer.parseInt(scanner.nextLine());

        PreparedStatement villainStatement = connection.prepareStatement
                ("select name from villains where id= ?");
        villainStatement.setInt(1, villains_id);

        ResultSet villainSet = villainStatement.executeQuery();

        if(!villainSet.next()){
            System.out.printf("No villain with ID %d exists in the database.", villains_id);
            return;
        }

        String villainName = villainSet.getString("name");
        System.out.printf("Villain: %s\n", villainName);

        PreparedStatement statement = connection.prepareStatement
                ("select  m.name, m.age" +
                        " from minions as m" +
                        " join minions_villains mv on m.id = mv.minion_id" +
                        " where mv.villain_id = ?");
        statement.setInt(1, villains_id);

        ResultSet resultSet = statement.executeQuery();

        for (int i = 1; resultSet.next() ; i++) {
            String minionName = resultSet.getNString("m.name");
            int minionAge = resultSet.getInt("m.age");


            System.out.printf("%d. %s %d\n", i, minionName, minionAge);
        }

        connection.close();
    }
}
