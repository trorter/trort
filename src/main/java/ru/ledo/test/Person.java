package ru.ledo.test;

public class Person {
    String firstName = "Test";
    String lastName = "Test";

    Person() {};

    Person(String firstName, String lastName) {
        this.lastName=lastName;
        this.firstName=firstName;
    }
}
