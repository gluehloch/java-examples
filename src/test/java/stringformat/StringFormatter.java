package stringformat;

import static de.awtools.string.ExceptionMessageFormatter.format;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import de.awtools.string.Nickname;
import de.awtools.string.UserService;

class StringFormatter {

    /**
     * Siehe auch
     * {@linkplain https://docs.oracle.com/en/java/javase/15/docs/api/java.base/java/util/Formatter.html}.
     */
    @Test
    void printf() {
        System.out.printf("Das ist mein %d. Test", 1);
        assertThat(String.format("Das ist mein %d. Test.", 1)).isEqualTo("Das ist mein 1. Test.");
        assertThat(String.format("Das ist mein %1$d. und mein %1$d. Test. %2$d", 1, 2))
                .isEqualTo("Das ist mein 1. und mein 1. Test. 2");
    }

    @Test
    void formatter() {
        String str = format(() -> "string {}", "string");
        assertThat(str).isEqualTo("string string");
    }

    @Test
    void string() {
        UserService userService = new UserService();
        userService.addUser(Nickname.of("Nickname"));
    }

//	public String getString(String test) {
//        return switch (test) {
//        case "test3":
//            "test3";
//        case "test1":
//            "test1";
//        case "test2":
//            "test2";
//        default: "default";
//        }
//    }

}
