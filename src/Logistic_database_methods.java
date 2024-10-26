import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.*;

public class Logistic_database_methods {


    //Denne metoden tar inn en mailadresse, forbereder en prompt til SQL ved hjelp av prepareStatement,
    //sender prompten med 'email' paramenter, og returnerer 1 aka true dersom emailen eksisterer.
    //Bruker prepareStatement for å tryggere introdusere parameter inn i queryen.
    //setString(1, email) erstatter første '?' i queryen med 'email'.
    public static boolean checkIfUserExists(String email){

        Connection connection = Main.connection;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            String query = "SELECT 1 FROM sql_memberserver.members WHERE email = ? LIMIT 1";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();

            return resultSet.next();

        } catch (SQLException ex) {
            System.out.println(ex);
            return true;
        }
    }


    //Metoden sjekker om et telefonnummer matcher norske standarder.
    //Returnerer 'true' hvis nummeret er godkjent.
    public static boolean checkIfValidPhoneNumber(String phone_number){

        String regex = "^(\\+47|0047)?\\s?[2-9]\\d{7}$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phone_number);

        return matcher.matches();
    }

    //Metoden sjekker om parameterne fra oppretting av ny bruker matcher visse betingelser.
    public static boolean checkIfInputsAreValid(String username, String password, String email, String phoneNumber){
        if (Logistic_database_methods.checkIfUserExists(email)) {
            JOptionPane.showMessageDialog(new JFrame(),"There is already an existing account for this e-mail", "Oops!",JOptionPane.ERROR_MESSAGE);
            return false;

        } else if (username.length() < 6) {
            JOptionPane.showMessageDialog(new JFrame(),"Username must contain 6 or more characters!", "Oops!",JOptionPane.ERROR_MESSAGE);
            return false;

        }else if (password.length() < 12) {
            JOptionPane.showMessageDialog(new JFrame(),"Your password must contain 12 or more characters!", "Oops!",JOptionPane.ERROR_MESSAGE);
            return false;

        } else if (!email.contains("@") || !email.contains(".")) {
            JOptionPane.showMessageDialog(new JFrame(),"Please use a valid e-mail address!", "Oops!",JOptionPane.ERROR_MESSAGE);
            return false;

        }  else if (!checkIfValidPhoneNumber(phoneNumber)) {
            JOptionPane.showMessageDialog(new JFrame(),"Your phone number is not valid!", "Oops!",JOptionPane.ERROR_MESSAGE);
            return false;

        } else {
            return true;
        }
    }


    //En metode for å hente ut all info hos en bruker i databasen ved hjelp av e-mail som parameter.
    public static List<String> getUserInfo(String email){

        Connection connection = Main.connection;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<String> user_info = List.of();

        try {
            String query = "SELECT * FROM sql_memberserver.members WHERE email = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                user_info = List.of(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return user_info;
    }

}
