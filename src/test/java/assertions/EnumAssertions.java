package assertions;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class EnumAssertions {

    public static enum Status {
        ACTIVE, INACTIVE
    }

    @Test
    void validateStatus() {
        assertThat(Status.ACTIVE).isEqualTo(Status.ACTIVE);
        assertThat(Enum.valueOf(Status.class, "ACTIVE")).isEqualTo(Status.ACTIVE);
        assertThat(Status.valueOf("ACTIVE")).isEqualTo(Status.ACTIVE);
        assertThat(Status.values()).containsExactly(Status.ACTIVE, Status.INACTIVE);
    }

}
