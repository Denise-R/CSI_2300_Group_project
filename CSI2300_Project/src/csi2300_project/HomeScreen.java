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

        // Create a panel for login UI components
        JPanel loginPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);

        // Create username and password labels, text fields, and login button
        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");
        usernameTextField = new JTextField(20);
        passwordField = new JPasswordField(20);
        loginButton = new JButton("Login");

        // Add username label to panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        loginPanel.add(usernameLabel, gbc);

        // Add username text field to panel
        gbc.gridx = 1;
        gbc.gridy = 0;
        loginPanel.add(usernameTextField, gbc);

        // Add password label to panel
        gbc.gridx = 0;
        gbc.gridy = 1;
        loginPanel.add(passwordLabel, gbc);

        // Add password field to panel
        gbc.gridx = 1;
        gbc.gridy = 1;
        loginPanel.add(passwordField, gbc);

        // Add login button to panel
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        loginPanel.add(loginButton, gbc);

        // Create menu bar with file and help menus
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("Menu");
        JMenu helpMenu = new JMenu("Help");

        // Create menu item for adding a user and add it to file menu
        addUserMenuItem = new JMenuItem("Add User");
        fileMenu.add(addUserMenuItem);
        menuBar.add(fileMenu);
        menuBar.add(helpMenu);
        setJMenuBar(menuBar);

        // Add login panel to frame
        add(loginPanel);

        // Load user data from file
        loadUserData();

        // Create menu item for "How to Use" and add it to help menu
        JMenuItem howToUseMenuItem = new JMenuItem("How to Use");
        helpMenu.add(howToUseMenuItem);

        // Add action listener for "How to Use" menu item
        howToUseMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(HomeScreen.this,
                        "Welcome to the Encryption Messaging Application!\n\n"
                                + "After you have created a username and password, you are prompted with a text box and value key.\n"
                                + "Insert what you would like encrypted and insert a key value (1-9). Once you are ready to Encrypt your message, press Encrypt.\n");
            }
        });

        // Add action listener for login button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameTextField.getText();
                String password = new String(passwordField.getPassword());
        
       if (users.containsKey(username) && users.get(username).equals(password)) {
    // If the entered username and password match with the stored values
    JOptionPane.showMessageDialog(HomeScreen.this, "Login successful!");
    setVisible(false); // hide the HomeScreen
    String[] responses = {"Encrypt Message", "Translate Message", "Cancel"};
    int selectedOption = JOptionPane.showOptionDialog(null, "Select Action", "Encryption Page", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, responses, 0);
    // Show a dialog box with options and store the selected option
    if(selectedOption == 0){
        String shiftTypeLabelText = "Message Encryption";
        new MyFrame(shiftTypeLabelText, selectedOption); // Create a new MyFrame object with the selected option
    }else if(selectedOption == 1){
        String shiftTypeLabelText = "Message Translation";
        new MyFrame(shiftTypeLabelText, selectedOption); // Create a new MyFrame object with the selected option
    }
} else {
    // If the entered username or password is invalid
    JOptionPane.showMessageDialog(HomeScreen.this, "Invalid username or password");
}
    }
});
        addUserMenuItem.addActionListener((ActionEvent e) -> {
            addUser();
        });
    }
private void addUser() {
    String username = JOptionPane.showInputDialog(this, "Enter a new username:");
    // Show an input dialog box to enter a new username and store it in the 'username' variable
    if (username == null || username.isEmpty()) {
        // If the entered username is null or empty, show an error message
        JOptionPane.showMessageDialog(this, "Username cannot be blank.");
        return;
    }
    if (users.containsKey(username)) {
        // If the entered username already exists in the 'users' map, show an error message
        JOptionPane.showMessageDialog(this, "Username already exists. Please choose a different username.");
        return;
    }
    String password = JOptionPane.showInputDialog(this, "Enter a password for " + username + ":");
    // Show an input dialog box to enter a password for the new username and store it in the 'password' variable
    users.put(username, password); // Add the new username and password to the 'users' map
    userData.add(username + "," + password); // Add the new username and password as a string to the 'userData' list
    saveUserData(); // Call the 'saveUserData()' method to save the updated 'userData' list to a file
    JOptionPane.showMessageDialog(this, "User " + username + " added successfully!");
    // Show a success message with the newly added username
}
   private void loadUserData() {
    try {
        File file = new File("user_data.txt");
        if (!file.exists()) {
            file.createNewFile();
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 2) {
                    users.put(data[0], data[1]); // Load username and password from file and add to 'users' map
                    userData.add(line); // Add the loaded data as a string to the 'userData' list
                }
            }
        }
    } catch (IOException e) {
    }
}
    private void saveUserData() {
    try {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("user_data.txt"))) {
            for (String line : userData) {
                writer.write(line);
                writer.newLine();
            }
        }
    } catch (IOException e) {
        // Empty catch block to handle IOException silently
    }
}
    public static void main(String[] args) {
        HomeScreen app = new HomeScreen();
        app.setVisible(true);
    }
}