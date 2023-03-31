package csi2300_project;

import static csi2300_project.CSI2300Project.optionPane;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import static csi2300_project.MessageShift.messageShift;



public class MyFrame extends JFrame implements ActionListener{
    // creating class variables
    int shiftType;
    JButton messagebButton = new JButton("Submit");
    JTextArea messageTextArea = new JTextArea();
    JButton keyButton = new JButton("Submit");
    JTextField keyTextField = new JTextField();
    JButton options = new JButton("Message Options Page");
    boolean isMessageEditable = true;
    boolean isKeyEditable = true;
    
    MyFrame(String shiftTypeLabelText, int selectedOption){
        shiftType = selectedOption;
        //ImageIcon OULogo = new ImageIcon("C:\\Users\\denis\\Pictures\\Saved Pictures\\ou_logo.png"); // creates an ImageIcon
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // make 'x' operation valid to close the program
        this.setSize(800,800); // sets size of window
        this.setTitle("Message Encryption App"); // sets window title
        this.getContentPane().setBackground(Color.DARK_GRAY); // sets background of frame to grey
        
        // creating a encryption/ translation label and associated panel
        JLabel shiftTypeLabel = new JLabel(shiftTypeLabelText); // creates a label
        shiftTypeLabel.setFont(new Font(null, Font.BOLD, 30)); // sets the font style
        JPanel namePanel = new JPanel(); // creates a panel
        namePanel.setBackground(Color.white);
        namePanel.setBounds(50, 25, 700, 50); // sets the position and size of the panel
        namePanel.add(shiftTypeLabel); // adds the label to the panel
        
        // creating a message label and associated panel
        JLabel typeMessage = new JLabel("Type Message:");
        typeMessage.setFont(new Font(null, Font.BOLD, 30));
        JPanel messagePanel = new JPanel();
        messagePanel.setBackground(Color.white);
        messagePanel.setBounds(50, 100, 700, 400);
        messagePanel.add(typeMessage);
        
        messagebButton.addActionListener(this); // allows the button to respond to being clicked
        messagebButton.setSize(125, 30);

        // setting textArea preferences
        messageTextArea.setPreferredSize(new Dimension(675,300));
        messageTextArea.setFont(new Font(null, Font.BOLD, 30));
        messageTextArea.setForeground(Color.black);
        messageTextArea.setBackground(Color.lightGray);
        messageTextArea.setCaretColor(Color.black);
        messageTextArea.setLineWrap(true);
        messageTextArea.setWrapStyleWord(true);
        messagePanel.add(messageTextArea);
        messagePanel.add(messagebButton);
        
        // creating a key label and associated panel
        JLabel typeKey = new JLabel("Type Shift Key:");
        typeKey.setFont(new Font(null, Font.BOLD, 30));
        typeKey.setHorizontalTextPosition(JLabel.LEFT);
        JPanel keyPanel = new JPanel();
        keyPanel.setBackground(Color.white);
        keyPanel.setBounds(50, 500, 700, 200);
        keyPanel.add(typeKey);
        
        keyButton.addActionListener(this);
        keyButton.setSize(125, 30);

        keyTextField.setPreferredSize(new Dimension(675,100));
        keyTextField.setFont(new Font(null, Font.BOLD, 20));
        keyTextField.setForeground(Color.black);
        keyTextField.setBackground(Color.LIGHT_GRAY);
        keyTextField.setCaretColor(Color.black);
        keyTextField.setBorder(javax.swing.BorderFactory.createEmptyBorder()); //removing textField border
        keyPanel.add(keyTextField);
        keyPanel.add(keyButton);
        
        options.addActionListener(this);
        options.setBounds(580, 710, 170, 30);
        
        this.add(namePanel);
        this.add(messagePanel);
        this.add(keyPanel);
        this.add(options);
        this.setLayout(null);
        this.setVisible(true); // makes elements visable
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == messagebButton){
            messagebButton.setEnabled(false); // no longer editable
            messageTextArea.setEditable(false); // no longer editable
            isMessageEditable = false;
        } else if(e.getSource() == keyButton){
            keyButton.setEnabled(false); // no longer editable
            keyTextField.setEditable(false); // no longer editable
            isKeyEditable = false;
        }

        if(isMessageEditable == false & isKeyEditable == false){
            String message = messageTextArea.getText(); // getting User's message
            String keyString = keyTextField.getText(); // getting user's private key text
            int key = Integer.parseInt(keyString);
            String newMessage = messageShift(shiftType, message, key); // encrypting message based on private key
            
            //Creating a new Text area to display the encrypted message
            JTextArea encryptedText = new JTextArea(5,20); 
            encryptedText.setFont(new Font(null, Font.BOLD, 20));
            encryptedText.setLineWrap(true);
            encryptedText.setWrapStyleWord(true);
            encryptedText.setText("Shifted Message: " + newMessage);
            
            // closing the frame
            this.dispose();
            
            // displaying the message using a JOptionPane popup
            String[] responses = {"Message Options Page","Exit Application"};
            int selectedOption = JOptionPane.showOptionDialog(null, encryptedText, "Message Encryption App", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, responses, 0);
            if(selectedOption == 0){
                optionPane();
            }
        }
        
        if(e.getSource() == options){
            this.dispose();
            optionPane();
        }
    }  
}
