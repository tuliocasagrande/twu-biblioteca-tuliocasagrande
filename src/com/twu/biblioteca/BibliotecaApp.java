package com.twu.biblioteca;

public class BibliotecaApp {

    public static void main(String[] args) {
        Book[] books = {new Book("Kent Beck", "Test Driven Development: By Example", 2002),
                        new Book("Martin Fowler", "Refactoring: Improving the Design of Existing Code", 1999)};

        UserInterface.welcome();
        UserInterface.listBooks(books);
        UserInterface.listBooksWithDetails(books);
    }
}
