package innerclass;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class AccessOuterClass {

    private String text;

    public class InnerClass {
        public String access() {
            String outerText = AccessOuterClass.this.text;
            return outerText;
        }
    }

    @Test
    public void testAccess() {
        text = "Das ist ein Test";
        InnerClass innerClass = new InnerClass();
        assertThat(innerClass.access()).isEqualTo(text);
    }

}
