import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.Arrays;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class VigenereCipherTest {

    @ParameterizedTest
    @DisplayName("Check shift of table row")
    @ValueSource(strings = {"ABCDEFGHIJKLMNOPQRSTUVWXYZ"})
    void shiftByOne(String ab) {
        String[] target = "EFGHIJKLMNOPQRSTUVWXYZABCD".split("");
        String[] abArray = ab.split("");
        VigenereTable t = new VigenereTable();
        assertTrue(Arrays.equals(t.shift(abArray, 4), target));
    }

    @Test
    @DisplayName("Read file contents")
    void readFileContents() {
        ArrayList<String> data = VigenereCipher.readFile("res/encrypt_text.txt");
        System.out.println(data);
    }

    @ParameterizedTest
    @DisplayName("Generate message keyword")
    @ValueSource(strings = {"max"})
    void generateMessageKeyword(String keyword) {
        String message = "blah blah, blah";
        String keyedMessage = VigenereCipher.getKeyedMessage(message, keyword);
        //System.out.println(keyedMessage);
        assertEquals(
                "maxm axma, xmax",
                keyedMessage);
    }

    @Test
    @DisplayName("Encrypt successfully")
    void encryptMessage() {
        assertEquals( "SFB'K RAZTKI HU TQEB EDLF TOPQ!\n" +
                        "UIYSXNIEQ LUJ KOCL NM BTIM UIEMXCUXEHKU.",
        VigenereCipher.myEncrypt(
                "res/encrypt_text.txt",
                "res/key_check.txt")
        );
    }

}
