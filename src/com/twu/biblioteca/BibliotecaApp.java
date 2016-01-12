package com.twu.biblioteca;

class BibliotecaApp {

    public static void main(String[] args) {
        Book[] books = {new Book(1, "Kent Beck", "Test Driven Development: By Example", 2002),
                new Book(2, "Martin Fowler", "Refactoring: Improving the Design of Existing Code", 1999),
                new Book(3, "Kathy Sierra", "Head First Java", 2005),
                new Book(4, "Robert C. Martin", "Clean Code: A Handbook of Agile Software Craftsmanship", 2008)};

        Movie[] movies = {new Movie(1, "The Imitation Game", "Morten Tyldum", 2014, 8),
                new Movie(2, "The Wolf of Wall Street", "Martin Scorsese", 2013, 8),
                new Movie(3, "The Avengers", "Joss Whedon", 2012, 9)};

        User[] users = {new User("123-1234", "weak_password", User.Type.CUSTOMER),
                new User("121-1212", "1234", User.Type.LIBRARIAN)};

        UserInterface userInterface = new UserInterface(new Library(books, movies, users));
        userInterface.printWelcome();
        userInterface.logUser(userInterface.readUser());

        int option;
        do {
            userInterface.printMenu();
            option = userInterface.readInteger();
            userInterface.handleMenuOption(option);
        } while (option != 0);

    }
}
