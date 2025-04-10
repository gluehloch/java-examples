package java11;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

/**
 * Show me some Java 11 features!
 *
 * @author winkler
 */
class Java11FeatureShow {

    @Test
    public void switchCaseWithString() {
        var xxx = "Ahorn";
        switch (xxx) {
            case "Ahorn":
                xxx = "Ahorn";
                break;
            case "Buche":
                xxx = "Buche";
                break;
            default:
                xxx = "Wiese";
        }
        assertThat(xxx).isEqualTo("Ahorn");
    }

}
