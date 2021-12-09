import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import org.apache.commons.lang3.RandomStringUtils;

public class Connector {
    String randomFirstName = "FirstName" + RandomStringUtils.randomAlphabetic(4);
    String randomLastName = "LastNama" + RandomStringUtils.randomAlphabetic(4);
    String randomEmail = "Email" + RandomStringUtils.randomAlphabetic(3) + "@gmail.com";
    public static void main(String[] args) {
        String jdbcURL = "jdbc:postgresql://localhost:5432/Java connection";
        String username = "postgres";
        String password = "Qwerty123";
        try {
            Connection connection = DriverManager.getConnection(jdbcURL,username,password);
            System.out.println("Connection to PostgreSQL server");
            String sql = "INSERT INTO contacts2 (first_name, last_name, email)" + "VALUES ('Victor','Panchenko','vp@gmail.com')";

            Statement statement = connection.createStatement();
            statement.execute(sql);
             int rows = statement.executeUpdate(sql);
             if (rows > 0){
                 System.out.println("New contact has been inserted");
             }
            connection.close();
        } catch (SQLException throwables) {
            System.out.println("Error in connection to PostgreSQL server");
            throwables.printStackTrace();
        }
    }
}
