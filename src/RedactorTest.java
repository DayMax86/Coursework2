import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class RedactorTest {
    @ParameterizedTest
    @ValueSource(strings = {"the","quick","brown","Fox","apostrophe'","hyphen-"})
    void redact_ShouldReturnTrue(String redactWords) {
        String[] words = new String[1];
        words[0] = redactWords;
        Assertions.assertEquals("true", Redactor.redact("The quick brown fox", words));
    }
}