
package csi2300_project;

public class MessageShift {
    public static String messageShift(int x, String message, int privateKey){
        // making message all lower case so make the encryption stronger
        message = message.toLowerCase();
        
        // turning the String message into a character array
        char[] charMessage = message.toCharArray();

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
                int index = 0;
                if(x == 0){
                    index = (alphabet.indexOf(charMessage[i]) + privateKey) % 26;
                }else if(x == 1){
                    index = ((alphabet.indexOf(charMessage[i]) - privateKey) + 26) % 26;
                }
                char letter = alphabetArray[index];
                encryptedMessage[i] = letter;
            }else{
                encryptedMessage[i] = c;
            }  
        }
        String result = new String(encryptedMessage);
        return result;
    }
}
