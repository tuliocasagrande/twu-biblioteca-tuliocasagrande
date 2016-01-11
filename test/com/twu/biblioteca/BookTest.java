package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BookTest {

    private Book book;

    @Before
    public void setUp() throws Exception {
        book = new Book(1, "Kent Beck", "Test Driven Development: By Example", 2002);
    }

    @Test
    public void bookHasTitle() throws Exception {
        assertEquals("Test Driven Development: By Example", book.getTitle());
    }

    @Test
    public void bookHasAuthor() throws Exception {
        assertEquals("Kent Beck", book.getAuthor());
    }

    @Test
    public void bookHasYear() throws Exception {
        assertEquals(2002, book.getYear());
    }

    @Test
    public void bookHasId() throws Exception {
        assertEquals(1, book.getId());
    }

    @Test
    public void bookIsInitializedWithAvailableStatus() throws Exception {
        assertEquals(Book.Status.AVAILABLE, book.getStatus());
    }

    @Test
    public void bookCanBeBorrowed() throws Exception {
        book.checkOut();
        assertEquals(Book.Status.BORROWED, book.getStatus());
    }

    @Test
    public void bookCanBeReturned() throws Exception {
        book.checkOut();
        book.checkIn();
        assertEquals(Book.Status.AVAILABLE, book.getStatus());
    }
}
