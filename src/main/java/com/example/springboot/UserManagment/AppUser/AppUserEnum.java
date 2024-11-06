package com.example.springboot.UserManagment.AppUser;

public enum AppUserEnum {

    ADMIN("ADMIN"),
    USER("USER");

    private final String value;

    public String getValue() {
        return value;
    }

    private AppUserEnum(String value) {
        this.value = value;
    }
}
