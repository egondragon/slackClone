import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class testSwing extends JDialog implements ActionListener {
    JButton registerButton;
    JButton cancelButton;

    public testSwing() {
        this.setBounds(300, 300, 600, 520); // Increased height to accommodate buttons
        JPanel jp = new JPanel(new BorderLayout());

        JTextField usernameTextField = new JTextField();
        JLabel usernameLabel = new JLabel("Username");

        JTextField passwordTextField = new JTextField(); // Use JPasswordField for password if needed
        JLabel passwordLabel = new JLabel("Password");

        JTextField emailTextField = new JTextField();
        JLabel emailLabel = new JLabel("Email");

        JTextField addressTextField = new JTextField();
        JLabel addressLabel = new JLabel("Address");

        JTextField firstnameTextField = new JTextField();
        JLabel firstnameLabel = new JLabel("First Name");

        JTextField lastnameTextField = new JTextField();
        JLabel lastnameLabel = new JLabel("Last Name");

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
        passwordTextField.setPreferredSize(textFieldDimension);
        emailTextField.setPreferredSize(textFieldDimension);
        addressTextField.setPreferredSize(textFieldDimension);
        firstnameTextField.setPreferredSize(textFieldDimension);
        lastnameTextField.setPreferredSize(textFieldDimension);

        gbc.gridy = 0;
        cp.add(usernameTextField, gbc);

        gbc.gridy = 1;
        cp.add(passwordTextField, gbc);

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
        if (e.getSource() == cancelButton) {
            dispose(); // Close the dialog when cancelButton is clicked
        }
        // Add functionality for registerButton if needed
    }
}
