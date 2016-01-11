package com.twu.biblioteca;

public class User {
    private final String libraryNumber;
    private final String password;
    private final Type type;

    public User(String libraryNumber, String password, Type type) {
        this.libraryNumber = libraryNumber;
        this.password = password;
        this.type = type;
    }

    public String getLibraryNumber() {
        return libraryNumber;
    }

    public String getPassword() {
        return password;
    }

    public Type getType() {
        return type;
    }

    public enum Type {CUSTOMER, LIBRARIAN}

}
