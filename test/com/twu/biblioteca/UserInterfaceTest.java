package com.twu.biblioteca;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class UserInterfaceTest {

    private ByteArrayOutputStream outContent;

    @Before
    public void setUp() throws Exception {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
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
        Book[] books = {new Book("Kent Beck", "Test Driven Development: By Example"),
                        new Book("Martin Fowler", "Refactoring: Improving the Design of Existing Code")};

        UserInterface.listBooks(books);
        String printed = "Test Driven Development: By Example - by Kent Beck\n" +
                         "Refactoring: Improving the Design of Existing Code - by Martin Fowler\n";

        assertEquals(printed, outContent.toString());
    }
}
