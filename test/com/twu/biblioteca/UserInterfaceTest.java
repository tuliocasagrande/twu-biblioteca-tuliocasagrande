package com.twu.biblioteca;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class UserInterfaceTest {

    private ByteArrayOutputStream outContent;
    private Book[] books;

    @Before
    public void setUp() throws Exception {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        books = new Book[]{new Book("Kent Beck", "Test Driven Development: By Example", 2002),
                           new Book("Martin Fowler", "Refactoring: Improving the Design of Existing Code", 1999)};
    }

    @After
    public void tearDown() throws Exception {
        System.setOut(null);
    }

    @Test
    public void welcomeMessageIsPrinted() throws Exception {
        UserInterface.welcome();
        assertEquals("Welcome!!! :D\n", outContent.toString());
    }

    @Test
    public void booksAreListed() throws Exception {
        UserInterface.listBooks(books);
        String printed = "Test Driven Development: By Example - by Kent Beck\n" +
                         "Refactoring: Improving the Design of Existing Code - by Martin Fowler\n";
        assertEquals(printed, outContent.toString());
    }

    @Test
    public void booksAreListedWithDetails() throws Exception {
        UserInterface.listBooksWithDetails(books);
        String printed = String.format("%-5s %-5s %-20s %s\n",  "#", "Year", "Author", "Title") +
                String.format("%-5s %-5s %-20s %s\n",  "1", "2002", "Kent Beck", "Test Driven Development: By Example") +
                String.format("%-5s %-5s %-20s %s\n",  "2", "1999", "Martin Fowler", "Refactoring: Improving the Design of Existing Code");

        assertEquals(printed, outContent.toString());
    }
}
