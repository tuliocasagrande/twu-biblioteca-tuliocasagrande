package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BookTest {

    private Book book;

    @Before
    public void setUp() throws Exception {
        book = new Book("Kent Beck", "Test Driven Development: By Example");
    }

    @Test
    public void bookHasTitle() throws Exception {
        assertEquals("Test Driven Development: By Example", book.getTitle());
    }

    @Test
    public void bookHasAuthor() throws Exception {
        assertEquals("Kent Beck", book.getAuthor());
    }
}
