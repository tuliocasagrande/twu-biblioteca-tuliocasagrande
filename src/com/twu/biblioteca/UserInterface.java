package com.twu.biblioteca;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class UserInterface {

    private final BufferedReader input;

    public UserInterface() {
        input = new BufferedReader(new InputStreamReader(System.in));
    }

    public void printWelcome() {
        System.out.println("Welcome to the Bangalore Public Library!");
    }

    public void printMenu() {
        System.out.println("\nMain menu:");
        System.out.println("<1> List Books");
        System.out.println("<2> Checkout Book");
        System.out.println("<3> Return Book");
        System.out.println("<0> Quit");
        System.out.print("Choose an option: ");
    }

    public void listBooks(Library library) {
        System.out.printf("%-5s %-5s %-20s %s\n", "ID", "Year", "Author", "Title");

        for (Book b : library.getBooks()) {
            if (b.getStatus() == Book.Status.AVAILABLE) {
                System.out.printf("%-5s %-5s %-20s %s\n", b.getId(), b.getYear(), b.getAuthor(), b.getTitle());
            }
        }
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

    public void handleMenuOption(int option, Library library) {
        int book_id;
        switch (option) {
            case 1:
                System.out.println("\nThese are the available books:");
                listBooks(library);
                break;
            case 2:
                book_id = readInteger();
                checkoutBook(library, book_id);
                break;
            case 3:
                book_id = readInteger();
                returnBook(library, book_id);
                break;
            case 0:
                System.out.println("\nSee you soon!");
                break;
            default:
                System.out.println("\nSelect a valid option!");
        }
    }

    public void returnBook(Library library, int book_id) {
        if (library.returnBook(book_id)) {
            System.out.println("Thank you for returning the book.");
        } else {
            System.out.println("That is not a valid book to return.");
        }
    }

    public void checkoutBook(Library library, int book_id) {
        if (library.checkoutBook(book_id)) {
            System.out.println("Thank you! Enjoy the book.");
        } else {
            System.out.println("That book is not available.");
        }
    }
}
