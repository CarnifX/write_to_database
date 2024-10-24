import javax.swing.*;

//Denne klassen fokuserer på å lage en user interface for innlogging til et brukersystem.
//Foreløpig har jeg bare sett på oppretting av ny bruker.
public class Create_user_interface {

    public static void create_user_interface(){

        JFrame frame = new JFrame("Login");
        JPanel panel = new JPanel();

        frame.setSize(340, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        panel.setLayout(null);
        frame.setLocationRelativeTo(null);


        JLabel username = new JLabel("Username: ");
        username.setBounds(30, 20, 100, 25);
        panel.add(username);

        JTextField userText = new JTextField();
        userText.setBounds(120, 20, 165, 25);
        panel.add(userText);

        JLabel password = new JLabel("Password: ");
        password.setBounds(30, 50, 100, 25);
        panel.add(password);

        JPasswordField passwordText = new JPasswordField();
        passwordText.setBounds(120, 50, 165, 25);
        panel.add(passwordText);

        JButton acceptButton = new JButton("Accept");
        acceptButton.setBounds(210, 100, 80, 25);
        panel.add(acceptButton);

        JButton signUpButton = new JButton("Sign up");
        signUpButton.setBounds(120, 100, 80, 25);
        panel.add(signUpButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBounds(30, 100, 80, 25);
        panel.add(cancelButton);

        frame.setVisible(true);
        frame.getRootPane().setDefaultButton(acceptButton);

        acceptButton.addActionListener(e -> {
            String written_username = userText.getText();
            String written_password = String.valueOf(passwordText.getPassword());
        });

        signUpButton.addActionListener(ActionEvent -> Create_new_member.newMember());
        cancelButton.addActionListener(ActionEvent -> frame.dispose());


    }

}