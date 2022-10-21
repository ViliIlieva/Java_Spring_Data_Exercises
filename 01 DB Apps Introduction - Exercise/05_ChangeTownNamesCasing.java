import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class _05_ChangeTownNamesCasing {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "1234");

        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/minions_db", props);

        String countryName = scanner.nextLine();

        PreparedStatement updateTownsByCountry = connection.prepareStatement(
                "UPDATE towns set `name` = upper(`name`) where country = ?");
        updateTownsByCountry.setString(1, countryName);
        int executeUpdate = updateTownsByCountry.executeUpdate();

        if(executeUpdate == 0){
            System.out.println("No town names were affected.");
            connection.close();
            return;
        }
        System.out.println(executeUpdate + " town names were affected.");

        PreparedStatement selectAllTowns = connection.prepareStatement(
                "SELECT name FROM towns where country = ?");
        selectAllTowns.setString(1, countryName);
        ResultSet townsSet = selectAllTowns.executeQuery();

        List<String> townsList = new ArrayList<>();
        while (townsSet.next()){
            townsList.add(townsSet.getString("name"));
        }
        System.out.println(townsList);
        connection.close();
    }
}
