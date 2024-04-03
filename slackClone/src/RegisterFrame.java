import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterFrame extends JDialog implements ActionListener {
    JButton registerButton;
    JButton cancelButton;
    JTextField usernameTextField;
    JLabel usernameLabel;
    JPasswordField passwordField;
    JLabel passwordLabel = new JLabel("Password");
    JTextField emailTextField;
    JLabel emailLabel;
    JTextField addressTextField;
    JLabel addressLabel;
    JTextField firstnameTextField;
    JLabel firstnameLabel;
    JTextField lastnameTextField;
    JLabel lastnameLabel;

    public RegisterFrame() {
        this.setBounds(300, 300, 600, 520); // Increased height to accommodate buttons
        JPanel jp = new JPanel(new BorderLayout());

        usernameTextField = new JTextField();
        usernameLabel = new JLabel("Username");

        passwordField = new JPasswordField(); // Use JPasswordField for password if needed
        passwordLabel = new JLabel("Password");

        emailTextField = new JTextField();
        emailLabel = new JLabel("Email");

        addressTextField = new JTextField();
        addressLabel = new JLabel("Address");

        firstnameTextField = new JTextField();
        firstnameLabel = new JLabel("First Name");

        lastnameTextField = new JTextField();
        lastnameLabel = new JLabel("Last Name");

        // Align the text of labels to the right
        usernameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        passwordLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        emailLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        addressLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        firstnameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        lastnameLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        Container cp = this.getContentPane();
        cp.setLayout(new GridBagLayout()); // Use GridBagLayout for precise component placement

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST; // Align component to the right
        gbc.insets = new Insets(5, 5, 5, 5); // Add some padding

        cp.add(usernameLabel, gbc);
        gbc.gridy = 1;
        cp.add(passwordLabel, gbc);
        gbc.gridy = 2;
        cp.add(emailLabel, gbc);
        gbc.gridy = 3;
        cp.add(addressLabel, gbc);
        gbc.gridy = 4;
        cp.add(firstnameLabel, gbc);
        gbc.gridy = 5;
        cp.add(lastnameLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST; // Align component to the left

        // Set preferred width for text fields
        Dimension textFieldDimension = new Dimension(200, 25);
        usernameTextField.setPreferredSize(textFieldDimension);
        passwordField.setPreferredSize(textFieldDimension);
        emailTextField.setPreferredSize(textFieldDimension);
        addressTextField.setPreferredSize(textFieldDimension);
        firstnameTextField.setPreferredSize(textFieldDimension);
        lastnameTextField.setPreferredSize(textFieldDimension);

        gbc.gridy = 0;
        cp.add(usernameTextField, gbc);

        gbc.gridy = 1;
        cp.add(passwordField, gbc);

        gbc.gridy = 2;
        cp.add(emailTextField, gbc);

        gbc.gridy = 3;
        cp.add(addressTextField, gbc);

        gbc.gridy = 4;
        cp.add(firstnameTextField, gbc);

        gbc.gridy = 5;
        cp.add(lastnameTextField, gbc);

        // Add buttons at the bottom
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        registerButton = new JButton("Register");
        cancelButton = new JButton("Cancel");

        registerButton.addActionListener(this); // Register ActionListener
        cancelButton.addActionListener(this); // Register ActionListener

        buttonPanel.add(registerButton);
        buttonPanel.add(cancelButton);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER; // Align buttons to the center
        cp.add(buttonPanel, gbc);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == registerButton) {
            User data = new User(
                usernameTextField.getText(),
                firstnameTextField.getText(),
                lastnameTextField.getText(),
                emailTextField.getText(),
                addressTextField.getText(),
                passwordField.getText(),
                "images/icons/user.png" // !! needed inside MyFrame when we extract the ist of users to display it
            );
            dbManager.insertData(data);
            // Optionally, display a message or perform additional actions after registration
        } else if (e.getSource() == cancelButton) {
            dispose(); // Close the dialog when cancelButton is clicked
        }
    }
}
