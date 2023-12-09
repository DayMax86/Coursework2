import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class VigenereCipher implements Cipher {

    VigenereTable table = new VigenereTable();

    //FOR TESTING -------------- TODO()
    public static void main(String[] args) {

    }

    public static String myEncrypt(String message_filename, String key_filename) {
        VigenereTable table = new VigenereTable();
        String message = readFile(message_filename).toString();
        String keyword = readFile(key_filename).toString();

        StringBuilder encryptedMessage = new StringBuilder();

        String[] messageArray = message.split("");
        String[] keyedMessageArray = getKeyedMessage(message,keyword).split("");

        int i;
        for (i = 0; i < messageArray.length; i++) {
            if (String.valueOf(messageArray[i]).matches("^[a-zA-Z]+$")) {
                encryptedMessage.append(table.lookup(messageArray[i], keyedMessageArray[i]));
            } else {
                encryptedMessage.append(messageArray[i]);
            }
        }

        return encryptedMessage.toString();
    }

    //---------------------------------

    @Override
    public String encrypt(String message_filename, String key_filename) {
        String message = readFile(message_filename).toString(); //TODO() 'data' returns an array so toString method returns brackets.
        String keyword = readFile(key_filename).toString();

        StringBuilder encryptedMessage = new StringBuilder();

        String[] messageArray = message.split("");
        String[] keyedMessageArray = getKeyedMessage(message,keyword).split("");

        int i;
        for (i = 0; i < messageArray.length; i++) {
            if (String.valueOf(messageArray[i]).matches("^[a-zA-Z]+$")) {
                encryptedMessage.append(table.lookup(messageArray[i], keyedMessageArray[i]));
            } else {
                encryptedMessage.append(messageArray[i]);
            }
        }

        return encryptedMessage.toString();
    }

    public static String getKeyedMessage(String message, String keyword) {
        String keyedMessage = "";
        int i = 0;
        for (String c : message.split("")) {
            if (String.valueOf(c).matches("^[a-zA-Z]+$")) {
                //Character is a letter
                keyedMessage += keyword.charAt(i); //Can String receive char? //TODO()
                i++;
            } else {
                //Mustn't be a letter
                keyedMessage += c;
            }
            if (i >= keyword.length()) {
                i = 0;
            }
        }
        return keyedMessage;
    }

    @Override
    public String decrypt(String message_filename, String key_filename) {
        return null;
    }

    public static ArrayList<String> readFile(String filename) {
        ArrayList<String> data = new ArrayList<>();
        try {
            File f = new File(filename);
            Scanner scanner = new Scanner(f);
            while (scanner.hasNextLine()) {
                data.add(scanner.nextLine());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("No such file found");
        }
        return data;
    }

    /*
- You should use the Vigenère square above to encrypt and decrypt the messages using a given key (both retrieved from a file).
- Encrypted messages should be in capital letters to obfuscate the message from anyone intercepting them
- Decrypted messages should also be in capital letters
- If the character is a letter of the alphabet it should be encrypted based on the above
- If the character is not in the alphabet then it should remain unchanged
     */

}

