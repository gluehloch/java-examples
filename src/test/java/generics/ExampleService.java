package generics;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class ExampleService {

    public static interface Result<T, U> {
        T getResult();

        U getValidationMessage();
    }

    public Result<String, String> findSomething() {
        return new Result<String, String>() {
            @Override
            public String getResult() {
                return "not found";
            }

            @Override
            public String getValidationMessage() {
                return "i was not able to find a result";
            }
        };
    }

    public Result<String, Integer> findSomeOtherThings() {
        return new Result<>() {
            @Override
            public String getResult() {
                return "not found";
            }

            @Override
            public Integer getValidationMessage() {
                return Integer.valueOf(1998);
            }
        };
    }

    @Test
    void exampleService() {
        final var exampleService = new ExampleService();
        final Result<String, String> something = exampleService.findSomething();
        assertThat(something.getResult()).isEqualTo("not found");
        assertThat(something.getValidationMessage()).isEqualTo("i was not able to find a result");
        final Result<String, Integer> someOtherThings = exampleService.findSomeOtherThings();
        assertThat(someOtherThings.getResult()).isEqualTo("not found");
        assertThat(someOtherThings.getValidationMessage()).isEqualTo(Integer.valueOf(1998));
    }

}
