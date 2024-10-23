import java.sql.*;


public class Main {

    public static Statement statement = null;

    public static void main(String[] args) throws SQLException {

        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sql_memberserver", "root", "");
            statement = connection.createStatement();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        Create_user_interface.create_user_interface();


    }
}
