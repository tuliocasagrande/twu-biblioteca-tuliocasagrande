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
    private Library library;
    private UserInterface userInterface;

    @Before
    public void setUp() throws Exception {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Book[] books = {new Book(1, "Kent Beck", "Test Driven Development: By Example", 2002),
                new Book(2, "Martin Fowler", "Refactoring: Improving the Design of Existing Code", 1999)};

        Movie[] movies = {new Movie(1, "The Imitation Game", "Morten Tyldum", 2014, 8),
                new Movie(2, "The Wolf of Wall Street", "Martin Scorsese", 2013, 8)};

        library = new Library(books, movies);
        userInterface = new UserInterface();
    }

    @After
    public void tearDown() throws Exception {
        System.setOut(System.out);
        System.setIn(System.in);
    }

    @Test
    public void welcomeMessageIsPrinted() throws Exception {
        userInterface.printWelcome();
        assertTrue(outContent.toString().contains("Welcome"));
    }

    @Test
    public void booksAreListed() throws Exception {
        userInterface.listBooks(library);
        String printed = String.format("%-5s %-5s %-20s %s\n", "ID", "Year", "Author", "Title") +
                String.format("%-5s %-5s %-20s %s\n", "1", "2002", "Kent Beck", "Test Driven Development: By Example") +
                String.format("%-5s %-5s %-20s %s\n", "2", "1999", "Martin Fowler", "Refactoring: Improving the Design of Existing Code");

        assertEquals(printed, outContent.toString());
    }

    @Test
    public void menuIsPrinted() throws Exception {
        userInterface.printMenu();
        assertTrue(outContent.toString().contains("Main menu"));
    }

    @Test
    public void validReadShouldReturnAnInteger() throws Exception {
        System.setIn(new ByteArrayInputStream("1".getBytes()));
        assertEquals(1, new UserInterface().readInteger());
    }

    @Test
    public void invalidReadShouldReturnNegativeOne() throws Exception {
        System.setIn(new ByteArrayInputStream("asd".getBytes()));
        assertEquals(-1, new UserInterface().readInteger());
    }

    @Test
    public void invalidOptionShouldNotifyUser() throws Exception {
        userInterface.handleMenuOption(-1, library);
        assertEquals("\nSelect a valid option!\n", outContent.toString());
    }

    @Test
    public void optionZeroShouldSayGoodbye() throws Exception {
        userInterface.handleMenuOption(0, library);
        assertEquals("\nSee you soon!\n", outContent.toString());
    }

    @Test
    public void optionOneShouldListBooks() throws Exception {
        userInterface.handleMenuOption(1, library);
        assertTrue(outContent.toString().contains("These are the available books"));
    }

    @Test
    public void optionTwoShouldCheckoutBook() throws Exception {
        System.setIn(new ByteArrayInputStream("1".getBytes()));
        new UserInterface().handleMenuOption(2, library);
        assertTrue(library.getBooks()[0].getStatus() == Book.Status.BORROWED);
    }

    @Test
    public void optionThreeShouldReturnBook() throws Exception {
        library.checkoutBook(1);
        System.setIn(new ByteArrayInputStream("1".getBytes()));
        new UserInterface().handleMenuOption(3, library);
        assertTrue(library.getBooks()[0].getStatus() == Book.Status.AVAILABLE);
    }

    @Test
    public void borrowedBookShouldNotBeListed() throws Exception {
        library.checkoutBook(1);
        userInterface.listBooks(library);
        String printed = String.format("%-5s %-5s %-20s %s\n", "ID", "Year", "Author", "Title") +
                String.format("%-5s %-5s %-20s %s\n", "2", "1999", "Martin Fowler", "Refactoring: Improving the Design of Existing Code");

        assertEquals(printed, outContent.toString());
    }

    @Test
    public void aSuccessfulCheckoutShouldNotifyUser() throws Exception {
        userInterface.checkoutBook(library, 1);
        assertEquals("Thank you! Enjoy the book.\n", outContent.toString());
    }

    @Test
    public void anUnsuccessfulCheckoutShouldNotifyUser() throws Exception {
        userInterface.checkoutBook(library, 100);
        assertEquals("That book is not available.\n", outContent.toString());
    }

    @Test
    public void aSuccessfulReturnShouldNotifyUser() throws Exception {
        library.checkoutBook(2);
        userInterface.returnBook(library, 2);
        assertEquals("Thank you for returning the book.\n", outContent.toString());
    }

    @Test
    public void anUnsuccessfulReturnShouldNotifyUser() throws Exception {
        userInterface.returnBook(library, 200);
        assertEquals("That is not a valid book to return.\n", outContent.toString());
    }

    @Test
    public void moviesAreListed() throws Exception {
        userInterface.listMovies(library);
        String printed = String.format("%-5s %-5s %-20s %s\n", "ID", "Year", "Rating", "Director", "Title") +
                String.format("%-5s %-5s %-20s %s\n", "1", "2014", "8", "Morten Tyldum", "The Imitation Game") +
                String.format("%-5s %-5s %-20s %s\n", "2", "2013", "8", "Martin Scorsese", "The Wolf of Wall Street");

        assertEquals(printed, outContent.toString());
    }

    @Test
    public void optionFourShouldListMovies() throws Exception {
        userInterface.handleMenuOption(4, library);
        assertTrue(outContent.toString().contains("These are the available movies"));
    }
}
