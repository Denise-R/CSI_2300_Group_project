package csi2300_project;
import javax.swing.JOptionPane;

public class OptionPane {
    
    public static void optionPane(){
        String[] responses = {"Encrypt Message", "Translate Message", "Cancel"};
        int selectedOption = JOptionPane.showOptionDialog(null, "Select Action", "Message Options Page", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, responses, 0);
        if(selectedOption == 0){
            String shiftTypeLabelText = "Message Encryption";
            new MyFrame(shiftTypeLabelText, selectedOption);
        }else if(selectedOption == 1){
            String shiftTypeLabelText = "Message Translation";
            new MyFrame(shiftTypeLabelText, selectedOption);
        } 
    }

    public static void main(String[] args) {
        optionPane();
    } 
}
