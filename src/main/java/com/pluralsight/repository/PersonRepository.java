package com.pluralsight.repository;

import com.pluralsight.model.Person;

public interface PersonRepository {

    void savePerson(Person person);

    void updatePerson(Person person);

    void deletePerson(Person person);

    Person getPersonById(String personId);

    Person getPersonByEmailAddress(String emailAddress);

    Person getPersonByDateOfBirth(String dateOfBirth);

    Person[] getPersons();

    void printPerson(String personId);
}
