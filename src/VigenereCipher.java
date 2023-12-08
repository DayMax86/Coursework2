import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class VigenereCipher implements Cipher {

    //FOR TESTING -------------- TODO()
    public static void main (String[] args) {
        VigenereTable t = new VigenereTable();
        t.populateTable();
    }
    //---------------------------------

    @Override
    public String encrypt(String message_filename, String key_filename) {
        ArrayList<String> message = readFile(message_filename);
        ArrayList<String> key = readFile(key_filename);



        return null;
    }

    @Override
    public String decrypt(String message_filename, String key_filename) {
        return null;
    }

    public static ArrayList<String> readFile(String filename){
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

