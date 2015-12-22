package com.twu.biblioteca;

public class BibliotecaApp {

    public static void main(String[] args) {
        Book[] books = {new Book("Kent Beck", "Test Driven Development: By Example", 2002),
                        new Book("Martin Fowler", "Refactoring: Improving the Design of Existing Code", 1999)};

        UserInterface userInterface = new UserInterface();
        userInterface.welcome();
        int option = 1;

        do {
            userInterface.menu();
            option = userInterface.readOption();
            userInterface.handleOption(option, books);
        } while (option != 0);

    }
}
