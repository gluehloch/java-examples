package lambda;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import de.awtools.lambda.MethodReference;

public class MethodReferenceTest {

    @DisplayName("Example: Lambda expression as method reference.")
    @Tag("lambda")
    @Test
    void methodReferenceWithLambda() {
        MethodReference mr = new MethodReference();
        MethodReference.calculate(mr, p -> p.addThat("that"));
        MethodReference.calculate(mr, p -> p.addThis("this"));
        
        assertThat(mr.getStrings()).containsExactly("that", "this");
    }
    
}
