package de.awtools.builder;

public final class PersonBuilder {
    private String name;
    private String firstName;
    private String lastName;
    private int age;
    private String gender;

    private PersonBuilder() {
    }

    public static PersonBuilder person() {
        return new PersonBuilder();
    }

    public static PersonBuilder person(Customizer<PersonBuilder> customizer) {
        PersonBuilder builder = person();
        customizer.customize(builder);
        return builder;
    }

    public PersonBuilder name(String name) {
        this.name = name;
        return this;
    }

    public PersonBuilder firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public PersonBuilder lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public PersonBuilder age(int age) {
        this.age = age;
        return this;
    }

    public PersonBuilder gender(String gender) {
        this.gender = gender;
        return this;
    }

    public PersonBuilder customize(Customizer<PersonBuilder> customizer) {
        customizer.customize(this);
        return this;
    }

    public Person build() {
        Person person = new Person();
        person.setName(name);
        person.setFirstName(firstName);
        person.setLastName(lastName);
        person.setAge(age);
        person.setGender(gender);
        return person;
    }
}

