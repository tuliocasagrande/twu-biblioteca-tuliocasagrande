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

        ByteArrayInputStream option = new ByteArrayInputStream("1".getBytes());
        System.setIn(option);
        
        books = new Book[]{new Book(1, "Kent Beck", "Test Driven Development: By Example", 2002),
                           new Book(2, "Martin Fowler", "Refactoring: Improving the Design of Existing Code", 1999)};

        userInterface = new UserInterface();
    }

    @After
    public void tearDown() throws Exception {
        System.setOut(null);
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
        assertEquals(1, userInterface.readOption());
    }

    @Test
    public void optionOneShouldListBooks() throws Exception {
        userInterface.handleOption(1, books);
        String printed = String.format("%-5s %-5s %-20s %s\n",  "ID", "Year", "Author", "Title") +
                String.format("%-5s %-5s %-20s %s\n",  "1", "2002", "Kent Beck", "Test Driven Development: By Example") +
                String.format("%-5s %-5s %-20s %s\n",  "2", "1999", "Martin Fowler", "Refactoring: Improving the Design of Existing Code");

        assertEquals(printed, outContent.toString());
    }
}
