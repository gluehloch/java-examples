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

    @Test
    void someEnumStunts() {
        assertThat(Status.ACTIVE.name()).isEqualTo("ACTIVE");
        assertThat(Status.ACTIVE.ordinal()).isEqualTo(0);
        assertThat(Status.ACTIVE.compareTo(Status.INACTIVE)).isEqualTo(-1);
        assertThat(Status.ACTIVE.getDeclaringClass()).isEqualTo(Status.class);
        assertThat(Status.ACTIVE.toString()).isEqualTo("ACTIVE");
    }

    @Test
    void moreEnumStuntsForInactive() {
        assertThat(Status.INACTIVE.name()).isEqualTo("INACTIVE");
        assertThat(Status.INACTIVE.ordinal()).isEqualTo(1);
        assertThat(Status.INACTIVE.compareTo(Status.ACTIVE)).isEqualTo(1);
        assertThat(Status.INACTIVE.getDeclaringClass()).isEqualTo(Status.class);
        assertThat(Status.INACTIVE.toString()).isEqualTo("INACTIVE");
    }
}
