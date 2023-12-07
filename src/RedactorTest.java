import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static java.lang.String.valueOf;
import static org.junit.jupiter.api.Assertions.*;

class RedactorTest {

    @Test
    @DisplayName("Check regex split function")
    void splitWithRegex() {
        String content = "The quick brown fox";
        String[] splitContent = content.split("");
        for (int i = 0; i < splitContent.length; i++) {
            System.out.println(splitContent[i]);
        }
        assertEquals(19, splitContent.length);
    }

    @ParameterizedTest
    @DisplayName("Check regex alphanumeric function")
    @ValueSource(strings = {"a", "1", "A", "0", "Z", "z"})
    void alphanumericRegex(String s) {
        assertTrue(valueOf(s).matches("^[a-zA-Z0-9]+$"));
    }

    @ParameterizedTest
    @DisplayName("Check regex alphanumeric failure")
    @ValueSource(strings = {" ", "*", "-", "'", "", ".", ","})
    void nonAlphanumericRegex(String s) {
        assertFalse(valueOf(s).matches("^[a-zA-Z0-9]+$"));
    }

    @ParameterizedTest
    @DisplayName("Testing redact method with various inputs")
    @ValueSource(strings = {"the","quick","brown","Fox"})
    void shouldRedact(String s) {
        String content = "The quICk brOwn Fox";
        String[] words = new String[1];
        words[0] = s;
        //System.out.println(s);
        String output = Redactor.redact(content, words);
        System.out.println("output: " + output);
        assertTrue(output.contains("*"));
    }

    @ParameterizedTest
    @DisplayName("Testing redact method with non-redactable inputs")
    @ValueSource(strings = {"them","th","brown "," ", "x", ""})
    void shouldNotRedact(String s) {
        String content = "The quICk brOwn Fox";
        String[] words = new String[1];
        words[0] = s;
        System.out.println(s);
        String output = Redactor.redact(content, words);
        System.out.println("output: " + output);
        assertFalse(output.contains("*"));
    }

    @Test
    @DisplayName("Testing redact method with two redact strings")
    void shouldRedact() {
        String content = "The quICk brOwn Fox brownblah quickly quick";
        String[] words = new String[3];
        words[0] = "quick";
        words[1] = "the";
        words[2] = "brOWn";
        String output = Redactor.redact(content, words);
        System.out.println("output: " + output);
        assertTrue(output.contains("*"));
    }

}
