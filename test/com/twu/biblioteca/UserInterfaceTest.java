package com.twu.biblioteca;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UserInterfaceTest {

    private ByteArrayOutputStream outContent;
    private Book[] books;
    private UserInterface userInterface;

    @Before
    public void setUp() throws Exception {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        
        books = new Book[]{new Book(1, "Kent Beck", "Test Driven Development: By Example", 2002),
                           new Book(2, "Martin Fowler", "Refactoring: Improving the Design of Existing Code", 1999)};

        userInterface = new UserInterface();
    }

    @After
    public void tearDown() throws Exception {
        System.setOut(System.out);
        System.setIn(System.in);
    }

    @Test
    public void welcomeMessageIsPrinted() throws Exception {
        userInterface.welcome();
        assertEquals("Welcome!!! :D\n", outContent.toString());
    }

    @Test
    public void booksAreListedWithDetails() throws Exception {
        userInterface.listBooks(books);
        String printed = String.format("%-5s %-5s %-20s %s\n",  "ID", "Year", "Author", "Title") +
                String.format("%-5s %-5s %-20s %s\n",  "1", "2002", "Kent Beck", "Test Driven Development: By Example") +
                String.format("%-5s %-5s %-20s %s\n",  "2", "1999", "Martin Fowler", "Refactoring: Improving the Design of Existing Code");

        assertEquals(printed, outContent.toString());
    }

    @Test
    public void menuIsPrinted() throws Exception {
        userInterface.menu();
        assertTrue(outContent.toString().length() > 0);
        assertTrue(outContent.toString().contains("Choose an option"));
    }

    @Test
    public void optionIsRead() throws Exception {
        System.setIn(new ByteArrayInputStream("1".getBytes()));
        assertEquals(1, new UserInterface().readOption());
    }

    @Test
    public void invalidOptionShouldReturnNegativeOne() throws Exception {
        System.setIn(new ByteArrayInputStream("asd".getBytes()));
        assertEquals(-1, new UserInterface().readOption());
    }

    @Test
    public void optionOneShouldListBooks() throws Exception {
        userInterface.handleOption(1, books);
        String printed = String.format("%-5s %-5s %-20s %s\n",  "ID", "Year", "Author", "Title") +
                String.format("%-5s %-5s %-20s %s\n",  "1", "2002", "Kent Beck", "Test Driven Development: By Example") +
                String.format("%-5s %-5s %-20s %s\n",  "2", "1999", "Martin Fowler", "Refactoring: Improving the Design of Existing Code");

        assertEquals(printed, outContent.toString());
    }

    @Test
    public void invalidOptionShouldNotifyUser() throws Exception {
        userInterface.handleOption(-1, books);
        assertEquals("Select a valid option!\n", outContent.toString());
    }

    @Test
    public void optionZeroShouldGoodbyeUser() throws Exception {
        userInterface.handleOption(0, books);
        assertEquals("See you soon!\n", outContent.toString());
    }

    @Test
    public void optionTwoShouldCheckoutBook() throws Exception {
        System.setIn(new ByteArrayInputStream("1".getBytes()));
        new UserInterface().handleOption(2, books);
        assertTrue(books[0].getStatus() == Book.Status.BORROWED);
    }

    @Test
    public void optionThreeShouldReturnBook() throws Exception {
        books[1].setStatus(Book.Status.BORROWED);
        System.setIn(new ByteArrayInputStream("2".getBytes()));
        new UserInterface().handleOption(3, books);
        assertTrue(books[1].getStatus() == Book.Status.AVAILABLE);
    }

    @Test
    public void borrowedBookShouldNotBeListed() throws Exception {
        System.setIn(new ByteArrayInputStream("1".getBytes()));
        new UserInterface().checkoutBook(books);

        userInterface.listBooks(books);
        String printed = String.format("%-5s %-5s %-20s %s\n",  "ID", "Year", "Author", "Title") +
                String.format("%-5s %-5s %-20s %s\n",  "2", "1999", "Martin Fowler", "Refactoring: Improving the Design of Existing Code");

        assertEquals(printed, outContent.toString());
    }

    @Test
    public void aSuccessfulCheckoutShouldNotifyUser() throws Exception {
        System.setIn(new ByteArrayInputStream("1".getBytes()));
        new UserInterface().handleOption(2, books);
        assertEquals("Thank you! Enjoy the book.\n", outContent.toString());
    }

    @Test
    public void anUnsuccessfulCheckoutShouldNotifyUser() throws Exception {
        System.setIn(new ByteArrayInputStream("0".getBytes()));
        new UserInterface().handleOption(2, books);
        assertEquals("That book is not available.\n", outContent.toString());
    }
}
