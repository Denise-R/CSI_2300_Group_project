package csi2300_project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeScreen extends JFrame {
    private JTextField usernameTextField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JMenuItem addUserMenuItem;
    private Map<String, String> users = new HashMap<>();
    private List<String> userData = new ArrayList<>();

    public HomeScreen() {
        setTitle("Login Application");
        setSize(500, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel loginPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);
        
        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");
        usernameTextField = new JTextField(20);
     
        passwordField = new JPasswordField(20);
        loginButton = new JButton("Login");

        gbc.gridx = 0;
        gbc.gridy = 0;
        loginPanel.add(usernameLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        loginPanel.add(usernameTextField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        loginPanel.add(passwordLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        loginPanel.add(passwordField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        loginPanel.add(loginButton, gbc);

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("Menu");
        JMenu helpMenu = new JMenu("Help");
        addUserMenuItem = new JMenuItem("Add User");
        fileMenu.add(addUserMenuItem);
        menuBar.add(fileMenu);
        menuBar.add(helpMenu);
        setJMenuBar(menuBar);
        add(loginPanel);
        
        loadUserData();
        JMenuItem howToUseMenuItem = new JMenuItem("How to Use");
helpMenu.add(howToUseMenuItem);

howToUseMenuItem.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(HomeScreen.this,
        "Welcome to the Encryption Messaging Application!\n\n"
                + "After you have created a username and password, you are prompted with a text box and value key.\n"
                + "Insert what you would like encrypted and insert a key value. Once you are ready to Encrypt your message, press Encrypt.\n");
    }
});
      
        loginButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        String username = usernameTextField.getText();
        String password = new String(passwordField.getPassword());
        if (users.containsKey(username) && users.get(username).equals(password)) {
            JOptionPane.showMessageDialog(HomeScreen.this, "Login successful!");
            // Open the next screen here
            String[] responses = {"Encrypt Message", "Translate Message", "Cancel"};
            int selectedOption = JOptionPane.showOptionDialog(null, "Select Action", "Encryption Page", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, responses, 0);
            if(selectedOption == 0){
                String shiftTypeLabelText = "Message Encryption";
                new MyFrame(shiftTypeLabelText, selectedOption);
            }else if(selectedOption == 1){
                String shiftTypeLabelText = "Message Translation";
                new MyFrame(shiftTypeLabelText, selectedOption);
            }
        } else {
            JOptionPane.showMessageDialog(HomeScreen.this, "Invalid username or password");
        }
    }
});

        // Add "Add User" menu item action listener
        addUserMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addUser();
            }
        });
    }

private void addUser() {
    String username = JOptionPane.showInputDialog(this, "Enter a new username:");
    if (username == null || username.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Username cannot be blank.");
        return;
    }
    if (users.containsKey(username)) {
        JOptionPane.showMessageDialog(this, "Username already exists. Please choose a different username.");
        return;
    }
    String password = JOptionPane.showInputDialog(this, "Enter a password for " + username + ":");
    users.put(username, password);
    userData.add(username + "," + password);
    saveUserData();
    JOptionPane.showMessageDialog(this, "User " + username + " added successfully!");
}
    

    private void loadUserData() {
        try {
            File file = new File("user_data.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 2) {
                    users.put(data[0], data[1]);
                    userData.add(line);
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveUserData() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("user_data.txt"));
            for (String line : userData) {
                writer.write(line);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        HomeScreen app = new HomeScreen();
        app.setVisible(true);
    }
}