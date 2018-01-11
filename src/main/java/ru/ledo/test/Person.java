package ru.ledo.test;

@Hints({@Hint("hint1"), @Hint("hint2")})
public class Person {
    String firstName = "Test";
    String lastName = "Test";

    Person() {};

    Person(String firstName, String lastName) {
        this.lastName=lastName;
        this.firstName=firstName;
    }
}
