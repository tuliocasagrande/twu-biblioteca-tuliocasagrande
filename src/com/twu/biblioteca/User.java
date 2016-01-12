package com.twu.biblioteca;

public class User {
    private final String libraryNumber;
    private final String password;
    private final String name;
    private final String email;
    private final String phoneNumber;
    private final Type type;

    public User(String libraryNumber, String password, String name, String email, String phoneNumber, Type type) {
        this.libraryNumber = libraryNumber;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.type = type;
    }

    public String getLibraryNumber() {
        return libraryNumber;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public boolean isLibrarian() {
        return type == Type.LIBRARIAN;
    }

    public enum Type {CUSTOMER, LIBRARIAN}

}
