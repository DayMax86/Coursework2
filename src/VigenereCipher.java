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
        String[] keyedMessageArray = getKeyedMessage(message, keyword).split("");

        System.out.println("Length of messageArray = " + messageArray.length + " | Length of keyedMessageArray = " + keyedMessageArray.length);
        int i;
        for (i = 0; i < messageArray.length; i++) {
            System.out.println("messageArray[i] = " + messageArray[i] + " | keyedMessageArray[i] = " + keyedMessageArray[i]);
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
        String message = readFile(message_filename).toString();
        String keyword = readFile(key_filename).toString();

        StringBuilder encryptedMessage = new StringBuilder();

        String[] messageArray = message.split("");
        String[] keyedMessageArray = getKeyedMessage(message, keyword).split("");

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
            keyedMessage += keyword.charAt(i);
            i++;
            if (i >= keyword.length()) {
                i = 0;
            }
        }
        System.out.println(keyedMessage);
        return keyedMessage;

        //  THE BELOW LOGIC ALLOWS FOR SPACES AND PUNCTUATION IN THE SAME WAY ALL OTHER IMPLEMENTATIONS OF THE CIPHER DO
//        for (String c : message.split("")) {
//            if (String.valueOf(c).matches("^[a-zA-Z.!?'-]+$")) {
//                //Character is a letter
//                keyedMessage += keyword.charAt(i);
//                i++;
//            } else {
//                //Mustn't be a letter
//                keyedMessage += c;
//            }
//            if (i >= keyword.length()) {
//                i = 0;
//            }
//        }

    }

    @Override
    public String decrypt(String message_filename, String key_filename) {
        //TODO()
        return null;
    }

    public static String readFile(String filename) {
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
        //String strData = data.toString();
        String strData = "";
        for (String line : data) {
            for (int i = 0; i < line.length(); i++) {
                String c = line.split("")[i];
                strData += c;
            }
            strData += System.lineSeparator();
        }

        if (strData.endsWith("\r\n")) {
            strData = strData.substring(0, strData.length() - 2);
        } else if ( strData.endsWith("\n")) {
            strData = strData.substring(0, strData.length() - 1);
        }
        return strData;
    }

}

