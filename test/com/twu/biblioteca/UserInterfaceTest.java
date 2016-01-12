package com.twu.biblioteca;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class UserInterfaceTest {

    private ByteArrayOutputStream outContent;
    private Library library;
    private UserInterface userInterface;
    private User customer;
    private User librarian;

    @Before
    public void setUp() throws Exception {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Book[] books = {new Book(1, "Kent Beck", "Test Driven Development: By Example", 2002),
                new Book(2, "Martin Fowler", "Refactoring: Improving the Design of Existing Code", 1999)};

        Movie[] movies = {new Movie(1, "The Imitation Game", "Morten Tyldum", 2014, 8),
                new Movie(2, "The Wolf of Wall Street", "Martin Scorsese", 2013, 8)};

        customer = new User("123-1234", "weak_password", User.Type.CUSTOMER);
        librarian = new User("121-1212", "1234", User.Type.LIBRARIAN);
        User[] users = {customer, librarian};

        library = new Library(books, movies, users);
        userInterface = new UserInterface(library);
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
        userInterface.listBooks();
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
        assertEquals(1, new UserInterface(library).readInteger());
    }

    @Test
    public void invalidReadShouldReturnNegativeOne() throws Exception {
        System.setIn(new ByteArrayInputStream("asd".getBytes()));
        assertEquals(-1, new UserInterface(library).readInteger());
    }

    @Test
    public void invalidOptionShouldNotifyUser() throws Exception {
        userInterface.handleMenuOption(-1);
        assertEquals("\nSelect a valid option!\n", outContent.toString());
    }

    @Test
    public void optionZeroShouldSayGoodbye() throws Exception {
        userInterface.handleMenuOption(0);
        assertEquals("\nSee you soon!\n", outContent.toString());
    }

    @Test
    public void optionOneShouldListBooks() throws Exception {
        userInterface.handleMenuOption(1);
        assertTrue(outContent.toString().contains("These are the available books"));
    }

    @Test
    public void optionThreeShouldCheckoutBook() throws Exception {
        System.setIn(new ByteArrayInputStream("1".getBytes()));
        userInterface = new UserInterface(library);
        userInterface.userLogin(customer);
        userInterface.handleMenuOption(3);
        assertFalse(library.getBooks()[0].isAvailable());
    }

    @Test
    public void optionFiveShouldReturnBook() throws Exception {
        library.checkoutBook(1, customer);
        System.setIn(new ByteArrayInputStream("1".getBytes()));
        userInterface = new UserInterface(library);
        userInterface.userLogin(customer);
        userInterface.handleMenuOption(5);
        assertTrue(library.getBooks()[0].isAvailable());
    }

    @Test
    public void borrowedBookShouldNotBeListed() throws Exception {
        library.checkoutBook(1, customer);
        userInterface.listBooks();
        String printed = String.format("%-5s %-5s %-20s %s\n", "ID", "Year", "Author", "Title") +
                String.format("%-5s %-5s %-20s %s\n", "2", "1999", "Martin Fowler", "Refactoring: Improving the Design of Existing Code");

        assertEquals(printed, outContent.toString());
    }

    @Test
    public void aSuccessfulCheckedOutBookShouldNotifyUser() throws Exception {
        userInterface.checkoutBook(1);
        assertEquals("Thank you! Enjoy the book.\n", outContent.toString());
    }

    @Test
    public void anUnsuccessfulCheckOutBookShouldNotifyUser() throws Exception {
        userInterface.checkoutBook(100);
        assertEquals("That book is not available.\n", outContent.toString());
    }

    @Test
    public void aSuccessfulReturnedBookShouldNotifyUser() throws Exception {
        library.checkoutBook(2, customer);
        userInterface.returnBook(2);
        assertEquals("Thank you for returning the book.\n", outContent.toString());
    }

    @Test
    public void anUnsuccessfulReturnedBookShouldNotifyUser() throws Exception {
        userInterface.returnBook(200);
        assertEquals("That is not a valid book to return.\n", outContent.toString());
    }

    @Test
    public void moviesAreListed() throws Exception {
        userInterface.listMovies();
        String printed = String.format("%-5s %-5s %-7s %-20s %s\n", "ID", "Year", "Rating", "Director", "Title") +
                String.format("%-5s %-5s %-7s %-20s %s\n", "1", "2014", "8", "Morten Tyldum", "The Imitation Game") +
                String.format("%-5s %-5s %-7s %-20s %s\n", "2", "2013", "8", "Martin Scorsese", "The Wolf of Wall Street");

        assertEquals(printed, outContent.toString());
    }

    @Test
    public void optionTwoShouldListMovies() throws Exception {
        userInterface.handleMenuOption(2);
        assertTrue(outContent.toString().contains("These are the available movies"));
    }

    @Test
    public void optionFourShouldCheckoutMovie() throws Exception {
        System.setIn(new ByteArrayInputStream("1".getBytes()));
        userInterface = new UserInterface(library);
        userInterface.userLogin(customer);
        userInterface.handleMenuOption(4);
        assertFalse(library.getMovies()[0].isAvailable());
    }

    @Test
    public void optionSixShouldReturnMovie() throws Exception {
        library.checkoutMovie(1, customer);
        System.setIn(new ByteArrayInputStream("1".getBytes()));
        userInterface = new UserInterface(library);
        userInterface.userLogin(customer);
        userInterface.handleMenuOption(6);
        assertTrue(library.getMovies()[0].isAvailable());
    }

    @Test
    public void borrowedMovieShouldNotBeListed() throws Exception {
        library.checkoutMovie(1, customer);
        userInterface.listMovies();
        String printed = String.format("%-5s %-5s %-7s %-20s %s\n", "ID", "Year", "Rating", "Director", "Title") +
                String.format("%-5s %-5s %-7s %-20s %s\n", "2", "2013", "8", "Martin Scorsese", "The Wolf of Wall Street");

        assertEquals(printed, outContent.toString());
    }

    @Test
    public void aSuccessfulCheckedOutMovieShouldNotifyUser() throws Exception {
        userInterface.checkoutMovie(1);
        assertEquals("Thank you! Enjoy the movie.\n", outContent.toString());
    }

    @Test
    public void anUnsuccessfulCheckedOutMovieShouldNotifyUser() throws Exception {
        userInterface.checkoutMovie(100);
        assertEquals("That movie is not available.\n", outContent.toString());
    }

    @Test
    public void aSuccessfulReturnedMovieShouldNotifyUser() throws Exception {
        library.checkoutMovie(2, customer);
        userInterface.returnMovie(2);
        assertEquals("Thank you for returning the movie.\n", outContent.toString());
    }

    @Test
    public void anUnsuccessfulReturnedMovieShouldNotifyUser() throws Exception {
        userInterface.returnMovie(200);
        assertEquals("That is not a valid movie to return.\n", outContent.toString());
    }

    @Test
    public void itShouldReadUserCredentials() throws Exception {
        System.setIn(new ByteArrayInputStream("123-1234\nweak_password".getBytes()));
        User user = new UserInterface(library).readUser();
        assertNotNull(user);
    }

    @Test
    public void librarianShouldSeeBorrowings() throws Exception {
        userInterface.userLogin(librarian);
        userInterface.printMenu();
        assertTrue(outContent.toString().contains("See borrowings"));
    }

    @Test
    public void customerShouldNotSeeBorrowings() throws Exception {
        userInterface.userLogin(customer);
        userInterface.printMenu();
        assertFalse(outContent.toString().contains("See borrowings"));
    }
}
