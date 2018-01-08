package ru.ledo.test;

public interface PersonFactory<P extends Person> {
    P createPerson(String firstName, String lastName);
}
