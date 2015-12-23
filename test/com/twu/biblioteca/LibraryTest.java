package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LibraryTest {

    Library library;

    @Before
    public void setUp() throws Exception {
        Book[] books = new Book[]{new Book(1, "Kent Beck", "Test Driven Development: By Example", 2002),
                new Book(2, "Martin Fowler", "Refactoring: Improving the Design of Existing Code", 1999)};
        library = new Library(books);
        library.checkoutBook(2);
    }

    @Test
    public void anAvailableBookCanBeCheckedOut() throws Exception {
        assertTrue(library.getBooks()[0].getStatus() == Book.Status.AVAILABLE);
        assertTrue(library.checkoutBook(1));
        assertTrue(library.getBooks()[0].getStatus() == Book.Status.BORROWED);
    }

    @Test
    public void aBorrowedBookCanBeReturned() throws Exception {
        assertTrue(library.getBooks()[1].getStatus() == Book.Status.BORROWED);
        assertTrue(library.returnBook(2));
        assertTrue(library.getBooks()[1].getStatus() == Book.Status.AVAILABLE);
    }

    @Test
    public void anAvailableBookCannotBeReturned() throws Exception {
        assertTrue(library.getBooks()[0].getStatus() == Book.Status.AVAILABLE);
        assertFalse(library.returnBook(1));
        assertTrue(library.getBooks()[0].getStatus() == Book.Status.AVAILABLE);
    }

    @Test
    public void aBorrowedBookCannotBeCheckedOut() throws Exception {
        assertTrue(library.getBooks()[1].getStatus() == Book.Status.BORROWED);
        assertFalse(library.checkoutBook(2));
        assertTrue(library.getBooks()[1].getStatus() == Book.Status.BORROWED);
    }
}
