import javax.swing.*;
import java.sql.*;
import java.util.concurrent.atomic.AtomicReference;


//Klassen 'CreateNewMember' åpner en user interface hvor man kan opprette en ny bruker.
//Infoen man skriver inn blir automatisk lagret på en lokal SQL server.
//Passord blir foreløpig ikke behandlet på en ordentlig måte, og blir
//bare lagret vanlig i databasen. Skal se over hashing, salting, etc senere.
public class Create_new_member {

    public static void newMember(){



        JFrame frame = new JFrame("New member!");
        JPanel panel = new JPanel();

        frame.setSize(330, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);

        panel.setLayout(null);
        frame.setLocationRelativeTo(null);


        JLabel userName = new JLabel("Username: ");
        userName.setBounds(10, 20, 100, 25);
        panel.add(userName);

        JTextField userText = new JTextField();
        userText.setBounds(120, 20, 165, 25);
        panel.add(userText);

        JLabel password = new JLabel("Password: ");
        password.setBounds(10, 50, 100, 25);
        panel.add(password);

        JPasswordField passwordText = new JPasswordField();
        passwordText.setBounds(120, 50, 165, 25);
        panel.add(passwordText);

        JLabel email = new JLabel("E-mail: ");
        email.setBounds(10, 80, 100, 25);
        panel.add(email);

        JTextField emailText = new JTextField();
        emailText.setBounds(120, 80, 165, 25);
        panel.add(emailText);

        JLabel phoneNumber = new JLabel("Phone number: ");
        phoneNumber.setBounds(10, 110, 100, 25);
        panel.add(phoneNumber);

        JTextField phoneNumberText = new JTextField();
        phoneNumberText.setBounds(120, 110, 165, 25);
        panel.add(phoneNumberText);

        JButton acceptButton = new JButton("Accept");
        acceptButton.setBounds(170, 160, 80, 25);
        panel.add(acceptButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBounds(80, 160, 80, 25);
        panel.add(cancelButton);

        frame.getRootPane().setDefaultButton(acceptButton);
        frame.setVisible(true);

        acceptButton.addActionListener(e -> {
            String newUsername = userText.getText();
            String newPassword = String.valueOf(passwordText.getPassword());
            String newEmail = emailText.getText();
            String newPhoneNumber = phoneNumberText.getText();

            //If-statementen sjekker om inputdata matcher visse betingelser jeg har satt
            //i metoden checkIfInputsAreValid.
            if (Logistic_database_methods.checkIfInputsAreValid(newUsername, newPassword, newEmail, newPhoneNumber)) {
                try {
                    Connection connection = Main.connection;
                    PreparedStatement preparedStatement = null;
                    String query_to_set_new_user = "insert into members values (DEFAULT, ?, ?, ?, ?)";
                    preparedStatement = connection.prepareStatement(query_to_set_new_user);
                    preparedStatement.setString(1, newUsername);
                    preparedStatement.setString(2, newPassword);
                    preparedStatement.setString(3, newEmail);
                    preparedStatement.setString(4, newPhoneNumber);
                    preparedStatement.executeUpdate();

                    frame.dispose();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }


        });

        cancelButton.addActionListener(ActionEvent -> frame.dispose());

    }

}

