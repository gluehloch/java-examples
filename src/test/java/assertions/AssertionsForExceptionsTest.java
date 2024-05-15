package assertions;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.junit.jupiter.api.Test;

class AssertionsForExceptionsTest {

    @Test
    void validateThatException() {
        assertThatExceptionOfType(Exception.class)
                .isThrownBy(this::throwsAnException)
                .withMessage("That was a failure.");
    }

    private void throwsAnException() throws Exception {
        throw new Exception("That was a failure.");
    }

}
