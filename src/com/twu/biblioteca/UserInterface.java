package com.twu.biblioteca;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UserInterface {

    private BufferedReader input;

    public UserInterface() {
        input = new BufferedReader(new InputStreamReader(System.in));
    }

    public void welcome() {
        System.out.println("Welcome!!! :D");
    }

    public void menu() {
        System.out.println("Main menu:");
        System.out.println("<1> List Books");
        System.out.println("<0> Quit");
        System.out.print("Choose an option: ");;
    }

    public void listBooks(Book[] books) {
        for (Book b: books) {
            System.out.println(b.getTitle() + " - by " + b.getAuthor());
        }
    }

    public void listBooksWithDetails(Book[] books) {
        int i = 1;
        System.out.printf("%-5s %-5s %-20s %s\n", "#", "Year", "Author", "Title");

        for (Book b: books) {
            System.out.printf("%-5s %-5s %-20s %s\n", i, b.getYearPublished(), b.getAuthor(), b.getTitle());
            i++;
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
                listBooksWithDetails(books);
                break;
            case 0:
                break;
            case -1:
            default:
                System.out.println("Select a valid option!");
        }
    }
}
