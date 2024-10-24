import java.sql.*;


public class Main {

    private static boolean login = false;
    public static Statement statement = null;
    public static Connection connection = null;


    public static void main(String[] args) throws SQLException, InterruptedException {

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sql_memberserver", "root", "");
            statement = connection.createStatement();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        final Object lock = new Object();
        Create_user_interface myInterface = new Create_user_interface(lock);

        synchronized (lock){
            lock.wait();
        }

    }
}
