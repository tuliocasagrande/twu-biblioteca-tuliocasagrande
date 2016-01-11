package com.twu.biblioteca;

public class BibliotecaApp {

    public static void main(String[] args) {
        Book[] books = {new Book(1, "Kent Beck", "Test Driven Development: By Example", 2002),
                new Book(2, "Martin Fowler", "Refactoring: Improving the Design of Existing Code", 1999),
                new Book(3, "Kathy Sierra", "Head First Java", 2005),
                new Book(4, "Robert C. Martin", "Clean Code: A Handbook of Agile Software Craftsmanship", 2008)};

        Movie[] movies = {new Movie(1, "The Imitation Game", "Morten Tyldum", 2014, 8),
                new Movie(2, "The Wolf of Wall Street", "Martin Scorsese", 2013, 8)};

        Library library = new Library(books, movies);
        UserInterface userInterface = new UserInterface();
        userInterface.printWelcome();
        int option;

        do {
            userInterface.printMenu();
            option = userInterface.readInteger();
            userInterface.handleMenuOption(option, library);
        } while (option != 0);

    }
}
