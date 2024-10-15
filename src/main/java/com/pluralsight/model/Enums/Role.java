package com.pluralsight.model.Enums;

public enum Role {

    USER, ADMIN;

    public String authority() {
        return "ROLE_" + this.name();
    }


}
