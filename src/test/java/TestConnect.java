import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.*;

public class TestConnect implements Data {

  private final String  jdbcURL = "jdbc:postgresql://localhost:5432/Java connection";
  private final String username = "postgres";
  private final String password = "Qwerty123";
    Connection connection;


@BeforeMethod
    public void setConnection(){

    try {
        connection = DriverManager.getConnection(jdbcURL,username,password);
        System.out.println("Connection to PostgreSQL server");
    } catch (SQLException throwables) {
        System.err.println("Error in connection to PostgreSQL server");
        throwables.printStackTrace();
    }
}

   @Test(dataProvider = "RandomInsertDataProvider")
    public void insertData(String randomFirstName,String randomLastName, String randomEmail) throws SQLException {
       String sql = "INSERT INTO contacts2 (first_name, last_name, email)" + "VALUES (?,?,?)";

       PreparedStatement statement = connection.prepareStatement(sql);

       statement.setString(1,randomFirstName);
       statement.setString(2,randomLastName);
       statement.setString(3,randomEmail);

       statement.execute();
 }
  @Test
    public void selectAllFromTable() throws SQLException {
    String selectQuery = "Select * FROM contacts2";
      Statement statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery(selectQuery);

      while (resultSet.next()){
          int id = resultSet.getInt("id");
          String firstname = resultSet.getString("first_name");
          String lastname = resultSet.getString("last_name");
          String email = resultSet.getString("email");

          System.out.printf("%d - %s -%s - %s\n", id,firstname,lastname,email);
      }
     // statement.execute(selectQuery);
  }
   @AfterMethod
    public void closeConnection(){
       try {
           connection.close();
       } catch (SQLException throwables) {
           throwables.printStackTrace();
       }
   }
}
