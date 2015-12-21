package com.twu.biblioteca;

public class UserInterface {

    private UserInterface() {}

    public static void welcome() {
        System.out.println("Welcome!!! :D");
    }

    public static void listBooks(Book[] books) {
        for (Book b: books) {
            System.out.println(b.getTitle() + " - by " + b.getAuthor());
        }
    }
}
