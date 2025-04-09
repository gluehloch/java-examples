package listassert;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * Some examples to test a list.
 * 
 * @author Andre Winkler
 */
class ListAssert {

    @SuppressWarnings("static-access")
    @Test
    public void assertJdemo() {
        final Person a = Person.of("Winkler", "Andre");
        final Person b = Person.of("Winkler", "Adam");
        final Person c = Person.of("Winkler", "Lars");
        final Person d = Person.of("Winkler", "Erwin");

        final List<Person> list = List.of(a, b, c, d);

        assertThat(list).extracting("name", "firstName").contains(
                tuple("Winkler", "Andre"),
                tuple("Winkler", "Adam"),
                tuple("Winkler", "Lars"),
                tuple("Winkler", "Erwin"));

        assertThat(list).extracting("name", "firstName").containsOnly(
                tuple("Winkler", "Andre"),
                tuple("Winkler", "Adam"),
                tuple("Winkler", "Lars"),
                tuple("Winkler", "Erwin"));

        assertThat(list).extracting("name", "firstName").containsExactlyInAnyOrder(
                tuple("Winkler", "Andre"),
                tuple("Winkler", "Adam"),
                tuple("Winkler", "Lars"),
                tuple("Winkler", "Erwin"));

        assertThat(list).extracting(Person::getName, Person::getFirstName).containsOnly(
                tuple("Winkler", "Andre"),
                tuple("Winkler", "Adam"),
                tuple("Winkler", "Lars"),
                tuple("Winkler", "Erwin"));
    }

    @Test
    void assertJListExample() {
        final Person a = Person.of("Doe", "John");
        final Person b = Person.of("Smith", "Jane");
        final Person c = Person.of("Brown", "Charlie");

        final List<Person> list = List.of(a, b, c);

        // Check if the list contains specific elements
        assertThat(list).contains(a, b);

        // Check if the list contains specific elements in the given order
        assertThat(list).containsExactly(a, b, c);

        // Check if the list contains only the specified elements
        assertThat(list).containsOnly(a, b, c);

        // Check if the list contains elements with specific properties
        assertThat(list).extracting("name", "firstName").contains(
                tuple("Doe", "John"),
                tuple("Smith", "Jane"),
                tuple("Brown", "Charlie"));
    }

    private static class Person {
        public String name;
        public String firstName;

        private Person(String name, String firstName) {
            this.name = name;
            this.firstName = firstName;
        }

        public static Person of(String name, String firstName) {
            return new Person(name, firstName);
        }

        public String getName() {
            return name;
        }

        public String getFirstName() {
            return firstName;
        }
    }

}
