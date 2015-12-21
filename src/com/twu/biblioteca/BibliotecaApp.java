package com.twu.biblioteca;

public class BibliotecaApp {

    public static void main(String[] args) {
        UserInterface.welcome();
        Book[] books = {new Book("Kent Beck", "Test Driven Development: By Example"),
                        new Book("Martin Fowler", "Refactoring: Improving the Design of Existing Code")};
        UserInterface.listBooks(books);
    }
}
