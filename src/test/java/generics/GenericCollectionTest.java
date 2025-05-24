package generics;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class GenericCollectionTest {

    @Test
    void testGenericCollctionWithSuperAndSubTypes() {
        List<Human> humans = new ArrayList<>();
        humans.add(Human.of("John"));
        humans.add(Student.of("Jane", "12345"));
        humans.add(Student.of("Doe", "67890"));
        humans.add(Human.of("Alice"));

        assertThat(humans).hasSize(4);
        assertThat(humans.get(0).getName()).isEqualTo("John");

        // List<Human> studentsAsHumans = getStudents(); // Does not compile!
        List<? extends Human> studentsAsHumans = getStudents();
        assertThat(studentsAsHumans).hasSize(2);
        List<Student> students = getStudents();
        // studentsAsHumans.addAll(students); // Does not compile!
        assertThat(students).hasSize(2);
    }

    private static List<Human> getHumans() {
        List<Human> humans = new ArrayList<>();
        humans.add(Human.of("John"));
        humans.add(Student.of("Jane", "12345"));
        humans.add(Student.of("Doe", "67890"));
        humans.add(Human.of("Alice"));
        return humans;
    }

    private static List<Student> getStudents() {
        List<Student> students = new ArrayList<>();
        students.add(Student.of("Jane", "12345"));
        students.add(Student.of("Doe", "67890"));
        return students;
    }

    static class Human {
        private final String name;

        private Human(String name) {
            this.name = name;
        }

        public static Human of(String name) {
            return new Human(name);
        }

        public final String getName() {
            return name;
        }
    }

    static class Student extends Human {
        private final String id;

        private Student(String name, String id) {
            super(name);
            this.id = id;
        }

        public static Student of(String name, String id) {
            return new Student(name, id);
        }

        public final String Id() {
            return id;
        }
    }
}
