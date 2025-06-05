/*
	Machine Problem - GUI
	-------------------------------------
    Name: Velez Jr., Ariel A.
    Course: Bachelor of Science in Information Technology
    Year: Second Year
    Section: BSIT 2-2

    Note: Modified Version
*/

package pages;

import dal.admins.AdminDAO;

import javax.swing.*;
import java.awt.*;

public class LogInPage_Modified extends JFrame {
    private final AdminDAO adminDao = new AdminDAO();
    private final JTextField usernameField;
    private final JPasswordField passwordField;

    public LogInPage_Modified() {
        setTitle("Admin Login");
        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        // Edited: For spacing of the buttons and text fields
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel usernameLabel = new JLabel("Username:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(usernameLabel, gbc);

        usernameField = new JTextField(15);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(usernameField, gbc);

        JLabel passwordLabel = new JLabel("Password:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(passwordLabel, gbc);

        passwordField = new JPasswordField(15);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(passwordField, gbc);

        JButton signInButton = new JButton("Sign In");
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(signInButton, gbc);

        JLabel noAccount = new JLabel("Don't have an account?");
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(noAccount, gbc);

        JButton signUpButton = new JButton("Sign Up");
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.CENTER;
        add(signUpButton, gbc);

        // Action Listeners
        signInButton.addActionListener(e -> handleLogin());
        signUpButton.addActionListener(e -> {
            new SignupPage();
            dispose();
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void handleLogin() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please enter both username and password.");
            return;
        }

        boolean valid = adminDao.checkIfAdminExists(username, password);
        if (valid) {
            JOptionPane.showMessageDialog(this,
                    "Log-In successful!");
            new StudentPage();

            dispose();
        } else {
            JOptionPane.showMessageDialog(this,
                    "Invalid username or password.");
        }
    }
}
