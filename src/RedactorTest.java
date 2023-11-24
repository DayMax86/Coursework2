import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class RedactorTest {
    @ParameterizedTest
    @CsvSource({"The quick brown fox, {brown}"})
    void redact() {
    }
}