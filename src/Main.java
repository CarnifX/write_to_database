import java.sql.*;


public class Main {

    public static void main(String[] args) throws SQLException {

        String envVar = System.getenv("SERVER_PASSWORD");
        System.out.println(envVar);

        Connection connection = null;
        try {

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sql_memberserver", "root", "");
            Statement statement = connection.createStatement();

            createNewMember.newMember(statement);


        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }
}
