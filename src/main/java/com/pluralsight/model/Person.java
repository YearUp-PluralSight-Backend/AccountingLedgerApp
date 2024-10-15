package com.pluralsight.model;

import com.pluralsight.model.Enums.Role;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class Person {

    // Properties (fields) of the Person class
    private UUID id;
    private String name;
    private int age;
    private String email;
    private String password;
    private String phoneNumber;
    private LocalDate dateOfBirth;
    private Address address;
    private List<Account> accounts;
    private Role role;

    // Constructor to initialize Person object
    public Person() {
    }

    public Person(String name, int age, String email, String password, String phoneNumber, LocalDate dateOfBirth, Address address, List<Account> accounts, Role role) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.age = age;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.accounts = accounts;
        this.role = role;
    }

    // Getter and setter methods for each property

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    // Override the toString method to display the person information
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Person {")
                .append("\n    id: ").append(id)
                .append("\n    name: '").append(name).append('\'')
                .append("\n    age: ").append(age)
                .append("\n    email: '").append(email).append('\'')
                .append("\n    password: '").append(password).append('\'')
                .append("\n    phoneNumber: '").append(phoneNumber).append('\'')
                .append("\n    dateOfBirth: ").append(dateOfBirth)
                .append("\n    address: ").append(address)
                .append("\n    accounts: ").append(accounts)
                .append("\n}");
        return sb.toString();
    }

}
