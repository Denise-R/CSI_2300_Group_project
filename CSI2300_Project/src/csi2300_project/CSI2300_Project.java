package csi2300_project;
import java.util.Scanner;

public class CSI2300_Project {
    public static String encryptMessage(){
        // getting user's message
        System.out.println("Type your unencrypted, plain-text message: ");
        Scanner inputMessage = new Scanner(System.in);
        String message = inputMessage.nextLine();
        
        // making message all lower case so make the encryption stronger
        message = message.toLowerCase();
        
        // turning the String message into a character array
        char[] charMessage = message.toCharArray();
        
        // getting user's private key
        System.out.println("Type your private key: ");
        Scanner inputKey = new Scanner(System.in);
        int privateKey = inputKey.nextInt();
        
        // creating an alphabet string and array to look up position
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        char[] alphabetArray = alphabet.toCharArray();
        
        // shifting message by private key
        // iterating through character in charMessage
        char[] encryptedMessage = new char[charMessage.length];
        for(int i = 0; i < charMessage.length; i++){
            char c = charMessage[i];
            String s = String.valueOf(c);
            if(alphabet.contains(s)){
                int index = (alphabet.indexOf(charMessage[i]) + privateKey) % 26;
                char letter = alphabetArray[index];
                encryptedMessage[i] = letter;
            }else{
                encryptedMessage[i] = c;
            }
            
        }
        String result = new String(encryptedMessage);
        return result;
    }
    
    public static String decryptMessage(String message){
        // making message all lower case so make the encryption stronger
        message = message.toLowerCase();

        // turning the String message into a character array
        char[] charMessage = message.toCharArray();

        // getting user's private key
        System.out.println("Type your private key: ");
        Scanner inputKey = new Scanner(System.in);
        int privateKey = inputKey.nextInt();

        // creating an alphabet string and array to look up position
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        char[] alphabetArray = alphabet.toCharArray();
        
        // shifting message by private key
        // iterating through character in charMessage
        char[] encryptedMessage = new char[charMessage.length];
        for(int i = 0; i < charMessage.length; i++){
            char c = charMessage[i];
            String s = String.valueOf(c);
            if(alphabet.contains(s)){
                int index = ((alphabet.indexOf(charMessage[i]) - privateKey) + 26) % 26;
                char letter = alphabetArray[index];
                encryptedMessage[i] = letter;
            }else{
                encryptedMessage[i] = c;
            }   
        }
        String result = new String(encryptedMessage);
        return result;
    }
        
    public static void main(String[] args) {
        String encryptedMessage = encryptMessage();
        System.out.println(encryptedMessage);
        String decryptedMessage = decryptMessage(encryptedMessage);
        System.out.println(decryptedMessage);
    }
    
}


    


