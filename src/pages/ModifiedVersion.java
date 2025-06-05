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

public class ModifiedVersion extends JFrame {
    private final AdminDAO adminDao = new AdminDAO();
    private final JTextField usernameField;
    private final JPasswordField passwordField;

    public ModifiedVersion() {
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
        signUpButton.addActionListener(e -> handleSignUp());

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void handleLogin() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter both username and password.");
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

    private void handleSignUp() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please enter both username and password.");
            return;
        }

        // Validation for Username.
        if (username.length() < 4 || username.length() > 11) {
            JOptionPane.showMessageDialog(this,
                    "Invalid Username!\n" +
                            "Username must be 5-10 characters only.");
            return;
        } else if (username.matches(".*[\\s()].*")) {
            JOptionPane.showMessageDialog(this,
                    "Invalid Username!\n" +
                            "Username cannot have any types of special characters, " +
                            "even a whitespaces.");
            return;
        }

        // Validation for Password.
        if (password.length() < 4 || password.length() > 10) {
            JOptionPane.showMessageDialog(this,
                    "Invalid Password!\n" +
                            "Password must not be shorter than 4 characters " +
                            "but must not exceed 10.");
            return;
        } else if (password.matches(".*[\\s()].*")) {
            JOptionPane.showMessageDialog(this,
                    "Invalid Password!\n" +
                            "Username cannot have any types of special characters, " +
                            "even a whitespaces.");
            return;
        } else if (password.contains(username)) {
            JOptionPane.showMessageDialog(this,
                    "Invalid Password!\n" +
                            "Password cannot contain or be the same as its associated username.");
            return;
        }

        boolean valid = adminDao.addingAccount(username, password);
        if (valid) {
            JOptionPane.showMessageDialog(this,
                    "Sign-Up successful!");
            new LoginPage();

            dispose();
        } else {
            JOptionPane.showMessageDialog(this,
                    "Username already exist.\n" +
                            "Please create a new one.");
        }
    }
}
