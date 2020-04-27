package java11;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Show me some Java 11 features!
 *
 * @author winkler
 */
public class Java11FeatureShow {

    @Test
    public void xxx() {
        var xxx = "Ahorn";
        // Ups. My Eclipse installation does not suppot JDK 11.
        switch (xxx) {
            case "Ahorn": xxx = "Ahorn"; break;
            case "Buche": xxx = "Buche"; break;
            default: xxx = "Wiese";
        }
        assertThat(xxx).isEqualTo("Ahorn");
    }

}
