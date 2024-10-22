import java.sql.*;


public class Main {

    public static void main(String[] args) throws SQLException {

        //Lager en connection instance som snakker med den lokale SQL serveren.
        Connection connection = null;
        try {

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sql_memberserver", "root", "");
            Statement statement = connection.createStatement();

            //Kaller inn metoden for å opprette et nytt medlem. Sender med connection statementen.
            createNewMember.newMember(statement);


        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }
}
