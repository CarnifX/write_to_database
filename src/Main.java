

import java.sql.*;


public class Main {

    //Oppretter statement- og connection-object her så jeg kan kalle på disse i de forskjellige klassene.
    //Koden handler kun om å kommunisere med SQL server, så tenkte det var like greit å opprette kommunikasjon med en gang.
    public static Statement statement = null;
    public static Connection connection = null;


    public static void main(String[] args) throws SQLException, InterruptedException {

        //Dotenv dotenv = Dotenv.load();
        //String db_password = dotenv.get("DB_PASSWORD");
        //String db_username = dotenv.get("DB_USERNAME");
        //String db_host = dotenv.get("DB_HOST");
        //String db_port = dotenv.get("DB_PORT");
        //String url = "jdbc:mysql://" + db_host + ":" + db_port + "/sql_memberserver";

        //Oppretter connection:
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sql_memberserver", "root", "");
            statement = connection.createStatement();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        //Oppretter en lock som skal samhandle med Create_user_interface klassen.
        //Ved å gjøre dette unngår jeg at koden i main fullføres før Create_user_interface får utredet noen ting.
        //Dette i tilfellet koden skal gjøre noe videre etter login, freks.
        final Object lock = new Object();

        //Opretter et interface objekt.
        Create_user_interface myInterface = new Create_user_interface(lock);

        //Dette er hvor koden venter på at Create_user_interface skal sende en lock notification.
        synchronized (lock){
            lock.wait();
        }

        //Foreløpig returnerer man her dersom man enten har logget inn, eller krysser ut JFrame vinduet.
        System.out.println("You returned to main");
    }
}
