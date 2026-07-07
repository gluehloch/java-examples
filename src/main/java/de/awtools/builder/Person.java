package de.awtools.builder;


public class Person {
    private String name;
    private String firstName;
    private String lastName;
    private int age;
    private String gender;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static PersonBuilder builder() {
        return PersonBuilder.person();
    }

    public Customizer<Person> person() {
        return person -> {
            person.setFirstName(firstName);
            person.setLastName(lastName);
        };
    }

}
