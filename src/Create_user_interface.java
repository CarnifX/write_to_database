import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

//Denne klassen fokuserer på å lage en user interface for innlogging til et brukersystem.
//Foreløpig har jeg bare sett på oppretting av ny bruker.
public class Create_user_interface {

    private final Object lock;


    public Create_user_interface(Object lock){
        this.lock = lock;
        AtomicBoolean login = new AtomicBoolean(false);
        AtomicReference<String> written_email = new AtomicReference<>();
        AtomicReference<String> written_password = new AtomicReference<>();

        JFrame frame = new JFrame("Login");
        JPanel panel = new JPanel();

        frame.setSize(340, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        panel.setLayout(null);
        frame.setLocationRelativeTo(null);


        JLabel email = new JLabel("E-mail: ");
        email.setBounds(30, 20, 100, 25);
        panel.add(email);

        JTextField emailText = new JTextField();
        emailText.setBounds(120, 20, 165, 25);
        panel.add(emailText);

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

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                synchronized (lock) {
                    lock.notify(); // Notify the main thread to continue
                }
                frame.dispose(); // Dispose of the JFrame
            }
        });

        acceptButton.addActionListener(ActionEvent -> {
            written_email.set(String.valueOf(emailText.getText()));
            written_password.set(String.valueOf(passwordText.getPassword()));


            List<String> user_info = Logistic_database_methods.getUserInfo(String.valueOf(written_email));

            //En litt messy if-statement, men den sjekker om email eksisterer i databasen,
            //og i tillegg sjekker om innskrevet passord matcher passordet lagret i databasen under emailen.
            if (Logistic_database_methods.checkIfUserExists(String.valueOf(written_email)) && String.valueOf(written_password).equals(user_info.get(2))) {
                synchronized (lock) {
                    lock.notify(); // Notify the main thread to continue
                }
                frame.dispose();

            } else {
                JOptionPane.showMessageDialog(new JFrame(),"Either E-mail og password is wrong. Please try again!", "Oops!",JOptionPane.ERROR_MESSAGE);
            }
        });

        signUpButton.addActionListener(ActionEvent -> Create_new_member.newMember());
        cancelButton.addActionListener(ActionEvent -> frame.dispose());


    }

}