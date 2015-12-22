package com.twu.biblioteca;

public class BibliotecaApp {

    public static void main(String[] args) {
        Book[] books = {new Book(1, "Kent Beck", "Test Driven Development: By Example", 2002),
                        new Book(2, "Martin Fowler", "Refactoring: Improving the Design of Existing Code", 1999),
                        new Book(3, "Kathy Sierra", "Head First Java", 2005),
                        new Book(4, "Robert C. Martin", "Clean Code: A Handbook of Agile Software Craftsmanship", 2008)};

        UserInterface userInterface = new UserInterface();
        userInterface.welcome();
        int option;

        do {
            userInterface.menu();
            option = userInterface.readOption();
            userInterface.handleOption(option, books);
        } while (option != 0);

    }
}
