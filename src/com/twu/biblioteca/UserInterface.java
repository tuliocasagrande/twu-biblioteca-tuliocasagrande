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
        System.out.println("Welcome to the Bangalore Public Library!\n");
    }

    public void printMenu() {
        System.out.println("Main menu:");
        System.out.println("<1> List Books");
        System.out.println("<2> Checkout Book");
        System.out.println("<3> Return Book");
        System.out.println("<0> Quit");
        System.out.print("Choose an option: ");
    }

    public void listBooks(Book[] books) {
        System.out.printf("%-5s %-5s %-20s %s\n", "ID", "Year", "Author", "Title");

        for (Book b : books) {
            if (b.getStatus() == Book.Status.AVAILABLE) {
                System.out.printf("%-5s %-5s %-20s %s\n", b.getId(), b.getYearPublished(), b.getAuthor(), b.getTitle());
            }
        }
    }

    public int readOption() {
        int option;
        try {
            option = Integer.parseInt(input.readLine());
        } catch (IOException e) {
            option = -1;
        } catch (NumberFormatException e) {
            option = -1;
        }
        return option;
    }

    public void handleOption(int option, Book[] books) {
        switch (option) {
            case 1:
                listBooks(books);
                break;
            case 2:
                if (checkoutBook(books)) {
                    System.out.println("Thank you! Enjoy the book.");
                } else {
                    System.out.println("That book is not available.");
                }
                break;
            case 3:
                if (returnBook(books)) {
                    System.out.println("Thank you for returning the book.");
                } else {
                    System.out.println("That is not a valid book to return.");
                }
                break;
            case 0:
                System.out.println("See you soon!");
                break;
            case -1:
            default:
                System.out.println("Select a valid option!");
        }
    }

    // LINEAR SEARCH!!!
    // If books[] is getting big, consider change it to a Hash
    private Book searchBook(Book[] books, int book_id) {
        for (Book b : books) {
            if (b.getId() == book_id) {
                return b;
            }
        }
        return null;
    }

    public boolean checkoutBook(Book[] books) {
        int book_id;
        try {
            book_id = Integer.parseInt(input.readLine());
        } catch (IOException e) {
            return false;
        } catch (NumberFormatException e) {
            return false;
        }

        Book book = searchBook(books, book_id);
        if (book != null && book.getStatus() == Book.Status.AVAILABLE) {
            book.setStatus(Book.Status.BORROWED);
            return true;
        }
        return false;
    }

    private boolean returnBook(Book[] books) {
        int book_id;
        try {
            book_id = Integer.parseInt(input.readLine());
        } catch (IOException e) {
            return false;
        } catch (NumberFormatException e) {
            return false;
        }

        Book book = searchBook(books, book_id);
        if (book != null && book.getStatus() == Book.Status.BORROWED) {
            book.setStatus(Book.Status.AVAILABLE);
            return true;
        }
        return false;
    }
}
