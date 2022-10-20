import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class _04_AddMinion {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "1234");

        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/minions_db", props);

        String [] MinionInput = scanner.nextLine().split(" ");
        String [] villainInput = scanner.nextLine().split(" ");

        String minName = MinionInput[1];
        int minAge = Integer.parseInt(MinionInput[2]);
        String town = MinionInput[3];
        String villainName = villainInput[1];

        int townId = getOrInsertTown(connection, town);
        int villainId = getOrInsertVillain(connection, villainName);
        

    }

    private static int getOrInsertVillain(Connection connection, String villainName) throws SQLException {
        PreparedStatement selectVillain = connection.prepareStatement(
                "SELECT id from villains where name = ?");
        selectVillain.setString(1, villainName);

        ResultSet villainSet = selectVillain.executeQuery();

        int villainId = 0;
        if(!villainSet.next()){
            PreparedStatement insertVillain = connection.prepareStatement(
                    "INSERT INTO villains(name, evilness_factor) values (?, ?)");
            insertVillain.setString(1, villainName);
            insertVillain.setString(1, "evil");
            insertVillain.executeUpdate();

            ResultSet newVillainSet = selectVillain.executeQuery();
            newVillainSet.next();
            villainId = newVillainSet.getInt("id");
            System.out.printf("Villain %s was added to the database.\n", villainName);
        }else {
            villainId = villainSet.getInt("id");
        }
        return villainId;
    }

    private static int getOrInsertTown(Connection connection, String town) throws SQLException {
        PreparedStatement selectTown = connection.prepareStatement(
                "SELECT id from towns where name = ?");
        selectTown.setString(1, town);

        ResultSet townSet = selectTown.executeQuery();

        int townId = 0;
        if(!townSet.next()){
           PreparedStatement insertTown = connection.prepareStatement(
                   "INSERT INTO towns(name) values (?)");
           insertTown.setString(1, town);
           insertTown.executeUpdate();

           ResultSet newTownSet = selectTown.executeQuery();
           newTownSet.next();
           townId = newTownSet.getInt("id");
           System.out.printf("%s was added to the database.\n", town);
        }else {
            townId = townSet.getInt("id");
        }
        return townId;
    }
}
