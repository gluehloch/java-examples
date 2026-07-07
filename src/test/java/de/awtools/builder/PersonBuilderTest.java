package de.awtools.builder;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class PersonBuilderTest {

    @Test
    void buildsPersonWithAllValues() {
        Person person = Person.builder()
                .name("AWTools")
                .firstName("Andre")
                .lastName("Winkler")
                .age(42)
                .gender("male")
                .build();

        assertThat(person.getName()).isEqualTo("AWTools");
        assertThat(person.getFirstName()).isEqualTo("Andre");
        assertThat(person.getLastName()).isEqualTo("Winkler");
        assertThat(person.getAge()).isEqualTo(42);
        assertThat(person.getGender()).isEqualTo("male");
    }

    @Test
    void builderCanBeReusedAndOverriddenLikeSpringStyleBuilder() {
        PersonBuilder builder = Person.builder().firstName("Andre").lastName("Winkler");

        Person first = builder.age(41).build();
        Person second = builder.age(42).gender("male").build();

        assertThat(first.getFirstName()).isEqualTo("Andre");
        assertThat(first.getLastName()).isEqualTo("Winkler");
        assertThat(first.getAge()).isEqualTo(41);
        assertThat(first.getGender()).isNull();

        assertThat(second.getFirstName()).isEqualTo("Andre");
        assertThat(second.getLastName()).isEqualTo("Winkler");
        assertThat(second.getAge()).isEqualTo(42);
        assertThat(second.getGender()).isEqualTo("male");
    }

    @Test
    void customizerCanConfigureBuilderUpFront() {
        Person person = PersonBuilder.person(builder -> builder
                .firstName("Andre")
                .lastName("Winkler")
                .age(42)
                .gender("male"))
                .name("AWTools")
                .build();

        assertThat(person.getName()).isEqualTo("AWTools");
        assertThat(person.getFirstName()).isEqualTo("Andre");
        assertThat(person.getLastName()).isEqualTo("Winkler");
        assertThat(person.getAge()).isEqualTo(42);
        assertThat(person.getGender()).isEqualTo("male");
    }

    @Test
    void customizeMethodAppliesSpringStyleLambdaConfiguration() {
        Person person = Person.builder()
                .customize(builder -> builder
                        .name("AWTools")
                        .firstName("Andre")
                        .lastName("Winkler"))
                .build();

        assertThat(person.getName()).isEqualTo("AWTools");
        assertThat(person.getFirstName()).isEqualTo("Andre");
        assertThat(person.getLastName()).isEqualTo("Winkler");
    }

}
