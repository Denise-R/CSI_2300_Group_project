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
        HashMap<Character,Integer> letterAssignment = new HashMap<Character,Integer>(){{
            put('a', 1);
            put('b', 2);
            put('c', 3);
            put('d', 4);
            put('e', 5);
            put('f', 6);
            put('g', 7);
            put('h', 8);
            put('i', 9);
            put('j', 10);
            put('k', 11);
            put('l', 12);
            put('m', 13);
            put('n', 14);
            put('o', 15);
            put('p', 16);
            put('q', 17);
            put('r', 18);
            put('s', 19);
            put('t', 20);
            put('u', 21);
            put('v', 22);
            put('w', 23);
            put('x', 24);
            put('y', 25);
            put('z', 26);
            
            
            
        }}; // there may be a way to do this all in one line, but this is the best I got rn
        
        
        // turning the String message into a character array
        char[] charMessage = message.toCharArray();
        
        // junk code just to visualize what is going on (lines 54-59)
        int privateKey = 3;
        for(int i = 0; i < charMessage.length; i++){
            System.out.print(charMessage[i] + " + " + privateKey + ",");

        }
        
        // What we still need to do to finish the encryption: a for loop that contains steps 1-5
          // 1. for each value in charArray look up int (alphabet position) value in hashmap
              // we will also want to use an if/else statment to 'continue' on not alphabetic characters (a-z) and execute steps 1-5 on any ith iteration a-z values 
          // 2. add private key (shift number) to int value
          // 3. look up char in hashMap using updated int value from step 2 (may want to make a new variable)
          // 4. update char using index

        
          // 5. convert char array back to a string (completed)
        String outputMessage = String.copyValueOf(charMessage);
        System.out.println(outputMessage);
    }
    
}

