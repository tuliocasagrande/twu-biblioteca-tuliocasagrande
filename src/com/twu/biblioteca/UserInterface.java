package com.twu.biblioteca;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class UserInterface {

    private final BufferedReader input;
    private final Library library;
    private User user;

    public UserInterface(Library library) {
        this.input = new BufferedReader(new InputStreamReader(System.in));
        this.library = library;
    }

    public void printWelcome() {
        System.out.println("Welcome to the Bangalore Public Library!");
    }

    public void printMenu() {
        System.out.println("\nMain menu:");
        System.out.println("<1> List Books");
        System.out.println("<2> List Movies");
        System.out.println();
        if (userIsLogged()) {
            System.out.println("<3> Checkout Book");
            System.out.println("<4> Checkout Movie");
            System.out.println();
            System.out.println("<5> Return Book");
            System.out.println("<6> Return Movie");
            System.out.println();
            if (user.isLibrarian()) {
                System.out.println("<7> List borrowings");
                System.out.println();
            }
            System.out.println("<9> Logout");
        } else {
            System.out.println("<9> Login");
        }
        System.out.println();
        System.out.println("<0> Quit");
        System.out.println();
        System.out.print("Choose an option: ");
    }

    public int readInteger() {
        try {
            return Integer.parseInt(input.readLine());
        } catch (IOException e) {
            return -1;
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public void handleMenuOption(int option) {
        int id;

        if (!userIsLogged() && option > 2 && option < 9) {
            option = -1;
        }

        switch (option) {
            case 1:
                System.out.println("\nThese are the available books:");
                listBooks();
                break;
            case 2:
                System.out.println("\nThese are the available movies:");
                listMovies();
                break;
            case 3:
                id = readInteger();
                checkoutBook(id);
                break;
            case 4:
                id = readInteger();
                checkoutMovie(id);
                break;
            case 5:
                id = readInteger();
                returnBook(id);
                break;
            case 6:
                id = readInteger();
                returnMovie(id);
                break;
            case 7:
                if (user.isLibrarian()) {
                    System.out.println("These are the borrowings:");
                    listBorrowings();
                } else {
                    System.out.println("\nSelect a valid option!");
                }
                break;
            case 9:
                handleLogin();
                break;
            case 0:
                System.out.println("\nSee you soon!");
                break;
            default:
                System.out.println("\nSelect a valid option!");
        }
    }

    public void listBorrowings() {
        System.out.printf("%-5s %-7s %s\n", "ID", "Type", "Borrower");

        for (Book b : library.getBooks()) {
            if (!b.isAvailable()) {
                System.out.printf("%-5s %-7s %s\n", b.getId(), "book", b.getBorrower().getLibraryNumber());
            }
        }

        for (Movie m : library.getMovies()) {
            if (!m.isAvailable()) {
                System.out.printf("%-5s %-7s %s\n", m.getId(), "movie", m.getBorrower().getLibraryNumber());
            }
        }
    }

    private void handleLogin() {
        if (userIsLogged()) {
            userLogout();
        } else {
            userLogin(readUser());
        }
    }

    public void listBooks() {
        System.out.printf("%-5s %-5s %-20s %s\n", "ID", "Year", "Author", "Title");

        for (Book b : library.getBooks()) {
            if (b.isAvailable()) {
                System.out.printf("%-5s %-5s %-20s %s\n", b.getId(), b.getYear(), b.getAuthor(), b.getTitle());
            }
        }
    }

    public void returnBook(int book_id) {
        if (library.returnBook(book_id)) {
            System.out.println("Thank you for returning the book.");
        } else {
            System.out.println("That is not a valid book to return.");
        }
    }

    public void checkoutBook(int book_id) {
        if (library.checkoutBook(book_id, user)) {
            System.out.println("Thank you! Enjoy the book.");
        } else {
            System.out.println("That book is not available.");
        }
    }

    public void listMovies() {
        System.out.printf("%-5s %-5s %-7s %-20s %s\n", "ID", "Year", "Rating", "Director", "Title");

        for (Movie m : library.getMovies()) {
            if (m.isAvailable()) {
                System.out.printf("%-5s %-5s %-7s %-20s %s\n", m.getId(), m.getYear(),
                        m.getRating(), m.getDirector(), m.getTitle());
            }
        }
    }

    public void returnMovie(int movie_id) {
        if (library.returnMovie(movie_id)) {
            System.out.println("Thank you for returning the movie.");
        } else {
            System.out.println("That is not a valid movie to return.");
        }
    }

    public void checkoutMovie(int movie_id) {
        if (library.checkoutMovie(movie_id, user)) {
            System.out.println("Thank you! Enjoy the movie.");
        } else {
            System.out.println("That movie is not available.");
        }
    }

    public User readUser() {
        User user = null;
        do {
            try {
                System.out.print("Library number: ");
                String libraryNumber = input.readLine();
                System.out.print("Password: ");
                String password = input.readLine();
                user = library.authenticate(libraryNumber, password);
                if (user == null) {
                    System.out.println("\nWrong library number or password!!! Please inform again.");
                }
            } catch (IOException ignored) {
            }
        } while (user == null);
        return user;
    }

    public void userLogin(User user) {
        this.user = user;
    }
    public void userLogout() {
        this.user = null;
    }
    private boolean userIsLogged() {
        return user != null;
    }
}
