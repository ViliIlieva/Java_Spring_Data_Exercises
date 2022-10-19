import java.sql.*;
import java.util.Properties;

public class _02_GetVillainNameAndCountMinion {
    public static void main(String[] args) throws SQLException {
        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "1234");

        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/minions_db", props);

        PreparedStatement statement = connection.prepareStatement
                ("select v.name, count(distinct (mv.minion_id)) as count" +
                        " from villains as v" +
                        " join minions_villains as mv on v.id = mv.villain_id" +
                        " group by mv.villain_id" +
                        " having count > 15" +
                        " order by count desc");
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()){
            String villainName = resultSet.getNString("name");
            int count = resultSet.getInt("count");
            System.out.println(villainName + " " + count);
        }
        connection.close();
    }
}
