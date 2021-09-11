package record;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

class UsingMyFirstRecord {

    @Test
    void testingMyFirstRecord() {
        MyFirstRecord record = new MyFirstRecord("Andre", "Winkler");

        assertThat(record.firstName()).isEqualTo("Andre");
        assertThat(record.name()).isEqualTo("Winkler");
    }

}
