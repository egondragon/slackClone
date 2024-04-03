import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class loginDialog extends JDialog {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public loginDialog(JFrame parent) {
        setTitle("Login");
        this.setSize( 320, 200);
        setLocationRelativeTo(null);
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");
        usernameField = new JTextField(15);
        passwordField = new JPasswordField(15);
        JButton loginButton = new JButton("Login");
        JButton cancelButton = new JButton("Cancel");

        JButton registerButton = new JButton("Register");
        registerButton.setBorderPainted(false); // Remove border
        registerButton.setForeground(Color.BLUE); // Set text color to blue
        // Add action listener to the register button
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the register JFrame here
                RegisterFrame registerFrame = new RegisterFrame();
                registerFrame.setVisible(true);
            }
        });

        JLabel forgetPasswordLabel = new JLabel("Forgot Password?");

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(usernameLabel, gbc);

        gbc.gridy = 1;
        panel.add(passwordLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(usernameField, gbc);

        gbc.gridy = 1;
        panel.add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new BorderLayout(20, 10));
        btnPanel.add(loginButton, BorderLayout.LINE_START);
        btnPanel.add(cancelButton, BorderLayout.LINE_END);
        panel.add(btnPanel, gbc);
        /*
        gbc.gridy = 3;
        panel.add(cancelButton, gbc);
        */
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.LINE_START;
        panel.add(registerButton, gbc);

        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.LINE_END;
        panel.add(forgetPasswordLabel, gbc);

        getContentPane().add(panel);
        pack();
        setLocationRelativeTo(parent);

        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Exit the program
            }
        });

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == loginButton) {
                    String sz_login = getUsername();
                    String sz_passwd = getPassword();

                    // You can perform login validation here
                    // Check if this user exist
                    System.out.println(sz_login);
                    System.out.println(sz_passwd);
                    if (dbManager.checkLoginCredentials(sz_login, sz_passwd)) {
                        // Open MyFrame
                        ChatWindow myFrame = new ChatWindow();
                        myFrame.setVisible(true);
                        // Close the login dialog
                        dispose();

                    } else {
                        JOptionPane.showMessageDialog(
                                null,
                                "User not found, please register first!",
                                "User not found",
                                JOptionPane.ERROR_MESSAGE
                        );
                    }

                }
                if (e.getSource() == cancelButton) {
                    dispose();
                    System.exit(0);
                }
            }
        });
    }

    public String getUsername() {
        return usernameField.getText().trim();
    }

    public String getPassword() {
        return new String(passwordField.getPassword());
    }
/*
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
                loginDialog dialog = new loginDialog(frame);
                dialog.setVisible(true);
            }
        });
    }
 */
}
