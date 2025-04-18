package record;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class UsingMyFirstRecord {

    @Test
    void testingMyFirstRecord() {
        MyFirstRecord record = new MyFirstRecord("Andre", "Winkler");

        assertThat(record.firstName()).isEqualTo("Andre");
        assertThat(record.name()).isEqualTo("Winkler");

        RecordDefinedOnClassLevel rdcl = new RecordDefinedOnClassLevel("1", "Name", "Message");
        assertThat(rdcl.toMessage()).isEqualTo("message=[1, Name, Message]");

        MyFirstRecord record2 = new MyFirstRecord("Andre", null);
        assertThat(record2.firstName()).isEqualTo("Andre");
        assertThat(record2.name()).isNull();
    }

}

record RecordDefinedOnClassLevel(String level, String name, String message) {

    public String toMessage() {
        return String.format("message=[%s, %s, %s]", level, name, message);
    }
}
