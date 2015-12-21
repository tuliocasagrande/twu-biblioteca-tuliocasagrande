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

    public static void listBooksWithDetails(Book[] books) {
        int i = 1;
        System.out.printf("%-5s %-5s %-20s %s\n", "#", "Year", "Author", "Title");

        for (Book b: books) {
            System.out.printf("%-5s %-5s %-20s %s\n", i, b.getYearPublished(), b.getAuthor(), b.getTitle());
            i++;
        }
    }
}
