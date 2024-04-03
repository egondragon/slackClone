import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ChatWindow extends JFrame implements ActionListener {
    JButton activityButton;
    JButton chatButton;
    JButton teamsButton;
    JButton calendarButton;
    JButton filesButton;
    JButton appsButton;
    JButton helpButton;
    JButton settingsButton;
    JButton quitButton;
    JButton sendButton;
    // Used to send messages to the server
    chatTcpClient chatClient;
    JTextArea chatMsgTextArea;
    public ChatWindow() {
        // Connect transaction inside the constructor
        chatClient = new chatTcpClient();

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            System.out.println("Look and feel not found");
        }
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("JFrame principal window !");
        this.setBounds(new Rectangle(300, 300, 1024, 864));
        // JButton btn = new JButton("Button 0");

        JPanel leftPanel = new JPanel();
        JPanel userListPanel = new JPanel();
        userListPanel.setLayout(new BoxLayout(userListPanel, BoxLayout.Y_AXIS));

        // FIll with the db users content

        for (User user: dbManager.getAllUsers()) {
            JPanel userPanel = createUserPanel(user);
            userListPanel.add(userPanel);
        }
        // Create a List control to show at the left of the frame
        // Change the renderer to have our icons

        leftPanel.add(new JScrollPane(userListPanel));
        leftPanel.setBackground(new Color(255, 255, 255, 255));

        // Now form the right panel with at the top a message area
        // and at the bottom a textfield + send button
        JPanel rightPanel = new JPanel();

        JPanel msgListPanel = new JPanel();
        JPanel msgSendPanel = new JPanel();

        // display the list of message
        JList msgListArea = new JList();
        msgListPanel.setLayout(new BorderLayout());
        msgListPanel.add(msgListArea, BorderLayout.CENTER);

        // get the input from the user
        chatMsgTextArea = new JTextArea(100, 50);
        chatMsgTextArea.setMargin(new Insets(10, 10, 10, 10));

        sendButton = new JButton();
        // Next 3 lines too long, simplify !!
        ImageIcon x = new ImageIcon("images/icons/send.png");
        // Style: make Icon and make transparent
        sendButton.setIcon(new ImageIcon(x.getImage().getScaledInstance(64, 64, Image.SCALE_DEFAULT)));
        sendButton.setOpaque(false);
        sendButton.setContentAreaFilled(false);
        sendButton.setBorderPainted(false);

        sendButton.addActionListener(this); // will trigger a call to chatTcpClient.sendMsg()
        sendButton.setMargin(new Insets(10, 10, 10, 10));

        msgSendPanel.setLayout(new BorderLayout(10, 10));
        msgSendPanel.add(chatMsgTextArea, BorderLayout.CENTER);
        msgSendPanel.add(sendButton, BorderLayout.LINE_END);

        // assemble the msg display zone and the input zone into rightPane
        JSplitPane rightSplitPane = new JSplitPane(
            SwingConstants.HORIZONTAL,
            msgListPanel,
            msgSendPanel
        );
        int vertbar_pos = 650;
        rightSplitPane.setDividerLocation(vertbar_pos);

        // add the treePane and the rightPane in a global Panel
        JSplitPane jp = new JSplitPane(SwingConstants.VERTICAL, leftPanel, rightSplitPane);
        int horizbar_pos = 250;
        jp.setDividerLocation(horizbar_pos); // horiz bar position
        this.setLayout(new BorderLayout());
        this.add(jp, BorderLayout.CENTER);

        // Toolbar
        JToolBar jtb = new JToolBar();

        // JToolbar icons
        activityButton = new JButton( getScaledIcon("images/icons/bell.png"));
        chatButton = new JButton( getScaledIcon("images/icons/chat.png"));
        teamsButton = new JButton(getScaledIcon("images/icons/teams.png"));
        calendarButton = new JButton( getScaledIcon("images/icons/calendar.png"));
        filesButton = new JButton( getScaledIcon("images/icons/files.png"));
        appsButton = new JButton( getScaledIcon("images/icons/apps.png"));
        helpButton = new JButton( getScaledIcon("images/icons/help.png"));
        settingsButton = new JButton( getScaledIcon("images/icons/settings.png"));
        quitButton = new JButton( getScaledIcon("images/icons/quit.png"));

        activityButton.addActionListener(this);
        chatButton.addActionListener(this);
        teamsButton.addActionListener(this);
        calendarButton.addActionListener(this);
        filesButton.addActionListener(this);
        appsButton.addActionListener(this);
        helpButton.addActionListener(this);
        settingsButton.addActionListener(this);
        quitButton.addActionListener(this);

        jtb.add(activityButton);
        jtb.add(chatButton);
        jtb.add(teamsButton);
        jtb.add(calendarButton);
        jtb.add(filesButton);
        jtb.add(appsButton);
        jtb.add(helpButton);
        jtb.add(settingsButton);
        jtb.add(quitButton);
        
        this.add(jtb, BorderLayout.NORTH);
    }

    private JPanel createUserPanel(User user) {
        JPanel userPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                setOpaque(false); // Make panel transparent
            }
        };
        userPanel.setLayout(new BoxLayout(userPanel, BoxLayout.X_AXIS));

        ImageIcon icon = getScaledIcon("images/icons/user.png");
        JLabel iconLabel = new JLabel(icon);
        iconLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Handle icon click action
                JOptionPane.showMessageDialog(null, "Icon clicked for user: " + user.getUsername());
            }
        });
        userPanel.add(iconLabel);

        JLabel nameLabel = new JLabel(user.getUsername());
        userPanel.add(nameLabel);

        return userPanel;
    }
    // Scale the icons used on button etc
    public static ImageIcon getScaledIcon(String imagePath) {
        ImageIcon icon = new ImageIcon(imagePath);
        Image scaledImage = icon.getImage().getScaledInstance(32, 32, Image.SCALE_DEFAULT);
        return new ImageIcon(scaledImage);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == quitButton) {
            dispose();
        }
        if (e.getSource() == sendButton) {
            chatClient.sendMsg(chatMsgTextArea.getText());
        }
    }
}
