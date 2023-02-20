package csi2300_project;
import java.util.Scanner;
import java.util.HashMap;

public class CSI2300_Project {

    public static void main(String[] args) {
        // getting user's message
        System.out.println("Type your unencrypted, plain-text message: ");
        Scanner messageIn = new Scanner(System.in);
        String message = messageIn.nextLine();
        
        // making message all lower case so make the encryption stronger
        message = message.toLowerCase();
        System.out.println(message);
        
        
        // creating a HashMap to match each letter to its position in the English alphabet 
        // may want to do tis as a for loop to make code less repetative
        HashMap<Character, Integer> letterAssignment = new HashMap<>();
        for (char c = 'a'; c <= 'z'; c++) {
        letterAssignment.put(c, c - 'a' + 1);
        }

        
        // turning the String message into a character array
        char[] charMessage = message.toCharArray();
        
        // junk code just to visualize what is going on (lines 54-59)
        int privateKey = 3;
        for(int i = 0; i < charMessage.length; i++){
            System.out.print(charMessage[i] + " + " + privateKey + ",");

        }
        
        // What we still need to do to finish the encryption: a for loop that contains steps 1-5
          // 1. for each value in charArray look up int (alphabet position) value in hashmap
          for(int i = 0; i < charMessage.length; i++){
              // we will also want to use an if/else statment to 'continue' on not alphabetic characters (a-z) and execute steps 1-5 on any ith iteration a-z values
              int position = letterAssignment.get(charMessage[i]);
              System.out.print(position + ",");
              // 2. add private key (shift number) to int value
              position = position + privateKey;
              // 3. look up char in hashMap using updated int value from step 2 (may want to make a new variable)
              // 4. update char using index

        
              // 5. convert char array back to a string (completed)
              String outputMessage = String.copyValueOf(charMessage);
              System.out.println(outputMessage);
          } 
          
    }
    
}
