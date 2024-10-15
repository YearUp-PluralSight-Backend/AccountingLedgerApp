package com.pluralsight.model;

/**
 * This class represents an address in {@code Person}
 */
public class Address {

    // Properties (fields) of the Address class
    private String street;
    private String city;
    private String state;
    private String zipCode;

    // Constructor to initialize Address object
    public Address(String street, String city, String state, String zipCode) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }

    // Getter and setter methods for each property
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    //  Override the toString method to display the address information
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(street).append(", ")
                .append(city).append(", ")
                .append(state).append(" ")
                .append(zipCode);
        return sb.toString();
    }

}
